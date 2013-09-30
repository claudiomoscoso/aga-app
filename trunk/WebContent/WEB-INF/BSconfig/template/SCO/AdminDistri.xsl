<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Lista de Grupos</h1>
		<form name="f" method="post" action="/{/Service/Context/SiteName}/">
			<input type="hidden" value="SCO.EstadoGrupo" name="bsServiceName" />

<div class="content">
			<table class="cList" cellpadding="0" cellspacing="0" id="TablaGrupos">
				<caption>Grupos Habilitados</caption>
				<tr>
					<td class="cHeadTD">Codigo</td>
					<td class="cHeadTD">Hab.</td>
					<td class="cHeadTD">Nombre Grupo</td>
					<td class="cHeadTD">Categoria</td>
					<td class="cHeadTD">Fecha</td>
					<td class="cHeadTD">C.I.</td>
					<td class="cHeadTD">Valor Tramo</td>
					<td class="cHeadTD">Cantidad</td>
					<td class="cHeadTD">A Cancelar</td>
					<td class="cHeadTD">Saldo Favor</td>
					<td class="cHeadTD">Total Pagar</td>
					<td class="cHeadTD">Seguro</td>

					<td class="cHeadTD">Valor Neto</td>
					<td class="cHeadTD">25% Reg.</td>
					<td class="cHeadTD">$150 Zona</td>
					<td class="cHeadTD">A Depositar</td>
				</tr>
				<xsl:for-each select="/Service/Response/Fields/ListGrupos/Record">
					<tr>
						<td class="cDataTD" align="center">
							<xsl:value-of select="cID" />
						</td>
						<td class="cDataTD" align="center">
							<input type="checkbox" name="fldGrupo" value="{cID}">
								<xsl:if test="cHabilitado=1">
									<xsl:attribute name="checked" />
								</xsl:if>
							</input>
						</td>
						<td class="cDataTD">
							<a href="ControlServlet?bsServiceName=SCO.DetalleGrupo&amp;fldGrupo={cID}">
								<xsl:value-of select="cNombre" />
							</a>
						</td>
						<td class="cDataTD" align="center">
							<xsl:value-of select="cCategoria" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="/Service/Request/Date" />
						</td>
						<td class="cDataTD">...?</td>
						<td class="cDataTD" align="right">
							<xsl:value-of select="cValorCategoria" />
						</td>
						<td class="cDataTD" align="right">
							<xsl:value-of select="cCantidad" />
						</td>
						<td class="cDataTD" align="right">
							<xsl:value-of select="cCancelar" />
						</td>
						<td class="cDataTD" align="right">
							<input size="6" value="0" onchange="javaScript:calculaFila(document.getElementById('TablaGrupos'), {position()});sumatoriaFinal();" />
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
						<td class="cDataTD" align="right">
							<input size="6" readonly="" value="0" style="text-align:right"/>
						</td>
 					</tr>
				</xsl:for-each>
				<tr>
					<td colspan="7" class="cDataTD" >&#160;</td>
					<td class="cDataTD" align="right">
						 0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						0
					</td>
					<td class="cDataTD" align="right">
						<input size="6" readonly="" value="0" style="text-align:right"/>
					</td>
				</tr>
			</table>
</div>
			<br />
			<input type="submit" value="Aceptar" />
		</form>
<!-- 
			<xsl:copy-of select="/" />
 -->
		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){
					calculaMatriz();
				}
				
				function calculaMatriz(){
					var tabla = document.getElementById("TablaGrupos");
					var rows = tabla.rows.length;
 
					for(var i = 1;i &lt; rows-1 ; i += 1){
						calculaFila(tabla, i);
					}
					
					sumatoriaFinal();
				}

				function calculaFila(tabla, i) { 
					var fila = tabla.rows(i);
					var valorCategoria = fila.cells(6).innerText;
					var cantidad = fila.cells(7).innerText;
					var cancelar = fila.cells(8).innerText;

					var saldoFavor = fila.cells(9).children(0).value;

					if(saldoFavor.length==0){
						saldoFavor = 0;
					}

					var totalPagar = (cantidad*cancelar)-saldoFavor;
					var seguro = (cantidad * 950);
					var neto = totalPagar - seguro;
					var registro = neto * 0.25;
					var valorZona = cantidad * 150;
					var nacional = totalPagar - registro - valorZona;

					fila.cells(10).children(0).value = totalPagar;
					fila.cells(11).children(0).value = seguro;
					fila.cells(12).children(0).value = neto;
					fila.cells(13).children(0).value = registro;
					fila.cells(14).children(0).value = valorZona;
					fila.cells(15).children(0).value = nacional;
				}
			
				function sumatoriaFinal(){
					var tabla = document.getElementById("TablaGrupos");
					var rows = tabla.rows.length;
					var i = 0;
 					var cantidad = 0;
					var cancelar = 0;
					var saldoFavor = 0;
					var totalPagar = 0;
					var seguro = 0;
					var neto = 0;
					var registro = 0;
					var valorZona = 0;
					var nacional = 0;

					for(i = 1;i &lt; rows-1 ; i += 1){
						cantidad += toNumber(tabla.rows(i).cells(7).innerText);
						cancelar += toNumber(tabla.rows(i).cells(8).innerText);
						saldoFavor += toNumber(tabla.rows(i).cells(9).children(0).value);

						totalPagar += toNumber(tabla.rows(i).cells(10).children(0).value);
						seguro += toNumber(tabla.rows(i).cells(11).children(0).value);
						neto += toNumber(tabla.rows(i).cells(12).children(0).value);
						registro += toNumber(tabla.rows(i).cells(13).children(0).value);
						valorZona += toNumber(tabla.rows(i).cells(14).children(0).value);
						nacional += toNumber(tabla.rows(i).cells(15).children(0).value);

//alert(cantidad);
					}
//					alert(i);
					tabla.rows(i).cells(1).innerText = cantidad;
					tabla.rows(i).cells(2).innerText = cancelar;
					tabla.rows(i).cells(3).innerText = saldoFavor;
					tabla.rows(i).cells(4).innerText = totalPagar;
					tabla.rows(i).cells(5).innerText = seguro;
					tabla.rows(i).cells(6).innerText = neto;
					tabla.rows(i).cells(7).innerText = registro;
					tabla.rows(i).cells(8).innerText = valorZona;
					tabla.rows(i).cells(9).innerText = nacional;
				}
			</xsl:comment>
		</script>

	</xsl:template>
</xsl:stylesheet>