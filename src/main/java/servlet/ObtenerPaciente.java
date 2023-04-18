package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import modelo.Paciente;
import modelo.PacienteDAO;

@WebServlet("/ObtenerPaciente")
@MultipartConfig
public class ObtenerPaciente extends HttpServlet {

	public ObtenerPaciente() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String p = null;
		HttpSession sesion = req.getSession();
		String email = ((Paciente) sesion.getAttribute("paciente")).getEmail();
		
		try {
			p = PacienteDAO.getInstance().buscarPacientePorAtributoJSON("email", email);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		resp.getWriter().print(p);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
