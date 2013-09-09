function sendForm() {
	var fileType = document.getElementById('TypeFile').value.length > 0;
	var fileName = document.getElementById('fldFile').value.length > 0;

	if (fileType && fileName) {
		document.getElementById('frm').submit();
	} else {
		if (!fileType) {
			alert('Tiene que seleccionar un tipo de archivo');
		} else {
			if (!fileName) {
				alert('Tiene que seleccionar un archivo');
			}
		}
	}
}

function borrarArchivo(id, name){
	if(confirm('¿Esta seguro de querer borrar el archivo "' + name + '"?')){
		document.getElementById('bsServiceName2').value = 'AF.DelFile';
		document.getElementById('ID2').value = id;
		document.getElementById('frm2').submit();
	}	
}

function processFile(id, name){
	if(confirm('Se procesará el archivo "' + name + '", ¿Esta seguro?')){
		document.getElementById('bsServiceName2').value = 'AF.ProcessToFlow';
		document.getElementById('ID2').value = id;
		document.getElementById('frm2').submit();
	}	
	
	
}