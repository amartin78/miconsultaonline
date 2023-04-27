
window.onload = function () {
	
    // Se obtienen datos del servidor sobre el paciente para mostrarlos en el panel principal.
    fetch('CPacientes?opcion=4').
	then(response => response.json()).
	then(paciente => {
		nombreCompleto = paciente["nombre"] + " " + paciente["apellidos"];
	    document.getElementById("nombre-paciente").innerText = nombreCompleto;
	    document.getElementById("nombre-paciente").innerHTML += "<span id='flecha'>&#8744</span>";
	});
	
	// Se muestra el menú paciente.
	document.getElementById("nombre-paciente").addEventListener('click', function() {

		if(document.getElementById("menu-paciente").style.display == "none") {
			document.getElementById("menu-paciente").style.display = "block";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8743";
		} else {
			document.getElementById("menu-paciente").style.display = "none";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8744";
		}
	});
	
	// Se oculta el menú del paciente al clickear en cualquier parte del documento que no sea
	// el mismo menú del paciente.
	document.querySelector("body").addEventListener('click', function(ev) {

		if(document.getElementById("menu-paciente").style.display == "block" && 
		  (ev.target.id != "nombre-paciente" && ev.target.id != "flecha" && 
		   ev.target.id != "perfil" && ev.target.id != "cuenta")) 
		{
			document.getElementById("menu-paciente").style.display = "none";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8744";
		}
	});
	
	// Se determina el origen del perfil o el contenido correspondiente a la opción de menú elegida por el 
	// paciente (ej. cuenta) y se muestra dicha información en el panel principal.
	let origen = "";
	let cookies = document.cookie.split(";");
	for(let i=0; i<cookies.length; i++) {
		if(cookies[i].indexOf("origen") != -1) {
			origen = cookies[i].split("=")[1];
			document.cookie = "origen=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path:/;";
		} 
	}
	// Si la página se carga por primera vez, entonces se muestra al paciente la información relativa
    // a su historial clínico, si no, el contenido relativo a la opción que el usuario haya elegido
    // (perfil o cuenta).
	if(origen == "perfil") {
		opcion("Perfil");
	} else if(origen == "cuenta") {
		opcion("Cuenta");
	} else {
		opcion("Analítica");
	}
	
	// Se comprueba cada medio minuto que la cookie este activa, en caso contrario se redirecciona al  
	// cliente a la página de loguin para autenticarse de nuevo e iniciar una nueva sesión.
	// setInterval(checkCookie, 2000);
};

// Redirecciona al cliente a la página de loguin solo en el caso 
// de que la cookie este inactiva.
function checkCookie() {
	
	let cookie = obtenerCookie();
	if (cookie == null) {
		// Entorno local
		// window.location.href = "http://localhost:8080/login.html";
		
		// Entorno producción
		window.location.href = "https://miconsultaonline.herokuapp.com/login.html";
	}
}

// Devuelve el valor de la cookie con nombre email o null si su valor esta vacío
function obtenerCookie() {
	
	let dc = document.cookie.split("=");
	let email = dc[1];
	if(email == "") {
		return null;
	}
	return email;
}

// Muestra al usuario el contenido correspondiente (tablas, listados) a la opción elegida 
// en el menú. Para facilitar la modularidad del código, se divide el contenido en funciones, 
// cada una con su respectiva opción de menú.
function opcion(o) {
	
    let nombreOpcion = typeof o == "string" ? o : o.innerText;
    switch(nombreOpcion) {
		case "Perfil":
            perfil();
            break;
        case "Cuenta":
            cuenta();
            break;
        case "Historia clínica":
            historiaClinica();
            break;
        case "Analítica":
            analisis();
            break;
        case "Diagnóstico por imagen":
            imagen();
            break;
        case "Medicación":
            medicacion();
            break;
        case "Citas":
            citas();
            break;
        default:
            console.log("Opción incorrecta");
    }
}

// Esta función restablece los estilos del menú principal y del menú del paciente 
// cada vez que el paciente cambia de opción en el menú.
function limpiarContenidoPrincipal() {
	
	let opcionesMenuPrincipal = document.querySelectorAll("#menu li");
	for(let i = 0; i < opcionesMenuPrincipal.length; i++) {
		opcionesMenuPrincipal[i].style.color = "#333";
	}
	
	let opcionesMenuPaciente = document.querySelectorAll("#menu-paciente li");
	for(let i = 0; i < opcionesMenuPaciente.length; i++) {
		opcionesMenuPaciente[i].style.backgroundColor = "#fff";
		opcionesMenuPaciente[i].style.color = "#333";
	}
}

