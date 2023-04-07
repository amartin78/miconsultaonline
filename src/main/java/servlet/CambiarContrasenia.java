package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			
			int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
			// System.out.println("El id del paciente es: " + id);
			Paciente p = PacienteDAO.getInstance().obtenerPacientePorID(id);
			p.setPassword(req.getParameter("nuevaContrasenia"));
			p.modificarContrasenia();
			// System.out.println("La nueva contraseña " + p.getPassword() + " ha sido guardada con éxito.");
			resp.sendRedirect("panel.html");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
