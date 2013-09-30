/*
*	Versión 4.2
*	Por Claudio Moscoso Howard.
*	Cualquier acotación o aporte, favor escribír mail a cmosco90@bancoestado.cl
*	
*----------------------------------------------------------------------------------------
* Nota importante:
*		La funcion "FilaSeleccionada()" ha sido  anulada,  por  un  manejo  inesperado  de
*	variables de parte de Internet Explorer (IE), para obtener la fila(s) seleccionada(s)
*	de una tabla, existe una variable llamada "arrRows", la cual es  un  objeto  de  tipo
*	array que contiene un set de rows(DHTML), esta variable  se  encuentra  publicada  en
*	página que llamó a la función SeteaTabla4(). Esta  variable  contendrá  un  0  (cero)
*	cuando no haya ninguna fila seleccionada.
*
*		Por favor utilice la siguiente sintaxis para poder obtener la fila selecionada  de
*	una tabla.
*
*		var objROW = <página.>objTABLE.rows[<página.>arrRows]
*
*		EJEMPLO:
*				var objFila = fraLista.tblTabla.rows[fraLista.lastSelectRow];
*				if (objFila.length == 0)
*				{
*					alert('Fila no seleccionada');
*					return;
*				}
*----------------------------------------------------------------------------------------
*		Funciones de la librería para su uso desde la página HTML.
*		----------------------------------------------------------
*		SeteaTabla4(objTabla, blnSort, blnSelectRow)
*		
*		Donde
*		objTabla: Requerido. objTabla es el objeto tipo TABLE, el  cual  será  configurado
*			para su uso.
*		blnSort: Requerido. Es un valor boolean (true/false), el cual indica si  la  tabla
*			poseerá la facultad de poder ser ordenada al hacer click sobre la  cabecera  de
*			la columna de orden. Este ordenamiento se hace con el método llamado QuickSort.
*		blnSelectRow: Requerido. Es un valor boolean (true/false), el cual  indica  si  la
*			tabla poseerá la facultad de destacar filas cundo éstas sean clickeadas.
*
*		Ejemplo:
*		<HTML>
*			...
*			...
*			<BODY onLoad='SeteaTabla4(tblTabla,true,true)'>
*				<TABLE Name=tblTabla ID=tblTabla>
*					...
*					...
*					...
*				</TABLE>
*			</BODY>
*		</HTML>
*
*		BuscaTabla()
*		-----------
*		No usa parámetros. Esta función busca dentro de la página HTML, el  primer  objeto
*		de tipo tabla, y lo retorna.
*
*		Ejemplo:
*		SeteaTabla4(BuscaTabla(),true,false,true);
*
*		La Función que es llamada cuando se selecciona una Fila es ClickRow(objRow), y recibe por 
*	parámetro el objeto Row
*
*/
//-------------------------------
//			BEGIN CODE
//-------------------------------
var intLastSelectRow;
var arrRows;
var strFilaOriginal = '';  //'clsFilaOriginal';
var strFilaSeleccionada = 'clsFilaSeleccionada';
//-------------------------------
function SeteaTabla4(objTabla, blnSort, blnSelectRow, blnMultiSelect)
{
	objTabla.style.cursor = 'default';
	fncSeteoGeneral(objTabla);

	if (blnMultiSelect == null)
	{
		blnMultiSelect = false;
	}
	if (blnSort) 
	{
		fncSeteaSort(objTabla.rows[0]);
	}
	else
	{
		objTabla.rows[0].style.cursor = 'default';
	}
	
	if (blnSelectRow)
	{
		arrRows = new Array();
		fncSelectRow(objTabla, blnMultiSelect)
	}
}
//-------------------------------
function FilaSeleccionada()
{
	return(arrRows);
}
//-------------------------------
/*			Seteo para genérico, para cualquier Tabla */
function fncSeteoGeneral(objTabla)
{
	var C;
	with(objTabla)
	{
		className = 'clsIniTabla';
		rows[0].className = 'Etiqueta6';
		
		
		for ( C = 0 ; C <= rows(0).cells.length - 1 ; C++ )
		{
			rows[0].cells[C].innerHTML = '<B>' + rows[0].cells[C].innerText + '</B>';
		}
/*
		for ( var F, F = 1; F <= rows.length - 1; F++ )
		{
//			rows[F].onmouseover = new Function('fncMouseOver(this)');
//			rows[F].onmouseout = new Function('fncMouseOut(this)');
			rows[F].className = strFilaOriginal;
		}
*/
	}
}
//-------------------------------
function fncSeteaSort(objFila0)
{
	with(objFila0)
	{
		for ( var C, C = 0 ; C <= cells.length - 1 ; C++ )
		{
			cells[C].onclick = new Function('sortTabla(this.parentElement.parentElement.parentElement,' + C + ')' );
			cells[C].style.cursor = 'hand';
		}
	}
}
//-------------------------------
function fncSelectRow(objTabla, blnMulti)
{
	intLastSelectRow = -1;
	with(objTabla)
	{
		for(var I, I = 1; I <= objTabla.rows.length-1; I++ )
		{
			objTabla.rows(I).style.cursor = 'hand';
			objTabla.rows(I).onclick = new Function('fncClickRow(this,' + I + ',' + blnMulti + ')');
		}
	}
}
//-------------------------------
function fncClickRow(objRow, intIndex, blnMulti)
{
	if (objRow.className == strFilaOriginal) // Si la pinchada NO esta seleccionada!
	{
		objRow.className = strFilaSeleccionada;
		if (blnMulti)
		{
			arrRows[arrRows.length] = objRow;
		}
		else
		{
			arrRows[0] = objRow;
			if (intLastSelectRow > -1 && intLastSelectRow != intIndex)
			{
				objRow.parentElement.rows[intLastSelectRow].className = strFilaOriginal;
			}
		}
	}
	else
	{
		var arrTemp = new Array();
		for (var i, i=0;i<=arrRows.length-1;i++)
		{
			if (!(objRow == arrRows[i]))
			{
				arrTemp[arrTemp.length] = arrRows[i];
			}
		}
		arrRows = arrTemp;
		objRow.className = strFilaOriginal;
		i = null;
		arrTemp = null;
	}
	try
	{
		ClickRow(objRow);
	}
	catch(e1)
	{	}
/*	var ta = objRow.parentElement;
		for (var i, i=0;i<ta.rows.length;i++)
		{
			ta.rows[i].cells[0].innerText = ta.rows[i].className;
		}
*/
	intLastSelectRow = intIndex;
}
//-------------------------------
function sayArray(xArr)
{
	var i;
	for (i=0;i<=xArr.length-1;i++)
	{
		alert(xArr[i].cells[0].innerText);
	}
}
//-------------------------------
/*	Esta Función retorna un puntero a la primera tabla que esté en el documento */
function BuscaTabla()
{
	var I;
	for ( I = 0 ; I <= document.all.length - 1 ; I++ )
	{
		if (document.all(I).tagName == 'TABLE')
		{
			return(document.all(I));
		}
	}
}
//-------------------------------
function sortTabla(objTabla, intCol)
{
	var intRows, intCols, strColor, rowColor;
	intRows = objTabla.rows.length-2;
	intCols = objTabla.rows[0].cells.length-1;
	var mtx = new Array();
	var aux = new Array();
	var i,j,k;

	/* HTML to Matriz */
	for(i=0;i<=intRows;i++)
	{
		mtx[i] = new Array();
		for(j=0;j<=intCols;j++)
		{
			var aux = new Number(objTabla.rows[i+1].cells[j].innerHTML);
			if (isNaN(aux))
			{
				mtx[i][j] = objTabla.rows[i+1].cells[j].innerHTML;
			}
			else
			{
				mtx[i][j] = aux;
			}
		}
		mtx[i][j] = objTabla.rows[i+1].className;
	}
	/* Sorting Matriz */
	for(i=0;i<intRows;i++)
	{
		j=i;
		k=i+1;
		while(mtx[j][intCol]>mtx[k][intCol])
		{
			aux = mtx[j];
			mtx[j] = mtx[k];
			mtx[k] = aux;
			j--;
			k--;
				
			if(j<0)
			{
				j=0;
				k=1;
			}
		}
	}

	/* Matriz to HTML*/
	aux=0;
	for(i=0;i<=intRows;i++)
	{
		for(j=0;j<=intCols;j++)
		{
			objTabla.rows[i+1].cells[j].innerHTML = mtx[i][j];
		}
		
		objTabla.rows[i+1].className = mtx[i][j];
	}
}
//-------------------------------
function fncMouseOver(objFila)
{
//	oldbackgroundColor = objFila.style.backgroundColor;
//	oldColor = objFila.style.color;
	
//	alert(objFila.cells[0].innerText);
	objFila.className = 'clsFilaSeleccionada2';
//	objFila.style.color = 'black';
}
//-------------------------------
function fncMouseOut(objFila)
{
//	if (objFila.style.className == 'clsFilaSeleccionada2')
	{
//		alert('chao');
		objFila.className = '';
//		objFila.style.color = oldColor;
	}
}
//-------------------------------
function reset(objTabla)
{
	with(objTabla)
	{
		for ( var F, F = 1; F <= rows.length - 1; F++ )
		{
			rows[F].className = strFilaOriginal;
		}
	}
}