<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<h1 class="cTitle">Eliminacion de Usuario</h1>
	<form method="POST" action="ControlServlet" name="frm" id="frm">
		<input type="hidden" name="bsServiceName"/>
		<input type="hidden" name="fldID" value="{/Service/Response/Fields/SearchUser/cID}"/>
		<table>
			<tr>
				<td class="cLabel">ID</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cID"/></td>
			</tr>
			<tr>
				<td class="cLabel">Login</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cLogin"/></td>
			</tr>
			<tr>
				<td class="cLabel">Nombre</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cName"/></td>
			</tr>
			<tr>
				<td class="cLabel">Mail</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cMail"/></td>
			</tr>
			<tr>
				<td class="cLabel">Rol</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cRolName"/></td>
			</tr>
			<tr>
				<td class="cLabel">Telefono Fijo</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cPhone"/></td>
			</tr>
			<tr>
				<td class="cLabel">Teléfono Móvil</td>
				<td class="cData"><xsl:value-of select="/Service/Response/Fields/SearchUser/cMovil"/></td>
			</tr>
		</table>
		<input type="button" value="Eliminar" onclick="executeAction(frm, 'USR.deleteUser')" />&#160;&#160;
		<input type="button" value="Cancelar" onclick="executeAction(frm, 'USR.listUser')"/>
	</form>
<!-- 
<xsl:copy-of select="/"/>
-->
	</xsl:template>
</xsl:stylesheet>