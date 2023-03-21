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


@WebServlet("/AltaPaciente")
@MultipartConfig
public class AltaPaciente extends HttpServlet {

	/**
	 *  Constructor por defecto
	 */
	public AltaPaciente() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Date fecNacimiento = Date.valueOf(req.getParameter("fecNacimiento"));
//		String direccion = req.getParameter("direccion");
//		int telefono = req.getParameter("telefono").isEmpty() ? "" : Integer.parseInt(req.getParameter("telefono"));
//		String estadoCivil = req.getParameter("estadoCivil");
		
		Paciente p = new Paciente(nombre, apellidos, email, password, fecNacimiento);
		try {			
			p.insertar();
			resp.sendRedirect("login.html");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

