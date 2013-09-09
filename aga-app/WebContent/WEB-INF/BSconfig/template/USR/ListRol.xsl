<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<H1 class="cTitle">Listado de Grupos</H1>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">ID</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Eliminar</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/ListRol/Record">
				<tr>
					<td class="cDataTD">&#160;<a href="ControlServlet?bsServiceName=USR.editRol&amp;fldID={cID}"><xsl:value-of select="cID"/></a></td>
					<td class="cDataTD">&#160;<xsl:value-of select="cName"/></td>
					<td class="cDataTD" align="center"><a href="ControlServlet?bsServiceName=USR.confirmDeleteRol&amp;fldID={cID}">(X)</a></td>
				</tr>
			</xsl:for-each>
		</table>
<br/>
		<center>
			<input type="button" name="fldNuevo" onclick="redirect('USR.newRol')" value="Nuevo Rol..."/>
		</center>
	</xsl:template>
</xsl:stylesheet>