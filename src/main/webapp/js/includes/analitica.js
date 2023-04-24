
function analitica() {
	
    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
	// Se muestra por pantalla la información relativa a las anomalías del paciente
    /* fetch('CAnaliticas').
	then(response => response.json()).
	then(analiticas => { */
		
		if(true) {
			tablaAnalitica = "<div class='contenedor-analitica'>";
		    tablaAnalitica += "<table id='analitica' class='listado'>";
		    // tablaAnalitica += "<tr><th>Prueba realizada</th><th>Estado</th><th>Fecha</th></tr>";
		    
		    let analiticas = [
				{"prueba": "Prueba1", "estado": "Estado1", "fecha": "12-04-2010", "ruta": "<a href=/CAnaliticas>Ver</a>"},
				{"prueba": "Prueba2", "estado": "Estado2", "fecha": "12-04-2021", "ruta": "<a href=#''>Ver</a>"},
				{"prueba": "Prueba3", "estado": "Estado3", "fecha": "12-04-2022", "ruta": "<a href=#''>Ver</a>"}
			];
		    
			for(let a in analiticas) {
				if(analiticas.hasOwnProperty(a)) {
					let prueba = analiticas[a]["prueba"] == undefined ? "" : analiticas[a]["prueba"];
			        let estado = analiticas[a]["estado"] == undefined ? "" : analiticas[a]["estado"];
			        let fecha = analiticas[a]["fecha"];
			        fecha = new Date(fecha);
			        let anio = fecha.getFullYear();
			        let mes = fecha.getMonth() + 1;
			        mes = String(mes).length == 2 ? mes : `0${mes}`;
			        let dia = fecha.getDate();
			        dia = String(dia).length == 2 ? dia : `0${dia}`;
			        fecha = anio + "-" + mes + "-" + dia;
			        let ruta = analiticas[a]["ruta"] == undefined ? "" : analiticas[a]["ruta"];
			        
					tablaAnalitica += "<tr><td>" + prueba + "</td>" + 
										"<td>" + estado + "</td>" + 
					                    "<td>" + fecha + "</td>" +
					                    "<td>" + ruta + "</td></tr>";
					}
				}
			tablaAnalitica += "</table>";
	    	tablaAnalitica += "</div>";
	    	
	    	document.getElementById("contenido-principal").innerHTML += tablaAnalitica;
	    	
	    	// Añadir mensaje cuando no haya ninguna tabla que mostrar.
	    	// let numeroTablas = document.querySelectorAll('div.contenedor-hClinica').length;
	    	// mensajeContenidoVacio(numeroTablas);
	    	
		}
    // }); 
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