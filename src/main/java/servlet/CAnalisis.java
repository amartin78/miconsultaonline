package servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnalisisDAO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
// import java.sql.Date;

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
		int analisis_id = 0;
		String categoria = null;
		HttpSession sesion = req.getSession();
		int id = ((Paciente) sesion.getAttribute("paciente")).getId();
		int opcion = Integer.parseInt(req.getParameter("opcion"));
		
		
		try {
			
			// Se recogen los datos correspondientes en formato json de acuerdo según la opción 
			// enviada desde la parte cliente.
			if(opcion == 1) {
				ConexionBBDD.insertarDatosAnalisis(id);
				datos = AnalisisDAO.getInstance().listarAnalisisPorPacienteSesionJSON(id);
				resp.setContentType("text/html;charset=UTF8");
				resp.getWriter().print(datos);
			} else if(opcion == 2) {
				analisis_id = Integer.parseInt(req.getParameter("id"));
				categoria = req.getParameter("categoria");
				ConexionBBDD.insertarDatosMarcador(analisis_id, categoria);
				datos = AnalisisDAO.getInstance().listarMarcadoresPorAnalisisJSON(analisis_id);
				Cookie cookieOrigen = new Cookie("origen", "marcadores");
				resp.addCookie(cookieOrigen);
				resp.sendRedirect("panel.html");
				resp.setContentType("text/html;charset=UTF8");
				resp.getWriter().print(datos);
//			} else if(opcion == 3) {
//				analisis_id = Integer.parseInt(req.getParameter("id"));
//				datos = AnalisisDAO.getInstance().listarMarcadoresPorAnalisisJSON(analisis_id);
//				resp.setContentType("text/html;charset=UTF8");
//				resp.getWriter().print(datos);
			} else {
				System.out.println("Opción no válida.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
//		resp.setContentType("text/html;charset=UTF8");
//		resp.getWriter().print(datos);
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

