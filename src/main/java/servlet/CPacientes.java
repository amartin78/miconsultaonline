package servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.sql.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

import modelo.Paciente;
import dao.PacienteDAO;

@WebServlet("/CPacientes")
@MultipartConfig
public class CPacientes extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CPacientes() {
		super();
	}

	protected void inicio(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		switch(req.getParameter("opcion")) {
			case "1": 
				altaPaciente(req, resp); 
				return;
			case "2": 
				bajaPaciente(req, resp); 
				return;
			case "3": 
				actualizarPaciente(req, resp); 
				return;
			case "4": 
				buscarPaciente(req, resp); 
				return;
			case "5": 
				cambiarContrasenia(req, resp); 
				return;
			case "6": 
				abrirSesion(req, resp); 
				return;
			case "7": 
				cerrarSesion(req, resp); 
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
	}
	
	private void altaPaciente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Date fecNacimiento = req.getParameter("fecNacimiento") == null || req.getParameter("fecNacimiento").isBlank() ? 
							 null : Date.valueOf(req.getParameter("fecNacimiento"));
		
		Paciente p = new Paciente(nombre, apellidos, email, password, fecNacimiento);
		try {			
			p.insertar();
			resp.sendRedirect("login.html");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void bajaPaciente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
		Paciente p;
		try {
			
			p = PacienteDAO.getInstance().obtenerPacientePorID(id);
			p.eliminar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void actualizarPaciente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
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
			int telefono = req.getParameter("telefono").isBlank() ? 0 : Integer.parseInt(req.getParameter("telefono"));
			p.setTelefono(telefono);
			p.setEstadoCivil(req.getParameter("estadoCivil"));
			
			p.actualizar();
			Cookie cookieOrigen = new Cookie("origen", "perfil");
			resp.addCookie(cookieOrigen);
			resp.sendRedirect("panel.html");
			System.out.println("El paciente se ha modificado con éxito");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void buscarPaciente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String datos;
		HttpSession sesion = req.getSession();
		Paciente p = new Paciente();
		String email = ((Paciente) sesion.getAttribute("paciente")).getEmail();
		
		try {
			datos = p.buscar("email", email);
			resp.setContentType("text/html;charset=UTF8");
			resp.getWriter().print(datos);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cambiarContrasenia(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			
			HttpSession sesion = req.getSession();
			int id = req.getParameter("id") == null ? null : Integer.parseInt(req.getParameter("id"));
			String email = req.getParameter("email");
			String actualContrasenia = req.getParameter("actualContrasenia");
			String nuevaContrasenia = req.getParameter("nuevaContrasenia");
			String contraseniaRepetida = req.getParameter("contraseniaRepetida");
			
			String mensaje = null;
			Paciente p = PacienteDAO.getInstance().autenticarPaciente(email, actualContrasenia);
			
			if (p != null) {
				if(nuevaContrasenia.equals(contraseniaRepetida)) {
					Cookie cookieValidez = new Cookie("passwordValido", "valido");
					resp.addCookie(cookieValidez);
					p.setPassword(req.getParameter("nuevaContrasenia"));
					p.cambiarContrasenia();
					System.out.println("La nueva contraseña ha sido guardada con éxito.");
				} else {
					System.out.println("La nueva contraseña y la repetida deben ser idénticas");
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
	
	private void abrirSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					
					PacienteDAO.getInstance().reconectar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 50 * 1000, TimeUnit.MILLISECONDS);
		
		HttpSession sesion = req.getSession();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String mensaje;
				
		try {
			
			Paciente paciente = PacienteDAO.getInstance().autenticarPaciente(email, password);
			if (paciente != null) {
				System.out.println("Se inicia la sesión");
				// Tiempo máximo que estará activa la sesión
				sesion.setMaxInactiveInterval(10 * 60);
				sesion.setAttribute("paciente", paciente);
				Cookie cookie = new Cookie("email", paciente.getEmail());
				// Se establece un tiempo máximo para la cookie igual al de la sesión menos un minuto, 
				// que es la frecuencia con que la parte cliente comprueba el estado de la sesión 
				// para en el caso de estar finalizada redireccionar al cliente a la página de loguin.
				cookie.setMaxAge(9 * 60);
				resp.addCookie(cookie);
				// Una vez autenticado el usuario y creadas la sesión y la cookie redireccionamos
				// al cliente hacia el panel. 
				resp.sendRedirect("panel.html");	
			// En este caso el usuario ha intentado iniciar la sesión pero sus 
			// credenciales no son válidas y se redirecciona a la página de loguin.
			} else {
				mensaje = "noValido";
				Cookie cookieValidez = new Cookie("credencialesInvalidas", mensaje);
				resp.addCookie(cookieValidez);
				resp.sendRedirect("login.html");
			}
		} catch(Exception e) {
			resp.sendRedirect("login.html");
			e.printStackTrace();
		}
	}
	
	private void cerrarSesion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sesion = req.getSession();
				
		try {
			
			// Si la sesión esta abierta entonces se cierra.
			if(sesion.getAttribute("paciente") != null) {
				System.out.println("Se cierra la sesión");
				sesion.invalidate();
				resp.sendRedirect("login.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

