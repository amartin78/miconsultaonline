window.onload = function () {
	
    const e = document.createElement("h1");
    document.getElementById("contenido-principal").appendChild(e).innerText = "Perfil";
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
	
	document.getElementById("nombre-paciente").addEventListener('click', function() {

		if(document.getElementById("menu-paciente").style.display == "none") {
			document.getElementById("menu-paciente").style.display = "block";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8743";
		} else {
			document.getElementById("menu-paciente").style.display = "none";
			document.getElementById("nombre-paciente").getElementsByTagName("span")[0].innerHTML = "&#8744";
		}
	});
	
	
};

function opcion(o) {
	
    let nombreOpcion = o.innerText;
    document.getElementById("contenido-principal").
             getElementsByTagName("h1")[0].innerText = "";
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
        case "Diagnóstico":
            diagnostico();
            break;
        case "Analítica":
            analitica();
            break;
        case "Medicación":
            medicacion();
            break;
        case "Recetas":
            receta();
            break;
        case "Citas":
            cita();
            break;
        default:
            console.log("Opción incorrecta");
    }
}

function historiaClinica() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
}

function diagnostico() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(2)").style.color = "#d87093";
    let contenidoPrincipal = document.getElementById("contenido-principal").innerHTML;
    let datosDiagnostico = [["Dolor muscular 1", "04/01/2021"], 
                            ["Dolor muscular 2", "04/02/2021"],
                            ["Dolor muscular 3", "04/03/2021"], 
                            ["Dolor muscular 4", "04/04/2021"], 
                            ["Dolor muscular 5", "04/05/2021"], 
                            ["Dolor muscular 6", "04/06/2021"], 
                            ["Dolor muscular 7", "04/07/2021"], 
                            ["Dolor muscular 8", "04/08/2021"], 
                            ["Dolor muscular 9", "04/09/2021"], 
                            ["Dolor muscular 10", "04/10/2021"], 
                            ["Dolor muscular 11", "04/11/2021"], 
                            ["Dolor muscular 12", "04/12/2021"], 
                            ["Dolor muscular 13", "05/01/2021"], 
                            ["Dolor muscular 14", "05/02/2021"], 
                            ["Dolor muscular 15", "05/03/2021"],
                            ["Dolor muscular 1", "04/01/2021"], 
                            ["Dolor muscular 2", "04/02/2021"],
                            ["Dolor muscular 3", "04/03/2021"], 
                            ["Dolor muscular 4", "04/04/2021"], 
                            ["Dolor muscular 5", "04/05/2021"], 
                            ["Dolor muscular 6", "04/06/2021"], 
                            ["Dolor muscular 7", "04/07/2021"], 
                            ["Dolor muscular 8", "04/08/2021"], 
                            ["Dolor muscular 9", "04/09/2021"], 
                            ["Dolor muscular 10", "04/10/2021"], 
                            ["Dolor muscular 11", "04/11/2021"], 
                            ["Dolor muscular 12", "04/12/2021"], 
                            ["Dolor muscular 13", "05/01/2021"], 
                            ["Dolor muscular 14", "05/02/2021"], 
                            ["Dolor muscular 15", "05/03/2021"] 
                        ];
    let tablaDiagnostico = "<table border='1'>";
    tablaDiagnostico += "<tr><th>Anomalía</th><th>Fecha consulta</th></tr>";
    for(let i = 0; i < datosDiagnostico.length; i++) {
        tablaDiagnostico += "<tr><td>" + datosDiagnostico[i][0] + "</td>" + 
                                "<td>" + datosDiagnostico[i][1] + "</td></tr>";
    }
    tablaDiagnostico += "</table>";
    if(contenidoPrincipal.indexOf("table") == -1) {
        document.getElementById("contenido-principal").innerHTML += tablaDiagnostico;
    }
}

function analitica() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(3)").style.color = "#d87093";
}

function medicacion() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(4)").style.color = "#d87093";
}

function receta() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(5)").style.color = "#d87093";
}

function cita() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(7)").style.color = "#d87093";
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

