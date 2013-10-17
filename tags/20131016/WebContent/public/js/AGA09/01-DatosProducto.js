function cargaTipoProducto() {
	document.getElementById("TipoProducto").value = tipoProducto;
}

function updateAnalisis(obj) {
	callURL("AGA.UpdateAnalisis", "Analisis", obj.value);
	return;
}

function updateOrden(obj) {
	callURL("AGA.UpdateOrden", "Orden", obj.value);
	return;
}

function updateTipoProducto(obj){
	callURL("AGA.UpdateTipoProducto", "TipoProducto", obj.value);
	return;
}

function updateComentario(o){
	callURL("AGA.UpdateComentario", "Comentario", escape(o.value));
	return;
}

function imposeMaxLength(o, maxLen){
	var len = o.value.length;
	var out = len <= maxLen;
	if(out){
		lenComentario.innerHTML = len + "/100";
	}
	return out;
}
