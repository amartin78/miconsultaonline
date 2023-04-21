
function historiaClinica() {

    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = null;
    /*tablaDiagnostico = "";
    tablaAlergias = "";
    tablaVacunas = "";*/
    mensaje=null;
    mostrarAnomalias = false;
    mostrarAlergias = false;
    mostrarVacunas = false;
    
    // Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CHistoriaClinica?opcion=1').
	then(response => response.json()).
	then(anomalias => {
		
		if(anomalias) {
			tablaDiagnostico = "<div class='contenedor-hClinica'>";
		    tablaDiagnostico += "<table id='diagnostico' class='listado'>";
		    tablaDiagnostico += "<tr><th>Anomalía</th><th>Síntomas</th><th>Facultativo</th><th>Fecha</th></tr>";
		    
			for(let a in anomalias) {
				if(anomalias.hasOwnProperty(a)) {
					let nombre = anomalias[a]["nombre"] == undefined ? "" : anomalias[a]["nombre"];
			        let sintomas = anomalias[a]["sintomas"] == undefined ? "" : anomalias[a]["sintomas"];
			        let facultativo = anomalias[a]["facultativo"] == undefined ? "" : anomalias[a]["facultativo"];
			        let fecha = anomalias[a]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        
					tablaDiagnostico += "<tr><td>" + nombre + "</td>" + 
										"<td>" + sintomas + "</td>" +
										"<td>" + facultativo + "</td>" + 
					                    "<td>" + fecha + "</td></tr>";
					}
				}
			tablaDiagnostico += "</table>";
	    	tablaDiagnostico += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaDiagnostico;
		} else {
			mostrarAnomalias = false;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    });
    
    // Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CHistoriaClinica?opcion=2').
	then(response => response.json()).
	then(alergias => {
		
		if(alergias) {
			tablaAlergias = "<div class='contenedor-hClinica'>";                    
    		tablaAlergias += "<table id='alergias' class='listado'>";
    		tablaAlergias += "<tr><th>Alergia</th><th>Frecuencia</th><th>Test realizado</th><th>Fecha</th></tr>";
		    
			for(let a in alergias) {
				if(alergias.hasOwnProperty(a)) {
					let nombre = alergias[a]["nombre"] == undefined ? "" : alergias[a]["nombre"];
			        let frecuencia = alergias[a]["frecuencia"] == undefined ? "" : alergias[a]["frecuencia"];
			        let testRealizado = alergias[a]["testRealizado"] == undefined ? "" : alergias[a]["testRealizado"];
			        let fecha = alergias[a]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        
					tablaAlergias += "<tr><td>" + nombre + "</td>" + 
										"<td>" + frecuencia + "</td>" +
										"<td>" + testRealizado + "</td>" + 
					                    "<td>" + fecha + "</td></tr>";
					}
				}
			tablaAlergias += "</table>";
	    	tablaAlergias += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaAlergias;
		} else {
			mostrarAlergias = false;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    });
    
    // document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
    // document.getElementById("contenido-principal").innerHTML += tablaAlergias;
    
    // Se muestra por pantalla la información relativa a las anomalías del paciente
    /* fetch('CHistoriaClinica?opcion=3').
	then(response => response.json()).
	then(anomalias => {
		
		if(anomalias.length > 0) {
			tablaDiagnostico = "<div class='contenedor-hClinica'>";
		    tablaDiagnostico += "<table id='diagnostico' class='listado'>";
		    tablaDiagnostico += "<tr><th>Anomalía</th><th>Síntomas</th><th>Facultativo</th><th>Fecha</th></tr>";
		    
			for(const a in anomalias) {
				if(anomalias.hasOwnProperty(a)) {
					// console.log(`anomalia es: ${anomalias[a]["nombre"]}`);
					let nombre = anomalias[a]["nombre"] == undefined ? "" : anomalias[a]["nombre"];
			        let sintomas = anomalias[a]["sintomas"] == undefined ? "" : anomalias[a]["sintomas"];
			        let facultativo = anomalias[a]["facultativo"] == undefined ? "" : anomalias[a]["facultativo"];
			        let fecha = anomalias[a]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        
			        console.log(`anomalia es: ${facultativo}`);
			        
					tablaDiagnostico += "<tr><td>" + nombre + "</td>" + 
										"<td>" + sintomas + "</td>" +
										"<td>" + facultativo + "</td>" + 
					                    "<td>" + fecha + "</td></tr>";
					}
				}
			tablaDiagnostico += "</table>";
	    	tablaDiagnostico += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    }); */
    
    // Se crea una tabla que muestra información relativa a las anomalías 
    // o enfermedades que haya tenido el paciente.
    /* let datosDiagnostico = [["Esguince de tobillo", "Dolor moderado y hematoma", "Dr. Arturo García", "29-09-2012"], 
                            ["Gripe", "Estornudos y malestar general", "Dr. Carlos Rodríguez", "04-01-2015"], 
                            ["Otitis", "Inflamación y dolor en los oídos", "Dr. Carlos Rodríguez", "22-04-2021"]
                           ]; */
                           
    /*if(!(mostrarAnomalias || mostrarAlergias || mostrarVacunas)) {
	
		mensaje = "No hay datos que mostrar.";
		
	} */                      
    
    // Se crea una tabla que muestra información relativa a las alergías 
    // que haya tenido el paciente.
    /*let datosAlergias = [["Polen", "Primavera", "PPC", "04-09-2008"],
    					 ["Ácaros", "paladar y garganta", "Otoño", "IgE", "12-03-2010"],
    					 ["Moho", "Todo el año", "PPC", "01-08-2014"] 
                        ];
    tablaAlergias = "<div class='contenedor-hClinica'>";                    
    tablaAlergias += "<table id='alergias' class='listado'>";
    tablaAlergias += "<tr><th>Alergia</th><th>Frecuencia</th><th>Test realizado</th><th>Fecha</th></tr>";
    for(let i = 0; i < datosAlergias.length; i++) {
        tablaAlergias += "<tr><td>" + datosAlergias[i][0] + "</td>" + 
       					     "<td>" + datosAlergias[i][1] + "</td>" +
       					     "<td>" + datosAlergias[i][2] + "</td>" + 
                             "<td>" + datosAlergias[i][3] + "</td></tr>";
    }
    tablaAlergias += "</table>";
    tablaAlergias += "</div>"; */
    
    // Se crea una tabla que muestra información relativa a las vacunas del paciente.
    /*let datosVacunas = [["Sarampión", "Sanofi Pasteur", "LZ3590", "08-03-2001"],
                        ["Polio", "GSK", "WT9817", "01-06-2002"],
                        ["Covid-19", "Pfizer", "EC5384", "02-12-2021"]
                       ];
    tablaVacunas = "<div class='contenedor-hClinica'>";
    tablaVacunas += "<table id='vacunas' class='listado'>";
    tablaVacunas += "<tr><th>Vacuna</th><th>Laboratorio</th><th>Lote</th><th>Fecha</th></tr>";
    for(let i = 0; i < datosVacunas.length; i++) {
        tablaVacunas += "<tr><td>" + datosVacunas[i][0] + "</td>" + 
        						"<td>" + datosVacunas[i][1] + "</td>" +
        						"<td>" + datosVacunas[i][2] + "</td>" + 
                                "<td>" + datosVacunas[i][3] + "</td></tr>";
    }
    tablaVacunas += "</table>";
    tablaVacunas += "</div>";
    
    // Se añade cada tabla en el DOM para mostrarla al usuario en el panel principal.
    // document.getElementById("contenido-principal").innerHTML = tablaDiagnostico;
    
    if(mensaje) {	
	    document.getElementById("contenido-principal").innerHTML += mensaje;
	}
    
    document.getElementById("contenido-principal").innerHTML += tablaAlergias;
    document.getElementById("contenido-principal").innerHTML += tablaVacunas;*/
}

