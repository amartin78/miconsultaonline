
function marcadores() {
	
    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
	// Se muestra por pantalla la información relativa a las anomalías del paciente
    /* fetch('CAnalisis?opcion=3').
	then(response => response.json()).
	then(marcadores => { */
		// console.log("datos de marcadores son " + datos);
		if(marcadores) {
			tablaMarcadores = "<div class='contenedor-marcadores'>";
		    tablaMarcadores += "<table id='marcadores' class='listado'>";
			
			/* for(let a in analisis) {
				if(analisis.hasOwnProperty(a)) {
					let analisis_id = analisis[a]["analisis_id"];
					let nombre = analisis[a]["nombre"] == undefined ? "" : analisis[a]["nombre"];
			        let estado = analisis[a]["estado"] == undefined ? "" : analisis[a]["estado"];
			        let fecha = analisis[a]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        
			        // Se almacena solo el tipo de análisis (colesterol, vitaminas, etc.) para no incluir espacios
			        // en el parámetro.
			        let categoria = nombre.substring(5);
			        
			        let enlace = "<a href=/CAnalisis?opcion=2&id=" + analisis_id + "&categoria=" + categoria + ">Ver</a>";
			        if(estado == "Muestra en laboratorio") {
						enlace = "Pendiente";
					}
			        
					tablaMarcadores += "<tr><td>" + nombre + "</td>" + 
										"<td>" + estado + "</td>" + 
					                    "<td>" + fecha + "</td>" +
					                    "<td>" + enlace + "</td></tr>";
					}
				} */
			tablaMarcadores += "</table>";
	    	tablaMarcadores += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaMarcadores;
	    	
	    	// Añadir mensaje cuando no haya ninguna tabla que mostrar.
	    	// let numeroTablas = document.querySelectorAll('div.contenedor-hClinica').length;
	    	// mensajeContenidoVacio(numeroTablas);
	    	
		}
    // }); 
    document.querySelector("#menu li:nth-child(2)").style.color = "#d87093";  
}