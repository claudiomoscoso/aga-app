<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<H1 class="cTitle">Propiedades de Archivo</H1>
		<form name="f1" id="f1" action="ControlServlet" method="post">
			<input type="hidden" name="bsServiceName" value="ACO.UpdateFile" />

			<table border="0" width="100%">
				<tr>
					<td class="cLabel">Código:</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetFile/Record/cID" />
						<input type="hidden" name="fldID" value="{/Service/Response/Fields/GetFile/Record/cID}" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Descripción:</td>
					<td>
						<input type="text" name="fldDescription" value="{/Service/Response/Fields/GetFile/Record/cDescription}" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Archivo:</td>
					<td class="cData">
						<xsl:value-of select="/Service/Response/Fields/GetFile/Record/cFileName" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">URL:</td>
					<td class="cData">
						<input readonly="1" size="100"
							value="/{/Service/Context/SiteName}/public/ACO/{/Service/Response/Fields/GetFile/Record/cFileName}" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<img alt="{/Service/Response/Fields/GetFile/Record/cDescription}" src="/{/Service/Context/SiteName}/public/ACO/{/Service/Response/Fields/GetFile/Record/cFileName}" />
					</td>
				</tr>
			</table>
		</form>

		<form name="f2" id="f2" action="ControlServlet" method="post">
			<input type="hidden" name="bsServiceName" value="ACO.ListPages" />

		</form>
		<input type="button" value="Aceptar" onclick="javascript:aceptar();" />
		&#160;&#160;
		<input type="button" value="Cancelar" onclick="javascript:cancelar();" />
		<script type="text/javascript">
			<xsl:comment>

				function cancelar(){document.getElementById("f2").submit();}

				function aceptar(){

				var desc = document.getElementById("f1").fldDescription;

				if(desc.value.length == 0){ alert("Descripcion no se ha especificado"); return;}

				document.getElementById("f1").submit(); }

			</xsl:comment>
		</script>
	</xsl:template>
</xsl:stylesheet>