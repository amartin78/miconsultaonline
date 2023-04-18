
function cuenta() {
	
    limpiarContenidoPrincipal();

	// Se obtiene información del paciente desde el servidor y se guarda temporalmente.
    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		
		let id = paciente["id"];
        let email = paciente["email"];
        
        let mensaje = "<div id='mensaje'></div>";
        
        // Este formulario invoca al servlet CambiarContrasenia que modifica el password del paciente.
        // Muestra el solo el email del paciente. Todos los campos se muestran inactivos hasta que el 
        // usuario pulse el botón Editar.
        let formulario = "<form id='cuenta' action='CambiarContrasenia' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td></tr>";
        formulario += "<tr><td><label>Contraseña actual</label><input name='actualContrasenia' id='actualContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Nueva contraseña</label><input name='nuevaContrasenia' id='nuevaContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Repetir nueva contraseña</label>";
        formulario += "<input name='repetirContrasenia' id='repetirContrasenia' onblur='comprobarContrasenia()' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><input id='editar' class='boton' type='button' value='Editar'>";
        // El botón Enviar envía el formulario al servidor pero además comprueba justo antes del envío que 
        // los datos correspondientes a la contraseña sean correctos.
        formulario += "<input id='enviar' class='boton' type='submit' onclick='comprobarContrasenia()' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input name='id' type='hidden'>";
        formulario += "</form>";
    
    	// Se añade el formulario al DOM para mostrarlo al usuario en el panel principal.
        document.getElementById("contenido-principal").innerHTML = mensaje;
        document.getElementById("contenido-principal").innerHTML += formulario;
        
        // Una vez añadido el formulario al DOM mostramos el valor del email en el campo.
        document.getElementsByName("id")[0].value = id;
        document.getElementsByName("email")[0].value = email;
        
        // Para modificar o añadir datos se activan los campos pulsando el botón Editar.
        // El campo email seguirá inactivo pues es único al paciente en cuestión.
        document.querySelector("#editar").addEventListener('click', function() {
		
			let camposTexto = document.querySelectorAll("#cuenta input");
			for(let i = 0; i < camposTexto.length; i++) {
				camposTexto[i].disabled = false;
			}
			camposTexto[0].disabled = true;
			document.getElementById("mensaje").style.display = "none";
		});
		
		// Se habilita el campo enviar justo antes de enviar el formulario para 
		// que pueda ser enviado al servidor. 
		 document.querySelector("#enviar").addEventListener('click', function() {
			
			document.getElementsByName("email")[0].disabled = false;
			document.getElementById("mensaje").style.display = "none";
		});
        
        // Si se ha cambiado la contraseña entonces la cookie passwordValido estará activa y según 
        // su valor mostraremos un mensaje al usuario con el resultado de dicha modificación.
        let texto = "";
        let validez = "";
        let cookies = document.cookie.split(";");
		for(let i=0; i<cookies.length; i++) {
			if(cookies[i].indexOf("passwordValido") != -1) {
				validez = cookies[i].split("=")[1];
				document.cookie = "passwordValido=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path:/;";
			}
		}
		if (validez !== "") {
			if (validez === "valido") {
				texto = "Su contraseña ha sido modificada con éxito.";
				document.getElementById("mensaje").style.backgroundColor = "darkseagreen";
			} else if (validez === "noValido") {
				texto = "Su contraseña no coincide con la almacenada en nuestra base de datos.";
				document.getElementById("mensaje").style.backgroundColor = "lavenderblush";			
			}
			document.getElementById("mensaje").style.maxWidth = "70%";
			document.getElementById("mensaje").style.margin = "1.8em auto";
			document.getElementById("mensaje").style.padding = "1.2em 0";
			document.getElementById("mensaje").innerText = texto;	
		}
        
		// Se cambia el estilo de la opción de menú paciente elegida (cuenta), para mostrarla como activa.
		document.querySelector("#menu-paciente li:nth-child(2)").style.backgroundColor = "#f0f0f0";
	});
}

// Esta función comprueba que los datos de la contraseña aportados por el paciente
// son correctos.
function comprobarContrasenia() {
	
	let nuevaContrasenia = document.getElementById("nuevaContrasenia").value;
	let repetirContrasenia = document.getElementById("repetirContrasenia").value;
	
	// La nueva contraseña y la repetida deben ser idénticas.
	if(nuevaContrasenia !== repetirContrasenia) {
		alert("La nueva contraseña y la repetida deben ser idénticas.");
		event.preventDefault();
		return false;
	}
}

