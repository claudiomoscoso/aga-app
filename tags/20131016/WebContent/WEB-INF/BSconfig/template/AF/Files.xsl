<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Listado de Archivos</h1>
		<form action="ControlServlet" method="post" id="frm2">
			<input type="hidden" name="bsServiceName" id="bsServiceName2" value="AF.DelFile" />
			<input type="hidden" name="ID" id="ID2" />
		</form>

		<form action="ControlServlet" method="post" enctype="multipart/form-data" id="frm">
			<input type="hidden" name="bsServiceName" value="AF.UploadFile" />

			<table border="0">
				<tr>
					<td class="cLabel">Archivo:</td>
					<td>
						<input type="file" name="fldFile" id="fldFile" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Tipo:</td>
					<td>
						<select name="TypeFile" id="TypeFile">
							<option value="">
								<xsl:attribute name="selected" />
								- Tipo de Archivo-
							</option>
							<option value="CB">Conciliaci&#243;n Bancaria</option>
							<option value="CP">Cliente /Proveedor</option>
						</select>
					</td>
				</tr>			
				<tr>
					<td colspan="2">
						<input type="button" value="Procesar..." onclick="sendForm()"/>
					</td>
				</tr> 
			</table>
		</form>
		
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">ID</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Fecha Carga</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Estado</td>
				<td class="cHeadTD">Comentario</td>
				<td class="cHeadTD">Accion</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/ListFiles/Record">
				<tr>
					<td class="cDataTD" style="width:200px" valign="top">
						<a href="?bsServiceName=AF.GetFileContent&amp;ID={cID}&amp;Type={cType}">
							<xsl:value-of select="cID" />
						</a>
					</td>
					<td class="cDataTD" valign="top">
						<xsl:value-of select="cName" />
					</td>
					<td class="cDataTD" valign="top">
						<xsl:attribute name="nowrap"/>
						<xsl:value-of select="substring-after(cUploadDate, ';')" />&#160;<xsl:value-of select="cUploadDate_Time"/>
					</td>
					<td class="cDataTD" valign="top">
						<xsl:choose>
							<xsl:when test="cType='CB'">Conciliaci&#243;n Bancaria</xsl:when>
							<xsl:otherwise>Cliente/Proveedor</xsl:otherwise>
						</xsl:choose>
<!--
						<xsl:value-of select="cType" />
 -->
					</td>
					<td class="cDataTD" valign="top">
					<xsl:choose>
						<xsl:when test="cStatus='NEW'">Nuevo</xsl:when>
						<xsl:when test="cStatus='PRC'">Procesando</xsl:when>
						<xsl:when test="cStatus='BAD'">Archivo defectuoso</xsl:when>
						<xsl:when test="cStatus='OK'">Archivo correcto</xsl:when>
						<xsl:when test="cStatus='DONE'">Ya procesado</xsl:when>
					</xsl:choose>&#160;
<!-- 		<xsl:value-of select="cStatus" /> -->				
					</td>
					<td class="cDataTD" valign="top">
						<xsl:value-of select="cComment" />&#160;
					</td>
					<td class="cDataTD" valign="top">
						<xsl:choose>
							<xsl:when test="cStatus='BAD'">Defectuoso&#160;<a href="javascript:borrarArchivo('{cID}', '{cName}')">(X)Borrar</a></xsl:when>
							<xsl:when test="cStatus='PRC'">En proceso</xsl:when>
							<xsl:when test="cStatus='DONE'">Ya se proceso, no se puede modificar ni borrar.</xsl:when>
							<xsl:when test="StatusContent='PEND'">Pendiente&#160;<a href="javascript:borrarArchivo('{cID}', '{cName}')">(X)Borrar</a></xsl:when>
							<xsl:when test="StatusContent='OK'"><a href="javascript:processFile('{cID}', '{cName}')">Procesar para Flujo</a><br/><a href="javascript:borrarArchivo('{cID}', '{cName}')">(X)Borrar</a></xsl:when>
							<!-- 
							?bsServiceName=AF.ProcessToFlow&amp;ID={cID}
							 -->
							
						</xsl:choose>
					</td>
				</tr>	
			</xsl:for-each>
		</table>
	
  	  
	 <xsl:element name="textarea">
		 <xsl:copy-of select="/"/>
	 </xsl:element>
 
	  
	  <script src="public/js/AF/file.js">&#160;</script>
	  
	</xsl:template>
</xsl:stylesheet>
