/*
	Resumen de Funciones
	--------------------
	fmtLabel(strTexto): retorna el strTexto con el formato LABEL, para mostrar información en
					pantalla.
	function fmtSpan(strTexto): retorna el strTexto con el formato SPAN.
	salirFecha()
	onlyInteger()
	onlyDouble()
	onlyDate()
	redondear(dblNumero, intDecimales): recorta una cifra a la cantidad de decimales que se 
					indica por intDecimales.
	replaceAll(strCadena, strSearch, strReplace): Reemplaza caracteres.
	calculable(dblValor)
	entrarNumero()
	formatoSalida(dblValor)
	dosDecimales(dblValor)
	setAnchoControls(arrControls)
	entero(dblValor)
	replaceAll(strCadena, strSearch, strReplace)
	setAnchoControls(arrControls)
	anchoMaximo(arrControls)
	seteaFormatoPrinter(docContainer)
	rTrim(txtCadena)
	cargando(strMensaje)
	validaFecha(dtmFecha)

*/
//-------------------------------------------------
function fmtLabel(strTexto)
{
	return('<LABEL tabIndex=-1>' + strTexto + '</LABEL>');
}
//-------------------------------------------------
function fmtSpan(strTexto)
{
	return('<SPAN>' + strTexto + '</SPAN>');
}
//-------------------------------------------------
function salirFecha()
{
	var strFecha = new String(txtInicioLaboral.value);
	if (!strFecha.length == 0)
		txtInicioLaboral.value = FormateaFecha(txtInicioLaboral.value, 'DDMMAAAA', 'dd/mm/yyyy');
}
//-------------------------------------------------
function onlyInteger()
{
	var keyASCII;
	var strNumber = new String(event.srcElement.value);
//	CodAsc = Asc(UCase(Chr(window.event.keyCode)))
//	75 : k
	keyASCII = window.event.keyCode;
//	alert(keyASCII);
	if( keyASCII != 13 && keyASCII != 75 && keyASCII < 48 || keyASCII > 57 ) //  And Not IsNumeric(Chr(CodAsc)) )
	{
	   window.event.keyCode = '';
	}
}
//-------------------------------------------------
function onlyDouble()
{
	var keyASCII;
	var strNumber = new String(event.srcElement.value);
//	CodAsc = Asc(UCase(Chr(window.event.keyCode)))
//	75 : k
	keyASCII = window.event.keyCode;
	if(keyASCII == 46)
	{
		window.event.keyCode = 44;
		keyASCII = 44;
	}
//	alert(keyASCII);
	if( keyASCII != 44 && keyASCII != 13 && keyASCII != 75 && keyASCII < 48 || keyASCII > 57 ) //  And Not IsNumeric(Chr(CodAsc)) )
	{
	   window.event.keyCode = '';
	}
	if(strNumber.indexOf(',') != -1 && keyASCII == 44)
	{
		window.event.keyCode = '';
	}
}
/*
//-------------------------------------------------
function onlyDate()
{
	var keyASCII;
	var strNumber = new String(event.srcElement.value);
//	CodAsc = Asc(UCase(Chr(window.event.keyCode)))
//	75 : k
	keyASCII = window.event.keyCode;
	if(keyASCII == 46)
	{
		window.event.keyCode = 44;
		keyASCII = 44;
	}
//	alert(keyASCII);
	if( keyASCII != 44 && keyASCII != 13 && keyASCII != 75 && keyASCII < 48 || keyASCII > 57 ) //  And Not IsNumeric(Chr(CodAsc)) )
	{
	   window.event.keyCode = '';
	}
	if(strNumber.indexOf(',') != -1 && keyASCII == 44)
	{
		window.event.keyCode = '';
	}
}
*/
//-------------------------------------------------
function redondear(dblNumero, intDecimales)
{
	var dblRetorno, dblFactor;
	dblFactor = Math.pow( 10, intDecimales);
	dblRetorno = dblNumero * dblFactor;
	dblRetorno = Math.round(dblRetorno);
	dblRetorno = dblRetorno / dblFactor;
	return(dblRetorno);
}
//-------------------------------------------------
function calculable(dblValor)
{
	var strValor = new String(dblValor);
	var dblRetorno;
	
//	alert('largo ' + strValor.length + ' ' + dblValor);
	if(strValor.length==0)
	{
		dblRetorno = 0;
	}
	else
	{
		strValor = replaceAll(strValor, '.', '');
		
//		alert();
		strValor = strValor.replace(',',signoDecimal());
		
		dblRetorno = (dblValor == parseFloat(strValor) ? parseFloat(dblValor) : parseFloat(strValor));
	}
	
//	alert('' + dblValor + '->' + dblRetorno + '->' + parseFloat(dblRetorno));
	return(dblRetorno);
}
//-------------------------------------------------
function signoDecimal()
{
	var dblValor = 1 / 10;
	var strValor = new String(dblValor);
//	alert(strValor);
	return( strValor.indexOf('.') > 0 ? '.' : ',' );
}
//-------------------------------------------------
function entrarNumero()
{
	var objInput = event.srcElement;
	var strRetorno = new String(objInput.value);
	objInput.value = replaceAll(strRetorno, '.', '');
	objInput.select();
}
//-------------------------------------------------
function entrarFecha()
{
	var objInput = event.srcElement;
	var strRetorno = new String(objInput.value);
	objInput.value = replaceAll(strRetorno, '/', '');
	objInput.select();
}
//-------------------------------------------------
function formatoSalida(dblValor)
{
	var strValor;
	var strRetorno;
	var dblDecimal, strDecimal;
	var I, J;
	
	if(dblValor.length==0) {
		strRetorno = '';
	} else {
		dblValor = replaceAll(dblValor, ',', '.');
		strValor = new String(entero(dblValor));
		dblDecimal = resta(dblValor, entero(dblValor));
	
		strRetorno = '';
		J=1
		for(I = strValor.length-1 ; I >= 0 ; I--) {
			strRetorno = strValor.substr(I,1) + strRetorno;
			if(J / 3 == entero(J / 3)) {
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
//-------------------------------------------------
function resta(dblValor1, dblValor2)
{
	var dblResultado, presicion;
	presicion = 1000000;
	dblResultado = dblValor1 - dblValor2;
	dblResultado = dblResultado * presicion;
	dblResultado = Math.round(dblResultado)/presicion;
	return(dblResultado);
}
//-------------------------------------------------
function dosDecimales(dblValor)
{
	var strValor;
	var strRetorno;
	var dblDecimal, strDecimal;
	var I, J;
	
	if(dblValor.length==0)
	{
		strRetorno = '';
	}
	else
	{

//alert(dblValor);

		dblValor = replaceAll(dblValor, ',', '.');
		strValor = new String(entero(dblValor));
		dblDecimal = dblValor - entero(dblValor);
//alert('' + entero(dblValor) + '<->' + dblValor + '<->' + dblDecimal + '<->' + (Math.round(dblDecimal*100)/100));
		dblDecimal = Math.round(dblDecimal*100)/100;

		
		strRetorno = '';
		J=1
		for(I = strValor.length-1 ; I >= 0 ; I--)
		{
			strRetorno = strValor.substr(I,1) + strRetorno;
			if(J / 3 == entero(J / 3))
			{
				if(I>0) strRetorno = '.' + strRetorno;
			}
			J++;
		}
		if(dblDecimal > 0)
		{
			strDecimal = new String(dblDecimal);
			dblDecimal = parseFloat(strDecimal.substr(0,8));
			strDecimal = new String(dblDecimal);
			strDecimal = strDecimal.substr(2);
			
			switch (strDecimal.length)
			{
			case 1 :
			    strDecimal += '0';
			    break;
			case 2 :
			    break;
			default :
			    strDecimal.substr(2,2);
			    break;
			} 
		}
		else
		{
			strDecimal = '00';
		}
		strRetorno += ',' + strDecimal;
	}
	return(strRetorno);
}
//-------------------------------------------------
function entero(dblValor) {
	var strValor = new String(dblValor);
	var intValor;
	
	if(strValor.indexOf('.') > -1) {
		intValor = strValor.substr(0, strValor.indexOf('.'));
	} else {
		intValor = dblValor;
	}
	return(parseInt(intValor));
}
//-------------------------------------------------
function replaceAll(strCadena, strSearch, strReplace)
{
	var strRetorno = new String(strCadena)
	
	while(strRetorno.indexOf(strSearch) > 0)
	{
		strRetorno = strRetorno.replace(strSearch, strReplace);
	}
	return(strRetorno);
}
//-------------------------------------------------
function setAnchoControls(arrControls)
{
			
	intAncho = anchoMaximo(arrControls);
			
	for(I = 0 ; I <= arrControls.length-1; I++)
	{
		arrControls[I].style.width = intAncho;
	}
}
//-------------------------------------------------
function anchoMaximo(arrControls)
{
	var intMaximo, I;
	intMaximo = 0;
	
//	alert(arrControls.length);
	for( I = 0 ; I <= arrControls.length - 1 ; I++)
	{
//		alert(arrControls[I].name + '->' + arrControls[I].offsetWidth + ' -> ' + intMaximo);
		
		if(arrControls[I].offsetWidth > intMaximo)
		{
			intMaximo = arrControls[I].offsetWidth;
		}
//		alert(intMaximo);
	}
	return(intMaximo);
}
//-------------------------------------------------
function seteaFormatoPrinter(docContainer)
{
	var I;
	
	with(docContainer)
	{
/*
*/
		for(I = 0 ; I <= document.all.length - 1 ; I++)
		{
			switch (document.all[I].tagName)
			{
				case 'CAPTION' :
					document.all[I].className = 'paraCaption';
					break;
				case 'SPAN' :
					document.all[I].innerHTML = '<strong>' + document.all[I].innerHTML + '</strong>';
					break;
					/*
				case 'CAPTION' :
					document.all[I].innerHTML = '<big><U>' + document.all[I].innerHTML + '</U></big>';
					break;
					*/
			}
		}
	}
}
//-------------------------------------------------
function rTrim(txtCadena)
{
	var I, blnBlanco, strRetorno;
	blnBlanco=true;
	
	strRetorno = '';
	for(I=txtCadena.length-1;I>0;I--)
	{
		if(txtCadena.substr(I,1) != ' ')
		{
//			strRetorno += txtCadena.subst(I,1);
			blnBlanco=false;
		}
		if(!blnBlanco)
		{
			strRetorno = txtCadena.substr(0,I+1);
			I=0;
		}
	}
	return(strRetorno);
}


//-------------------------------------------------
var x, ventanaAbierta;
function cargando(strMensaje)
{																				// resizable:no;
	var intLeft, intTop, intHeight, intWidth, strProperty;
	
	intWidth = 350;
	intHeight = 100;
	intLeft = (screen.width / 2)-(intWidth/2);
	intTop = (screen.height / 2)-(intHeight/2);
	
	strProperty = 'height=' + intHeight;
	strProperty += ',width=' + intWidth;
	strProperty += ',left=' + intLeft;
	strProperty += ',top=' + intTop;
	strProperty += ',titlebar=no,resizable=no';
	
//	alert(strProperty);
	
	ventanaAbierta = window.open('../General/wait.asp?strMensaje=' + strMensaje, null,strProperty);
	x = setInterval('cerrarVentana()', 3500);
}
//-------------------------------------------------
function cerrarVentana()
{
	ventanaAbierta.close();
	clearInterval(x);
}
//--------------------------------------
function validaFecha(dtmFecha)
{
	var strFecha = new String(dtmFecha);
	var DD = calculable(strFecha.substr(0,2));
	var MM = calculable(strFecha.substr(3,2)-1);
	var YY = calculable(strFecha.substr(6,4));
	var blnRetorno;
	var dtmFecha = new Date(YY, MM, DD);
	
//	alert('' + DD + ' ' + dtmFecha.getDate() + '/' + MM + ' ' + dtmFecha.getMonth() + '/' + YY + ' ' + dtmFecha.getFullYear() );
	
	if(DD == dtmFecha.getDate() && MM == dtmFecha.getMonth() && YY == dtmFecha.getFullYear())
	{
		blnRetorno = true;
	}
	else
	{
		blnRetorno = false;
	}
	return(blnRetorno);
}
