
function imagen() {

    limpiarContenidoPrincipal();
    
    document.getElementById("contenido-principal").innerHTML = "<div id='mensaje'></div>";
	
	fetch("CDiagnosticoImagenes").
	then(result => result.json()).
	then(pruebas => {
		
		let tablaImagen = "<div id='contenedor'>";
		let rutaImgFlechaAsc = "../../img/flecha-hacia-arriba.png";
		let rutaImgFlechaDesc = "../../img/flecha-hacia-abajo.png";
		let fecha = "";
		
		for(let i = 0; i < pruebas.length; i++) {
			
			let numeroImgs = pruebas[i]["rutas"].length;
			tablaImagen += "<div id='contenedor-diagnostico-imagen'>";
			tablaImagen += "<div id='contenedor-texto'>";
			tablaImagen += "<h3>" + pruebas[i]["nombre"] + "</h3>";
			fecha = pruebas[i]["fecha"] == undefined ? "" : pruebas[i]["fecha"];
	        if(fecha !== undefined) {
				fecha = new Date(fecha);
				const options = {day: "numeric", month: "long", year: "numeric"};
				fecha = new Intl.DateTimeFormat("es-ES", options).format(fecha);
				fecha = fecha.split(" ");
				fecha = fecha[0] + " " + fecha[2][0].toUpperCase() + fecha[2].substring(1, 3) + " " + fecha[4];
			}
			tablaImagen += "<h6>" + fecha + "</h6>";
			tablaImagen += "<p>" + pruebas[i]["descripcion"] + "</p>";
			tablaImagen += "</div>"; // contenedor-texto
			tablaImagen += "<div id='contenedor-imagenes'>";
			tablaImagen += "<img id='imgRayosX_" + i + "' src='../../img/rayos_x/" + pruebas[i]["rutas"][0] + ".jpg'>";
			tablaImagen += "<div id='contenedor-navegacion'>";
			tablaImagen += "<div class='flecha'><img src='" + rutaImgFlechaAsc + "' onclick='siguiente(" + i + ", " + numeroImgs + ")'></div>";
			tablaImagen += "<div><span id='contImgActual_" + i + "'>1</span>&nbsp;/ " + numeroImgs + "</div>";
			tablaImagen += "<div class='flecha'><img src='" + rutaImgFlechaDesc + "' onclick='anterior(" + i + ")'></div>";
			tablaImagen += "</div>"; // contenedor-navegacion
			tablaImagen += "</div>"; // contenedor-imagenes
			tablaImagen += "</div>"; // contenedor-diagnostico-imagen
		}
		tablaImagen += "</div>"; // contenedor
		document.getElementById("contenido-principal").innerHTML += tablaImagen;
	});
    document.querySelector("#menu li:nth-child(3)").style.color = "#d87093";
}

function anterior(numPrueba) {
	
	numImgActual = document.getElementById("imgRayosX_" + numPrueba).src.substr(-5, 1);
	rutaImg = document.getElementById("imgRayosX_" + numPrueba).src.substr(-9, 4);
	
	if(numImgActual > 1) {
		numImgActual--;
		document.getElementById("imgRayosX_" + numPrueba).src = window.location.origin + "/img/rayos_x/rayos_x_" + rutaImg + numImgActual + ".jpg";
		document.getElementById("contImgActual_" + numPrueba).innerText = numImgActual;
	}
}

function siguiente(numPrueba, total) {
	
	numImgActual = document.getElementById("imgRayosX_" + numPrueba).src.substr(-5, 1);
	rutaImg = document.getElementById("imgRayosX_" + numPrueba).src.substr(-9, 4);
	
	if(numImgActual < total) {
		numImgActual++;
		document.getElementById("imgRayosX_" + numPrueba).src = window.location.origin + "/img/rayos_x/rayos_x_" + rutaImg + numImgActual + ".jpg";
		document.getElementById("contImgActual_" + numPrueba).innerText = numImgActual;
	}
}

