<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">

		<h1 class="cTitle">Actividad de usuario</h1>

		<table width="100%">
			<tr>
				<td class="cLabel">ID:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/SearchUser/cID" />
				</td>

				<td class="cLabel">Login:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/SearchUser/cLogin" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Nombre:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/SearchUser/cName" />
				</td>
<!-- 
				<td class="cLabel">Rol:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/SearchUser/cRolName" />
				</td>
 -->
			</tr>
		</table>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">Fecha</td>
				<td class="cHeadTD">Actividad</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/Activity/Record">
				<tr>
					<td class="cDataTD" nowrap="">
						<xsl:value-of select="substring-after(cDateEvent, ';')" />
						&#160;
						<xsl:value-of select="cDateEvent_Time" />
					</td>
					<td class="cDataTD" nowrap="">
						<xsl:value-of select="cMessage" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
		<hr />

<!--
			<form action="ControlServlet">
			<input type="hidden" name="bsServiceName" value="SERVICE_NAME"/>
			<input type="submit" value="Buscar..."/>
			</form>


			<xsl:copy-of select="/"/>
-->
	</xsl:template>
</xsl:stylesheet>