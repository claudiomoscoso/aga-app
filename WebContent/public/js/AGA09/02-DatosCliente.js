var http = getHttpRequest();

function buscarCliente() {
	var rut = document.getElementById("RUT").value;
	var url = "bsas?bsServiceName=AGA.GetCliente&RUT=" + rut;
	url += "&ID=" + document.getElementById("ID").value;
	url += "&r=" + Math.random();

	http.onreadystatechange = llegaCliente;
	http.open("GET", url, true);
	http.send(null);

//	var customer = http.responseXML;
	// alert(http.responseText);

}

function llegaCliente() {
	if (http.readyState === 4) {
		var customer = http.responseXML;
		
		if(customer.selectSingleNode("/Service/Request/Fields/RUT").text.toUpperCase() == 'SS'){
			alert('Se especifiqua que el cliente no esta registrado');
		}
		
		if (haveError(customer)) {
			showError(customer);
		} else {
			//alert(http.responseText);
			
			var browser = new BSbrowser();
			if (browser.ie) {
				document.getElementById('NombreCliente').value = "";
				document.getElementById('NombreContacto').value = "";
				
				var value = customer
						.selectSingleNode("/Service/Response/Fields/GetCliente/Record/cNombre").text;
				if (value == 'unknow') {
					value = "";
				}
				document.getElementById('NombreCliente').value = value;

				value = customer
						.selectSingleNode("/Service/Response/Fields/GetCliente/Record/cContacto").text;
				if (value == 'unknow') {
					value = "";
				}

				document.getElementById('NombreContacto').value = value;

				value = customer.selectSingleNode("/Service/Response/Fields/GetCliente/Record/cExiste").text;
				//alert(value);
				if(value == "0"){
					alert("Cliente no encontrado");
				}

				/**
				if (customer
							.selectNodes("/Service/Response/Fields/GetCliente/Record").length > 0) {
				} else {
					alert("Cliente no encontrado");
				}
				*/
			} else {
				alert("Maybe don't work with firefox");
				var element = selectNodes(customer,
						"/Service/Response/Fields/GetCliente/Record");

				if (element.length > 0) {
					element = element[0];
					document.getElementById('NombreCliente').value = getText(element
							.getElementsByTagName('cNombre')[0]);
					document.getElementById('NombreContacto').value = getText(element
							.getElementsByTagName('cContacto')[0]);
				} else {
					document.getElementById('NombreCliente').value = "";
					document.getElementById('NombreContacto').value = "";
					alert("Cliente no encontrado");
				}
			}
		}
	}
}

function updateContacto(obj) {
	callURL("AGA.UpdateContacto", "Contacto", obj.value);
	return;
}


