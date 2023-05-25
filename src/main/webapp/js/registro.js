
let mensajeRecibido = ""; // Esta variable almacena el valor del método validarContrasenia(campo) al ser invocado
let mensajeError = ""; // Mensaje con lista de errores de los campos del formulario registro para mostrarlos por pantalla 
let contadorErrores = 0;
let contadorCamposVacios = 0;
	
// Antes de enviar el formulario se realiza una serie de validaciones sobre los campos de éste 
// para decidir si se corrigen o se envían al servidor en caso de estar todos correctos
document.getElementById("enviar").addEventListener("click", function(event) {
 
	// Se evalúa cada campo del formulario registro
	document.querySelectorAll(".registro").forEach(function(campo) {
		
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
			case "fecNacimiento":
				if(campo.value.length == 0) {
					mensajeError += "Debe de introducir su fecha de nacimiento \n";
				} else if(!esFechaNacimientoValida(campo)) {
					mensajeError += "La fecha de nacimiento debe ser menor o igual a la fecha actual \n";
					contadorErrores++;
				}
				break;
			case "email":
				if(campo.value.length == 0) {
					mensajeError += "Debe de introducir un email \n";
				} 
				break;
			case "password":
				mensajeRecibido = validarContrasenia(campo);
				if(campo.value.length == 0) {
					mensajeError += "Debe de introducir una contraseña \n";
				} else if(mensajeRecibido) {
					mensajeError += "La contraseña actual debe tener " + mensajeRecibido + "\n";
					contadorErrores++;
				}
				break;
			default:
				console.log("Campo no definido");
		}
	});	// Fin forEach loop
	
	// Se cuenta el número de campos vacíos y se almacena en la variable contadorCamposVacios
	document.querySelectorAll(".registro").forEach(function(campo) {
		
		if(campo.value.length == 0) {					
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
});

