/*
	Resumen de Funciones
	--------------------
	loadFormat(document)
	focusOut(inputElement)
	focusIn(inputElement)
	function rTrim(txtCadena)
	function lTrim(txtCadena)
	function allTrim(s)
	function validaRUT(rut) : Valida el rut del parámetro, el formato tiene que ser 12345678-5, retorna un valor boolean
	replaceAll(strCadena, strSearch, strReplace): Reemplaza caracteres.
	function toNumber(valorNumber): convierte el valor a un valor numerico para poder realizar cálculos
	validaFecha(string)
*/
//-------------------------------------------------
    function loadFormat(theDocument){
		var i = 0;
		var element = '';
		var firstInput = true;
		if(theDocument==null){
			theDocument = document;
		}
		
		//alert(document.all.length);
		for(i=0;i<theDocument.all.length;i++){
	
			//document.getElementById(8).className 
			//lostFocusInput(document.getElementById("x"));
			
			element = theDocument.all.item(i);
			
			if(element.tagName == 'INPUT' && (element.type == 'text' || element.type == 'password')){
				//alert('hay input' + element.tagName);
				element.onfocus = new Function("javascript:focusIn(this)");
				element.onblur = new Function("javascript:focusOut(this)");
				
				if(firstInput){
					try{
						element.focus();
						firstInput = false;
					} catch(e){

					}
					
					if(element.tagName == 'TEXTAREA'){
						element.className = 'cTextAreaFocus';
					} else {
						element.className = 'cInputFocus';
					}
				} else {
					if(element.tagName == 'TEXTAREA'){
						element.className = 'cTextAreaNofocus';
					} else {
						element.className = 'cInputNofocus';
					}
				}
			}
			
			
			if(element.tagName == 'INPUT' && (element.type == 'button' || element.type=='submit')){
				element.onfocus = new Function("javascript:focusIn(this)");
				element.onblur = new Function("javascript:focusOut(this)");
				
				if(firstInput){
					try{
						element.focus();
					} catch(e) {
					}
					firstInput = false;

					element.className = 'cButtonFocus';
				} else {
					element.className = 'cButtonNofocus';
				}
			}


			if(element.tagName == 'SELECT'){
				element.onfocus = new Function("javascript:focusIn(this)");
				element.onblur = new Function("javascript:focusOut(this)");
				
				if(firstInput){
					try{
						element.focus();
					} catch(e) {
					}
					firstInput = false;

					element.className = 'cSelectFocus';
				} else {
					element.className = 'cSelectNofocus';
				}
			}
		}
	}

	function focusOut(inputElement){
		if(inputElement.tagName == 'TEXTAREA'){
		//alert('es un textarea');
			inputElement.className = 'cTextAreaNofocus';
		}
		if(inputElement.tagName == 'INPUT'){
			if(inputElement.type == 'text' || inputElement.type == 'password'){
				inputElement.className = 'cInputNofocus';
			} else if( inputElement.type == 'button' || inputElement.type=='submit' ){
				inputElement.className = 'cButtonNofocus';
			}
		} else {
			inputElement.className = 'cSelectNofocus';
		}
	}
		
	function focusIn(inputElement){
		if(inputElement.tagName == 'TEXTAREA'){
			//alert('es un textarea');
			inputElement.className = 'cTextAreaFocus';
		}
		if(inputElement.tagName == 'INPUT'){
			if(inputElement.type == 'text' || inputElement.type == 'password'){
				inputElement.className = 'cInputFocus';
			} else if( inputElement.type == 'button' || inputElement.type=='submit' ){
				inputElement.className = 'cButtonFocus';
			}		
                } else {
			inputElement.className = 'cSelectFocus';
		}
	}
	
	function redirect(actionTarget){
		self.location.href = 'ControlServlet?bsServiceName=' + actionTarget;
	}
	function redirectParent(actionTarget){
		parent.location.href = 'ControlServlet?bsServiceName=' + actionTarget;
	}
	
	function executeAction(formAction, serviceName){
		formAction.bsServiceName.value = serviceName;
		formAction.submit();
	}
	
	function lTrim(s) {
	   return s.replace(/^\s+/, "");
	}
	
	function rTrim(s) {
	   return s.replace(/\s+$/, "");
	}
	
	function allTrim(s) {
	   return rTrim(lTrim(s));
	}

	function validaRUT(rut){
		var out = false;
		var posicionGuion = rut.indexOf('-');
		var rutSinDigito = rut.substring(0, posicionGuion);
		var digito = rut.substring(posicionGuion + 1);
		var digitoValido = null;
	//	alert('rut:' + rut + ' rutSinDigito:' + rutSinDigito + ' digito:' + digito);
		if(posicionGuion >= 0){
			digitoValido = dv(rutSinDigito);
			out = digitoValido == digito.toLowerCase();
		}
		return out;
	}
	
	function dv(T){// Autor: Mannungo
		var M=0,S=1;for(;T;T=Math.floor(T/10))S=(S+T%10*(9-M++%6))%11;return S?S-1:'k';
	}
		
	function validaFecha(fecha){
		var out = false;
		fecha = allTrim(fecha);
//	alert("." + allTrim(dtmFecha) + ".");

		if(fecha.length > 0){
			var posicionSeparador1 = fecha.indexOf('/');
			var posicionSeparador2 = -1;

			if(posicionSeparador1 > -1){
				posicionSeparador2 = fecha.lastIndexOf('/');
			} else {
				posicionSeparador1 = fecha.indexOf('-');
				posicionSeparador2 = fecha.lastIndexOf('-');
			}

			if(posicionSeparador1 > -1 && posicionSeparador2 > -1  && posicionSeparador1 != posicionSeparador2){
//				alert(posicionSeparador1);
//				alert(posicionSeparador2);

				var DD = fecha.substring(0, posicionSeparador1);
				var MM = fecha.substring(posicionSeparador1 + 1, posicionSeparador2);
				var YY = fechtEntityTypea.substring(posicionSeparador2 + 1);
				MM--;
				var dtmFecha = new Date(YY, MM, DD);

//	alert('Dia:' + DD + ' ' + dtmFecha.getDate() + "\nMes:" + MM + ' ' + dtmFecha.getMonth() + '\nAno:' + YY + ' ' + dtmFecha.getFullYear() );
	
				out = DD == dtmFecha.getDate() && MM == dtmFecha.getMonth() && YY == dtmFecha.getFullYear();
			}
		}
		return out;
	}
	
	function replaceAll(strCadena, strSearch, strReplace) {
		var strRetorno = new String(strCadena)
		
		while(strRetorno.indexOf(strSearch) > 0) {
			strRetorno = strRetorno.replace(strSearch, strReplace);
		}
		return(strRetorno);
	}
	
	function toNumber(valorNumber){
		var valorString = new String(valorNumber);
		var out;
		
		if(valorString.length == 0) {
			dblRetorno = 0;
		} else {
			valorString = replaceAll(valorString, '.', '');
			valorString = valorString.replace(',',signoDecimal());
			out = (valorNumber == parseFloat(valorString) ? parseFloat(valorNumber) : parseFloat(valorString));
		}
		return(out);
	}

	function isNumber(valorNumber){
		var valorString = new String(valorNumber);
		var out=false;
		
		if(valorString.length > 0) {
			valorString = replaceAll(valorString, '.', '');
			valorString = valorString.replace(',',signoDecimal());
			
			// alert(valorString + '  ' + parseFloat(valorString));
			
			
			out =  !isNaN(parseFloat(valorString)); // (valorNumber == parseFloat(valorString) ? parseFloat(valorNumber) : parseFloat(valorString));
		}
		return out;
	}
	
	function signoDecimal() {
		var valorNumber = 1 / 10;
		var valorString = new String(valorNumber);
		return( valorString.indexOf('.') > 0 ? '.' : ',' );
	}

	function outputFormat(dblValor)	{
		var strValor;
		var strRetorno;
		var dblDecimal, strDecimal;
		var I, J;
		
		if(dblValor.length==0) {
			strRetorno = '';
		} else {
			dblValor = replaceAll(dblValor, ',', '.');
			strValor = new String(integer(dblValor));
			dblDecimal = subtraction(dblValor, integer(dblValor));
		
			strRetorno = '';
			J=1
			for(I = strValor.length-1 ; I >= 0 ; I--) {
				strRetorno = strValor.substr(I,1) + strRetorno;
				if(J / 3 == integer(J / 3) && !nextIsNegative(strValor, I)) {
					if(I>0) strRetorno = '.' + strRetorno;
				}
				J++;
			}
			
			if(dblDecimal > 0) {
				strDecimal = new String(dblDecimal);
				dblDecimal = parseFloat(strDecimal.substr(0,8));
				strDecimal = new String(dblDecimal);
				strDecimal = strDecimal.substr(2);
				strRetorno += ',' + strDecimal;
			}
		}
		return(strRetorno);
	}

	function nextIsNegative(s, i){
		var out = false;
		if(i < s.length){
			out = s.charAt(i-1) == "-";
		}
		
		return out;
	}
	
	
	
	function integer(dblValor) {
		var strValor = new String(dblValor);
		var intValor;
		
		if(strValor.indexOf('.') > -1) {
			intValor = strValor.substr(0, strValor.indexOf('.'));
		} else {
			intValor = dblValor;
		}
		return(parseInt(intValor));
	}

	function subtraction(dblValor1, dblValor2) {
		var dblResultado, presicion;
		presicion = 1000000;
		dblResultado = dblValor1 - dblValor2;
		dblResultado = dblResultado * presicion;
		dblResultado = Math.round(dblResultado)/presicion;
		return dblResultado;
	}
	
/**
* Script para validar fechas en una caja de texto.
* http://login.osirislms.com/index.php?modname=foro&op=message&idThread=16
*/
var a, mes, dia, anyo, febrero;

function anyoBisiesto(anyo) {
	var fin = anyo < 100 ? anyo + 1900 : fin = anyo;
	var out = false;
/*
	if (anyo < 100)
		fin = anyo + 1900;
	else
		fin = anyo;
*/
	if (fin % 4 != 0){
		out = false;
	} else {
		if (fin % 100 == 0) {
			if (fin % 400 == 0) {
				out = true;
			} else {
				out = false;
			}
		} else {
			out = true;
		}
	}
	return out;
}

function validaFecha2(a, c) {
//a=document.forms[0].fecha.value;
	dia=a.split(c)[0];
	mes=a.split(c)[1];
	anyo=a.split(c)[2];
	var out = "";
	
	if( (isNaN(dia)==true) || (isNaN(mes)==true) || (isNaN(anyo)==true) ) {
		out = "- La fecha introducida debe estar formada sólo por números\n";
		return out;
	}
	febrero = anyoBisiesto(anyo) ? 29 : 28

	if ((mes<1) || (mes>12)) {
		return "- El mes introducido no es valido. Por favor, introduzca un mes correcto\n";
	}
	
	if ((mes==2) && ((dia<1) || (dia>febrero))) {
		return "- El dia introducido no es valido. Por favor, introduzca un dia correcto\n";
	}

	if (((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12)) && ((dia<1) || (dia>31))) {
		return "- El dia introducido no es valido. Por favor, introduzca un dia correcto\n";
	}

	if (((mes==4) || (mes==6) || (mes==9) || (mes==11)) && ((dia<1) || (dia>30))) {
		return "- El dia introducido no es valido. Por favor, introduzca un dia correcto\n";
	}

	if ((anyo<1900) || (anyo>2010)) {
		return "- El año introducido no es valido. Por favor, introduzca un año entre 1900 y 2010\n";
	}
	return out;
} 	

	function fix(n){
		return n<10 ? "0" + n : n;		
	}

	function getCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') {
				c = c.substring(1,c.length);
			}
			if (c.indexOf(nameEQ) == 0) {
				return c.substring(nameEQ.length,c.length);
			}
		}
		return null;	
	}
	 
	function setCookie(name,value,days) {
		var expires = "";
		if (days) {
			var date = new Date();
			date.setTime(date.getTime()+(days*24*60*60*1000));
			expires = "; expires="+date.toGMTString();
		}
		document.cookie = name+"="+value+expires+"; path=/";
	}

	function delCookie(name) {
		setCookie(name,"",-1);
	}
	 