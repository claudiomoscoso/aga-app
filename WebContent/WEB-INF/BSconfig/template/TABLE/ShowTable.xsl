<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Listado</H1>
		<hr />
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">ID</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Eliminar</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/Table/Record">
				<xsl:sort data-type="text" select="Name" order="ascending"/>
				<tr>
					<td class="cDataTD">
						&#160;
						<a href="ControlServlet?bsServiceName=TABLE.Record&amp;fldID={ID}&amp;fldNew=0">
							<xsl:value-of select="ID" />
						</a>
					</td>
					<td class="cDataTD">
						&#160;
						<xsl:value-of select="Name" />
					</td>
					<td class="cDataTD" align="center">
						<a href="javascript:eliminar('{ID}');">(X)</a>
					</td>
				</tr>
			</xsl:for-each>
		</table>
		<hr />
		<center>
			<input type="button" value="Nuevo..." onclick="javascript:newRecord();" />
		</center>

		<form name="f" id="f" method="post" action="ControlServlet">
			<input type="hidden" name="bsServiceName" />
			<input type="hidden" name="fldNew" id="fldNew" />
			<input type="hidden" name="fldID" id="fldID" />
		</form>

		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){

				}

				function newRecord(){

				document.getElementById("f").bsServiceName.value = "TABLE.Record";

				document.getElementById("f").fldNew.value = "1";

				document.getElementById("f").submit();

				}

				function eliminar(id){

				var msg = "¿Esta seguro de eliminar este registro?";

				if(confirm(msg)){

				document.getElementById("f").bsServiceName.value = "TABLE.DeleteRecord";

				document.getElementById("f").fldID.value = id;

				document.getElementById("f").submit(); }

				}


			</xsl:comment>
		</script>


	</xsl:template>

</xsl:stylesheet>
