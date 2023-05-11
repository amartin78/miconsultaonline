
function esTexto(input) {
	
	let texto = input.value;
	
	for(let i = 0; i < texto.length; i++) {
		
		if(!/[A-Za-zÀ-ÖØ-öø-ÿ\s]/.test(texto[i])) {
			return false;
		}
	}
	return true;
}

function esFechaNacimientoValida(input) {
	
	let fechaNacimiento = new Date(input.value);
	let momentoActual = Date.now();
	let diferencia = momentoActual - fechaNacimiento;
	
	if(diferencia <= 0) {
		return false;
	}
	return true;
}

// Este método evalúa que la contrasña recibida como parámetro sea válida, devuelve 
// un mensaje de error o false en el supuesto de no haber ningún error de validación
function validarContrasenia(input) {
	
	let mensaje;
	let charContrasenia;
	totalLetras = 0;
	totalNumeros = 0;
	totalCharEspeciales = 0;  // Es un caracter especial
	totalMinusculas = 0;
	totalMayusculas = 0;
	esLetra = false;
	charsEspeciales = '[`!@#$%^&*()_+-=[]{};\':"\\|,.<>/?~]/';  
	let contrasenia = input.value;
	let tamanio = contrasenia.length;
	
	for(let i = 0; i < contrasenia.length; i++) {
		
		// Almacenamos la letra que se valida
		charContrasenia = contrasenia.charAt(i);
		
		// El caracter es una letra
		esLetra = charContrasenia.toLowerCase() != charContrasenia.toUpperCase();
		if(esLetra) {
			totalLetras++;			
		}
		// El caracter es un número
		if(!(isNaN(charContrasenia))) {
			totalNumeros++;
			esLetra = false;		
		}
		// El caracter es especial
		if(charsEspeciales.includes(charContrasenia)) {
			totalCharEspeciales++;
			esLetra = false;		
		}
		// El caracter es minúscula
		if(esLetra && (charContrasenia.toLowerCase() === charContrasenia)) {
			totalMinusculas++;
		}
		// El caracter es mayúscula
		if(esLetra && (charContrasenia === charContrasenia.toUpperCase())) {
			totalMayusculas++;
		}
	} // Fin for loop
	
	if(tamanio < 8) {
		mensaje = " al menos 8 caracteres";
		return mensaje;
	} else if(totalLetras < 2) {
		mensaje = " al menos 2 letras";
		return mensaje; 	
	} else if(totalMinusculas < 1) {
		mensaje = " al menos 1 letra minúscula";
		return mensaje; 
	} else if(totalMayusculas < 1) {
		mensaje = " al menos 1 letra mayúscula";
		return mensaje; 
	} else if(totalNumeros < 1) {
		mensaje = " al menos 1 número";
		return mensaje; 	
	} else if(totalCharEspeciales < 1) {
		mensaje = " al menos 1 caracter especial";
		return mensaje; 
	} else {
		console.log("Contraseña válida");
		return false;
	}
}

// Devuelve true solo en el caso de que la contraseña repetida sea distinta a la nueva contraseña
function esContraseniaDistinta() {
	
	let nuevaContrasenia = document.getElementById("nuevaContrasenia").value;
	let contraseniaRepetida = document.getElementById("contraseniaRepetida").value;
	
	if(nuevaContrasenia !== contraseniaRepetida) {
		return true;
	}
	return false;
}

function esCodigoPostal(input) {
	
	let codigoPostal = input.value;
	
	if(codigoPostal.length != 5) {
		return false;
	}
	return true;
}

// Devuelve un mensaje de error en caso de que el número de teléfono recibido como parámetro
// sea inválido, devuelve falso en caso de no haberse encontrado ningún error en la validación
function validarNumeroTelefonico(input) {
	
	let mensaje;
	let numeroTelefono = input.value;
	let primerDigito = numeroTelefono.substring(0, 1);
	
	if(primerDigito == 0) {	// No es necesario introducir el código de país
		mensaje = "El primer dígito no puede ser cero \n";
		return mensaje;
	} else if(numeroTelefono.length != 9) {	// Acepta unicamente números nacionales fijos o móvil
		mensaje = "Debe de introducir un número telefónico de 9 dígitos \n";
		return mensaje;
	} else {
		console.log("Teléfono válido");
		return false;
	}
}

