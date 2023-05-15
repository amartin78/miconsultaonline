
async function cuenta() {
	
    limpiarContenidoPrincipal();
		
	// Se obtiene información del paciente desde el servidor y se guarda temporalmente.
    await fetch('CPacientes?opcion=4').
	then(response => response.json()).
	then(paciente => {
		
		let id = paciente["id"];
        let email = paciente["email"];
        
        let mensaje = "<div id='mensaje'></div>";
        
        // Este formulario invoca al servlet CambiarContrasenia que modifica el password del paciente.
        // Muestra el solo el email del paciente. Todos los campos se muestran inactivos hasta que el 
        // usuario pulse el botón Editar.
        let formulario = "<form id='cuenta' action='CPacientes?opcion=5' method='post' autoComplete='off'>";
        formulario += "<input autoComplete='false' name='hidden' type='text' style='display:none;'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Email</label><input id='email' name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td></tr>";
        formulario += "<tr><td><label>Contraseña actual</label><input id='actualContrasenia' name='actualContrasenia' class='contrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Nueva contraseña</label><input id='nuevaContrasenia' name='nuevaContrasenia' class='contrasenia' disabled type='password' autoComplete='new-password' size='30'></td></tr>";
        formulario += "<tr><td><label>Repetir nueva contraseña</label>";
        formulario += "<input id='contraseniaRepetida' name='contraseniaRepetida' disabled type='password' class='contrasenia' autoComplete='new-password' size='30'></td></tr>";
        formulario += "<tr><td><input id='editar' class='boton' type='button' value='Editar'>";
        // El botón Enviar envía el formulario al servidor pero además comprueba justo antes del envío que 
        // la nueva contraseña sea igual a la nueva contraseña repetida.
        formulario += "<input id='enviar' class='boton' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input id='id' name='id' type='hidden'>";
        formulario += "</form>";
    
    	// Se añade el formulario al DOM para mostrarlo al usuario en el panel principal.
        document.getElementById("contenido-principal").innerHTML = mensaje;
        document.getElementById("contenido-principal").innerHTML += formulario;
        
        // Una vez añadido el formulario al DOM mostramos el valor del email en el campo.
        document.getElementById("id").value = id;
        document.getElementById("email").value = email;
        
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
		 
		let mensajeRecibido = ""; // Mensaje devuelto por el método validarContrasenia(campo) al ser invocado
		let mensajeError = ""; // Mensaje con lista de errores de los campos contraseña para mostrarlos por pantalla 
		let contadorErrores = 0;
		let contadorCamposVacios = 0;
		
		document.querySelector("#enviar").addEventListener('click', function(event) {
			
			// En caso de haber al menos un campo input deshabilitado (además del campo email) quiere decir que 
			// el formulario esta deshabilitado y no se deberá realizar ninguna acción.
	 		if(document.getElementById("actualContrasenia").disabled) {
				alert("Pulse antes el botón Editar para modificar o añadir información y luego Enviar");
				event.preventDefault();
				return;
			}
				
			let indice = 0;
			
			// Se evalúa cada campo del formulario cambiar contraseña
			document.querySelectorAll(".contrasenia").forEach(function(campo) {
				
				indice++; // Indica el campo cuya contraseña se evalúa
				mensajeRecibido = validarContrasenia(campo); // Esta variable almacena el valor devuelto por el método validarContrasenia(campo)
				
				if(mensajeRecibido) {	// Si el valor recibido no es falso, entonces es un mensaje
					switch(indice) {
						case 1:
							if(campo.value.length == 0) {	// Si el campo esta vacío se guarda un mensaje para mostrar al usuario
								mensajeError += "Debe de introducir su contraseña actual \n";
							} else {	// En otro caso se guarda un mensaje con el error detectado en el campo contraseña
								mensajeError += "La contraseña actual debe tener " + mensajeRecibido + "\n";
								contadorErrores++;	// Se incrementa en uno el contador de errores
							}	
							break;
						case 2:
							if(campo.value.length == 0) {
								mensajeError += "Debe de introducir una nueva contraseña \n";
							} else {
								mensajeError += "La nueva contraseña debe tener " + mensajeRecibido + "\n";
								contadorErrores++;
							}	
							break;
						case 3:
							if(campo.value.length == 0) {
								mensajeError += "Debe de introducir una vez más la nueva contraseña \n";
							}	
							break;
					}
				} 
			});	// Fin forEach loop
			
			// Se comprueba que la nueva contraseña y la repetida sean idénticas
			if(esContraseniaDistinta()) {
				mensajeError += "La nueva contraseña repetida debe de ser idéntica a la nueva contraseña \n";
				contadorErrores++;
			}
			
			// Se cuenta el número de campos vacío y se almacena en la variable contadorCamposVacios
			document.querySelectorAll(".contrasenia").forEach(function(campo) {
				
				if(campo.value.length == 0) {					
					contadorCamposVacios++;
				}
			});
						
			// En caso de existir al menos un error en la contraseña o un campo vacío, se muestra por pantalla 
			// al usuario un mensaje y se anula el envío del formulario al servidor
			if(contadorErrores > 0 || contadorCamposVacios > 0) {				
				alert(mensajeError);
				event.preventDefault();		
			}
			// Se reinician los marcadores
			mensajeError = "";
			contadorErrores = 0;
			contadorCamposVacios = 0;
			
			// Se habilita el campo enviar justo antes de enviar el formulario para que pueda ser enviado al servidor
			document.getElementById("email").disabled = false;	
			// Se oculta cualquier mensaje visible al usuario
			document.getElementById("mensaje").style.display = "none";
		});
        
        // Si se ha intentado cambiar la contraseña entonces la cookie passwordValido estará activa  
        // y según su valor (válido / no válido ) mostraremos un mensaje al usuario con el   
        // resultado de dicha modificación.
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
		document.querySelector("#menu-paciente li:nth-child(2)").style.color = "#d87093";
	});
}

