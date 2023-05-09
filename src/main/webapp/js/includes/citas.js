
function citas() {

    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	

	// Se muestra por pantalla la información relativa a las anomalías del paciente
    /* fetch('CCitas').
	then(response => response.json()).
	then(citas => { */
		
		elemCitas = "<div class='contenedor-citas'>";
		
		// if(citas) {
			// for(let c in citas) {
				// if(analisis.hasOwnProperty(c)) {
					
		    		elemCitas += "<div class='listado-citas'>";
		    		
		    		elemCitas += "<input type='radio' id='dia_10' name='dia_semana' value='2023-05-10'>";
		    		elemCitas += "<label for='dia_10'>Martes 10 May</label>";
		    		elemCitas += "<input type='radio' id='dia_11' name='dia_semana' value='2023-05-11'>";
		    		elemCitas += "<label for='dia_11'>Miércoles 11 May</label>";
		    		elemCitas += "<input type='radio' id='dia_12' name='dia_semana' value='2023-05-12'>";
		    		elemCitas += "<label for='dia_12'>Jueves 12 May</label>";
		    		
		    		
		    		
					// let nombre = analisis[a]["nombre"] == undefined ? "" : analisis[a]["nombre"];
			        // let fecha = analisis[a]["fecha"] == undefined ? "" : analisis[a]["fecha"];
			        /* if(fecha !== undefined) {
						fecha = new Date(fecha);
						const options = {day: "numeric", month: "long", year: "numeric"};
						fecha = new Intl.DateTimeFormat("es-ES", options).format(fecha);
						fecha = fecha.split(" ");
						fecha = fecha[0] + " " + fecha[2][0].toUpperCase() + fecha[2].substring(1) + " " + fecha[4];
					}
					
					// Encabezado tabla con el tipo de analítica y la fecha
					tablaAnalisis += "<tr><td>" + nombre + "</td>" +
									 "<td></td>" +
									 "<td></td>" +
									 "<td></td>" +  
							         "<td>" + fecha + "</td></tr>";
					
					// Nombre columnas							                    
					tablaAnalisis += "<tr><th>Nombre</th>" + 
					                     "<th>Valor</th>" +
					                     "<th>Valor mínimo</th>" +
					                     "<th>Valor máximo</th>" +
					                     "<th>Resultado</th></tr>";
							                     
					let marcadores = analisis[a]["marcadores"]; 
					for(let m in marcadores) {
						if(marcadores.hasOwnProperty(m) && marcadores.length > 0) {
							nombre = marcadores[m]["nombre"] == undefined ? "" : marcadores[m]["nombre"];
							let valor = marcadores[m]["valor"] == undefined ? "" : marcadores[m]["valor"];
					        let valorMinimo = marcadores[m]["valorMinimo"] == undefined ? "" : marcadores[m]["valorMinimo"];
					        let valorMaximo = marcadores[m]["valorMaximo"] == undefined ? "" : marcadores[m]["valorMaximo"];
					        let resultado = marcadores[m]["resultado"] == undefined ? "" : marcadores[m]["resultado"];
							tablaAnalisis += "<tr><td>" + nombre + "</td>" + 
							                     "<td>" + valor + "</td>" +
							                     "<td>" + valorMinimo + "</td>" +
							                     "<td>" + valorMaximo + "</td>" +
							                     "<td><span id='resultado'>" + resultado + "</span></td></tr>";
						}
					} */
					elemCitas += "</div>";
					elemCitas += "</div>";
				// }
			// }
			elemCitas += "</div>";
			
	    	document.getElementById("contenido-principal").innerHTML += elemCitas;
	    	
	    	document.querySelectorAll("label").forEach(function(elem) {
				if(elem.control.id == "dia_11") {
					elem.innerText += " (Lleno)";
				}
			});
	    	
	    	//console.log(document.querySelectorAll("label")[1].control.id == "dia_11");
	    	document.getElementById("dia_11").disabled = true;
	    	// document.querySelector("#dia_11 label").innerText += "(Lleno)";
		// }
    // }); 


    document.querySelector("#menu li:nth-child(6)").style.color = "#d87093";
}

function mensajeContenidoVacio(numeroTablas) {
	
	if(numeroTablas == undefined) {
		document.getElementById("mensaje").style.display = "block";	
		document.getElementById("mensaje").style.fontSize = "1.6em";
		document.getElementById("mensaje").style.marginTop = "7em";			
		texto = "No hay datos que mostrar.";
		document.getElementById("mensaje").innerText = texto;
	}
}

