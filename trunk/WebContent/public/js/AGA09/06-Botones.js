function addEtiqueta(obj){
//	var analisis = document.getElementById("analisis").value;
	var analisis = document.getElementById("ID").value;
	
	http.onreadystatechange = handlerEtiqueta;
	callURLparams("AGA.AddEtiqueta", "Numero=" + analisis, http);	
	return;
}

function handlerEtiqueta(){
	if (http.readyState === 4) {
		var customer = http.responseXML;
		if (haveError(customer)) {
			showError(customer);
		}else{
			alert("Etiqueta lista!");
		}
	}	
}

function validarAnalisis(){
	var url = 'ID=' + document.getElementById("ID").value;
	url += "&r=" + Math.random();
	
	http.onreadystatechange = enableButtons;
	callURLparams('AGA.ValidateAnalisis', url, http);
	
}

function enableButtons(){
	if (http.readyState === 4) {
		var doc = http.responseXML;
		if (haveError(doc)) {
			showError(doc);
		} else {
			var msg = doc.selectSingleNode("/Service/Response/Fields/ValidateAnalisis/Record/msg").text;
			if(msg == ''){
				disableButtons(false);
				document.getElementById("imprimirEtiqueta").disabled = document.getElementById('GasPuro').value == 1;
			} else {
				disableButtons(true);
				alert(msg);
			}
//			alert(document.getElementById('GasPuro').value == 1);
			
		}
	}	
}

function disableButtons(disable){
	document.getElementById("imprimirCertificado").disabled = disable;
	document.getElementById("imprimirEtiqueta").disabled = disable;	
}

function printCertificado(gaspuro, id){
	var url = '';
	if(gaspuro){
		url = 'PdfServletGas?id='+id;
	}else{
		url = 'PdfServlet?id='+id;
	}
	url += "&r=" + Math.random();
	self.location.href = url;
}
