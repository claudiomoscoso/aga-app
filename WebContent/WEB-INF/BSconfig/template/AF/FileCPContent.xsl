<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" >
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
 -->
		<h1 class="cTitle">Contenido de archivo</h1>
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">Orden</td>
				<td class="cHeadTD">C&#243;digo Cuenta</td>
				<td class="cHeadTD">Descripcion Cuenta</td>
				<td class="cHeadTD">C&#243;digo Area Negocio</td>
				<td class="cHeadTD">Nombre Area Negocio</td>
				<td class="cHeadTD">C&#243;digo Auxiliar</td>
				<td class="cHeadTD">RUT</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Tipo Documento</td>
				<td class="cHeadTD">Numero Documento</td>
				<td class="cHeadTD">Fecha Emision</td>
				<td class="cHeadTD">Monto</td>
				<td class="cHeadTD">Estado</td>
			</tr>
			<tr>
				<xsl:for-each select="/Service/Response/Fields/ListFileCPContent/Record">
					<tr>
						<td class="cDataTD">
							<xsl:value-of select="cOrden" />
						</td>
						<td class="cDataTD"><xsl:attribute name="nowrap"/>
							<xsl:value-of select="cCodigoCuenta" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cDescCuenta" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cCodigoAreaNegocio" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cDescAreaNegocio" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cCodigoAuxiliar" />
						</td>
						<td class="cDataTD"><xsl:attribute name="nowrap"/>
							<xsl:value-of select="cRUT" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cNombre" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cTipoDocumento" />
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cNumeroDocumento" />
						</td>
						<td class="cDataTD">
						<xsl:attribute name="nowrap"/>
							<xsl:value-of select="cFechaEmision" />
						</td>
						<td class="cDataTD"  align="right">
							<xsl:value-of select="cMonto" />
						</td>
						<td class="cDataTD" >
							<xsl:choose>
								<xsl:when test="cStatus='GOOD'">Correcto
									<xsl:call-template name="SayUndo">
										<xsl:with-param name="fldID" select="cID" />
										<xsl:with-param name="fldFile" select="cFile" />
										<xsl:with-param name="DescCuenta" select="cDescCuenta" />				
									</xsl:call-template>
<!-- 
										<xsl:with-param name="TipoMovimiento" select="cTipoMovimiento" />
 -->
								</xsl:when>
								<xsl:when test="cStatus='NEW_'">Nuevo</xsl:when>
								<xsl:when test="cStatus='BAD_'">Erroneo</xsl:when>
								<xsl:when test="cStatus='PEND'">
									<a href="/{/Service/Context/SiteName}/?bsServiceName=AF.ConfirmCP&amp;ID={/Service/Request/Fields/ID}&amp;Type={/Service/Request/Fields/Type}&amp;fldID={cID}&amp;fldValid=1">Valido</a>&#160;
									<a href="/{/Service/Context/SiteName}/?bsServiceName=AF.ConfirmCP&amp;ID={/Service/Request/Fields/ID}&amp;Type={/Service/Request/Fields/Type}&amp;fldID={cID}&amp;fldValid=0">Invalido</a>
								</xsl:when>
								<xsl:when test="cStatus='INVA'">Invalido
									<xsl:call-template name="SayUndo">
										<xsl:with-param name="fldID" select="cID" />
										<xsl:with-param name="fldFile" select="cFile" />
										<xsl:with-param name="DescCuenta" select="cDescCuenta" />					
									</xsl:call-template>
								</xsl:when>
								
								<!-- 
								<xsl:when test="cStatus='IGNO'">Ignorado
									<xsl:call-template name="SayUndo">
										<xsl:with-param name="fldID" select="cID" />
										<xsl:with-param name="fldFile" select="cFile" />
										<xsl:with-param name="DescCuenta" select="cDescCuenta" />					
									</xsl:call-template>
								</xsl:when>
								 -->
								<xsl:when test="cStatus='IGNO'">Ignorado</xsl:when>								
								<xsl:otherwise>Error</xsl:otherwise>
							</xsl:choose><!-- (<xsl:value-of select="cStatus"/>)  -->
						</td>
					</tr>
				</xsl:for-each>
			</tr>
		</table>
  <!-- 
		<xsl:element name="textarea">
			<xsl:copy-of select="/" />
		</xsl:element>
  -->
	</xsl:template>
	
	<xsl:template name="SayUndo">
		<xsl:param name="fldID" />
		<xsl:param name="fldFile" />
		<xsl:param name="DescCuenta"/>
 
<!-- 
             .<xsl:value-of select="/Service/Response/Fields/TypesCP/Record[2]/cType"/>.
.<xsl:value-of select="$DescCuenta"/>.
		<xsl:value-of select="count(/Service/Response/Fields/TypesCP/Record[cType=normalize-space($DescCuenta)])"/>
-->

		<xsl:if test="count(/Service/Response/Fields/TypesCP/Record[cType=normalize-space($DescCuenta)])">
				<a href="?bsServiceName=AF.UnconfirmCP&amp;fldID={$fldID}&amp;ID={$fldFile}&amp;ID={/Service/Request/Fields/ID}&amp;Type={/Service/Request/Fields/Type}">Deshacer</a>&#160;
		</xsl:if>

	</xsl:template>
</xsl:stylesheet>