
function medicacion() {

    limpiarContenidoPrincipal();
    
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
    
    // Se muestra por pantalla la información relativa a la medicación del paciente
    fetch('CMedicamentos').
	then(response => response.json()).
	then(medicamentos => {
		
		if(medicamentos) {
			tablaMedicamento = "<div class='contenedor-medicamento'>";
		    tablaMedicamento += "<table id='medicamento' class='listado-medicamento'>";
		    tablaMedicamento += "<tr><th>Nombre</th><th>Dosis</th><th>Posología</th><th>Tratamiento</th><th>Fecha</th></tr>";
		    
			for(let m in medicamentos) {
				if(medicamentos.hasOwnProperty(m)) {
					
					let nombre = medicamentos[m]["nombre"] == undefined ? "" : medicamentos[m]["nombre"];
			        let dosis = medicamentos[m]["dosis"] == undefined ? "" : medicamentos[m]["dosis"];
			        let posologia = medicamentos[m]["posologia"] == undefined ? "" : medicamentos[m]["posologia"];
			        let tratamiento = medicamentos[m]["tratamiento"] == undefined ? "" : medicamentos[m]["tratamiento"];
			        let fecha = medicamentos[m]["fecha"] == undefined ? "" : medicamentos[m]["fecha"];
			        if(fecha !== undefined) {
						fecha = new Date(fecha);
						const options = {day: "numeric", month: "long", year: "numeric"};
						fecha = new Intl.DateTimeFormat("es-ES", options).format(fecha);
						fecha = fecha.split(" ");
						fecha = fecha[0] + " " + fecha[2][0].toUpperCase() + fecha[2].substring(1, 3) + " " + fecha[4];
					}
			        
					tablaMedicamento += "<tr><td>" + nombre + "</td>" + 
										"<td>" + dosis + "</td>" +
										"<td>" + posologia + "</td>" +
										"<td>" + tratamiento + "</td>" +  
					                    "<td>" + fecha + "</td></tr>";
					}	
				}
			tablaMedicamento += "</table>";
	    	tablaMedicamento += "</div>";
	    	document.getElementById("contenido-principal").innerHTML += tablaMedicamento;
		}
	});
    
    document.querySelector("#menu li:nth-child(4)").style.color = "#d87093";
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


