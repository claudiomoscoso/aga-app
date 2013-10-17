<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h3 class="cTitle">Datos Analisis</h3>
		<xsl:variable name="GasPuro" select="/Service/Response/Fields/GetSolicitud/Record/cGaspuro=1"/>
		
		<table class="cList" cellspacing="0" cellpadding="0" id="tableAnalisis">
			<xsl:choose>
				<xsl:when test="$GasPuro">
					<tr>
						<td class="cHeadTD">Nombre Componente</td>
						<td class="cHeadTD">Impurezas</td>
						<td class="cHeadTD">Especificacion</td>
						<td class="cHeadTD">Unidad</td>
					</tr>
				</xsl:when>
				<xsl:otherwise>
					<tr>
						<td class="cHeadTD">Componente</td>
						<td class="cHeadTD">Sigla</td>
						<td class="cHeadTD">Requerido</td>
						<td class="cHeadTD">An&#225;lisis</td>
						<td class="cHeadTD">Unidad</td>
						<td class="cHeadTD">Desv. Relativa</td>
						<td class="cHeadTD">Desv. Absoluta</td>
					</tr>
				</xsl:otherwise>
			</xsl:choose>


			<xsl:choose>
				<xsl:when test="$GasPuro">
				<tbody>
					<xsl:for-each select="/Service/Response/Fields/ListComponentBySolicitud/Record">
						<tr>
							<td class="cDataTD"><xsl:value-of select="cNombre" /></td>
							<td class="cDataTD"><xsl:value-of select="cSigla" /></td>
							<td class="cDataTD"><xsl:value-of select="cRango" /></td>
							<td class="cDataTD"><xsl:value-of select="cUnidad" /></td>
						</tr>
					</xsl:for-each>
				</tbody>
						
			</xsl:when>
			<xsl:otherwise>			
				<tbody>
					<xsl:for-each select="/Service/Response/Fields/ListComponentBySolicitud/Record[cRelleno=0]">
						<tr>
							<td class="cDataTD">
								<xsl:choose>
									<xsl:when test="$GasPuro">
										<xsl:value-of select="cNombre" />								
									</xsl:when>
									<xsl:otherwise>
										<a href="javascript:openDialogComponent({position()}, '{cID}')">
											<xsl:value-of select="cNombre" />
										</a>
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<td class="cDataTD">
								<xsl:value-of select="cSigla" />
							</td>
							<td class="cDataTD"><xsl:value-of select="cRequerido" /></td>
							<td class="cDataTD"><xsl:value-of select="cAnalisis" /></td>
							<td class="cDataTD">
								<xsl:value-of select="cUnidad" />
							</td>
							<td class="cDataTD"><xsl:value-of select="cDesviacionRelativa" /></td>
							<td class="cDataTD"><xsl:value-of select="(cDesviacionRelativa div 100) * cAnalisis" /></td>
						</tr>
					</xsl:for-each>
				</tbody>
			</xsl:otherwise>
		</xsl:choose>

			<tfoot>
				<tr>
					<td class="cDataTD" colspan="7">&#160;</td>
				</tr>
				<xsl:for-each select="/Service/Response/Fields/ListComponentBySolicitud/Record[cRelleno=1]">
					<tr>
						<td class="cDataTD">
							<xsl:choose>
								<xsl:when test="$GasPuro">
									<xsl:value-of select="cNombre" />								
								</xsl:when>
								<xsl:otherwise>
									<a href="javascript:openDialogComponent(-1, '{cID}');">
										<xsl:value-of select="cNombre" />
									</a>
								</xsl:otherwise>
							</xsl:choose>
							
						</td>
						<td class="cDataTD">
							<xsl:value-of select="cSigla" />
						</td>
						<td class="cDataTD">&#160;</td>
						<td class="cDataTD">&#160;</td>
						<td class="cDataTD">
							<xsl:value-of select="cUnidad" />
						</td>
						<td class="cDataTD">&#160;</td>
						<td class="cDataTD">&#160;</td>
					</tr>
				</xsl:for-each>
			</tfoot>
		</table>
		<br />
		
		
		<xsl:if test="not($GasPuro)">
			<table border="1" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td align="left">
						<label class="cLabel" for="ToleranciaPreparacion">
							Tolerancia Preparaci&#243;n</label>
						<input size="2" maxlength="3" id="ToleranciaPreparacion"
							onFocus="javascript:currentValue=this.value;" 
							value="{/Service/Response/Fields/GetSolicitud/Record/cToleranciaPreparacion}" 
							onBlur="javascript:updateToleranciaPreparacion(this);"/>
					</td>
					<td align="left">
						<label class="cLabel" for="NivelConfianza">
							Nivel Confianza</label>
						<input size="2" maxlength="3" id="NivelConfianza"
							onFocus="javascript:currentValue=this.value;" 
							value="{/Service/Response/Fields/GetSolicitud/Record/cNivelConfianza}" 
							onBlur="javascript:updateNivelConfianza(this);"/>
						
					</td>
				</tr>
			</table>
		</xsl:if>
		<!-- AQUI COMIENZA EL TOOTLTIP -->

		<div id="divEditComponent" style="display:none;height:100px" border="1">
			<h3 class="cTitle">Datos de an&#225;lisis del componente</h3>
			<table>
				<tr>
					<td>
						<label class="cLabel" for="LabelNombreComponente">Componente</label>
					</td>
					<td class="cData" id="LabelNombreComponente"></td>
				</tr>
				<tr>

					<td>
						<label class="cLabel" for="LabelSiglaComponente">Sigla</label>
					</td>
					<td class="cData" id="LabelSiglaComponente"></td>
				</tr>
				<tr>
					<td>
						<label class="cLabel" for="Requerido">Requerido</label>
					</td>
					<td>
						<input id="Requerido" size="10" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td>
						<label class="cLabel" for="AnalisisComponente">Analisis</label>
					</td>
					<td>
						<input id="AnalisisComponente" size="10" maxlength="10" onBlur="javascript:calculaDesvAbs()"/>
					</td>
				</tr>
				<tr>
					<td>
						<label class="cLabel">Unidad</label>
					</td>
					<td class="cData" id="LabelUnidad"></td>
				</tr>
				<tr>
					<td>
						<label class="cLabel" for="DesviacionRelativa">Desviacion Relativa</label>
					</td>
					<td>
						<input id="DesviacionRelativa" size="10" maxlength="10" onBlur="javascript:calculaDesvAbs()"/>
					</td>
				</tr>
				<tr>
					<td>
						<label class="cLabel" for="DesviacionAbsoluta">Desviacion Absoluta</label>
					</td>
					<td>
						<input id="DesviacionAbsoluta">
							<xsl:attribute name="readonly" />
						</input>
					</td>
				</tr>

			</table>

			<input type="button" value="Aceptar" onclick="javascript:saveAnalisisComponent()" />
			&#160;&#160;
			<a href="javascript:closeTooltip()">Cancelar...</a>
			<input type="hidden" id="indexOfTable" />
			<input type="hidden" id="indexID" />
			
		</div>


	</xsl:template>
</xsl:stylesheet>