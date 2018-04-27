package com.example.demo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter("/CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {
	protected String encoding = "UTF-8";
	protected FilterConfig filterConfig = null;
    /**
     * Default constructor. 
     */
    public CharacterEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    	this.filterConfig = fConfig;
    	//this.encoding = filterConfig.getInitParameter("encoding");
    }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset="+encoding);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}


}
