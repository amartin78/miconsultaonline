package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

import modelo.Paciente;
import singleton.ConexionBBDD;
import dao.HistoriaClinicaDAO;

@WebServlet("/CHistoriasClinicas")
@MultipartConfig
public class CHistoriasClinicas extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Constructor vacío
	public CHistoriasClinicas() {
		super();
	}

	protected void inicio(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String datos = "";
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		int opcion = Integer.parseInt(req.getParameter("opcion")); 
		
		try {
			
			// Se recogen los datos correspondientes en formato json de acuerdo según la opción 
			// enviada desde la parte cliente.
			if(opcion == 1) {
				ConexionBBDD.insertarDatosHistoriaClinica(id);
				datos = HistoriaClinicaDAO.getInstance().listarAnomaliasPorPacienteSesionJSON(id);
			} else if(opcion == 2) {
				datos = HistoriaClinicaDAO.getInstance().listarAlergiasPorPacienteSesionJSON(id);
			} else if(opcion == 3) {
				datos = HistoriaClinicaDAO.getInstance().listarVacunasPorPacienteSesionJSON(id);
			} else {
				System.out.println("Opción no válida.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html;charset=UTF8");
		resp.getWriter().print(datos);
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

