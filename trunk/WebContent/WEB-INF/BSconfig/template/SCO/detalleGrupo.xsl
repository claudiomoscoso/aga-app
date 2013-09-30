<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />

	<xsl:template match="/">
		<h1 class="cTitle">Lista de Grupos</h1>
		<form name="f" method="post" action="/{/Service/Context/SiteName}/">
			 

			<table class="cList" cellpadding="0" cellspacing="0" id="TablaGrupo">
				<caption>Grupos Habilitados</caption>
				<tr>
					<td class="cHeadTD" align="center">
						<input type="checkbox" onclick="javascript:marcaTodo(this)"/>
					</td>
					<td class="cHeadTD">RUT</td>
					<td class="cHeadTD" align="center">Fecha Registro</td>
					<td class="cHeadTD">Nombre</td>
					<td class="cHeadTD">Direccion</td>
					<td class="cHeadTD">Telefono</td>
					<td class="cHeadTD" align="center">Valor Tramo</td>
					<td class="cHeadTD" align="center">Fecha Pago</td>
					<td class="cHeadTD" align="center">Valor Pagado</td>
				</tr>
				<xsl:for-each select="/Service/Response/Fields/DetalleGrupo/Record">
					<tr>
						<td class="cDataTD" align="center">
							<xsl:choose>
								<xsl:when test="string-length(cFechaPago)=0">
									<input type="checkbox" value="{cRUT}" name="fldScout"/>
								</xsl:when> 
								<xsl:otherwise>&#160;</xsl:otherwise>
							</xsl:choose>
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cRUT" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="substring-after(cFechaRegistro, ';')" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cNombre" />
							&#160;
							<xsl:value-of select="cApellidoPaterno" />
							&#160;
							<xsl:value-of select="cApellidoMaterno" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cDireccion" />
							&#160;
							<xsl:value-of select="cNumero" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cTelefono" />
						</td>
						<td class="cDataTD" align="right">
							<xsl:value-of select="/Service/Response/Fields/ValorCategoria/Record/cValor" />
						</td>
						<td class="cDataTD" align="center">
							<xsl:choose>
								<xsl:when test="string-length(cFechaPago)=0">
									Sin Pago registrado
								</xsl:when> 
								<xsl:otherwise>
									<xsl:value-of select="substring-after(cFechaPago, ';')" />
								</xsl:otherwise>
							</xsl:choose>
						</td>
						<td class="cDataTD" align="right">
							<xsl:choose>
								<xsl:when test="string-length(cFechaPago)=0">
									0
								</xsl:when> 
								<xsl:otherwise>
									<xsl:value-of select="cValorPago" />
								</xsl:otherwise>
							</xsl:choose>
						</td>
					</tr>
				</xsl:for-each>
				<tr>
					<td class="cDataTD" colspan="6">&#160;</td>
					<td class="cDataTD" align="right"><xsl:value-of select="count(/Service/Response/Fields/DetalleGrupo/Record) * /Service/Response/Fields/ValorCategoria/Record/cValor"/></td>
					<td class="cDataTD" colspan="1">&#160;</td>
					<td class="cDataTD" align="right">0</td>
				</tr>
			</table>

			<br />
			<input type="hidden" name="bsServiceName" value="SCO.RealizarPago"/>
			<input type="submit" value="Aceptar Pago" />
		</form>
<!-- 
		<xsl:copy-of select="/" />
 -->
		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){
					var tabla = document.getElementById("TablaGrupo");
					var rows = tabla.rows.length;
					var valorPagos = 0;
					var i = 0;

					for(i = 1;i &lt; rows-1 ; i += 1){
						valorPagos += toNumber(tabla.rows(i).cells(8).innerText);
					}
 
					tabla.rows(i).cells(3).innerText = valorPagos;
				}

				function marcaTodo(element){
					var marca = element.checked;
					
					var tabla = document.getElementById("TablaGrupo");
					var rows = tabla.rows.length;

					for(var i = 1;i &lt; rows ; i += 1){
//						alert(rows);
						try{
							tabla.rows(i).cells(0).children(0).checked = marca;
						} catch(e){}
					}
				}
			</xsl:comment>
		</script>

	</xsl:template>

</xsl:stylesheet>