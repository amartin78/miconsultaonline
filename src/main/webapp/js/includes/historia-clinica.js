
function historiaClinica() {

    limpiarContenidoPrincipal();
    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    
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
        document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
    }
}

