package servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.MultipartConfig;

@WebFilter("/panel.html")
@MultipartConfig
public class FiltroPanel implements Filter {
	
	private HttpServletRequest httpRequest;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		httpRequest = (HttpServletRequest) request;
		HttpSession sesion = httpRequest.getSession(false);
		Cookie listaCookies[] = httpRequest.getCookies();
		String email = "";
		for(Cookie cookie : listaCookies) {
			if(cookie.getName().equals("email")) {
				email = cookie.getValue();
			}
		}
		boolean clienteLogueado = sesion != null && sesion.getAttribute("paciente") != null && !email.trim().isEmpty();
		
		if(clienteLogueado) {
			request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		} else {
			httpRequest.getRequestDispatcher("/login.html").forward(request, response);
		} 		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
