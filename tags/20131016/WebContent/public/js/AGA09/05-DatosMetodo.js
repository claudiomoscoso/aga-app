function cambiaPatron() {
	var currentPatron = document.getElementById("Patron").value;
	var cat1 = currentPatron.indexOf('#');
	var cat2 = currentPatron.lastIndexOf('#');
	var id = currentPatron.substr(0, cat1);
	var cilindro = currentPatron.substr(cat1 + 1, cat2 - cat1 - 1);
	var fecha = currentPatron.substr(cat2 + 1);

	document.getElementById("CilindroPatron").value = cilindro;
	document.getElementById("VencimientoPatron").value = fecha;
}

function updateMetodo(obj) {
	var url = 'Metodo=' + obj.value;
	url += '&Enable=' + (obj.checked ? 1 : 0);
	url += '&ID=' + document.getElementById("ID").value;
	url += "&r=" + Math.random();
	
	http.onreadystatechange = genericHandler;
	callURLparams('AGA.UpdateMetodo', url, http);
}

/**
function updateNumeroPatron(obj){
	callURL("AGA.UpdateNumeroPatron", "NumeroPatron", obj.value);
}
*/

function addPatronSolicitud(){
	var listPatron = document.getElementById("Patron");
	var idPatron = document.getElementById("Patron").value;
	
	var url = 'Patron=' + getIdPatron(document.getElementById("Patron"));
	url += '&ID=' + document.getElementById("ID").value;
	url += "&r=" + Math.random();
	
	addOption( document.getElementById("Patrones"), idPatron, listPatron.options[listPatron.selectedIndex].text);
	listPatron.remove(listPatron.selectedIndex);
		
	http.onreadystatechange = genericHandler;
	callURLparams('AGA.AddPatronSolicitud', url, http);
}

function delPatronSolicitud(){
	var listPatron = document.getElementById("Patrones");
	if (listPatron.selectedIndex == -1) {
		alert('No se ha seleccionado un elemento de la lista');
	} else {
		var url = 'Patron=' + getIdPatron(listPatron);
		url += '&ID=' + document.getElementById("ID").value;
		url += "&r=" + Math.random();

		addOption(document.getElementById("Patron"), document.getElementById("Patrones").value,	
					listPatron.options[listPatron.selectedIndex].text);
		// alert(listPatron.value);
		listPatron.remove(listPatron.selectedIndex);

		http.onreadystatechange = genericHandler;
		callURLparams('AGA.DelPatronSolicitud', url, http);
	}
}

function updatePatron(obj){
	var id = getIdPatron(document.getElementById("Patron"));
	callURL("AGA.UpdatePatron", "Patron", id);
}

function getIdPatron(combo){
	var currentPatron = combo.value;
	var cat1 = currentPatron.indexOf('#');
	var id = currentPatron.substr(0, cat1);
	return id;	
}

function cargaPatron() {
	var patronSelect = document.getElementById("Patron");
	var patronesSelect = document.getElementById("Patrones");
	
	for(var i in patrons){
		patronSelect.value = patrons[i];
		addOption(document.getElementById("Patrones"), patrons[i], patronSelect.options[patronSelect.selectedIndex].text);
		patronSelect.remove(patronSelect.selectedIndex);
	}
}
