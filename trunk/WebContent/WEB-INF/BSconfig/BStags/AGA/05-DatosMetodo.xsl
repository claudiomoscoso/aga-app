<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<xsl:element name="script">
			var patrons = new Array();
			<xsl:for-each select="/Service/Response/Fields/ListPatronOfSolicitud/Record">
				patrons[<xsl:value-of select="position()" />] = "<xsl:value-of select="cID" />#<xsl:value-of select="cCilindro" />#<xsl:value-of select="substring-after(cFechaExpiracion, ';')" />";
			</xsl:for-each>
		</xsl:element>

		<h3 class="cTitle">Datos Metodo</h3>

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align="left" valign="top">
					<table border="1" cellpadding="0" cellspacing="0" class="cList">
						<tr>
							<td class="cHeadTD">Metodo Analitico</td>
						</tr>

						<xsl:for-each select="/Service/Response/Fields/ListMetodo/Record">
							<tr>
								<td class="cDataTD">
									<input type="checkbox" value="{cID}" id="ListMetodo{normalize-space(cID)}" onclick="javascript:updateMetodo(this)">
										<xsl:choose>
											<xsl:when test="cSelected=1">
												<xsl:attribute name="Checked" />
											</xsl:when>
										</xsl:choose>
									</input>
									<label for="ListMetodo{normalize-space(cID)}"><xsl:value-of select="cNombre" /></label>
								</td>
							</tr>

						</xsl:for-each>

					</table>
				</td>
				<td align="left" valign="top">
					<table border="1" cellpadding="0" cellspacing="0" class="cList">
						<tr>
							<td class="cHeadTD">Patron Usado</td>
						</tr>
						<tr>
							<td class="cDataTD">
<!-- 
								<input type="text" size="3" maxlength="5" id="NumeroPatron" style="text-align:right"
									value="{/Service/Response/Fields/GetSolicitud/Record/cNumeroPatron}" 
									onFocus="javascript:currentValue=this.value;"
									onBlur="javascript:updateNumeroPatron(this);" />
-->	
								<select id="Patron" onchange="javascript:cambiaPatron()">
									<xsl:for-each select="/Service/Response/Fields/ListPatron/Record">
										<option value="{cID}#{cCilindro}#{substring-after(cFechaExpiracion, ';')}">
											<xsl:value-of select="cNumero" />
											-
											<xsl:value-of select="cCompuesto" />
											:
											<xsl:value-of select="cComposicion" />
											(<xsl:value-of select="substring-after(cFechaExpiracion, ';')"/>)
										</option>
									</xsl:for-each>
								</select>
							</td>
						</tr>
						<tr>
							<td class="cDataTD">
								<label for="CilindroPatron">Cilindro</label>
								<input type="text" readonly="1" id="CilindroPatron" size="20" maxlength="20" />
							&#160;&#160;&#160;
								<label for="VencimientoPatron">Fecha Vencimiento</label>
								<input type="text" readonly="1" id="VencimientoPatron" size="10" maxlength="10" />
							</td>
						</tr>
						
						<tr>
							<td class="cDataTD" align="center">
								<input type="button"  onclick="javascript:addPatronSolicitud();" value="&#160;&#160;+&#160;&#160;" />&#160;&#160;&#160;
								<input type="button"  onclick="javascript:delPatronSolicitud();" value="&#160;&#160;-&#160;&#160;" />
							</td>
						</tr>
						<tr>
							<td class="cDataTD" align="center">
								<select size="3" id="Patrones" style="width: 100%;" />
								
							</td>
						</tr>
							
					</table>
				</td>
			</tr>
		</table>
	</xsl:template>
</xsl:stylesheet>