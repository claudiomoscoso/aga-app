<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:decimal-format name="NUM-CL" decimal-separator="," grouping-separator="." minus-sign="-"/>
	<xsl:template match="/">
		<h1 class="cTitle">Contenido de archivo</h1>
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">Orden</td>
				<td class="cHeadTD">Area de Negocio</td>
				<td class="cHeadTD">Banco</td>
				<td class="cHeadTD">Fecha</td>
				<td class="cHeadTD">Tipo de Movimiento</td>
				<td class="cHeadTD">Tipo Documento</td>
				<td class="cHeadTD">Monto</td>
				<td class="cHeadTD">Descripcion</td>
				<td class="cHeadTD">Seleccion</td>
			</tr>
			<tr>
				<xsl:for-each select="/Service/Response/Fields/ListFileCBContent/Record">
					<tr>
						<td class="cDataTD" valign="top">
							<xsl:value-of select="cOrden" />
						</td>
						<td class="cDataTD" valign="top">
							<xsl:attribute name="nowrap" />
							<xsl:call-template name="GetEnterpriseName">
								<xsl:with-param name="Code" select="cAreaNegocio" />
							</xsl:call-template>
						</td>
						<td class="cDataTD" valign="top">
							<xsl:attribute name="nowrap" />
							<xsl:value-of select="cBanco" />
						</td>

						<td class="cDataTD" valign="top">
							<xsl:choose>
								<xsl:when test="cTipoDocumento='CH' and cEntityType=''">
									<xsl:choose>
										<xsl:when test="cFechaEstimada">
											<input id="Fecha{cID}" value="{cFechaEstimada}" size="8px" />
										</xsl:when>
										<xsl:otherwise>
											<input id="Fecha{cID}" value="{cFecha}" size="8px" />
										</xsl:otherwise>
									</xsl:choose>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="cFecha" />
								</xsl:otherwise>
							</xsl:choose>
						</td>
						<td class="cDataTD" valign="top" align="center">
							<xsl:choose>
								<xsl:when test="cTipoMovimiento='I'">
									Ingreso
								</xsl:when>
								<xsl:when test="cTipoMovimiento='E'">
									Egreso
								</xsl:when>
								<xsl:otherwise>
									-
								</xsl:otherwise>
							</xsl:choose>
						</td>
						<td class="cDataTD" valign="top">
							<xsl:value-of select="cTipoDocumento" />
						</td>
						<td class="cDataTD" valign="top" align="right">
							<xsl:value-of select="format-number(cMonto, '###.###', 'NUM-CL')"/>
						<!-- 
							<xsl:value-of select="cMonto" />
							 -->
						</td>
						<td class="cDataTD" valign="top">
							<a name="{cID}" />
							<xsl:value-of select="cDescripcion" />

						</td>
						<td class="cDataTD" valign="top">
							<xsl:attribute name="nowrap" />
							 
							<xsl:choose>
								<!--
									<xsl:when test="/Service/Response/Fields/GetFileInfo/Record/cStatus='DONE'"> OK </xsl:when>
								-->
								<xsl:when test="cStatus='GOOD'">
									<xsl:call-template name="EntityTypeName">
										<xsl:with-param name="EntityType" select="cEntityType" />
									</xsl:call-template>
									<!-- 
<xsl:if test="count">
				</xsl:if> 
-->
									<xsl:call-template name="SayUndo">
										<xsl:with-param name="TipoMovimiento" select="cTipoMovimiento" />
										<xsl:with-param name="fldID" select="cID" />
										<xsl:with-param name="fldFile" select="cFile" />
									</xsl:call-template>
								</xsl:when>
								<xsl:when test="cStatus='PEND'">
									<xsl:call-template name="BankingTypes4TipoMovimiento">
										<xsl:with-param name="TipoDocumento" select="cTipoDocumento"/>
										<xsl:with-param name="TipoMovimiento" select="cTipoMovimiento" />
										<xsl:with-param name="fldID" select="cID" />
										<xsl:with-param name="fldFile" select="cFile" />
										
									</xsl:call-template>
								</xsl:when>
								<xsl:when test="cStatus='WRON'">
								<b>Registro erroneo</b>								
								</xsl:when>
							</xsl:choose>
							<!--
								' <xsl:value-of select="cStatus" /> ' x <a
								href="/{/Service/Context/SiteName}/?bsServiceName=AF.ConfirmCP&amp;ID={/Service/Request/Fields/ID}&amp;Type={/Service/Request/Fields/Type}&amp;fldID={cID}&amp;fldValid=0">xdx</a>
								x
							-->
						</td>
					</tr>
				</xsl:for-each>
			</tr>
		</table>

		<br/>
		<a href="?bsServiceName=AF.Files">Volver</a>

<!-- 
		<xsl:element name="textarea">
			<xsl:copy-of select="/" />
		</xsl:element>
 -->

		<xsl:element name="script">
			<xsl:comment>
			function acceptType(documentType, idRecord, idType, file){
				var msg = '';
				var fecha = '';
				if(documentType==='CH'){
					fecha = document.getElementById('Fecha'+idRecord).value;
					msg = validaFecha2(fecha, '/');
					if(msg!=''){
						alert(msg);
					}
				}
				var url = '?bsServiceName=AF.ConfirmCB&#38;fldID=' + idRecord + '&#38;fldType=' + idType + '&#38;ID=' + file + '&#38;date='+fecha+'#' + idRecord;
				//alert(url);
				if(msg===''){
					window.location=url;
				}
			}
			</xsl:comment>
		</xsl:element>

<!-- 
//	
//	<a href="?bsServiceName=AF.ConfirmCB&amp;fldID={$fldID}&amp;fldType={cID}&amp;ID={$fldFile}#{$fldID}">
 -->


	</xsl:template>
	<xsl:template name="GetEnterpriseName">
		<xsl:param name="Code" />
		<!-- 
		<xsl:value-of select="$Code"/>
		 -->
		<xsl:value-of select="/Service/Response/Fields/GetEnterprisesTable/Record[cID=$Code]/cName" />
	</xsl:template>

	<xsl:template name="EntityTypeName">
		<xsl:param name="EntityType" />
		<xsl:value-of select='/Service/Response/Fields/TypesCB/Record[cID=$EntityType]/cName' />
	</xsl:template>


	<xsl:template name="BankingTypes4TipoMovimiento">
		<xsl:param name="TipoDocumento"/>
		<xsl:param name="TipoMovimiento" />
		<xsl:param name="fldID" />
		<xsl:param name="fldFile" />

		<xsl:for-each select="/Service/Response/Fields/TypesCB/Record[cType=$TipoMovimiento]">

			<a href="javascript:acceptType('{$TipoDocumento}', '{$fldID}', '{cID}', '{$fldFile}');">
				<xsl:value-of select="cName" />
			</a>		
		<!--  
			<a href="?bsServiceName=AF.ConfirmCB&amp;fldID={$fldID}&amp;fldType={cID}&amp;ID={$fldFile}#{$fldID}">
				<xsl:value-of select="cName" />
			</a>
			-->
			<br />
		</xsl:for-each>
		<!-- 	 
		<xsl:value-of select='/Service/Response/Fields/TypesCB/Record[cID=$EntityType]/cName' />
		 -->
	</xsl:template>

	<xsl:template name="SayUndo">
		<xsl:param name="TipoMovimiento" />
		<xsl:param name="fldID" />
		<xsl:param name="fldFile" />
		<xsl:if test="/Service/Response/Fields/GetFileInfo/Record/cStatus != 'DONE'">
			<xsl:if test="count(/Service/Response/Fields/TypesCB/Record[cType=$TipoMovimiento])&gt;1">
				&#160;<a href="?bsServiceName=AF.UnconfirmCB&amp;fldID={$fldID}&amp;ID={$fldFile}#{$fldID}">Deshacer</a>
		</xsl:if></xsl:if>
	</xsl:template>

</xsl:stylesheet>