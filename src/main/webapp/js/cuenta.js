
function cuenta() {

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
        /*let formulario = "<form id='perfil' action='ObtenerPaciente' method='post'>";
        formulario += "<table>";
        formulario += "<tr><td><label>Nombre</label><input name='nombre' type='text' size='30' disabled></td>";
        formulario += "<td><label>Apellidos</label><input name='apellidos' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Email</label><input name='email' type='email' size='30' disabled placeholder='ejemplo@hotmail.com'></td>";
        formulario += "<td><label>Fecha de nacimiento</label><input name='fecNacimiento' id='fecNacimiento' disabled type='date' size='30'></td></tr>";
        formulario += "<tr><td><label>Domicilio</label><input name='domicilio' type='text' size='30' disabled></td>";
        formulario += "<td><label>Código postal</label><input name='cPostal' type='number' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Localidad</label><input name='localidad' type='text' size='30' disabled></td>";
        formulario += "<td><label>Provincia</label><input name='provincia' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td><label>Teléfono</label><input name='teléfono' type='number' size='30' disabled></td>";
        formulario += "<td><label>Estado civil</label><input name='estadoCivil' type='text' size='30' disabled></td></tr>";
        formulario += "<tr><td colspan='2'><input id='editar' class='boton' type='' value='Editar'>";
        formulario += "<input id='enviar' class='boton' type='submit' value='Enviar'></td></tr>";
        formulario += "</table>";
        formulario += "</form>";
    
        document.getElementById("contenido-principal").innerHTML += formulario;
        
        document.getElementsByName("nombre")[0].value = nombre;
        document.getElementsByName("apellidos")[0].value = apellidos;
        document.getElementsByName("fecNacimiento")[0].value = fecNacimiento;
        document.getElementsByName("email")[0].value = email;
        document.getElementsByName("direccion")[0].value = direccion;
        document.getElementsByName("telefono")[0].value = telefono;
        document.getElementsByName("estadoCivil")[0].value = estadoCivil; 
        
        document.getElementById("editar").addEventListener('click', function() {
		
			let camposTexto = document.querySelectorAll("#perfil input");
			for(let i = 0; i < camposTexto.length; i++) {
				camposTexto[i].disabled = false;
			}
		}); */
		
		document.querySelector("#menu-paciente li:nth-child(2)").style.backgroundColor = "#f0f0f0";
	});


}

