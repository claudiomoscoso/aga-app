var sec = 5;
function onLoadPage(){
	setInterval('updateTime()',1000);
	fldMessage.innerText = "" + sec + " segundos para aceptar";
}
function updateTime() {
	sec--;
	fldMessage.innerText = "" + sec + " segundos para aceptar";
	if(sec==0){
		redirect(fldNextService.value);
	}
}