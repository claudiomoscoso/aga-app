function openDialogComponent(index, id) {
	var desviacionRelativa = 0;
	var analisis = 0;
	showTooltip('divEditComponent', document.getElementById("tableAnalisis").offsetTop);

	var url = "bsas?bsServiceName=AGA.GetAnalisisComponent&ID=" + id;
	url += "&r=" + Math.random();

	var http = getHttpRequest();
	http.open("GET", url, false);
	http.send(null);

	var analisis = http.responseXML;

	var browser = new BSbrowser();
	if (browser.ie) {
		
		var record = analisis.selectSingleNode("/Service/Response/Fields/GetAnalisisComponent/Record");
		desviacionRelativa = record.selectSingleNode("cDesviacionRelativa").text;
		analisis = record.selectSingleNode("cAnalisis").text;
		document.getElementById("LabelNombreComponente").innerHTML = record.selectSingleNode("cNombre").text;
		document.getElementById("LabelSiglaComponente").innerHTML = record.selectSingleNode("cSigla").text;
		document.getElementById("Requerido").value = record.selectSingleNode("cRequerido").text;
		document.getElementById("AnalisisComponente").value = analisis;
		document.getElementById("LabelUnidad").innerHTML = record.selectSingleNode("cUnidad").text;
		document.getElementById("DesviacionRelativa").value = desviacionRelativa;

		document.getElementById("DesviacionAbsoluta").value = (desviacionRelativa / 100) * analisis;
		 // (cDesviacionRelativa div 100) * cAnalisis
	} else {
		alert("Only for IE8+");
		return;
	}


	// alert(http.responseText);

	// alert(nombre);
	// document.getElementById("LabelNombreComponente").innerHTML = nombre;
	// document.getElementById("LabelSiglaComponente").innerHTML = sigla;
	// alert(document.getElementById("tableAnalisis").style.top);

//	document.getElementById('TOOLTIP_CONTENT').style.top = 700; // document.getElementById("tableAnalisis").style.top;
	document.getElementById("Requerido").focus();
	document.getElementById("indexOfTable").value = index;
	document.getElementById("indexID").value = id;
}

function saveAnalisisComponent() {
	var id = document.getElementById("indexID").value;
	var requerido = document.getElementById("Requerido").value;
	var analisis = document.getElementById("AnalisisComponente").value;
	var desviacionRelativa = document.getElementById("DesviacionRelativa").value;

	var url = "bsas?bsServiceName=AGA.SaveAnalisisComponent";
	url += "&ID=" + id;
	url += "&Requerido=" + requerido;
	url += "&Analisis=" + analisis;
	url += "&DesviacionRelativa=" + desviacionRelativa;
	url += "&r=" + Math.random();
	
	var http = getHttpRequest();
	http.open("GET", url, false);
	http.send(null);

	var customer = http.responseXML;
	
	if (haveError(customer)) {
		showError(customer);
	} else {
		var index = document.getElementById("indexOfTable").value;
		var table = document.getElementById("tableAnalisis");

		if (index == -1) {
			index = table.rows.length - 1;
		}

		table.rows[index].cells[2].innerHTML = requerido;
		table.rows[index].cells[3].innerHTML = analisis;
		table.rows[index].cells[5].innerHTML = desviacionRelativa;
		table.rows[index].cells[6].innerHTML = calculo(desviacionRelativa, analisis);

		closeTooltip();
	}
}

function calculaDesvAbs(){
	var desviacionAbsoluta = document.getElementById("DesviacionAbsoluta");
	var analisisComponente = document.getElementById("AnalisisComponente").value;
	var desviacionRelativa = document.getElementById("DesviacionRelativa").value
	
	desviacionAbsoluta.value = calculo(desviacionRelativa, analisisComponente);
}

function calculo(desviacionRelativa, analisisComponente){
	return (desviacionRelativa / 100) * analisisComponente;
}

function updateToleranciaPreparacion(obj){
	callURL("AGA.UpdateToleranciaPreparacion", "ToleranciaPreparacion", obj.value);
}
function updateNivelConfianza(obj){
	callURL("AGA.UpdateNivelConfianza", "NivelConfianza", obj.value);
}
