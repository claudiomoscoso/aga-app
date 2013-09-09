<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Eliminaci@oacute;n de Rol</h1>
		<form method="POST" action="ControlServlet" name="frm" id="frm">
			<input type="hidden" name="bsServiceName" />
			<input type="hidden" name="fldID" value="{/Service/Response/Fields/SearchRol/Record/cID}" />
			<table>
				<tr>
					<td class="cLabel">ID</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchRol/Record/cID" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Nombre</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchRol/Record/cName" />
					</td>
				</tr>
			</table>
			<input type="button" value="Eliminar" onclick="executeAction(frm, 'USR.deleteRol')" />
			&#160;&#160;
			<input type="button" value="Cancelar" onclick="executeAction(frm, 'USR.ListRol')" />
		</form>
		<!--
			<xsl:copy-of select="/"/>
		-->
	</xsl:template>
</xsl:stylesheet>