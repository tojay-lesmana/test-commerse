package org.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.test.jpa.model.Account;
import org.test.jpa.model.Order;
import org.test.jpa.model.OrderDetail;
import org.test.jpa.model.Payment;
import org.test.jpa.model.Product;
import org.test.jpa.model.Shipping;
import org.test.jpa.model.helper.Cart;
import org.test.jpa.model.helper.CartCookies;
import org.test.jpa.model.helper.CartItem;
import org.test.jpa.model.helper.response.DefaultResponse;
import org.test.jpa.model.helper.response.ResponseData;
import org.test.jpa.repo.AccountRepository;
import org.test.jpa.repo.OrderRepository;
import org.test.jpa.repo.PaymentRepository;
import org.test.jpa.repo.ProductRepository;
import org.test.jpa.repo.ShippingRepository;
import org.test.model.form.PaymentForm;
import org.test.model.form.ShippingForm;
import org.test.service.CartService;
import org.test.service.CookieService;


@RestController
@RequestMapping("/api")
public class ApiController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CookieService cookieService; 
	
	@Autowired
	private CartService cartService;  
	
	@Autowired
	private AccountRepository accountRepository; 
	
	@Autowired
	private OrderRepository orderRepository; 
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object saveProduct(Product product){
		return productRepository.save(product);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object listProduct(){
		return productRepository.findByStockGreaterThan(0);
	}
	
	@RequestMapping(value = "/mycart", method = RequestMethod.GET, consumes = {"application/json"})
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Object myCart(
			HttpServletResponse response,
			@CookieValue(required=false, value=CookieService.CART_COOKIE_NAME) String cartCookie,
			@CookieValue(required=false, value=CookieService.REDIRECT_COOKIE_NAME) String redirectCookie) throws JsonParseException, JsonMappingException, IOException{
		if(redirectCookie != null){
			cookieService.delete(CookieService.REDIRECT_COOKIE_NAME, response);
			return "redirect:"+redirectCookie;
		}		
		return cartService.getCartData(cartCookie);		
	}
	
	@RequestMapping(value="/add-to-cart", method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseBody
	public Object saveCart(
			@RequestBody CartCookies cartCookies,
			HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException{
		if(cartService.saveCartCookie(cartCookies, response)){
			return new DefaultResponse();
		}else{
			return new DefaultResponse(DefaultResponse.ERROR, DefaultResponse.ERROR_MESSAGE);
		}
	}
	
	@RequestMapping(value="/delete-cart", method=RequestMethod.POST)
	@ResponseBody
	public DefaultResponse deleteCart(
			@RequestParam Long productId,
			HttpServletResponse response,
			@CookieValue(value=CookieService.CART_COOKIE_NAME, required=false) String cartCookie) 
					throws JsonGenerationException, JsonMappingException, IOException{
		if(cartService.deleteCartCookie(productId, response, cartCookie)){
			return new DefaultResponse();
		}else{
			return new DefaultResponse(DefaultResponse.ERROR, DefaultResponse.ERROR_MESSAGE);
		}
	}
	
	@RequestMapping(value="/get-cart-detail", method=RequestMethod.GET)
	@ResponseBody
	public ResponseData<Cart> get(
			@CookieValue(value=CookieService.CART_COOKIE_NAME, required=false) String cartCookie) 
					throws JsonParseException, JsonMappingException, IOException{
		ResponseData<Cart> data = new ResponseData<Cart>();
		Cart cart = cartService.getCartData(cartCookie);
		data.setData(cart);
		return data;
	}
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseBody
	public Object checkout(			
			@CookieValue(value=CookieService.CART_COOKIE_NAME, required=false) String cartCookie,
			@CookieValue(value=CookieService.SHIPPING_COOKIE_NAME, required=false) String shippingCookie) 
					throws JsonGenerationException, JsonMappingException, IOException{		
		ShippingForm shippingForm = null;
		Cart cart = cartService.getCartData(cartCookie);
		if(cart.getSize()>0){
			ObjectMapper mapper = new ObjectMapper();				
			try{
				shippingForm = mapper.readValue(shippingCookie, ShippingForm.class);
			}catch(Exception exception){
				shippingForm = null;
			}
		}		
		return new DefaultResponse();
	}

	@RequestMapping(value="/shipping", method = RequestMethod.POST, consumes = {"application/json"})
	@ResponseBody
	public Object checkoutShipping(
			@ModelAttribute ShippingForm shippingForm,
			HttpServletResponse response) 
					throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		cookieService.save(
				CookieService.SHIPPING_COOKIE_NAME, 
				CookieService.SHIPPING_COOKIE_AGE, 
				mapper.writeValueAsString(shippingForm), 
				response);
		return new DefaultResponse();
	}

	@RequestMapping(value="/payment", method=RequestMethod.POST)
	@ResponseBody
	public Object checkoutPayment(			
			HttpServletResponse response,
			@ModelAttribute PaymentForm paymentForm,
			@CookieValue(value=CookieService.CART_COOKIE_NAME, required=false) String cartCookie,
			@CookieValue(value=CookieService.SHIPPING_COOKIE_NAME, required=false) String shippingCookie) 
					throws JsonGenerationException, JsonMappingException, IOException{		
		Cart cart = cartService.getCartData(cartCookie);
		if(cart.getSize() == 0){
			return "redirect:/cart";
		}
		ObjectMapper mapper = new ObjectMapper();
		ShippingForm shippingForm = mapper.readValue(shippingCookie, ShippingForm.class);
					
		Account user = accountRepository.findByEmail(paymentForm.getPaymentHolder());
		
		List<OrderDetail> purchaseDetails = new ArrayList<OrderDetail>();
		long total = 0;
		for(CartItem item : cart.getItems()){
			OrderDetail detail = new OrderDetail();
			detail.setProduct(item.getProduct());
			detail.setQuantity(item.getQuantity());
			detail.setPrice(item.getProduct().getPrice());
			long subTotal = (detail.getPrice()-detail.getDiscount()) * detail.getQuantity();
			detail.setTotal(subTotal);
			purchaseDetails.add(detail);
			total += subTotal;
		}
		
		Payment payment = new Payment();
		payment.setPaymentInfo(paymentForm.getPaymentInfo());
		payment.setPaymentHolder(paymentForm.getPaymentHolder());		
		payment.setAmount(total);
		payment.setCreatedAt(new Date());
		payment = paymentRepository.save(payment);
				
		Shipping shipping  = new Shipping();
		shipping.setAddress(shippingForm.getStreet() + " "+shippingForm.getState()+" "+shippingForm.getCity()+" "+shippingForm.getCountry());
		shipping.setCount(purchaseDetails.size());
		shipping.setCountry(shippingForm.getCountry());
		shipping.setCreatedAt(new Date());
		shipping.setProvince(shippingForm.getState());
		shipping = shippingRepository.save(shipping);
		
		Order order = new Order();
		order.setAccount(user);
		order.setCreatedAt(new Date());
		order.setDetails(purchaseDetails);
		order.setInvoice(new Date()+"/INV");
		order.setPayment(payment);
		order.setShippingTo(shipping);
		
		order = orderRepository.save(order);
		
		cookieService.delete(CookieService.CART_COOKIE_NAME, response);
		cookieService.delete(CookieService.SHIPPING_COOKIE_NAME, response);
		
		return order;
	}
	
}
