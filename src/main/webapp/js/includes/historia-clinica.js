
function historiaClinica() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    
    // let contenidoPrincipal = document.getElementById("contenido-principal").innerHTML;
    
    // Consultas
    let datosDiagnostico = [["Náusea", "14-01-2010"], 
                            ["Reflujo gástrico", "29-09-2012"],
                            ["Revisión general", "03-07-2015"], 
                            ["Gripe", "04-01-2015"],  
                            ["Revisión general", "10-06-2018"],
                            ["Fiebre", "22-04-2021"]
                           ];
    let tablaDiagnostico = "<div class='contenedor-hClinica'>";
    tablaDiagnostico += "<table id='diagnostico' class='listado'>";
    tablaDiagnostico += "<tr><th>Anomalía</th><th>Fecha consulta</th></tr>";
    for(let i = 0; i < datosDiagnostico.length; i++) {
        tablaDiagnostico += "<tr><td>" + datosDiagnostico[i][0] + "</td>" + 
                                "<td>" + datosDiagnostico[i][1] + "</td></tr>";
    }
    tablaDiagnostico += "</table>";
    tablaDiagnostico += "</div>";
    
    // Alergias
    let datosAlergias = [["Polen", "04-09-2008"], 
                         ["Acaros", "12-03-2010"],
                         ["Moho", "01-08-2014"]
                        ];
    let tablaAlergias = "<div class='contenedor-hClinica'>";                    
    tablaAlergias += "<table id='alergias' class='listado'>";
    tablaAlergias += "<tr><th>Alergia</th><th>Fecha</th></tr>";
    for(let i = 0; i < datosAlergias.length; i++) {
        tablaAlergias += "<tr><td>" + datosAlergias[i][0] + "</td>" + 
                                "<td>" + datosAlergias[i][1] + "</td></tr>";
    }
    tablaAlergias += "</table>";
    tablaAlergias += "</div>";
    
    // Vacunas
    let datosVacunas = [["Sarampión", "08-03-2001"], 
                        ["Viruela", "12-05-2001"],
                        ["Polio", "01-06-2002"],
                        ["Covid", "02-12-2021"]
                       ];
    let tablaVacunas = "<div class='contenedor-hClinica'>";
    tablaVacunas += "<table id='vacunas' class='listado'>";
    tablaVacunas += "<tr><th>Vacuna</th><th>Fecha</th></tr>";
    for(let i = 0; i < datosVacunas.length; i++) {
        tablaVacunas += "<tr><td>" + datosVacunas[i][0] + "</td>" + 
                                "<td>" + datosVacunas[i][1] + "</td></tr>";
    }
    tablaVacunas += "</table>";
    tablaVacunas += "</div>";
    
    
    //if() {
	
	//}
    
    document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
    document.getElementById("contenido-principal").innerHTML += tablaAlergias;
    document.getElementById("contenido-principal").innerHTML += tablaVacunas;
}



// Estilo de vida
// Consultas
// Alergias
// Vacunas

