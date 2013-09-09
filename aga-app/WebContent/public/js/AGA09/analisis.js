var currentValue = null;
function onLoadPage() {
	imposeMaxLength(document.getElementById("Comentario"), 100);
	//cargaTipoProducto();
	cambiaPatron();
	cargaTipoCilindro();
	calculaVolumenGas();
	cargaPatron();
}

function callURL(service, key, newValue) {
	disableButtons(true);
	
	var id = document.getElementById("ID").value;
	// var newValue = obj.value;
	if (currentValue != newValue) {
		var url = "bsas?bsServiceName=" + service;
		url += "&ID=" + id;
		url += "&" + key + "=" + newValue;
		url += "&r=" + Math.random();

		http.onreadystatechange = genericHandler;
		http.open("GET", url, true);
		http.send(null);
	}
}

function genericHandler() {
	if (http.readyState === 4) {
		var customer = http.responseXML;
		if (haveError(customer)) {
			showError(customer);
		}
	}
}

function callURLparams(service, params, localHttp) {
	disableButtons(true);
	var url = "bsas?bsServiceName=" + service;
	url += "&" + params;
	url += "&r=" + Math.random();
	
	localHttp.open("GET", url, true);
	localHttp.send(null);
}
