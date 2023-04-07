
window.onload = function () {
	
    // Se activa la opción de Historia clínica
    document.querySelector("#menu li:nth-child(1)").click();
    document.getElementById("menu-paciente").style.display = "none";
    
    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		nombreCompleto = paciente["nombre"] + " " + paciente["apellidos"];
	    document.getElementById("nombre-paciente").innerText = nombreCompleto;
	    document.getElementById("nombre-paciente").innerHTML += "<span id='flecha'>&#8744</span>";
	});
	
	// Se muestra el menú paciente 
	document.getElementById("nombre-paciente").addEventListener('click', function() {

		if(document.getElementById("menu-paciente").style.display == "none") {
			document.getElementById("menu-paciente").style.display = "block";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8743";
		} else {
			document.getElementById("menu-paciente").style.display = "none";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8744";
		}
	});
	
	document.querySelector("body").addEventListener('click', function(ev) {

		if(document.getElementById("menu-paciente").style.display == "block" && 
		  (ev.target.id != "nombre-paciente" && ev.target.id != "flecha" && 
		   ev.target.id != "perfil" && ev.target.id != "cuenta")) 
		{
			document.getElementById("menu-paciente").style.display = "none";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8744";
		}
	});
	
};

function opcion(o) {
	
    let nombreOpcion = o.innerText;
    // document.getElementById("contenido-principal").innerHTML = "<span>Hola</span>";
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
            analitica();
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

function limpiarContenidoPrincipal() {
	
	let opcionesMenuPrincipal = document.querySelectorAll("#menu li");
	for(let i = 0; i < opcionesMenuPrincipal.length; i++) {
		opcionesMenuPrincipal[i].style.color = "#333";
	}
	
	let opcionesMenuPaciente = document.querySelectorAll("#menu-paciente li");
	for(let i = 0; i < opcionesMenuPaciente.length; i++) {
		opcionesMenuPaciente[i].style.backgroundColor = "#fff";
	}
	
    let contenidoPrincipal = document.getElementById("contenido-principal").innerHTML;
    if(contenidoPrincipal.indexOf("table") > 0) {
        document.getElementById("contenido-principal").getElementsByTagName("table")[0].remove();
    }
}

