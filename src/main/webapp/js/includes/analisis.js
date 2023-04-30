
function analisis() {
	
    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "";
	
	// Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CAnalisis').
	then(response => response.json()).
	then(analisis => {
		
		tablaAnalisis = "<div class='contenedor-analisis'>";
		
		if(analisis) {
			for(let a in analisis) {
				if(analisis.hasOwnProperty(a) && analisis[a]["marcadores"].length > 0) {
					
					tablaAnalisis += "<div class='contenedor-tabla-analisis'>";
		    		tablaAnalisis += "<table class='listado-analisis'>";
		    		
					let nombre = analisis[a]["nombre"] == undefined ? "" : analisis[a]["nombre"];
			        let fecha = analisis[a]["fecha"] == undefined ? "" : analisis[a]["fecha"];
			        if(fecha !== undefined) {
						fecha = new Date(fecha);
						const options = {day: "numeric", month: "long", year: "numeric"};
						fecha = new Intl.DateTimeFormat("es-ES", options).format(fecha);
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
					}
					tablaAnalisis += "</table>";
					tablaAnalisis += "</div>";
				}
			}
			tablaAnalisis += "</div>";
			
	    	document.getElementById("contenido-principal").innerHTML += tablaAnalisis;
	    	
	    	// Marcadores anormales se muestran con color naranja.
			document.querySelectorAll("#resultado").forEach(function(elem) {
				if(elem.innerText === "Anormal") {
					elem.style.color = "orange";
				}
			});
		}
    }); 
    document.querySelector("#menu li:nth-child(2)").style.color = "#d87093";  
}

