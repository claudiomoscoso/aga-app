<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Datos de nuevo Rol</h1>
		<form method="POST" action="ControlServlet"><input type="hidden"
			name="bsServiceName" value="USR.saveNewRol" />
		<table>
			<tr>
				<td class="cLabel">ID</td>
				<td class="cData">Nuevo</td>
			</tr>
			<tr>
				<td class="cLabel">Nombre</td>
				<td><input type="text" name="fldName" /></td>
			</tr>
		</table>
		<input type="submit" value="Aceptar" />&#160; <input type="button"
			value="Cancelar" onclick="redirect('USR.ListRol');" /></form>
	</xsl:template>
</xsl:stylesheet>
