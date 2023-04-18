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
			
			// Si la sesión esta abierta entonces se cierra.
			if(sesion.getAttribute("paciente") != null) {
				cerrarSesion(req, resp);
			}
			// En este caso la sesión estará cerrada y se inicia.
			else if (PacienteDAO.getInstance().autenticarPaciente(email, password)) {
				iniciarSesion(req, resp);		
			// En este caso el usuario ha intentado iniciar la sesión pero sus 
			// credenciales no son válidas y se redirecciona a la página de loguin.
			} else {
				resp.sendRedirect("login.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void iniciarSesion(HttpServletRequest req, HttpServletResponse resp) 
					throws ServletException, IOException, URISyntaxException, InterruptedException {
		
		System.out.println("Se inicia la sesión");
		HttpSession sesion = req.getSession();
		// Tiempo máximo que estará activa la sesión
		sesion.setMaxInactiveInterval(60 * 10);
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
		// Se establece un tiempo máximo para la cookie igual al de la sesión menos un minuto
		// que es la frecuencia con que la parte cliente comprueba el estado de la sesión 
		// para en el caso de estar finalizada redireccionar al cliente a la página de loguin.
		cookie.setMaxAge(60 * 9);
		resp.addCookie(cookie);
		// Una vez autenticado el usuario y creadas la sesión y la cookie redireccionamos
		// al cliente hacia el panel. 
		resp.sendRedirect("panel.html");
	}
	
	private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Se cierra la sesión");
		HttpSession sesion = req.getSession(false);
		sesion.invalidate();
		resp.sendRedirect("login.html");
	}
}
