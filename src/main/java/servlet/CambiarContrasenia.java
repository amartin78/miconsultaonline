package servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import modelo.Paciente;
import modelo.PacienteDAO;

/**
 * Esta clase permite modificar la contraseña del paciente
 * en la base de datos.
 * 
 * @author Antonio Martín
 */
@WebServlet("/CambiarContrasenia")
@MultipartConfig
public class CambiarContrasenia extends HttpServlet {

	public CambiarContrasenia() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			HttpSession sesion = req.getSession();
			int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
			String email = req.getParameter("email");
			String actualContrasenia = req.getParameter("actualContrasenia");
			String nuevaContrasenia = req.getParameter("nuevaContrasenia");
			String repetirContrasenia = req.getParameter("repetirContrasenia");
			
			String mensaje = null;
			
			if (PacienteDAO.getInstance().autenticarPaciente(email, actualContrasenia)) {
				
				if(nuevaContrasenia.equals(repetirContrasenia)) {
					Cookie cookieValidez = new Cookie("passwordValido", "valido");
					resp.addCookie(cookieValidez);
					Paciente p = PacienteDAO.getInstance().obtenerPacientePorID(id);
					p.setPassword(req.getParameter("nuevaContrasenia"));
					p.modificarContrasenia();
					System.out.println("La nueva contraseña ha sido guardada con éxito.");
				}
			} else {
				Cookie cookieValidez = new Cookie("passwordValido", "noValido");
				Cookie cookieOrigen = new Cookie("origen", "cuenta");
				resp.addCookie(cookieValidez);
				resp.addCookie(cookieOrigen);
			}
			Cookie cookieOrigen = new Cookie("origen", "cuenta");
			resp.addCookie(cookieOrigen);
			resp.sendRedirect("panel.html");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
