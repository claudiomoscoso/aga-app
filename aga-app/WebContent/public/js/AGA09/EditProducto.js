var currentValue = null;
var http = getHttpRequest();
function onLoadPage() {
}

function callURL(service, key, newValue) {
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

function updateTipoProducto(obj) {
	callURL("AGA.UpdateTipoProductoPrd", "TipoProducto", obj.value);
	return;
}

function showDetail(){
	showTooltip("detail");
}

function saveNewDetail(){
	document.getElementById("IDproducto").value = document.getElementById("ID").value;	
	document.getElementById("Relleno").value = document.getElementById("RellenoCheckbox").checked ? 1 : 0;
	document.getElementById("fSaveRelation").submit();
}


function confirmDelete(id, name){
	//alert(id);
	if(confirm('Seguro de borrar el componente "' + name + '"?')){
		document.getElementById("IDcomponent").value = id;
		document.getElementById("f").submit();
	}		
}

function updateEstabilidadGarantizada(obj){
	callURL("AGA.UpdateEstabilidadGarantizada", "EstabilidadGarantizada", obj.value);
	return;
}

function updateExpiracion(obj){
	callURL("AGA.UpdateExpiracionProd", "Expiracion", obj.value);
	return;	
}

function updateMetodoPreparacion(obj){
//	alert(obj.value);
	callURL("AGA.UpdateMetodoPreparacionPrd", "MetodoPreparacion", obj.value);
	return;
}
