<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<H1 class="cTitle">Listado de Patrones</H1>
		<hr/>
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
			<!-- 
				<td class="cHeadTD">ID</td>
				 -->
				<td class="cHeadTD">Numero</td>
				<td class="cHeadTD">Compuesto</td>
				<td class="cHeadTD">Composici&#243;n</td>
				<td class="cHeadTD">Cilindro</td>
				<td class="cHeadTD">Fecha de expiraci&#243;n</td>
				<td class="cHeadTD">Acciones</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/ListAllPatron/Record">
				<tr>
				<!-- 
					<td class="cDataTD">&#160;<xsl:value-of select="cID"/></td>
					 -->
					<td class="cDataTD">&#160;<a href="ControlServlet?bsServiceName=AGA.editPatron&amp;fldID={cID}"><xsl:value-of select="cNumero"/></a></td>
					<td class="cDataTD">&#160;<xsl:value-of select="cCompuesto"/></td>
					<td class="cDataTD">&#160;<xsl:value-of select="cComposicion"/></td>
					<td class="cDataTD">&#160;<xsl:value-of select="cCilindro"/></td>
					<td class="cDataTD">&#160;<xsl:value-of select="substring-after(cFechaExpiracion, ';')"/></td>
					<td class="cDataTD" align="center">
						<a href="ControlServlet?bsServiceName=AGA.confirmDeletePatron&amp;fldID={cID}">(X)</a>
					</td>
				</tr>
			</xsl:for-each>
		</table>
		<hr/>
		<center>
			<input type="button" name="fldNuevo" onclick="redirect('AGA.newUserPatron')" value="Nuevo Patron..."/>
		</center>
<!-- 
<xsl:copy-of select="/"/>
 -->
	</xsl:template>
</xsl:stylesheet>