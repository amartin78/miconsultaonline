
function cuenta() {
	
    limpiarContenidoPrincipal();

    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		
		let id = paciente["id"];
        let email = paciente["email"];
        password = paciente["password"];
        // console.log("password servidor: " + password);
        
        // Servlet ObtenerPaciente devuelve paciente (doGet) o modifica el paciente (doPost)
        // Este formulario invoca al servlet ModificarPaciente que modifica los datos del paciente 
        let formulario = "<form id='cuenta' action='CambiarContrasenia' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td></tr>";
        formulario += "<tr><td><label>Contraseña actual</label><input name='actualContrasenia' id='actualContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Nueva contraseña</label><input name='nuevaContrasenia' id='nuevaContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><label>Repetir contraseña</label><input name='repetirContrasenia' id='repetirContrasenia' disabled type='password' size='30'></td></tr>";
        formulario += "<tr><td><input class='boton' type='button' value='Editar'>";
        formulario += "<input class='boton' type='submit' onclick='comprobarContrasenia()' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input name='id' type='hidden'>";
        formulario += "</form>";
    
        document.getElementById("contenido-principal").innerHTML = formulario;
        
        document.getElementsByName("id")[0].value = id;
        document.getElementsByName("email")[0].value = email;
        
        document.querySelector("#cuenta input[type='button']").addEventListener('click', function() {
		
			let camposTexto = document.querySelectorAll("#cuenta input");
			for(let i = 0; i < camposTexto.length; i++) {
				camposTexto[i].disabled = false;
			}
			camposTexto[0].disabled = true;
		});
		
		document.querySelector("#menu-paciente li:nth-child(2)").style.backgroundColor = "#f0f0f0";
	});
}

function comprobarContrasenia() {
	
	let error = false;
	let actualContrasenia = document.getElementById("actualContrasenia").value;
	let nuevaContrasenia = document.getElementById("nuevaContrasenia").value;
	let repetirContrasenia = document.getElementById("repetirContrasenia").value;
	
	// console.log("contrasenia servidor: " + password + ", contrasenia recibida: " + actualContrasenia + ", son identicas: " + (password !== actualContrasenia));
	if(password !== actualContrasenia) {
		alert("La contraseña actual no coincide con la su cuenta.");
		error = true;
	}
	if(nuevaContrasenia !== repetirContrasenia) {
		alert("La nueva contraseña y la repetida deben ser idénticas.");
		error = true;
	}
	if(error) {
		event.preventDefault();
		return false;
	}
}

