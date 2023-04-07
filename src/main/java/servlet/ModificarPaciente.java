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

@WebServlet("/ModificarPaciente")
@MultipartConfig
public class ModificarPaciente extends HttpServlet {

	public ModificarPaciente() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
		// System.out.println("Paciente id para su modificacion: " + id);
		Paciente p;
		try {
			p = PacienteDAO.getInstance().obtenerPacientePorID(id);
			p.setNombre(req.getParameter("nombre"));
			p.setApellidos(req.getParameter("apellidos"));
			p.setFecNacimiento(Date.valueOf(req.getParameter("fecNacimiento")));
			p.setDomicilio(req.getParameter("domicilio"));
			int codPostal = req.getParameter("codPostal").isBlank() ? 0 : Integer.parseInt(req.getParameter("codPostal"));
			p.setCodPostal(codPostal);
			p.setLocalidad(req.getParameter("localidad"));
			p.setProvincia(req.getParameter("provincia"));
			System.out.println("El telefono es blank: " + req.getParameter("telefono").isBlank());
			int telefono = req.getParameter("telefono").isBlank() ? 0 : Integer.parseInt(req.getParameter("telefono"));
			p.setTelefono(telefono);
			p.setEstadoCivil(req.getParameter("estadoCivil"));
			
			p.modificar();
			resp.sendRedirect("panel.html");
			System.out.println("El paciente se ha modificado con éxito");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
