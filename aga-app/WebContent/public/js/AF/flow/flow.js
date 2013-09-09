function onLoadPage() {
	// alert(tableFlow.rows.length);
	var table = document.getElementById("tableFlow");
	var len = table.rows.length;
	for ( var i = 0; i < len; i++) {
		if (i % 2 == 1) {
			for ( var j = 0; j < table.rows[i].cells.length; j++) {
				table.rows[i].cells[j].style.backgroundColor = "#EEEEE0";
			}
		}
	}
	
//	alert(outputFormat(-12312));
//	alert(122121212);
}

function getChild(id) {
	document.getElementById("fldID").value = id;
	document.getElementById("bsServiceName").value = "AF.GetChild";
	doFilter();
}

function delChild(id) {
	document.getElementById("fldID").value = id;
	document.getElementById("bsServiceName").value = "AF.DelChild";
	doFilter();
}

function doFilter() {
	if (validDate("inputInitDate", 'divInitDate')) {
		var initDate = string2date(getDateString("inputInitDate"), "/");
		var initString = initDate.getDate() + "/" + (initDate.getMonth() + 1)
				+ "/" + initDate.getFullYear();

		document.getElementById("InitDate").value = initString;
		document.getElementById("f").submit();
	} else {
		document.getElementById("inputInitDate").focus();
	}
}

function validDate(dateField, validateElementId) {
	var dateString = getDateString(dateField);
	var validateString = validaFecha2(dateString, "/");
	document.getElementById(validateElementId).innerHTML = validateString;
	return validateString == "";
}

function getDateString(elementId) {
	var dateString = document.getElementById(elementId).value;
	return replaceAll(dateString, "-", "/");
}

function string2date(stringDate, c) {
	var fields = stringDate.split(c);
	dia = fields[0];
	mes = (fields[1]) - 1;
	anyo = fields[2];

	var date = new Date(anyo, mes, dia);

	return date;
}
function stringSQL2date(stringDate, c) {
	var fields = stringDate.split(c);
	anyo = fields[0];
	mes = (fields[1]) - 1;
	dia = fields[2];

	var date = new Date(anyo, mes, dia);

	return date;
}

function date2string(date) {
	return date.getFullYear() + "-" + fix(date.getMonth() + 1) + "-"
			+ fix(date.getDate());
}
function date2string2(date) {
	return fix(date.getDate()) + "-" + fix(date.getMonth() + 1) + "-"
			+ date.getFullYear();
}

function date2string2(date, c) {
	return fix(date.getDate()) + c + fix(date.getMonth() + 1) + c
			+ date.getFullYear();
}

function skipDate(n) {
	if (validDate("inputInitDate", 'divInitDate')) {
		var initDate = string2date(getDateString("inputInitDate"), "/");
		initDate.setDate(initDate.getDate() + n);
		var initString = initDate.getDate() + "/" + (initDate.getMonth() + 1)
				+ "/" + initDate.getFullYear();

		document.getElementById("inputInitDate").value = initString;
	}

	document.getElementById("bsServiceName").value = "AF.SkipDate";
	doFilter();
}

function interenterpriseDialog() {
	showTooltip('divMoveInterenterprise');
}

function dragDialog(id, name) {
	showTooltip('divUpdateDrag');
	document.getElementById('dragInfoId').value = id;

	document.getElementById('tdBN').innerHTML = name;

	var http = getHttpRequest();
	http.open("GET", getURLread(id), false);
	http.send(null);

	var dragInfo = http.responseXML;

	if (haveError(dragInfo)) {
		showError(dragInfo);
	} else {
		var element = selectNodes(dragInfo,
				"/Service/Response/Fields/GetDragInfo/Record");
		/*
		 * var element = getRootElement(dragInfo); element =
		 * element.getElementsByTagName('Response')[0]; element =
		 * element.getElementsByTagName('Fields')[0]; element =
		 * element.getElementsByTagName('GetDragInfo')[0]; element =
		 * element.getElementsByTagName('Record');
		 */
		// alert(element.length);
		if (element.length > 0) {
			element = element[0];
			document.getElementById('dateDrag').value = date2string2(
					stringSQL2date(getText(element
							.getElementsByTagName('cDate')[0]), '-'), '/');

			document.getElementById('amountDrag').value = getText(element
					.getElementsByTagName('cAmount')[0]);
		}
	}
}

function saveDragInfo() {
	var http = getHttpRequest();
	var id = document.getElementById('dragInfoId').value;
	var date = getDate();
	var value = getValue();

	if (date != null && value != null) {
		var url = getURLsave(id, date, value);

		http.open("GET", url, false);
		http.send(null);

		var response = http.responseXML;
		if (haveError(response)) {
			showError(response);
		}
		skipDate(0);
	} else {
		if (date == null) {
			document.getElementById('dateDrag').focus();
		} else if (value == null) {
			document.getElementById('amountDrag').focus();
		}
	}

	// alert('save');

}

function getDate() {
	var out = null;
	if (validDate("dateDrag", 'divDragDateMessage')) {
		out = date2string2(string2date(getDateString("dateDrag"), "/"), '/');
	}
	return out;
}

function getValue() {
	var out = null;
	var amountDrag = document.getElementById('amountDrag').value;
	if (isNumber(amountDrag)) {
		out = toNumber(amountDrag);
	} else {
		document.getElementById('divDragAmountMessage').innerHTML = '- El valor ingresado no es válido';
	}
	return out;
}

function getURLread(id) {
	// return "ControlServlet?bsServiceName=AF.GetDragInfo&ID=" + id;
	return "bsas?bsServiceName=AF.GetDragInfo&ID=" + id + "&r=" + rnd();
}

function getURLsave(id, date, value) {
	return "bsas?bsServiceName=AF.SaveDragInfo&ID=" + id + "&Date=" + date
			+ "&Amount=" + value;
}
function getURLtransfer(sourceEnterprise, sourceBank, targetEnterprise,
		targetBank, moveDate, moveAmount) {
	return "ControlServlet?bsServiceName=AF.DoTransfer&SourceEnterprise="
			+ sourceEnterprise + "&SourceBank=" + sourceBank
			+ "&TargetEnterprise=" + targetEnterprise + "&TargetBank="
			+ targetBank + "&MoveDate=" + moveDate + "&MoveAmount="
			+ moveAmount;
}

function transferAmount() {
	var http = getHttpRequest();
	var sourceEnterprise = document.getElementById('SourceEnterprise').value;
	var sourceBank = document.getElementById('SourceBank').value;
	var targetEnterprise = document.getElementById('TargetEnterprise').value;
	var targetBank = document.getElementById('TargetBank').value;
	var moveDate = getDateString('MoveDate');
	var moveAmount = document.getElementById('MoveAmount').value;

	if (validTransfer(sourceEnterprise, sourceBank, targetEnterprise,
			targetBank, moveDate, moveAmount)) {
		var url = getURLtransfer(sourceEnterprise, sourceBank,
				targetEnterprise, targetBank, moveDate, moveAmount);
		// alert(url);

		http.open("GET", url, false);
		http.send(null);

		var response = http.responseText;
		// alert(response);
		skipDate(0);
	}
}

function validTransfer(sourceEnterprise, sourceBank, targetEnterprise,
		targetBank, moveDate, moveAmount) {
	var out = true;
	if (sourceEnterprise == targetEnterprise) {
		alert('La empresa de origen y destino son iguales');
		out = false;
	} else {
		// alert(validDate("MoveDate", 'dateMoveInvlid'));
		if (!validDate("MoveDate", 'dateMoveInvlid')) {
			out = false;
		} else {
			var moveAmount = document.getElementById('MoveAmount').value;
			if (!isNumber(moveAmount)) {
				alert('El monto ingresado no es valido');
				out = false;
			}
		}
	}

	return out;
}

function showDetail(idStruct, year, month, day) {
	// alert(idStruct + ', ' + year + ', ' + month + ', '+ day);
	// return;
// overflow: scroll
	// select * from tTreeDetail; select * from tTreeValue order by cDate;
	// select * from tTreeStruct;

	var http = getHttpRequest();
	// var id = document.getElementById('dragInfoId').value;
	// var date = getDate();
	// var value = getValue();

	var url = getURLTreeDetail(idStruct, year, month, day);

	http.open("GET", url, false);
	http.send(null);

	var response = http.responseXML;
	if (haveError(response)) {
		showError(response);
	}
	var detailList = selectNodes(response,
			"/Service/Response/Fields/GetTreeDetail/Record");

	showTooltip("divShowDetail");
	
	var table = document.getElementById("movesTable");
	var detail = null;
	var row = null;
	var value = null;
	for(var i=0;i<detailList.length;i++){
		detail = detailList[i];
		row = table.insertRow(table.rows.length);
		
		cell = row.insertCell(0);
		cell.className = "cDataTD";
		cell.innerHTML = getText(detail.getElementsByTagName("cDetail")[0]);

		cell = row.insertCell(1);
		cell.className = "cDataTD";
		value = getText(detail.getElementsByTagName("cComment")[0]);
		cell.innerHTML = value.length == 0 ? "&nbsp;" : value;

		cell = row.insertCell(2);
		cell.className = "cDataTD";
		cell.innerHTML = getText(detail.getElementsByTagName("cType")[0]);
		
		cell = row.insertCell(3);
		cell.className = "cDataTD";
		cell.style.textAlign = "right";
		cell.innerHTML = outputFormat(getText(detail.getElementsByTagName("cAmount")[0]));
//		cell.innerHTML = getText(detail.getElementsByTagName("cAmount")[0]);
		
		
//		alert(elements.getElementById('cID')[0]);
		//alert(getText(elements[i].getElementsByTagName('cID')[0]));
	}
	
	
}

function getURLTreeDetail(id, year, month, day) {
	return "bsas?bsServiceName=AF.GetTreeDetail&ID=" + id + "&Year=" + year + '&Month=' + month + '&Day=' + day;
}
