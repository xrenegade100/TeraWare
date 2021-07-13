package net.teraware.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.teraware.model.bean.UtenteBean;

@WebFilter(urlPatterns = { "/pages/admin/*", "/admin/*" })
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest filteredRequest = (HttpServletRequest) request;
		HttpServletResponse filteredResponse = (HttpServletResponse) response;
		UtenteBean user = (UtenteBean) filteredRequest.getSession().getAttribute("user");
		if (user == null) {
			//filteredRequest.getSession().setAttribute("redirect", "/pages/");
			filteredResponse.sendRedirect(filteredRequest.getContextPath() + "/pages/login.jsp");
			return;
		} else if (user.getRole() != 1) {
			filteredResponse.setStatus(403);
			filteredResponse.getWriter().println("Non hai i permessi");
			return;
		}
		chain.doFilter(request, response);
	}
}
