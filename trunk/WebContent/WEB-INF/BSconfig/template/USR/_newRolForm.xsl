<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
	<h1 class="cTitle">Datos de nuevo usuario</h1>
<form method="POST" action="ControlServlet">

	<input type="hidden" name="bsServiceName" value="USR.saveNewUser"/>
<table>
	<tr>
		<td class="cLabel">ID</td>
		<td class="cData">Nuevo</td>
	</tr>
	<tr>
		<td class="cLabel">Login</td>
		<td><input type="text" name="fldLogin"/></td>
	</tr>
	<tr>
		<td class="cLabel">Password</td>
		<td><input type="password" name="fldPassword1"/></td>
	</tr>
	<tr>
		<td class="cLabel">Confirma Pasword</td>
		<td><input type="password" name="fldPassword2"/></td>
	</tr>
	<tr>
		<td class="cLabel">Nombre</td>
		<td><input type="text" name="fldName"/></td>
	</tr>
	<tr>
		<td class="cLabel">Mail</td>
		<td><input type="text" name="fldMail"/></td>
	</tr>
	<tr>
		<td class="cLabel">Rol</td>
		<td>
			<select name="fldRol">
				<xsl:for-each select="/Response/Fields/Rol/Record">
					<option value="{./cID}"><xsl:value-of select="./cName"/></option>
				</xsl:for-each>
			</select>
		</td>
	</tr>
	<tr>
		<td class="cLabel">Telefono Fijo</td>
		<td><input type="text" name="fldPhone"/></td>
	</tr>
	<tr>
		<td class="cLabel">Teléfono Móvil</td>
		<td><input type="text" name="fldMovil"/></td>
	</tr>

	</table>
	<input type="submit" value="Aceptar"/>&#160;
	<input type="button" value="Cancelar" onclick="redirect('USR.listUser');"/>
</form>
<!--
<xsl:copy-of select="/"/>
-->
	</xsl:template>
</xsl:stylesheet>