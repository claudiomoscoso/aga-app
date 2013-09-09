<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<xsl:variable name="UsaLote" select="/Service/Response/Fields/GetProductoById/Record/cUsaLote=1 or /Service/Response/Fields/GetProducto/Record/cUsaLote = 1"/>

		<xsl:element name="script">
			var tipoCilindro = "<xsl:value-of select="normalize-space(/Service/Response/Fields/GetSolicitud/Record/cTipoCilindro)" />";
			var valvula = "<xsl:value-of select="normalize-space(/Service/Response/Fields/GetSolicitud/Record/cValvula)" />";
			var usaLote = <xsl:value-of select="$UsaLote" />;
		</xsl:element>

<!-- 
+<xsl:value-of select="/Service/Response/Fields/GetProductoById/Record/cUsaLote"/>+
+<xsl:value-of select="/Service/Response/Fields/GetProducto/Record/cUsaLote"/>+
 -->

		<h3 class="cTitle">Datos del cilindro</h3>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td align="left" valign="top">
					<table border="1" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td colspan="3" align="center">
								<label class="cLabel" for="TipoCilindro">Tipo Cilindro</label>
							</td>
						</tr>
						<tr>
							<td>
								<select id="TipoCilindro" 
									onBlur="javascript:updateTipoCilindro(this);" 
									onFocus="javascript:currentValue=this.value;">
									<xsl:for-each select="/Service/Response/Fields/ListTipoCilindro/Record">
										<option value="{normalize-space(cID)}">
											<xsl:value-of select="cNombre" />
										</option>
									</xsl:for-each>
								</select>
							</td>
							<td>
								<input id="Litros" maxlength="4" size="4"  style="text-align:right"
									value="{/Service/Response/Fields/GetSolicitud/Record/cLitros}" 
									onFocus="javascript:currentValue=this.value;"
									onBlur="javascript:updateLitros(this);" />
							</td>
							<td class="cLabel">LTS</td>
						</tr>

						<tr>
							<td>
								<label for="PoLlenado" class="cLabel">P&#176; Llenado</label>
							</td>
							<td colspan="2">
								<input id="PoLlenado" maxlength="4" size="4"  style="text-align:right"
									value="{/Service/Response/Fields/GetSolicitud/Record/cPoLlenado}" 
									onFocus="javascript:currentValue=this.value;" 
									onBlur="javascript:updatePoLlenado(this);" />
							</td>

						</tr>
						<tr>
							<td ><label for="VolumenGas" class="cLabel">
								Volumen Gas</label>
							</td>
							<td colspan="2">
								<input id="VolumenGas" maxlength="5" size="5"  style="text-align:right"><xsl:attribute name="readonly"/></input>
							</td>
						</tr>
						<tr>
							<td colspan="3" class="cLabel" align="center">Valvula</td>
						</tr>
						<tr>
							<td colspan="3">
								<select id="Valvula"
									onBlur="javascript:updateValvula(this);" 
									onFocus="javascript:currentValue=this.value;">
									<xsl:for-each select="/Service/Response/Fields/ListValvula/Record">
										<option value="{normalize-space(cID)}">
											<xsl:value-of select="cNombre" />
										</option>
									</xsl:for-each>
								</select>
							</td>
						</tr>
					</table>
				</td>
				<td align="left" valign="top">
					<table border="1" cellpadding="1" cellspacing="1">
						<tr>
							<td class="cLabel" colspan="2">Expiraci&#243;n</td>
							<td colspan="2">
								<input size="2" style="text-align:right"
									value="{/Service/Response/Fields/GetSolicitud/Record/cExpiracion}" 
									onFocus="javascript:currentValue=this.value;" 
									onBlur="javascript:updateExpiracion(this);" />
								
							<label class="cLabel">meses</label></td>
						</tr>

						<tr>
							<td class="cLabel" colspan="2">T&#176; min. uso (&#176;C)</td>
							<td colspan="2">
								<input size="3" style="text-align:right" 
									value="{/Service/Response/Fields/GetSolicitud/Record/cTMinUso}" 
									onFocus="javascript:currentValue=this.value;" 
									onBlur="javascript:updateTMinUso(this);" />
							</td>

						</tr>

						<tr>
							<td class="cLabel" colspan="2">P&#176; min. uso (bar)</td>
							<td colspan="2">
								<input size="3" style="text-align:right"
									value="{/Service/Response/Fields/GetSolicitud/Record/cPMinUso}" 
									onFocus="javascript:currentValue=this.value;" 
									onBlur="javascript:updatePMinUso(this);" />
								
							</td>

						</tr>
						<tr>
							<td colspan="4">&#160;</td>
						</tr>

						<xsl:choose>
							<xsl:when test="$UsaLote">
								<tr>
									<td valign="top">
										<label class="cLabel" for="Lote">Numero Lote:</label>
									</td>
									<td colspan="2">
										<select size="5" id="LotesFO" >
										<xsl:for-each select="/Service/Response/Fields/ListLoteFO/Record">
											<option value="{normalize-space(cID)}">
												<xsl:if test="normalize-space(/Service/Response/Fields/GetSolicitud/Record/cLoteAGA)=normalize-space(cID)">
													<xsl:attribute name="selected"/>
												</xsl:if>
											 
												<xsl:value-of select="cNumeroLote" /> (<xsl:value-of select="normalize-space(cSucursal)" />, <xsl:value-of select="substring-after(cFecha, ';')" />)
												 
											</option>
										</xsl:for-each>
											
										</select>
									</td>
									<td valign="top">
										<input size="6" id="BuscarCilindros" value="Buscar" type="button" onclick="javascript:loadCilindros()"/>
									</td>
								</tr>
							</xsl:when>
							<xsl:otherwise>
								<tr>
									<td colspan="2">
										<label class="cLabel" for="Cilindro">Numero Cilindro/Volumen</label>
									</td>
									<td>
										<input size="10" maxlength="20" style="text-align:left" id="Cilindro" />
									</td>
									<td>
										<input size="10" maxlength="20" style="text-align:left" id="Volumen" />
									</td>
								</tr>
							</xsl:otherwise>
						</xsl:choose>
						
						
						<tr>
							<td class="cLabel" colspan="4" align="center">Cilindros del Lote(nro y vol)</td>
						</tr>
						<tr>
							<td>
								<xsl:attribute name="colspan">
									<xsl:choose>
										<xsl:when test="$UsaLote">4</xsl:when>
										<xsl:otherwise>3</xsl:otherwise>
									</xsl:choose>
								</xsl:attribute>
							
								<select size="5" style="width: 100%;" id="ListLote" onclick="javascript:selectListLote()" >
									<xsl:for-each select="/Service/Response/Fields/ListLote/Record">
										<option value="{normalize-space(cID)}">
											<xsl:value-of select="cCilindro" /> - <xsl:value-of select="cVolumen" />
										</option>
									</xsl:for-each>
								</select>
								
							</td>
							<xsl:choose>
								<xsl:when test="not($UsaLote)">
									<td align="center">
						
										<input type="button" value="&#160;&#160;&#160;+&#160;&#160;&#160;" onclick="javascript:addLote()" />
										<br />
										<input type="button" value="&#160;&#160;&#160;-&#160;&#160;&#160;" onclick="javascript:delLote()" />
						
									</td>
								</xsl:when>
							</xsl:choose>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</xsl:template>
</xsl:stylesheet>