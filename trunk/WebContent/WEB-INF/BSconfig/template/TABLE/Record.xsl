<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<form action="ControlServlet" method="post" id="f">

			<table width="100%">
				<tr>
					<td class="cLabel">ID:</td>
					<xsl:choose>
						<xsl:when test="/Service/Request/Fields/fldNew=1">
							<td class="cData">
								[Nuevo]
								<input type="hidden" name="fldID" />
								<input type="hidden" name="bsServiceName" value="TABLE.SaveRecord" />
							</td>
						</xsl:when>
						<xsl:otherwise>
							<td>
								<input type="text" readonly="" size="20" name="fldID" value="{/Service/Request/Fields/fldID}" />
								<input type="hidden" name="bsServiceName" id="bsServiceName" value="TABLE.UpdateRecord" />
							</td>
						</xsl:otherwise>
					</xsl:choose>
				</tr>
				<tr>
					<td class="cLabel">Descripcion:</td>
					<td>
						<input maxLength="100" size="100" name="fldName" value="{/Service/Response/Fields/Table/Record/Name}" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="fldNew" value="{/Service/Request/Fields/fldNew}" />
		</form>
		<input type="button" value="Aceptar" onclick="javascript:document.getElementById('f').submit();" />
		&#160;&#160;&#160;
		<input type="button" value="Cancelar"
			onclick="javascript:document.getElementById('bsServiceName').value='TABLE.ShowTable';document.getElementById('f').submit();" />
	</xsl:template>
</xsl:stylesheet>
