<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<H1 class="cTitle">Listado de Usuarios</H1>
		<hr/>
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">ID</td>
				<td class="cHeadTD">Login</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Mail</td>
				<td class="cHeadTD">Rol</td>
				<td class="cHeadTD">Acciones</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/listUser/Record">
				<tr>
					<td class="cDataTD">&#160;<xsl:value-of select="cID"/></td>
					<td class="cDataTD">&#160;<a href="ControlServlet?bsServiceName=USR.editUser&amp;fldID={cID}"><xsl:value-of select="cLogin"/></a></td>
					<td class="cDataTD">&#160;<xsl:value-of select="cName"/></td>
					<td class="cDataTD"><a href="mailto:{cMail}"><xsl:value-of select="cMail"/></a></td>
					<td class="cDataTD">&#160;<xsl:value-of select="RolName"/></td>
					<td class="cDataTD" align="center">
						<a href="ControlServlet?bsServiceName=USR.confirmDeleteUser&amp;fldID={cID}">(X)</a>
						&#160;&#160;
						<a href="ControlServlet?bsServiceName=USR.readActivity&amp;fldID={cID}">Auditar...</a>
					</td>
				</tr>
			</xsl:for-each>
		</table>
		<hr/>
		<center>
			<input type="button" name="fldNuevo" onclick="redirect('USR.newUserForm')" value="Nuevo Usuario..."/>
		</center>
<!-- 
<xsl:copy-of select="/"/>
 -->
	</xsl:template>
</xsl:stylesheet>