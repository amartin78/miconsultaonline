
function historiaClinica() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    
    // Se crea una tabla que muestra información relativa a las anomalías 
    // o enfermedades que haya tenido el paciente.
    let datosDiagnostico = [["Náusea", "14-01-2010"], 
                            ["Reflujo gástrico", "29-09-2012"],
                            // ["Revisión general", "03-07-2015"], 
                            ["Gripe", "04-01-2015"],  
                            // ["Revisión general", "10-06-2018"],
                            ["Fiebre", "22-04-2021"]
                           ];
    let tablaDiagnostico = "<div class='contenedor-hClinica'>";
    tablaDiagnostico += "<table id='diagnostico' class='listado'>";
    tablaDiagnostico += "<tr><th>Anomalía</th><th></th><th></th><th>Fecha diagnóstico</th></tr>";
    for(let i = 0; i < datosDiagnostico.length; i++) {
        tablaDiagnostico += "<tr><td>" + datosDiagnostico[i][0] + "</td>" + 
        						"<td></td>" +
        						"<td></td>" + 
                                "<td>" + datosDiagnostico[i][1] + "</td></tr>";
    }
    tablaDiagnostico += "</table>";
    tablaDiagnostico += "</div>";
    
    // Se crea una tabla que muestra información relativa a las alergías 
    // que haya tenido el paciente.
    let datosAlergias = [["Polen", "Congestión nasal", "Primavera", "PPC", "04-09-2008"],
    					 ["Ácaros", "Picazón nariz, paladar y garganta", "Otoño", "IgE", "12-03-2010"],
    					 ["Moho", "Piel seca", "Todo el año", "PPC", "01-08-2014"] 
                        ];
    let tablaAlergias = "<div class='contenedor-hClinica'>";                    
    tablaAlergias += "<table id='alergias' class='listado'>";
    tablaAlergias += "<tr><th>Alergia</th><th>Síntomas</th><th>Frecuencia</th><th>Test realizado</th><th>Fecha</th></tr>";
    for(let i = 0; i < datosAlergias.length; i++) {
        tablaAlergias += "<tr><td>" + datosAlergias[i][0] + "</td>" + 
       					     "<td>" + datosAlergias[i][1] + "</td>" +
       					     "<td>" + datosAlergias[i][2] + "</td>" +
       					     "<td>" + datosAlergias[i][3] + "</td>" + 
                             "<td>" + datosAlergias[i][4] + "</td></tr>";
    }
    tablaAlergias += "</table>";
    tablaAlergias += "</div>";
    
    // Se crea una tabla que muestra información relativa a las vacunas del paciente.
    let datosVacunas = [["Sarampión", "08-03-2001"], 
                        ["Viruela", "12-05-2001"],
                        ["Polio", "01-06-2002"],
                        ["Covid", "02-12-2021"]
                       ];
    let tablaVacunas = "<div class='contenedor-hClinica'>";
    tablaVacunas += "<table id='vacunas' class='listado'>";
    tablaVacunas += "<tr><th>Vacuna</th><th></th><th></th><th>Fecha</th></tr>";
    for(let i = 0; i < datosVacunas.length; i++) {
        tablaVacunas += "<tr><td>" + datosVacunas[i][0] + "</td>" + 
        						"<td></td>" +
        						"<td></td>" + 
                                "<td>" + datosVacunas[i][1] + "</td></tr>";
    }
    tablaVacunas += "</table>";
    tablaVacunas += "</div>";
    
    // Se añade cada tabla en el DOM para mostrarla al usuario en el panel principal.
    document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
    document.getElementById("contenido-principal").innerHTML += tablaAlergias;
    document.getElementById("contenido-principal").innerHTML += tablaVacunas;
}



// Estilo de vida
// Consultas
// Alergias
// Vacunas

