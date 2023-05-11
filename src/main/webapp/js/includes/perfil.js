
async function perfil() {

    limpiarContenidoPrincipal();

	// Se obtiene información del paciente desde el servidor y se guarda temporalmente.
    await fetch('CPacientes?opcion=4').
	then(response => response.json()).
	then(paciente => {
		let id = paciente["id"];
		let nombre = paciente["nombre"] == undefined ? "" : paciente["nombre"];
        let apellidos = paciente["apellidos"] == undefined ? "" : paciente["apellidos"];
        let email = paciente["email"] == undefined ? "" : paciente["email"];
        let fecNacimiento = paciente["fecNacimiento"];
        fecNacimiento = new Date(fecNacimiento);
        let anio = fecNacimiento.getFullYear();
        let mes = fecNacimiento.getMonth() + 1;
        mes = String(mes).length == 2 ? mes : `0${mes}`;
        let dia = fecNacimiento.getDate();
        dia = String(dia).length == 2 ? dia : `0${dia}`;
        fecNacimiento = anio + "-" + mes + "-" + dia;
        let domicilio = paciente["domicilio"] == undefined ? "" : paciente["domicilio"];
        let codPostal = paciente["codPostal"] == 0 ? "" : paciente["codPostal"];
        let localidad = paciente["localidad"] == undefined ? "" : paciente["localidad"];
        let provincia = paciente["provincia"] == undefined ? "" : paciente["provincia"];
        let telefono = paciente["telefono"] == 0 ? "" : paciente["telefono"];
        let estadoCivil = paciente["estadoCivil"] == undefined ? "" : paciente["estadoCivil"];
        
        // Este formulario invoca al servlet ModificarPaciente que modifica los datos del paciente.
        // Muestra los datos que hayan sido guardados por el usuario en el proceso de registro y posteriormente.
        // Todos los campos se muestran inactivos hasta que el usuario pulse el botón Editar.
        let formulario = "<form id='perfil' action='CPacientes?opcion=3' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Nombre</label><input id='nombre' name='nombre' class='perfil' type='text' size='30' disabled></td>";
        formulario += "<td><label>Apellidos</label><input id='apellidos' name='apellidos' class='perfil' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Email</label><input id='email' name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td>";
        formulario += "<td><label>Fecha de nacimiento</label><input id='fecNacimiento' name='fecNacimiento' class='perfil' type='date' disabled size='30'></td></tr>";
        formulario += "<tr><td><label>Domicilio</label><input id='domicilio' name='domicilio' class='perfil' type='text' size='30' disabled></td>";
        formulario += "<td><label>Código postal</label><input id='codPostal' name='codPostal' class='perfil' type='number' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Localidad</label><input id='localidad' name='localidad' class='perfil' type='text' size='30' disabled></td>";
        formulario += "<td><label>Provincia</label><input id='provincia' name='provincia' class='perfil' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Teléfono</label><input id='telefono' name='telefono' class='perfil' type='number' size='30' disabled></td>";
        formulario += "<td><label>Estado civil</label><input id='estadoCivil' name='estadoCivil' class='perfil' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td colspan='2'><input id='editar' class='boton' type='button' value='Editar'>";
        formulario += "<input id='enviar' class='boton' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input id='id' name='id' type='hidden'>";
        formulario += "</form>";
    
    	// Se añade el formulario al DOM para mostrarlo al usuario en el panel principal.
        document.getElementById("contenido-principal").innerHTML = formulario;
        
        // Una vez añadido el formulario al DOM mostramos los correspondientes datos del paciente 
        // obtenidos desde el servidor.
        document.getElementById("id").value = id;
        document.getElementById("nombre").value = nombre;
        document.getElementById("apellidos").value = apellidos;
        document.getElementById("email").value = email;
        document.getElementById("fecNacimiento").value = fecNacimiento;
        document.getElementById("domicilio").value = domicilio;
        document.getElementById("codPostal").value = codPostal;
        document.getElementById("localidad").value = localidad;
        document.getElementById("provincia").value = provincia;
        document.getElementById("telefono").value = telefono;
        document.getElementById("estadoCivil").value = estadoCivil;
        
        // Para modificar o añadir datos se activan los campos pulsando el botón Editar.
        // Campos como el email seguirán inactivos pues son únicos al paciente en cuestión.
        document.getElementById("editar").addEventListener('click', function() {
		
			let camposTexto = document.querySelectorAll("#perfil input");
			for(let i = 0; i < camposTexto.length; i++) {
				camposTexto[i].disabled = false;
			}
			// Campo correspondiente al email del paciente.
			camposTexto[2].disabled = true;
		});
		
		let mensajeRecibido = ""; // Esta variable almacena el valor del método validarNumeroTelefonico(campo) al ser invocado
		let mensajeError = ""; // Mensaje con lista de errores de los campos del formulario perfil para mostrarlos por pantalla 
		let contadorErrores = 0;
		let contadorCamposVacios = 0;
		
		// Antes de enviar el formulario se realiza una serie de validaciones sobre los campos de éste 
		// para decidir si se corrigen o se envían al servidor en caso de estar todos correctos
		document.getElementById("enviar").addEventListener("click", function(event) {
	 
			// Se evalúa cada campo del formulario perfil
			document.querySelectorAll(".perfil").forEach(function(campo) {
				
				switch(campo.id) {
					case "nombre":
						if(campo.value.length == 0) {
							mensajeError += "Debe de introducir su nombre \n";
						} else if(!esTexto(campo)) {
							mensajeError += "Debe de introducir texto en el campo Nombre \n";
							contadorErrores++;
						}
						break;
					case "apellidos":
						if(campo.value.length == 0) {
							mensajeError += "Debe de introducir sus apellidos \n";
						} else if(!esTexto(campo)) {
							mensajeError += "Debe de introducir texto en el campo Apellidos \n";
							contadorErrores++;
						}
						break;
					case "localidad":
						if(!esTexto(campo)) {
							mensajeError += "Debe de introducir texto en el campo Localidad \n";
							contadorErrores++;
						}
						break;
					case "provincia":
						if(!esTexto(campo)) {
							mensajeError += "Debe de introducir texto en el campo Provincia \n";
							contadorErrores++;
						}
						break;
					case "estadoCivil":
						if(!esTexto(campo)) {
							mensajeError += "Debe de introducir texto en el campo Estado civil \n";
							contadorErrores++;
						}
						break;
					case "fecNacimiento":
						if(campo.value.length == 0) {
							mensajeError += "Debe de introducir su fecha de nacimiento \n";
						} else if(!esFechaNacimientoValida(campo)) {
							mensajeError += "La fecha de nacimiento debe ser menor o igual a la fecha actual \n";
							contadorErrores++;
						}
						break;
					case "codPostal":
						if(campo.value.length > 0 && !esCodigoPostal(campo)) {
							mensajeError += "Debe de introducir un código postal de 5 dígitos \n";
							contadorErrores++;
						}
						break;
					case "telefono":
						mensajeRecibido = validarNumeroTelefonico(campo);
						if(campo.value.length > 0 && mensajeRecibido) {
							mensajeError += mensajeRecibido;
							contadorErrores++;
						}
						break;
					default:
						console.log("Campo no definido");
				}
			});	// Fin forEach loop
			
			// Se cuenta el número de campos vacíos y obligatorios y se almacena en la variable contadorCamposVacios
			document.querySelectorAll(".perfil").forEach(function(campo) {
				
				// Si es un campo vacío y además es obligatorio
				if(campo.value.length == 0 && (campo.id === "nombre" || campo.id === "apellidos" || campo.id === "fecNacimiento")) {					
					contadorCamposVacios++;
				}
			});
						
			// En caso de existir un campo vacío o al menos un error en cualquier campo, se muestra por pantalla 
			// al usuario un mensaje y se anula el envío del formulario al servidor
			if(contadorErrores > 0 || contadorCamposVacios > 0) {				
				alert(mensajeError);
				event.preventDefault();		
			}
			// Se reinician los marcadores
			mensajeError = "";
			contadorErrores = 0;
			contadorCamposVacios = 0;
			
			// Se cambia el estilo de la opción de menú paciente elegida (perfil), para mostrarla como activa.
			document.querySelector("#menu-paciente li:nth-child(1)").style.backgroundColor = "#f0f0f0";
			document.querySelector("#menu-paciente li:nth-child(1)").style.color = "#d87093";
		});
	});
}

