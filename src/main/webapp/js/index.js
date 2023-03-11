window.onload = function () {
    const e = document.createElement("h1");
    document.getElementById("contenido-principal").appendChild(e).innerText = "Perfil";
};

function opcion(o) {
    let nombreOpcion = o.innerText;
    document.getElementById("contenido-principal").
             getElementsByTagName("h1")[0].innerText = nombreOpcion;
    switch(nombreOpcion) {
        case "Perfil":
            perfil();
            break;
        case "Diagnóstico":
            diagnostico();
            break;
        case "Analítica":
            analitica();
            break;
        case "Medicación":
            medicacion();
            break;
        case "Recetas":
            receta();
            break;
        case "Citas":
            cita();
            break;
        default:
            console.log("Opción incorrecta");
    }
}

function perfil() {

}

function diagnostico() {
    
    let contenidoPrincipal = document.getElementById("contenido-principal").innerHTML;
    let datosDiagnostico = [["Dolor muscular", "04/09/2021"], 
                            ["Grano en la cara", "24/11/2021"]];
    let tablaDiagnostico = "<table border='1'>";
    tablaDiagnostico += "<tr><th>Anomalía</th><th>Fecha consulta</th></tr>";
    for(let i = 0; i < datosDiagnostico.length; i++) {
        tablaDiagnostico += "<tr><td>" + datosDiagnostico[i][0] + "</td>" + 
                                "<td>" + datosDiagnostico[i][1] + "</td></tr>";
    }
    tablaDiagnostico += "</table>";
    if(contenidoPrincipal.indexOf("table") == -1) {
        document.getElementById("contenido-principal").innerHTML += tablaDiagnostico;
    }
}

function analitica() {
    
}

function medicacion() {
    
}

function receta() {
    
}

function cita() {
    
}