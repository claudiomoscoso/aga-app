function cancelar(){ 
	document.getElementById("f").target = "";
	document.getElementById("f").bsServiceName.value = "SCO.ListPersona";
	document.getElementById("f").submit(); 
}

function grabaApoderado() {  
	var msg = validaFrom(); 

	//alert(document.getElementById("f").fldRUT.value);

	if(msg.length > 0){ 
		alert('ATENCION\n\n' + msg); 
	} else {
		document.getElementById("f").target = "iApoderado";
		document.getElementById("f").bsServiceName.value = "SCO.GrabaApoderado";
		document.getElementById("f").submit();
	}
}

function buscaApoderado(){
	var validRut = validaRUT($('RUT').value);

	if(!validRut){ 
		alert("Rut de apoderado no es válido\n"); 
	} else {
		$('f').target = 'iApoderado';
		$('fldRUT').value = $('RUT').value;
		$('bsServiceName').value = "SCO.BuscaApoderado";
		$('f').submit(); 
	}
}

function buscaMMBB(){
	var validRut = validaRUT(document.getElementById("fldRUTPupilo").value);

	if(!validRut){ 
		alert("Rut de Beneficiario no es v&#225;lido\n"); 
	} else {
		document.getElementById("fApoderado").fldRUT.value = document.getElementById("fldRUTPupilo").value;
		document.getElementById("fApoderado").bsServiceName.value = "SCO.BuscaMMBB";
		document.getElementById("fApoderado").fldRUTApoderado.value = document.getElementById("fldRUT").value;
		document.getElementById("fApoderado").submit(); 
	}					
}

function validaFrom(){ 
	var out = ""; 

	if(document.getElementById("fldNombre").value.length == 0) out += '- Nombre no fue ingresado \n';
	if(document.getElementById("fldApellidoPaterno").value.length == 0){out += '- Apellido Paterno no fue ingresado \n';}
	if(document.getElementById("fldApellidoMaterno").value.length == 0){out += '- Apellido Materno no fue ingresado \n';}

	if(document.getElementById("fldDireccion").value.length == 0){out += '- Direcci&#243;n no se ingres&#243;\n';}
	if(document.getElementById("fldNumero").value.length == 0){out += '- N&#250;mero de Direcci&#243;n no se ingres&#243;\n';}
	if(document.getElementById("fldTelefono").value.length == 0){out += '- Tel&#233;fono fijo no se ingres&#243;\n';}


	return out;
}
