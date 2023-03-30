
function perfil() {

    limpiarContenidoPrincipal();

    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		let nombre = paciente["nombre"];
        let apellidos = paciente["apellidos"];
        let fecNacimiento = paciente["fecNacimiento"];
        fecNacimiento = new Date(fecNacimiento);
        // console.log(fecNacimiento);
        let anio = fecNacimiento.getFullYear();
        let mes = fecNacimiento.getMonth() + 1;
        mes = String(mes).length == 2 ? mes : `0${mes}`;
        let dia = fecNacimiento.getDate();
        dia = String(dia).length == 2 ? dia : `0${dia}`;
        fecNacimiento = anio + "-" + mes + "-" + dia;
        let email = paciente["email"];
        let direccion = paciente["direccion"];
        let telefono = paciente["telefono"];
        let estadoCivil = paciente["estadoCivil"];
        
        // Servlet ObtenerPaciente devuelve paciente (doGet) o modifica el paciente (doPost)
        let formulario = "<form id='perfil' action='ObtenerPaciente' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Nombre</label><input name='nombre' type='text' size='35'></td></tr>";
        formulario += "<tr><td><label>Apellidos</label><input name='apellidos' type='text' size='35'></td></tr>";
        formulario += "<tr><td><label>Fecha de nacimiento</label><input name='fecNacimiento' id='fecNacimiento' type='date' size='35'></td></tr>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='35' placeholder='ejemplo@hotmail.com'></td></tr>";
        formulario += "<tr><td><label>Dirección</label><input name='dirección' type='text' size='35'></td></tr>";
        formulario += "<tr><td><label>Teléfono</label><input name='teléfono' type='number' size='35'></td></tr>";
        formulario += "<tr><td><label>Estado civil</label><input name='estadoCivil' type='text' size='35'></td></tr>";
        formulario += "<tr><td><input id='enviar' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "</form>";
    
        document.getElementById("contenido-principal").innerHTML += formulario;
        
        document.getElementsByName("nombre")[0].value = nombre;
        document.getElementsByName("apellidos")[0].value = apellidos;
        document.getElementsByName("fecNacimiento")[0].value = fecNacimiento;
        document.getElementsByName("email")[0].value = email;
        // document.getElementsByName("direccion")[0].value = direccion;
        // document.getElementsByName("telefono")[0].value = telefono;
        // document.getElementsByName("estadoCivil")[0].value = estadoCivil;
	});


}

