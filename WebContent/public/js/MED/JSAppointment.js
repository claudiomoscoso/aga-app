function cambiaEspecialidad() {
	var ajax = new BSServiceData();

	ajax.setServiceName("MED.GetInstitutionByEspeciality");
	ajax.addRequestField("fldSpeciality", document.getElementById("Speciality").value);
	ajax.send();

	var institutions = ajax.getResponseNode("GetInstitutionByEspeciality");
	
	var code = null;
	var name = null;

	var combo = document.getElementById("Institution");
	clearCombo(combo, 1);
	clearCombo(document.getElementById("Institution"), 1);
	clearCombo(document.getElementById("Location"), 1);
		
	for(var i=0 ; i < institutions.length ; i++){	
		code = getText(institutions.item(i).getElementsByTagName("ID")[0]);
		name = getText(institutions.item(i).getElementsByTagName("Name")[0]);
		
		addOption(combo, code, name);
	}
	
}

/*
function responseInstitution(r){
	fillCombo(r, 'GetInstitutionByEspeciality', 'Institution');
}
*/

function cambiaInstitution(){
	var ajax = new BSServiceData();

	ajax.setServiceName("MED.GetLocationByInstitution");
	ajax.addRequestField("fldInstitution", document.getElementById("Institution").value);
	ajax.send();

	var institutions = ajax.getResponseNode("GetLocationByInstitution");
	
	var code = null;
	var name = null;

	var combo = document.getElementById("Location");
	clearCombo(combo, 1);
//	clearCombo(document.getElementById("Institution"), 1);
	clearCombo(document.getElementById("Location"), 1);
		
	for(var i=0 ; i < institutions.length ; i++){	
		code = getText(institutions.item(i).getElementsByTagName("ID")[0]);
		name = getText(institutions.item(i).getElementsByTagName("Name")[0]);
		
		addOption(combo, code, name);
	}

/*
	var opt = {onComplete: responseLocation};
	var institution = $F('Institution');
	$('fldInstitution').value = institution;
	$('bsServiceName').value = 'MED.GetLocationByInstitution';
	$('f').request(opt);
*/
}

function cambiaLocation(){
	var ajax = new BSServiceData();

	ajax.setServiceName("MED.GetSchedule");

/*	
	alert(document.getElementById("Speciality").value);
	fldProfessional
	fldInstitution
	fldLocation
*/
	
	ajax.addRequestField("fldSpeciality", document.getElementById("Speciality").value);
	ajax.addRequestField("fldProfessional", "");
	ajax.addRequestField("fldInstitution", document.getElementById("Institution").value);
	ajax.addRequestField("fldLocation", document.getElementById("Location").value);
	ajax.send();


//	var service = new BSServiceData(r);	
	var professionals = ajax.getResponseNode('Professionals'); //[0].getElementsByTagName("Record");

	if(professionals!=null && professionals.length>0){
		professionals = professionals[0].childNodes;
		var len = professionals.length;
		var code = "";
		var desc = "";
		for(var i = 0 ; i < len ; i++){
			code = getText(professionals.item(i).attributes.getNamedItem("ID"));
			desc = getText(professionals.item(i).attributes.getNamedItem("Name"));
			
			addOption(document.getElementById("fldMedic"), code, desc);
		}
		
		showTooltip('divCalendar');		
	} else {
		alert("No se encontro informacion para la agenda");
	}
	
//alert(1);

			





/*
	var institutions = ajax.getResponseNode("GetLocationByInstitution");
	
	var code = null;
	var name = null;

	var combo = document.getElementById("Location");
	clearCombo(combo, 1);
	clearCombo(document.getElementById("Location"), 1);
		
	for(var i=0 ; i < institutions.length ; i++){	
		code = getText(institutions.item(i).getElementsByTagName("ID")[0]);
		name = getText(institutions.item(i).getElementsByTagName("Name")[0]);
		
		addOption(combo, code, name);
	}

	var opt = {onComplete: loadSchedule};
	$('fldLocation').value = $('Location').value;
	$('bsServiceName').value = 'MED.GetSchedule';
	$('f').request(opt);
*/
}



function buscaPaciente(){
	var rut = document.getElementById("RutPatient").value;
	var nombre = document.getElementById("NamePatient").value;
	
	if(rut.length!=0) {
		findByRut(rut + '%');
	} else {
		if(nombre.length!==0){
			findByName(nombre + '%');
		} else {
			alert('Tiene que especificar el RUT o el nombre');
		}
	}
}

/*
function loadSchedule(r){
	var service = new BSServiceData(r);	
	var professionals = service.getResponseNode('Professionals')[0].childNodes; //[0].getElementsByTagName("Record");

	var len = professionals.length;
	var code = "";
	var desc = "";
	for(var i = 0 ; i < len ; i++){
		code = getText(professionals.item(i).attributes.getNamedItem("ID"));
		desc = getText(professionals.item(i).attributes.getNamedItem("Name"));
	
		addOption($("fldMedic"), code, desc);
	}
	
	showTooltip($('divCalendar'));		
}
*/

function findByRut(rut){
alert(1);
	var ajax = new BSServiceData();
alert(2);	
	ajax.setServiceName("MED.FindPatientByRut");
alert(3);
	ajax.addRequestField("fldPatientRut", rut);
alert(4);
	ajax.send();
alert(5);


	var patients = ajax.getResponseNode('FindPatientByRut');
alert(6);	
	if(patients!=null){
		patients = patients[0].getElementsByTagName("Record");
	
		var id = null;
		var rut = null;
		var name = null;
	
		switch(patients.length){
			case 0:
				alert('No se encontraron Pacientes');
				break;
			case 1:
				id = patients.item(0).getElementsByTagName("ID")[0].textContent;
				rut = patients.item(0).getElementsByTagName("NationalNumber")[0].textContent;
				name = patients.item(0).getElementsByTagName("Name")[0].textContent;
				selectPatient(id, rut, name);
				
				break;
			default:
				displayPatients(patients);
				break;
		}	
	}
	
	
/*
	var opt = {onComplete : responseByRut};
	$('fldPatientRut').value = rut;
	$('bsServiceName').value = 'MED.FindPatientByRut';
	$('f').request(opt);
*/
}

function findByName(nombre){}

function responseLocation(r){
	fillCombo(r, 'GetLocationByInstitution', 'Location');
}

function responseByRut(r){
	var service = new BSServiceData(r);
	var patients = service.getResponseNode('FindPatientByRut')[0].getElementsByTagName("Record");

	var id = null;
	var rut = null;
	var name = null;

	switch(patients.length){
		case 0:
			alert('No se encontraron Pacientes');
			break;
		case 1:
			id = patients.item(0).getElementsByTagName("ID")[0].textContent;
			rut = patients.item(0).getElementsByTagName("NationalNumber")[0].textContent;
			name = patients.item(0).getElementsByTagName("Name")[0].textContent;
			selectPatient(id, rut, name);
			
			break;
		default:
			displayPatients(patients);
			break;
	}	
}

function displayPatients(patients){
	createHtmlTable(patients);
}


function createHtmlTable(patients){
	var divPatients = document.getElementById('divPatients');
	var tablePatients = document.getElementById('tablePatients');
	
alert("createHtmlTable");
	
	if(tablePatients !== null) {
		tablePatients.remove();
	}
	
	var tableHtml = "<table class='cList' cellpadding='0' cellspacing='0' id='tablePatients'>";
	tableHtml += "<tr>";
	tableHtml += "<td class='cHeadTD'>Rut</td>";
	tableHtml += "<td class='cHeadTD'>Nombre</td>";
	tableHtml += "</tr>";

	tableHtml += createRows(patients);
	
	tableHtml += "</table>"

	new Insertion.Top(divPatients, tableHtml);
	
	showTooltip(divPatients);
}

function createRows(patients){
	var out = "";
	var len = patients.length;
	var id = null;
	var rut = null;
	var name = null;
	var aLink = null;
	
	for(var i = 0 ; i < len ; i++){
		id = getText(patients.item(i).getElementsByTagName("ID")[0]); //.textContent;
		rut = getText(patients.item(i).getElementsByTagName("NationalNumber")[0]); //.textContent;
		name = getText(patients.item(i).getElementsByTagName("Name")[0]); //.textContent;
		aLink = "<a href=\"javascript:selectPatient('" + id + "', '" + rut + "', '" + name + "');\">" + rut + "</a>"
		
		out += "<tr>";
		out += "<td class='cDataTD'>" + aLink + "</td>";
		out += "<td class='cDataTD'>" + name + "</td>";
		out += "</tr>";
	}
	return out;
}

function selectPatient(id, rut, name){
	$('fldPatient').value = id;
	$('RutPatient').value = rut;
	$('NamePatient').value = name;
	closeTooltip();
}

function fillCombo(response, nodeName, comboName){
	var service = new BSServiceData(response);
	
	var institutions = service.getResponseNode(nodeName); //.getElementsByTagName("Record");
	
	var code = null;
	var name = null;

	var combo = $(comboName);
	clearCombo(combo, 1);
	
	for(var i=0 ; i < institutions.length ; i++){	
		code = getText(institutions.item(i).getElementsByTagName("ID")[0]);
		name = getText(institutions.item(i).getElementsByTagName("Name")[0]);
		
		addOption(combo, code, name);
	}
}

function clearCombo(combo, last){
	var len = combo.options.length;
	for(var i = len; i > last ; i-=1){
		combo.remove(i-1);
	}
}

function onLoadPage(){
	var divPatients = $('divPatients');
	$('divCalendar').hide();
	$('RutPatient').value = '';
	divPatients.hide();
}
