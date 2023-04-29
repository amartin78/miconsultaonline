
function analisis() {
	
    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
	// Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CAnalisis?opcion=1').
	then(response => response.json()).
	then(analisis => {
		
		tablaAnalisis = "<div class='contenedor-analisis'>";
		
		if(analisis) {
			for(let a in analisis) {
				if(analisis.hasOwnProperty(a) && analisis[a]["marcadores"].length > 0) {
					console.log(analisis[a])				
		    		tablaAnalisis += "<table class='listado'>";
					// let analisis_id = analisis[a]["analisis_id"];
					let nombre = analisis[a]["nombre"] == undefined ? "" : analisis[a]["nombre"];
			        let estado = analisis[a]["estado"] == undefined ? "" : analisis[a]["estado"];
			        let fecha = analisis[a]["fecha"] == undefined ? "" : analisis[a]["fecha"];
			        if(fecha !== undefined) {
						fecha = new Date(fecha);
				        let anio = fecha.getFullYear();
				        let mes = fecha.getMonth() + 1;
				        mes = String(mes).length == 2 ? mes : `0${mes}`;
				        let dia = fecha.getDate();
				        dia = String(dia).length == 2 ? dia : `0${dia}`;
				        fecha = anio + "-" + mes + "-" + dia;
					}

					let marcadores = analisis[a]["marcadores"]; 
					
					for(let m in marcadores) {
						if(analisis.hasOwnProperty(m)) {
							nombre = marcadores[m]["nombre"] == undefined ? "" : marcadores[m]["nombre"];
							let categoria = marcadores[m]["categoria"] == undefined ? "" : marcadores[m]["categoria"];
							let valor = marcadores[m]["valor"] == undefined ? "" : marcadores[m]["valor"];
					        let valorMinimo = marcadores[m]["valorMinimo"] == undefined ? "" : marcadores[m]["valorMinimo"];
					        let valorMaximo = marcadores[m]["valorMaximo"] == undefined ? "" : marcadores[m]["valorMaximo"];
					        let resultado = marcadores[m]["resultado"] == undefined ? "" : marcadores[m]["resultado"];
							tablaAnalisis += "<tr><td>" + nombre + "</td>" + 
							                     "<td>" + fecha + "</td>" +
							                     "<td>" + categoria + "</td>" +
							                     "<td>" + valor + "</td>" +
							                     "<td>" + valorMinimo + "</td>" +
							                     "<td>" + valorMaximo + "</td>" +
							                     "<td><span id='resultado'>" + resultado + "</span></td></tr>";
						}
					}
					tablaAnalisis += "</table>";
			    	
				}
			}
			
			tablaAnalisis += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaAnalisis;
	    	
			document.querySelectorAll("#resultado").forEach(function(elem) {
				
				if(elem.innerText === "Anormal") {
					elem.style.color = "orange";
				}
			});
		    	
	    	// Añadir mensaje cuando no haya ninguna tabla que mostrar.
	    	// let numeroTablas = document.querySelectorAll('div.contenedor-hClinica').length;
	    	// mensajeContenidoVacio(numeroTablas);
		}
    }); 
    document.querySelector("#menu li:nth-child(2)").style.color = "#d87093";  
}