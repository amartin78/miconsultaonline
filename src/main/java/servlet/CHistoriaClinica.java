package servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

import modelo.Anomalia;
import modelo.Paciente;
import dao.AlergiaDAO;
import dao.AnomaliaDAO;
import dao.PacienteDAO;

@WebServlet("/CHistoriaClinica")
@MultipartConfig

public class CHistoriaClinica extends HttpServlet {
	
	public CHistoriaClinica() {
		super();
	}

	protected void inicio(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch(req.getParameter("opcion")) {
		
			case "1": 
				buscarAnomalias(req, resp); 
				return;
			case "2": 
				buscarAlergias(req, resp); 
				return;
			case "3": 
				// buscarVacunas(req, resp); 
				return;
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
		// abrirSesion(req, resp);
	}
	
	private void buscarAnomalias(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Paciente p = null;
		String a = "";
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		
		try {
			
			a = AnomaliaDAO.getInstance().listarPorPacienteSesionJSON(id);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		resp.getWriter().print(a);
	}

	private void buscarAlergias(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Paciente p = null;
		String a = "";
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		
		try {
			
			a = AlergiaDAO.getInstance().listarPorPacienteSesionJSON(id);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Alergia: " + a);	
		resp.getWriter().print(a);
		
	}
	
	private void buscarVacunas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Paciente p = null;
		String a = "";
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		
		try {
			
			a = AnomaliaDAO.getInstance().listarPorPacienteSesionJSON(id);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		resp.getWriter().print(a);
	}

}
