package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.MultipartConfig;
import modelo.Paciente;
import modelo.PacienteDAO;

@WebFilter("/panel.html")
@MultipartConfig
public class FiltroAutenticacion implements Filter {
	
	private ServletContext context;
	private HttpServletRequest httpRequest;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		httpRequest = (HttpServletRequest) request;
		HttpSession sesion = httpRequest.getSession(false);
		boolean clienteLogueado = sesion != null && sesion.getAttribute("paciente") != null;
		
		if(clienteLogueado) {
			// httpRequest.getRequestDispatcher("/panel.html").forward(request, response);
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
