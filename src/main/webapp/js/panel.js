window.onload = function () {
	
    const e = document.createElement("h1");
    document.getElementById("contenido-principal").appendChild(e).innerText = "Perfil";
    
    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(data => {
		paciente = data;
		nombreCompleto = paciente["nombre"] + " " + paciente["apellidos"];
	    document.getElementById("paciente").getElementsByTagName("button")[0].innerText = nombreCompleto;
	});
	
	document.getElementById("paciente").getElementsByTagName("button")[0].addEventListener('click', function() {
		// alert("cierre de sesion");
		
	});
	
};

function opcion(o) {
	
    let nombreOpcion = o.innerText;
    document.getElementById("contenido-principal").
             getElementsByTagName("h1")[0].innerText = nombreOpcion;
    switch(nombreOpcion) {
        case "Perfil":
            perfil();
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

function perfil() {

    limpiarContenidoPrincipal();
}

function diagnostico() {

    limpiarContenidoPrincipal();
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
}

function medicacion() {

    limpiarContenidoPrincipal();
}

function receta() {

    limpiarContenidoPrincipal();
}

function cita() {

    limpiarContenidoPrincipal();
}

function limpiarContenidoPrincipal() {

    let contenidoPrincipal = document.getElementById("contenido-principal").innerHTML;
    if(contenidoPrincipal.indexOf("table") > 0) {
        document.getElementById("contenido-principal").getElementsByTagName("table")[0].remove();
    }
}

