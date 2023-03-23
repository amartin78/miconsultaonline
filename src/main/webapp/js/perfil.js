
function perfil() {

    limpiarContenidoPrincipal();

    fetch('ObtenerPaciente').
	then(response => response.json()).
	then(paciente => {
		let nombre = paciente["nombre"];
        let apellidos = paciente["apellidos"];
        let fecNacimiento = paciente["fecNacimiento"];
        console.log("fecNacimiento es " + fecNacimiento);
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
        document.getElementsByName("direccion")[0].value = direccion;
        document.getElementsByName("telefono")[0].value = telefono;
        document.getElementsByName("estadoCivil")[0].value = estadoCivil;
	});


}





// Form registro

{/* <table>
    <tr>
        <td>
            <label>Nombre</label>
            <input name="nombre" type="text" size="30">
        </td>
    </tr>
    <tr>
        <td>
            <label>Apellidos</label>
            <input name="apellidos" type="text" size="30">
        </td>
    </tr>
    <tr>
        <td>
            <label>Fecha de nacimiento</label>
            <input name="fecNacimiento" id="fecNacimiento" type="date">
        </td>
    </tr>
    <tr>
        <td>
            <label>Email</label>
            <input name="email" type="email" size="30" placeholder="ejemplo@hotmail.com">
        </td>
    </tr>
    <tr>
        <td>
            <label>Contraseña</label>
            <input name="password" size="30" type="password">
        </td>
    </tr>
    <tr>
        <td><input id="enviar" type="submit" value="Enviar"></td>
    </tr>
    </table> */}


// Diagnostico

// let tablaDiagnostico = "<table border='1'>";
// tablaDiagnostico += "<tr><th>Anomalía</th><th>Fecha consulta</th></tr>";
// for(let i = 0; i < datosDiagnostico.length; i++) {
//     tablaDiagnostico += "<tr><td>" + datosDiagnostico[i][0] + "</td>" + 
//                             "<td>" + datosDiagnostico[i][1] + "</td></tr>";
// }
// tablaDiagnostico += "</table>";
// if(contenidoPrincipal.indexOf("table") == -1) {
//     document.getElementById("contenido-principal").innerHTML += tablaDiagnostico;
// }