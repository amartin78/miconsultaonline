window.onload = function() {
	
	// Por defecto no se muestra ningún mensaje.
	document.getElementById("mensaje").style.display = "none";	
	
	// Si se ha producido un error de autenticación entonces se creará una cookie credencialesInvalidas   
    // que supondrá la creación de un mensaje al usuario por pantalla para que intente 
    // loguearse de nuevo.
    let texto = "";
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
		texto = "Sus credenciales no son válidas. Inténtelo de nuevo.";
		document.getElementById("mensaje").innerText = texto;
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
    
};