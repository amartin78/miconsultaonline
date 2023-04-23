
function historiaClinica() {

    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
    mensaje = null;
    /* mostrarAnomalias = false;
    mostrarAlergias = false;
    mostrarVacunas = false; */
    
    // Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CHistoriaClinica?opcion=1').
	then(response => response.json()).
	then(anomalias => {
		console.log("datos: " + window.location.search.substr(1));
		
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
	    	
	    	mostrarAnomalias = true;
	    	document.getElementById("contenido-principal").innerHTML += tablaDiagnostico;
		} else {
			mostrarAnomalias = false;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    });
    
    // Se muestra por pantalla la información relativa a las alergías del paciente
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
	    	
	    	mostrarAlergias = true;
	    	document.getElementById("contenido-principal").innerHTML += tablaAlergias;
		} else {
			mostrarAlergias = false;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    });
    
    // Se muestra por pantalla la información relativa a las vacunas del paciente
    fetch('CHistoriaClinica?opcion=3').
	then(response => response.json()).
	then(vacunas => {
		
		if(vacunas) {
			tablaVacunas = "<div class='contenedor-hClinica'>";
    		tablaVacunas += "<table id='vacunas' class='listado'>";
    		tablaVacunas += "<tr><th>Vacuna</th><th>Laboratorio</th><th>Lote</th><th>Fecha</th></tr>";
		    
			for(let v in vacunas) {
				if(vacunas.hasOwnProperty(v)) {
					let nombre = vacunas[v]["nombre"] == undefined ? "" : vacunas[v]["nombre"];
			        let laboratorio = vacunas[v]["laboratorio"] == undefined ? "" : vacunas[v]["laboratorio"];
			        let lote = vacunas[v]["lote"] == undefined ? "" : vacunas[v]["lote"];
			        let fecha = vacunas[v]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        
					tablaVacunas += "<tr><td>" + nombre + "</td>" + 
										"<td>" + laboratorio + "</td>" +
										"<td>" + lote + "</td>" + 
					                    "<td>" + fecha + "</td></tr>";
					}
				}
			tablaVacunas += "</table>";
	    	tablaVacunas += "</div>";
	    	
	    	mostrarVacunas = true;
	    	document.getElementById("contenido-principal").innerHTML += tablaVacunas;
	    	
	    	let numeroTablas = document.querySelectorAll('div.contenedor-hClinica').length;
	    	mensajeContenidoVacio(numeroTablas);
		} else {
			mostrarVacunas = false;
		}
        
	    document.querySelector("#menu li:nth-child(1)").style.color = "#d87093";
    });
}

function mensajeContenidoVacio(numeroTablas) {
	
	// console.log("numero de tablas es " + numeroTablas)
	if(numeroTablas < 1) {
		document.getElementById("mensaje").style.display = "block";	
		document.getElementById("mensaje").style.fontSize = "1.6em";
		document.getElementById("mensaje").style.marginTop = "7em";			
		texto = "No hay datos que mostrar.";
		document.getElementById("mensaje").innerText = texto;
	}
}

