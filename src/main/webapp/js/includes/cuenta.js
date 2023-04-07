
function cuenta() {
	
    limpiarContenidoPrincipal();

    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		// console.log("datos: " + paciente["email"]);
		let id = paciente["id"];
        let email = paciente["email"];
        let password = paciente["password"];
        
        // Servlet ObtenerPaciente devuelve paciente (doGet) o modifica el paciente (doPost)
        // Este formulario invoca al servlet ModificarPaciente que modifica los datos del paciente 
        let formulario = "<form id='cuenta' action='CambiarContrasenia' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td></tr>";
        formulario += "<tr><td><label>Contraseña actual</label><input name='actualContrasenia' id='actualContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Nueva contraseña</label><input name='nuevaContrasenia' id='nuevaContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Repetir contraseña</label><input name='repetirContrasenia' id='repetirContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><input class='boton' type='button' value='Editar'>";
        formulario += "<input class='boton' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input name='id' type='hidden'>";
        formulario += "</form>";
    
        document.getElementById("contenido-principal").innerHTML = formulario;
        
        document.getElementsByName("id")[0].value = id;
        document.getElementsByName("email")[0].value = email;
        document.getElementsByName("actualContrasenia")[0].value = password;
        
        document.querySelector("#cuenta input[type='button']").addEventListener('click', function() {
		
			let camposTexto = document.querySelectorAll("#cuenta input");
			for(let i = 0; i < camposTexto.length; i++) {
				camposTexto[i].disabled = false;
			}
			camposTexto[0].disabled = true;
		});
		
		document.getElementById("repetirContrasenia").addEventListener('blur', function() {
			
			let nuevaContrasenia = document.getElementById("nuevaContrasenia").value;
			let repetirContrasenia = document.getElementById("repetirContrasenia").value;
			
			if(nuevaContrasenia !== repetirContrasenia) {
				alert("La contraseña debe coincidir con la nueva.");
			}
		});
		
		document.querySelector("#menu-paciente li:nth-child(2)").style.backgroundColor = "#f0f0f0";
	});


}

