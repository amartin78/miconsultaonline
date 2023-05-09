
document.getElementById("nombre").addEventListener("blur", function() {
	
	if(!esTexto(this)) {
		alert("Debe de introducir texto en el campo Nombre");
	}
});

document.getElementById("apellidos").addEventListener("blur", function() {
	
	if(!esTexto(this)) {
		alert("Debe de introducir texto en el campo Apellidos");
	}
});

document.getElementById("fecNacimiento").addEventListener("blur", function() {
	
	validarFechaNacimiento(this);
});

document.getElementById("password").addEventListener("blur", function() {
	
	validarContrasenia();
});

function esTexto(input) {
	
	let texto = input.value;
	
	for(let i = 0; i < texto.length; i++) {
		
		if(!/[A-Za-zÀ-ÖØ-öø-ÿ\s]/.test(texto[i])) {
			return false;
		}
	}
	return true;
}

function validarFechaNacimiento(input) {
	
	let fechaNacimiento = new Date(input.value);
	let momentoActual = Date.now();
	let diferencia = momentoActual - fechaNacimiento;
	
	if(diferencia <= 0) {
		alert("La fecha de nacimiento debe ser menor o igual a la fecha actual");
	}
}

function validarContrasenia() {
	
	let charContrasenia;
	totalLetras = 0;
	totalNumeros = 0;
	totalCharEspeciales = 0;  // Es un caracter especial
	totalMinusculas = 0;
	totalMayusculas = 0;
	esLetra = false;
	charsEspeciales = '[`!@#$%^&*()_+-=[]{};\':"\\|,.<>/?~]/';  
	let contrasenia = document.getElementById("password").value;
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
	}
	
	if(tamanio < 8) {
		alert("Su contraseña debe contener al menos 8 caracteres.");
	} else if(totalLetras < 2) {
		alert("Su contraseña debe contener al menos 2 letras.");		
	} else if(totalMinusculas < 1) {
		alert("Su contraseña debe contener al menos 1 letra minúscula.");
	} else if(totalMayusculas < 1) {
		alert("Su contraseña debe contener al menos 1 letra mayúscula.");
	} else if(totalNumeros < 1) {
		alert("Su contraseña debe contener al menos 1 número.");		
	} else if(totalCharEspeciales < 1) {
		alert("Su contraseña debe contener al menos 1 caracter especial.");
	} else {
		console.log("Contraseña válida");
	}
}

