package org.test.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookieService {

	public static final String CART_COOKIE_NAME = "cart";
	public static final int CART_COOKIE_AGE = 6*60*60;
	public static final String REDIRECT_COOKIE_NAME = "redirect";
	public static final int REDIRECT_COOKIE_AGE = 60;
	public static final String SHIPPING_COOKIE_NAME = "shipping";
	public static final int SHIPPING_COOKIE_AGE = 6*60*60;
	public static final String EMAIL_COOKIE_NAME = "email";
	public static final int EMAIL_COOKIE_AGE = 6*60*60;
	
	public boolean save(String cookieName, int cookieAge, String cookieValue,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(cookieAge);
		response.addCookie(cookie);
		return true;
	}

	public boolean delete(String cookieName,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return true;
	}
}
