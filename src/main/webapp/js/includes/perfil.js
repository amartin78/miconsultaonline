
function perfil() {

    limpiarContenidoPrincipal();

	// Se obtiene información del paciente desde el servidor y se guarda temporalmente.
    fetch('ObtenerPaciente').
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
        let formulario = "<form id='perfil' action='ModificarPaciente' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Nombre</label><input name='nombre' type='text' size='30' disabled></td>";
        formulario += "<td><label>Apellidos</label><input name='apellidos' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td>";
        formulario += "<td><label>Fecha de nacimiento</label><input name='fecNacimiento' id='fecNacimiento' disabled type='date' size='30'></td></tr>";
        formulario += "<tr><td><label>Domicilio</label><input name='domicilio' type='text' size='30' disabled></td>";
        formulario += "<td><label>Código postal</label><input name='codPostal' type='number' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Localidad</label><input name='localidad' type='text' size='30' disabled></td>";
        formulario += "<td><label>Provincia</label><input name='provincia' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Teléfono</label><input name='telefono' type='number' size='30' disabled></td>";
        formulario += "<td><label>Estado civil</label><input name='estadoCivil' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td colspan='2'><input id='editar' class='boton' type='button' value='Editar'>";
        formulario += "<input id='enviar' class='boton' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "<input name='id' type='hidden'>";
        formulario += "</form>";
    
    	// Se añade el formulario al DOM para mostrarlo al usuario en el panel principal.
        document.getElementById("contenido-principal").innerHTML = formulario;
        
        // Una vez añadido el formulario al DOM mostramos los correspondientes datos del paciente 
        // obtenidos desde el servidor.
        document.getElementsByName("id")[0].value = id;
        document.getElementsByName("nombre")[0].value = nombre;
        document.getElementsByName("apellidos")[0].value = apellidos;
        document.getElementsByName("email")[0].value = email;
        document.getElementsByName("fecNacimiento")[0].value = fecNacimiento;
        document.getElementsByName("domicilio")[0].value = domicilio;
        document.getElementsByName("codPostal")[0].value = codPostal;
        document.getElementsByName("localidad")[0].value = localidad;
        document.getElementsByName("provincia")[0].value = provincia;
        document.getElementsByName("telefono")[0].value = telefono;
        document.getElementsByName("estadoCivil")[0].value = estadoCivil;
        
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
		
		// Se cambia el estilo de la opción de menú paciente elegida (perfil), para mostrarla como activa.
		document.querySelector("#menu-paciente li:nth-child(1)").style.backgroundColor = "#f0f0f0";
	});
}

