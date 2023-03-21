package servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		Paciente p = null;
		
		try {
			if(sesion.getAttribute("email") != null) {
				cerrarSesion(req, resp);
			}
			else if (PacienteDAO.getInstance().autenticar(email, password)) {
				iniciarSesion(req, resp);				
			} else {
				resp.sendRedirect("login.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static final String getBasicAuthenticationHeader(String username, String password) {
	    String valueToEncode = username + ":" + password;
	    return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
	}

	
	private void iniciarSesion(HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException, URISyntaxException, InterruptedException {

//		Ejemplo Http API Java 9
		
//		HttpClient client = HttpClient.newHttpClient();
		
//		HttpClient client = HttpClient.newBuilder()
//		  .authenticator(new Authenticator() {
//		      @Override
//		      protected PasswordAuthentication getPasswordAuthentication() {
//		          return new PasswordAuthentication("victoria@hotmail.com", "1234".toCharArray());
//		      }
//		  })
//		  .build();
//		
//		HttpRequest request = HttpRequest.newBuilder()
//		  .GET()
//		  .uri(new URI("http://localhost:8080/panel.html/get"))
//		  .header("Authorization", getBasicAuthenticationHeader("victoria@hotmail.com", "1234"))
//		  .build();
//		
//		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//		
//		System.out.println("status " + response.statusCode());

//		logger.info("Status {}", response.statusCode());
		
// ----------------------------------------------------------------------------------------------------------  //
		
		System.out.println("apertura de sesion");
		HttpSession sesion = req.getSession();
		String email = req.getParameter("email");
		sesion.setAttribute("email", email);
		resp.sendRedirect("panel.html");
	}
	
	private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("cierre de sesion");
		HttpSession sesion = req.getSession();
		sesion.removeAttribute("email");
		sesion.invalidate();
		resp.sendRedirect("login.html");
	}
}