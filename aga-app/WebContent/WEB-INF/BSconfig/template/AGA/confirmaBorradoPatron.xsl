<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Eliminaci&#243;n de Patron</h1>
		<form method="POST" action="ControlServlet" id="frm">
			<input type="hidden" name="bsServiceName" />
			<input type="hidden" name="fldID" value="{/Service/Response/Fields/GetPatron/Record/cID}" />
			<table>
				<tr>
					<td class="cLabel">ID</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cID" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">N&#250;mero</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cNumero" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Compuesto</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cCompuesto" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Composici&#243;n</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cComposicion" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Cilindro</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cCilindro" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Fecha Expiraci&#243;n</td>
					<td class="cData">
						<xsl:value-of select="substring-after(/Service/Response/Fields/GetPatron/Record/cFechaExpiracion, ';')" />
					</td>
				</tr>
			</table>

		</form>
		<input type="button" value="Eliminar" onclick="executeAction(frm, 'AGA.deletePatron')" />
		&#160;&#160;
		<input type="button" value="Cancelar" onclick="executeAction(frm, 'AGA.listPatron')" />
		<!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
 -->
	</xsl:template>
</xsl:stylesheet>