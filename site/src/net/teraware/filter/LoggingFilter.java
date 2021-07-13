package net.teraware.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class LoggingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		final String ip = request.getRemoteAddr();
		final String protocol = request.getProtocol();
		String method = ((HttpServletRequest) request).getMethod();
		String path = ((HttpServletRequest) request).getRequestURL().toString();
		Logger
			.getLogger("teraware")
			.log(Level.INFO, protocol + " " + method + " " + path + " FROM " + ip + "");
		chain.doFilter(request, response);
	}
}
