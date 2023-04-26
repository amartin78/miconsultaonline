
function analisis() {
	
    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
	// Se muestra por pantalla la información relativa a las anomalías del paciente
    fetch('CAnalisis?opcion=1').
	then(response => response.json()).
	then(analisis => {
		
		if(analisis) {
			tablaAnalisis = "<div class='contenedor-analisis'>";
		    tablaAnalisis += "<table id='analisis' class='listado'>";
		    // tablaAnalitica += "<tr><th>Prueba realizada</th><th>Estado</th><th>Fecha</th></tr>";
		    
		    /* let analisis = [
				{"prueba": "Prueba1", "estado": "Estado1", "fecha": "12-04-2010", "ruta": "<a href=/CAnaliticas>Ver</a>"},
				{"prueba": "Prueba2", "estado": "Estado2", "fecha": "12-04-2021", "ruta": "<a href=#''>Ver</a>"},
				{"prueba": "Prueba3", "estado": "Estado3", "fecha": "12-04-2022", "ruta": "<a href=#''>Ver</a>"}
			]; */
		    
			for(let a in analisis) {
				if(analisis.hasOwnProperty(a)) {
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
			        let ruta = analisis[a]["ruta"] == undefined ? "" : analisis[a]["ruta"];
			        
					tablaAnalisis += "<tr><td>" + nombre + "</td>" + 
										"<td>" + estado + "</td>" + 
					                    "<td>" + fecha + "</td>" +
					                    "<td><a href=/CAnaliticas?opcion=2>Ver</a></td></tr>";
					}
				}
			tablaAnalisis += "</table>";
	    	tablaAnalisis += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaAnalisis;
	    	
	    	// Añadir mensaje cuando no haya ninguna tabla que mostrar.
	    	// let numeroTablas = document.querySelectorAll('div.contenedor-hClinica').length;
	    	// mensajeContenidoVacio(numeroTablas);
	    	
		}
    }); 
    document.querySelector("#menu li:nth-child(2)").style.color = "#d87093";  
}

function mensajeContenidoVacio(numeroTablas) {
	
	console.log("numero de tablas es " + numeroTablas)
	
	if(numeroTablas == undefined) {
		document.getElementById("mensaje").style.display = "block";	
		document.getElementById("mensaje").style.fontSize = "1.6em";
		document.getElementById("mensaje").style.marginTop = "7em";			
		texto = "No hay datos que mostrar.";
		document.getElementById("mensaje").innerText = texto;
	}
}