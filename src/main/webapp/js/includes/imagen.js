
function imagen() {

    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
    
    
    /* let imagenDatos = [
		{
			"titulo": "Rodilla", 
			"descripcion": "Posible indicio de artrosis, desgaste del cartílago provocando a su vez falta de protección a " +
						   "se observa también una alteración en la forma en que los huesos están alineados.",
			"rutaImgRayosX": "../../img/rayos-x-1.jpg"
		}, 
		{
			"titulo": "Cadera", 
			"descripcion": "En principio no se aprecia ningún tipo de anomalía en la cadera. El paciente presenta cierta sintomatología " + 
			 			   "que puede sugerir una inflamación en los músculos que rodean los huesos.",
			"rutaImgRayosX": "../../img/rayos-x-2.jpg"
		}
	]; */
	
	fetch("CDiagnosticoImagenes").
	then(result => result.json()).
	then(pruebas => {
		
		
		
		let tablaImagen = "<div id='contenedor'>";
		let rutaImgFlechaAsc = "../../img/flecha-hacia-arriba.png";
		let rutaImgFlechaDesc = "../../img/flecha-hacia-abajo.png";
		let imgActual = 1;
		
		// console.log("Las pruebas son " + pruebas.length);
		
		for(let i = 0; i < pruebas.length; i++) {
			
			let numeroImgs = pruebas[i]["rutas"].length;
			tablaImagen += "<div id='contenedor-diagnostico-imagen'>";
			tablaImagen += "<div id='contenedor-texto'>";
			tablaImagen += "<h3>" + pruebas[i]["nombre"] + "</h3>";
			tablaImagen += "<p>" + pruebas[i]["descripcion"] + "</p>";
			tablaImagen += "</div>"; // contenedor-texto
			tablaImagen += "<div id='contenedor-imagenes'>";
			tablaImagen += "<img src='../../img/rayos_x/" + pruebas[i]["rutas"][0] + ".jpg'>";
			tablaImagen += "<div id='contenedor-navegacion'>";
			tablaImagen += "<div class='flecha'><img src='" + rutaImgFlechaAsc + "' onclick=anterior()''></div>";
			tablaImagen += "<div><p>" + imgActual + " / " + numeroImgs + "</p></div>";
			tablaImagen += "<div class='flecha'><img src='" + rutaImgFlechaDesc + "' onclick='siguiente()'></div>";
			tablaImagen += "</div>"; // contenedor-navegacion
			tablaImagen += "</div>"; // contenedor-imagenes
			tablaImagen += "</div>"; // contenedor-diagnostico-imagen
		}
		tablaImagen += "</div>"; // contenedor
		
		document.getElementById("contenido-principal").innerHTML += tablaImagen;
	});
	
    document.querySelector("#menu li:nth-child(3)").style.color = "#d87093";
}

function anterior() {
	
}

function siguiente() {
	
}


