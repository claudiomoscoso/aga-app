<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Listado de P&#225;ginas y Contenidos</H1>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">Id</td>
				<td class="cHeadTD">Descripci&#243;n</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Eliminar</td>
			</tr>

			<xsl:for-each select="/Service/Response/Fields/ListPages/Record">
				<tr>
					<td class="cDataTD">
						<xsl:value-of select="cID" />
					</td>
					<td class="cDataTD">
						<xsl:choose>
							<xsl:when test="cIsFile=1">
								<a href="/{/Service/Context/SiteName}/?bsServiceName=ACO.EditFile&amp;fldID={cID}">
									<xsl:value-of select="cDescription" />
								</a>
							</xsl:when>
							<xsl:otherwise>
								<a
									href="/{/Service/Context/SiteName}/?bsServiceName=ACO.EditPage&amp;fldID={cID}&amp;fldNewPage=0">
									<xsl:value-of select="cDescription" />
								</a>
							</xsl:otherwise>
						</xsl:choose>
					</td>
					<td class="cDataTD">
						<xsl:choose>
							<xsl:when test="cIsFile=0">Contenido</xsl:when>
							<xsl:otherwise>Archivo</xsl:otherwise>
						</xsl:choose>
					</td>
					<td class="cDataTD">
						<xsl:choose>
							<xsl:when test="cID=0 and cIsFile=0">Eliminar</xsl:when>
							<xsl:otherwise>
								<a href="javascript:eliminar({cID}, {cIsFile});">Eliminar</a>
							</xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
			</xsl:for-each>

		</table>
		<br />
		<input type="button" value="Nueva P&#225;gina..." onclick="javascript:newPage();" />
		&#160;&#160;
		<input type="button" value="Subir Archivo..." onclick="javascript:newFile();" />

		<!-- 
			<input type="button" value="Nuevo Miembro Beneficiario..." onclick="javascript:nuevoMMBB();" >
			<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
			<xsl:attribute name="disabled"/>
			</xsl:if>
			</input>
			&#160;&#160;&#160;
			<input type="button" value="Nuevo Dirigente o Guiadora..." onclick="javascript:nuevoDyG();" >
			<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
			<xsl:attribute name="disabled"/>
			</xsl:if>
			</input>
			&#160;&#160;&#160;
			<input type="button" value="Registro de Apoderados..." onclick="javascript:apoderado();" >
			<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
			<xsl:attribute name="disabled"/>
			</xsl:if>
			</input>
		-->

		<form name="f" id="f" method="post" action="ControlServlet">
			<input type="hidden" name="bsServiceName" />
			<input type="hidden" name="fldNewPage" value="1" />
			<input type="hidden" name="fldID" id="fldID" />
		</form>

		<!-- 
			<xsl:copy-of select="/" />
		-->

		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){

				}

				function newPage(){

				document.getElementById("f").bsServiceName.value = "ACO.NewPage";

				document.getElementById("f").submit();

				}

				function newFile(){

				document.getElementById("f").bsServiceName.value = "ACO.NewFile";

				document.getElementById("f").submit();

				}

				function eliminar(id, isFile){

				var msg = "¿Esta seguro de eliminar este ";

				if(isFile){

				msg += "archivo?";

				document.getElementById("f").bsServiceName.value = "ACO.DeleteFile";

				} else {

				msg += "contenido?";

				document.getElementById("f").bsServiceName.value = "ACO.DeleteContent";

				}

				if(confirm(msg)){

				document.getElementById("f").fldID.value = id;

				document.getElementById("f").submit(); }

				}


			</xsl:comment>
		</script>

	</xsl:template>

</xsl:stylesheet>