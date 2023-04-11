package servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;

import modelo.Paciente;
import modelo.PacienteDAO;

@WebServlet("/LoginPaciente")
@MultipartConfig
public class LoginPaciente extends HttpServlet {

	/**
	 * Constructor por defecto 
	 */
	public LoginPaciente() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = req.getSession();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
				
		try {
			if(sesion.getAttribute("paciente") != null) {
				cerrarSesion(req, resp);
			}
			else if (PacienteDAO.getInstance().autenticarPaciente(email, password)) {
				iniciarSesion(req, resp);				
			} else {
				resp.sendRedirect("login.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void iniciarSesion(HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException, URISyntaxException, InterruptedException {
		
		System.out.println("apertura de sesion");
		HttpSession sesion = req.getSession();
		String email = req.getParameter("email");
		Paciente paciente = null;
		
		try {
			
			paciente = PacienteDAO.getInstance().buscarPacientePorAtributo("email", email);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sesion.setAttribute("paciente", paciente);
		Cookie cookie = new Cookie("email", paciente.getEmail());
		cookie.setMaxAge(60 * 5);
		resp.addCookie(cookie);
		resp.sendRedirect("panel.html");
	}
	
	private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("cierre de sesion");
		HttpSession sesion = req.getSession(false);
		sesion.invalidate();
		resp.sendRedirect("login.html");
	}
}
