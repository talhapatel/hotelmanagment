package com.booking.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.booking.constant.Constants;
import com.booking.globalExeption.InvalidTokenException;
import com.booking.globalExeption.MissingTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@SuppressWarnings("deprecation")
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	/**
	 * This is not a good practice to use sysout. Always integrate any logger
	 * with your application. We will discuss about integrating logger with
	 * spring boot application in some later article
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		checkUserAndPassword(request, response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
	}

	private void checkUserAndPassword(final ServletRequest req, final ServletResponse res)
			throws IOException, ServletException, Exception {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("Authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		} else {
			if (authHeader == null) {
				throw new MissingTokenException("token missing");
			} else if (!authHeader.startsWith(Constants.PREFIX_KEY)) {
				throw new InvalidTokenException("Invalid Token");
			}

			final String token = authHeader.substring(Constants.PREFIX_KEY.length() + 1);

			try {
				final Claims claims = Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				request.setAttribute("user", claims.get("user"));
				String userId = ((Map<String, Object>) claims.get("user")).get("id").toString();
				
				return;
			} catch (final Exception e) {
				e.printStackTrace();
				throw new InvalidTokenException("Invalid Token");
			}
		}
	}

	// https://stackoverflow.com/a/25440389
	// https://programmingmitra.blogspot.in/2017/02/automatic-jpa-auditing-persisting-audit-logs-automatically-using-entityListeners.html


}
