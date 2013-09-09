<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Modificacion de datos de usuario</h1>
		<form method="POST" action="ControlServlet">
			<input type="hidden" name="bsServiceName" value="USR.cambioClave" />
			<input type="hidden" name="fldID" value="{/Service/Response/Fields/SearchUser/cID}" />
			<table border="0">
				<tr>
					<td class="cLabel">Login</td>
					<td>
						<span class="cData">
							<xsl:value-of select="/Service/Response/Fields/SearchUser/cLogin" />
						</span>
					</td>
				</tr>
				<tr>
					<td class="cLabel">Nombre</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchUser/cName" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Mail</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchUser/cMail" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Rol</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchUser/cRolName" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Tel&#233;fono Fijo</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchUser/cPhone" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Tel&#233;fono M&#243;vil</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/SearchUser/cMovil" />
					</td>
				</tr>
				<tr>
					<td colspan="2">&#160;</td>
				</tr>
				<tr>
					<td class="cLabel">Nueva Password</td>
					<td>
						<input type="password" name="fldPassword1" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Confirme Password</td>
					<td>
						<input type="password" name="fldPassword2" />
					</td>
				</tr>

			</table>
			<br />
			<input type="submit" value="Aceptar" />
		</form>
		<!-- 
			<xsl:copy-of select="/" />
		-->
	</xsl:template>
</xsl:stylesheet>