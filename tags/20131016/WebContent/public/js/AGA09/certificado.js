function nuevo(){
	var isCilindroAGA = document.getElementById("cilindroAGA").checked;
	var isCilindroParticular = document.getElementById("cilindroParticular").checked;
	var codigoGE = document.getElementById("codigoGe").value;
	
	if(codigoGE.length == 0){
		alert('No se ha indicado un codigo de producto');
		return;
	}
	if(!isCilindroAGA && !isCilindroParticular) {
		alert('No se ha seleccionado tipo de cilindro');
		return;
	}
	 document.getElementById("f1").submit();
}
