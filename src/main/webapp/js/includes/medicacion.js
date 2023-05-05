
function medicacion() {

    limpiarContenidoPrincipal();
    
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    // Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
    
    // Se muestra por pantalla la información relativa a la medicación del paciente
    /* fetch('CMedicamentos').
	then(response => response.json()).
	then(medicamentos => { */
	
		medicamentos = [
						{nombre: "Ibuprofeno", dosis: "400 mg", posologia: "1 cada 8 horas", tratamiento: "Artrosis", fecha: "2023-03-21"},
						{nombre: "Paracetamol pensa", dosis: "1 g", posologia: "1 cada 6 horas", tratamiento: "Esguince de tobillo", fecha: "2022-09-29"},
						{nombre: "Aspirina", dosis: "500 mg", posologia: "1 cada 6 horas", tratamiento: "Dolor de cabeza", fecha: "2023-02-04"},
						{nombre: "Champix", dosis: "1 mg", posologia: "1 cada 12 horas", tratamiento: "Tabaquismo", fecha: "2022-11-14"},
						{nombre: "Lovastatina Lareq", dosis: "20 mg", posologia: "1 cada 24 horas", tratamiento: "Colesterol alto", fecha: "2021-09-28"},
						{nombre: "Cetirizina Cinfa", dosis: "10 mg", posologia: "1 cada 24 horas", tratamiento: "Alergia", fecha: "2021-05-06"},
						{nombre: "Prednisona Cinfa", dosis: "30 mg", posologia: "1 cada 12 horas", tratamiento: "Eczema", fecha: "2018-08-26"},
						{nombre: "Relenza", dosis: "5 mg polvo", posologia: "1 cada 8 horas", tratamiento: "Gripe", fecha: "2015-01-04"},
						{nombre: "Amoxicilina Normon", dosis: "500 mg", posologia: "1 cada 8 horas", tratamiento: "Otitis", fecha: "2012-09-21"},
					];
		
		if(medicamentos) {
			tablaMedicamento = "<div class='contenedor-medicamento'>";
		    tablaMedicamento += "<table id='medicamento' class='listado-medicamento'>";
		    tablaMedicamento += "<tr><th>Nombre</th><th>Dosis</th><th>Posología</th><th>Tratamiento</th><th>Fecha</th></tr>";
		    
			for(let m in medicamentos) {
				if(medicamentos.hasOwnProperty(m)) {
					
					//console.log("Datos del medicamento son " + medicamentos[m]);
					
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
    // });
    
    
    document.querySelector("#menu li:nth-child(4)").style.color = "#d87093";
}

function mensajeContenidoVacio(numeroTablas) {
	
	// console.log("numero de tablas es " + numeroTablas)
	
	if(numeroTablas == undefined) {
		document.getElementById("mensaje").style.display = "block";	
		document.getElementById("mensaje").style.fontSize = "1.6em";
		document.getElementById("mensaje").style.marginTop = "7em";			
		texto = "No hay datos que mostrar.";
		document.getElementById("mensaje").innerText = texto;
	}
}


