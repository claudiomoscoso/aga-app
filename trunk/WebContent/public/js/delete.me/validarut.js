//
// Validador de Rut
//

// Entregar RUT en formato 12345678-5
function validaRUT(rut){
	var out = false;
	var posicionGuion = rut.indexOf('-');
	var rutSinDigito = rut.substring(0, posicionGuion);
	var digito = rut.substring(posicionGuion + 1);
	var digitoValido = null;
//	alert('rut:' + rut + ' rutSinDigito:' + rutSinDigito + ' digito:' + digito);
	if(posicionGuion < 0){
		out = false;
	} else {
		digitoValido = dv(rutSinDigito);
		out = digitoValido == digito.toLowerCase();
	}
	return out;
}
function dv(T){// Autor: Mannungo
	var M=0,S=1;for(;T;T=Math.floor(T/10))S=(S+T%10*(9-M++%6))%11;return S?S-1:'k';
}
