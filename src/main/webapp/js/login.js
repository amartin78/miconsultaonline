
window.onload = function() {
	
	// Si se ha producido un error de autenticación entonces se creará una cookie credencialesInvalidas   
    // que supondrá la creación de un mensaje al usuario por pantalla para que intente 
    // loguearse de nuevo. 
    let mensaje = "";
    let validez = "";
    let cookies = document.cookie.split(";");
	for(let i=0; i<cookies.length; i++) {
		if(cookies[i].indexOf("credencialesInvalidas") != -1) {
			validez = cookies[i].split("=")[1];
			document.cookie = "credencialesInvalidas=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path:/;";
		}
	}
	
	if (validez == "noValido") {			
		document.getElementById("mensaje").style.display = "block";
		document.getElementById("mensaje").style.backgroundColor = "lavenderblush";			
		mensaje = "Sus credenciales no son válidas. Inténtelo de nuevo.";
		document.getElementById("mensaje").innerText = mensaje;
	} else {
		document.getElementById("mensaje").style.display = "none";	
	}
	
	// Se borra el mensaje una vez que el usuario intenta modificar el email o la contraseña.
	document.getElementById("email").addEventListener('click', function() {
		
		document.getElementById("mensaje").style.display = "none";	
	});
	
	document.getElementById("password").addEventListener('click', function() {
		
		document.getElementById("mensaje").style.display = "none";	
	});
	
	// Antes de enviar el formulario se realiza una serie de validaciones sobre los campos de éste 
	// para decidir si se corrigen o se envían al servidor en caso de estar todos correctos
	document.getElementById("enviar").addEventListener("click", function(campo) {
		
		let mensajeError = "";
		let contadorCamposVacios = 0;
		let email = document.getElementById("email").value;
		let password = document.getElementById("password").value;
		
		if(email.length == 0) {
			mensajeError += "Debe de introducir su email \n";
			contadorCamposVacios++;
		}
		if(password.length == 0) {
			mensajeError += "Debe de introducir su contraseña \n";
			contadorCamposVacios++;
		}
		if(contadorCamposVacios > 0) {
			alert(mensajeError);
			event.preventDefault();
		}
	}
)};

