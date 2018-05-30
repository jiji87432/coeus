package com.pay.coeus.portal.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pay.coeus.common.utils.StringUtils;
import com.pay.commons.utils.Page;

public class PageHandlerInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(PageHandlerInterceptor.class);
	
	private static final String METHOD_PREFIX = "findAll";
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			String name = handlerMethod.getMethod().getName();
			logger.info("method name {} ", name);
			if(name != null && name.startsWith(METHOD_PREFIX)){
				String currentPage = request.getParameter("currentPage");
				String showCount = request.getParameter("showCount");
				Page page = new Page();
				if(StringUtils.isBlank(currentPage) || StringUtils.isBlank(showCount)){
					page.setCurrentPage(1);
					page.setShowCount(100000);
				} else {
					page.setCurrentPage(Integer.valueOf(currentPage));
					page.setShowCount(Integer.valueOf(showCount));
				}
				request.setAttribute("page", page);
			}
		}
		
		return true;
	}
}
