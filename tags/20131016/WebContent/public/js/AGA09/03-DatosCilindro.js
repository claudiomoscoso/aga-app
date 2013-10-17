function cargaTipoCilindro() {
	document.getElementById("TipoCilindro").value = tipoCilindro;
	document.getElementById("Valvula").value = valvula;
}

function updateTipoCilindro(obj) {
	callURL("AGA.UpdateTipoCilindro", "TipoCilindro", obj.value);
	return;
}

function updateLitros(obj) {
	callURL("AGA.UpdateLitros", "Litros", obj.value);
	calculaVolumenGas();
	return;
}

function updatePoLlenado(obj) {
	callURL("AGA.UpdatePoLlenado", "PoLlenado", obj.value);
	calculaVolumenGas();
	return;
}

function calculaVolumenGas() {
	var litros = document.getElementById("Litros").value;
	var poLlenado = document.getElementById("PoLlenado").value;
	var total = (litros * poLlenado) / 1000;
	document.getElementById("VolumenGas").value = total;
	if(!usaLote){
		document.getElementById("Volumen").value = total;
	}
}

function updateValvula(obj) {
	callURL("AGA.UpdateValvula", "Valvula", obj.value);
}

function updateExpiracion(obj) {
	callURL("AGA.UpdateExpiracion", "Expiracion", obj.value);
}

function updateTMinUso(obj) {
	callURL("AGA.UpdateTMinUso", "TMinUso", obj.value);
}

function updatePMinUso(obj) {
	callURL("AGA.UpdatePMinUso", "PMinUso", obj.value);
}

function getCilindro() {
	return allTrim(document.getElementById("Cilindro").value);
}
function getVolumen() {
	return allTrim(document.getElementById("Volumen").value);
}

function addLote() {
	// var listLote = document.getElementById("ListLote");
	var key = null;
	var desc = null;

	if (getCilindro().length == 0) {
		alert('No se ha especificado Cilindro');
		return;
	}
	if (getVolumen().length == 0) {
		alert('No se ha especificado Volumen');
		return;
	}

	http.onreadystatechange = getLoteId;
	callURLparams("AGA.AddLote", "Solicitud="
			+ document.getElementById("ID").value + "&Cilindro="
			+ getCilindro() + "&Volumen=" + getVolumen(), http);
}

function getLoteId() {
	if (http.readyState === 4) {
		var doc = http.responseXML;
		if (haveError(doc)) {
			showError(doc);
		} else {
			var listLote = document.getElementById("ListLote");
			var key = doc.selectSingleNode("/Service/Response/Fields/AddLote").text;
			var desc = getCilindro() + " - " + getVolumen();

			addOption(listLote, key, desc);

		}
	}
}

function delLote() {
	var listCilindro = document.getElementById("ListLote"); //

	if (listCilindro.selectedIndex == -1) {
		alert('No se ha seleccionado un elemento de la lista');
	} else {
		http.onreadystatechange = genericHandler;
		callURLparams("AGA.DelLote", "Lote="
				+ listCilindro.options[listCilindro.selectedIndex].value, http);

		listCilindro.remove(listCilindro.selectedIndex);
	}
}

function selectListLote() {
//	alert(1);
	if (!usaLote) {
		var listCilindro = document.getElementById("ListLote");
		if (listCilindro.selectedIndex >= 0) {
			var valueList = listCilindro.options[listCilindro.selectedIndex].text;
			var cat = valueList.indexOf(' - ', 0);
			var cilindro = valueList.substr(0, cat);
			var volumen = valueList.substr(cat + 3);

			document.getElementById("Cilindro").value = cilindro;
			document.getElementById("Volumen").value = volumen;
		}
	}
}

var clear_all = 0;
function loadCilindros(){
	var lotes = document.getElementById("LotesFO");
	if(lotes.selectedIndex >= 0) {
//		alert(lotes.options[lotes.selectedIndex].value);
		var params = "Lote=" + lotes.options[lotes.selectedIndex].value + "&";
		params += "Solicitud=" + document.getElementById("ID").value;
		
		clearCombo(document.getElementById("ListLote"), clear_all);
		addOption(document.getElementById("ListLote"), "", "Cargando...");
		document.getElementById("BuscarCilindros").disabled = true;
		
		http.onreadystatechange = loadCilindrosFromServer;
		callURLparams("AGA.loadCilindros", params, http);
		
	} else {
		alert('Tiene que seleccionar un numero de lote');
	}
	//http.onreadystatechange = getLoteId;
}

function loadCilindrosFromServer() {
	if (http.readyState === 4) {
		var doc = http.responseXML;
		if (haveError(doc)) {
			showError(doc);
		} else {
			
			var listLote = document.getElementById("ListLote");
			var cilindros = doc.selectNodes("/Service/Response/Fields/LoadCilindros/Record");
			var key = null;
			var desc = null;

			clearCombo(document.getElementById("ListLote"), clear_all);

			for(var i=0;i!=cilindros.length;i++){
				key = cilindros.item(i).selectSingleNode("cID").text;
				desc = cilindros.item(i).selectSingleNode("cCilindro").text + " - " + cilindros.item(i).selectSingleNode("cVolumen").text ;
				//alert(i + " - " + cilindros.item(i).selectSingleNode("cCilindro").text);
				addOption(listLote, key, desc);
			}
			document.getElementById("BuscarCilindros").disabled = false;

//			var key = doc.selectSingleNode("/Service/Response/Fields/LoadCilindros").text;
//			var desc = getCilindro() + " - " + getVolumen();
//

		}
	}
}


