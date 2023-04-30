package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnalisisDAO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

import singleton.ConexionBBDD;
import modelo.Paciente;

@WebServlet("/CAnalisis")
@MultipartConfig
public class CAnalisis extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Constructor vacío
	public CAnalisis() {
		super();
	}

	protected void inicio(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String datos = "";
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		
		try {
			
			ConexionBBDD.insertarDatosAnalisis(id);
			ConexionBBDD.insertarDatosMarcador(id);
			datos = AnalisisDAO.getInstance().listarAnalisisPorPacienteSesionJSON(id);
			resp.setContentType("text/html;charset=UTF8");
			resp.getWriter().print(datos);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.inicio(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.inicio(req, resp);
	}
}

