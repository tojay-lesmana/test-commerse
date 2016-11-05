package org.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.jpa.model.Product;
import org.test.jpa.model.helper.Cart;
import org.test.jpa.model.helper.CartCookies;
import org.test.jpa.model.helper.CartItem;
import org.test.jpa.model.helper.CartItemCookies;
import org.test.jpa.repo.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CookieService cookieService;
	
	@Autowired
	private ProductRepository productRepository;

	public boolean saveCartCookie(Long productId,
			Integer quantity, 
			HttpServletResponse response,
			String cartCookie) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		CartCookies cartCookies = null;
		if(cartCookie != null){
			cartCookies = mapper.readValue(cartCookie, CartCookies.class);
		}
		Product product = productRepository.getOne(productId);
		if(product != null){
			Set<CartItemCookies> items = new HashSet<CartItemCookies>();
			if(cartCookies == null){
				cartCookies = new CartCookies();
				CartItemCookies cartItemCookies = new CartItemCookies(productId, quantity);
				items.add(cartItemCookies);
			}else{
				if(cartCookies.getItems() != null){
					items.addAll(cartCookies.getItems());
				}
				CartItemCookies cartItemCookies = new CartItemCookies(productId, quantity);
				if(!items.contains(cartItemCookies)){
					items.add(cartItemCookies);
				}else{
					items.remove(cartItemCookies);
					items.add(cartItemCookies);
				}
			}
			cartCookies.setItems(new ArrayList<CartItemCookies>(items));
			cartCookies.setSize(items.size());
			cookieService.save(
					CookieService.CART_COOKIE_NAME, 
					CookieService.CART_COOKIE_AGE, 
					mapper.writeValueAsString(cartCookies), 
					response);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean saveCartCookie(CartCookies cartCookies, 
			HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		if(cartCookies != null){
			Set<CartItemCookies> items = new HashSet<CartItemCookies>();
			if(cartCookies.getItems() != null){
				items.addAll(cartCookies.getItems());
			}
			cartCookies.setItems(new ArrayList<CartItemCookies>(items));
			cartCookies.setSize(items.size());
			cookieService.save(
					CookieService.CART_COOKIE_NAME, 
					CookieService.CART_COOKIE_AGE, 
					mapper.writeValueAsString(cartCookies), 
					response);
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteCartCookie(
			Long productId,
			HttpServletResponse response,
			String cartCookie) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		CartCookies cartCookies = null;
		if(cartCookie != null){
			cartCookies = mapper.readValue(cartCookie, CartCookies.class);
			if(cartCookies != null){
				Set<CartItemCookies> items = new HashSet<CartItemCookies>();
				if(cartCookies.getItems() != null){
					items.addAll(cartCookies.getItems());
				}
				for(CartItemCookies cartItemCookies : items){
					if(cartItemCookies.getProductId()==productId){
						items.remove(cartItemCookies);
					}
				}
				cookieService.save(
						CookieService.CART_COOKIE_NAME, 
						CookieService.CART_COOKIE_AGE, 
						mapper.writeValueAsString(cartCookies), 
						response);
			}
			return true;
		}else{
			return false;
		}
	}

	public Cart getCartData(String cartCookie) throws JsonParseException, JsonMappingException, IOException{
		Cart cart = new Cart();
		Set<CartItem> cartItems = new HashSet<CartItem>();
		long total = 0;
		ObjectMapper mapper = new ObjectMapper();
		CartCookies cartCookies = null;
		if(cartCookie != null){
			cartCookies = mapper.readValue(cartCookie, CartCookies.class);
			if(cartCookies.getItems()!=null){
				for(CartItemCookies item : cartCookies.getItems()){
					Product product = productRepository.getOne(item.getProductId());
					CartItem cartItem = new CartItem(product, item.getQuantity());
					cartItem.setTotal(product.getPrice() * item.getQuantity());
					cartItems.add(cartItem);
					total += (cartItem.getTotal());
				}
			}
		}
		cart.setItems(cartItems);
		cart.setSize(cartItems.size());
		cart.setTotal(total);
		return cart;
	}
}
