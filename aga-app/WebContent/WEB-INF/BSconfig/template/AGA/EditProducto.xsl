<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<xsl:variable name="HaveLocal" select="/Service/Response/Fields/GetProductoByCode/Record/cID != ''" />
		<xsl:variable name="HaveFFOO" select="/Service/Response/Fields/GetProductoFFOO/Record/cID != ''" />
		<xsl:variable name="HaveMDB" select="/Service/Response/Fields/Producto/Record/Cuantos &gt; 0" />

		<script type="text/javascript" src="public/js/AGA09/EditProducto.js?rnd={/Service/Session/@ID}"></script>

		<input type="hidden" id="ID" value="{/Service/Response/Fields/GetProductoByCode/Record/cIDInterno}" />

		<h1 class="cTitle">
			Edici&#243;n de Producto
		</h1>

		<table>
			<tr>
				<td class="cLabel">C&#243;digo:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/GetProductoByCode/Record/cID" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Nombre:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/GetProductoByCode/Record/cNombre" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Observacion:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/GetProductoByCode/Record/cCertificado" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Tipo Producto:</td>
				<td class="cData">
					<select name="TipoProducto" id="TipoProducto" onchange="javascript:updateTipoProducto(this)">

						<xsl:for-each select="/Service/Response/Fields/ListTipoProducto/Record">
							<option value="{cID}">
								<xsl:if test="/Service/Response/Fields/GetProductoByCode/Record/cTipoProducto=cID">
									<xsl:attribute name="selected" />
								</xsl:if>
								<xsl:value-of select="cNombre" />
							</option>
						</xsl:for-each>
					</select>
				</td>
			</tr>
			<tr>
				<td class="cLabel">Estabilidad Garantizada:</td>
				<td class="cData">
					<input value="{/Service/Response/Fields/GetProductoByCode/Record/cEstabilidadGarantizada}" size="3" maxlength="5"
						onchange="javascript:updateEstabilidadGarantizada(this);" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Expiracion:</td>
				<td class="cData">
					<input value="{/Service/Response/Fields/GetProductoByCode/Record/cExpiracion}" size="3" maxlength="5"
						onchange="javascript:updateExpiracion(this);" />
				</td>
			</tr>
			<tr>
				<td class="cLabel">Metodo Preparacion:</td>
				<td class="cData">
					<select name="MetodoPreparacion" id="MetodoPreparacion" onchange="javascript:updateMetodoPreparacion(this)">
						<xsl:for-each select="/Service/Response/Fields/ListMetodoPreparacion/Record">
							<option value="{cID}">
								<xsl:if test="/Service/Response/Fields/GetProductoByCode/Record/cMetodoPreparacion=cID">
									<xsl:attribute name="selected" />
								</xsl:if>
								<xsl:value-of select="cNombre" />
							</option>
						</xsl:for-each>
					</select>
				</td>
			</tr>
		</table>
		<h2 class="cTitle2">Componentes</h2>
		<table class="cList" cellSpacing="0" cellPadding="0">
			<tr>
				<td class="cHeadTD">Relleno</td>
				<td class="cHeadTD">Componente</td>
				<td class="cHeadTD">Abreviacion</td>
				<td class="cHeadTD">Rango</td>
				<td class="cHeadTD">Unidad</td>
				<td class="cHeadTD">Accion</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/ListComponentByProduct/Record">
				<tr>
					<td class="cDataTD">
						<input type="checkbox" disabled="1">
							<xsl:if test="cRelleno='1'">
								<xsl:attribute name="checked" />
							</xsl:if>
						</input>
					</td>
					<td class="cDataTD">
						<xsl:value-of select="cDescripcionComponente" />
					</td>

					<td class="cDataTD">
						<xsl:value-of select="cAbreviacion" />
					</td>
					<td class="cDataTD">
						<xsl:value-of select="cRango" />
					</td>
					<td class="cDataTD">
						<xsl:value-of select="cUnidad" />
					</td>
					<td class="cDataTD">
						<img src="public/img/delete.jpg" style="width:22;cursor:pointer" onclick="javascript:confirmDelete('{cID}','{cDescripcionComponente}');" />
					</td>
				</tr>
			</xsl:for-each>
		</table>

		<input type="button" value="Nuevo..." onclick="javascript:showDetail()" />
		<br />

		<div id="detail" style="display:none">
			<h2 class="cTitle2">Relacion con componente</h2>
			
			<form action="?codigoGe={/Service/Request/Fields/codigoGe}" method="post" id="fSaveRelation">
				<input type="hidden" name="bsServiceName" value="AGA.AddRProductoComponente" />
				<input type="hidden" name="IDproducto" id="IDproducto" />
			
				<table>
					<tr>
						<td class="cLabel">Componente:</td>
						<td>
							<select Name="Componente" >
								<xsl:for-each select="/Service/Response/Fields/ListComponents/Record">
									<option value="{cID}">
										<xsl:value-of select="cNombre" />
									</option>
								</xsl:for-each>
							</select>
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="cLabel">Nombre:</td>
						<td>
							<input id="Nombre" />
						</td>
					</tr>
					 -->
					<tr>
						<td class="cLabel">Rango:</td>
						<td>
							<input Name="Rango" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<td class="cLabel">Unidad:</td>
						<td>
							<input Name="Unidad" maxlength="5"/>
						</td>
					</tr>
					<tr>
						<td class="cLabel">Relleno:</td>
						<td>
							<input id="RellenoCheckbox" type="checkbox"/>
							<input Name="Relleno" type="hidden"/>
						</td>
					</tr>
	
					<tr>
						<td colspan="2" align="center">
							<input value="Aceptar" type="button" onclick="javascript:saveNewDetail()"/>
							&#160;&#160;&#160;
							<input value="Cancelar" type="button" onclick="javascript:closeTooltip();" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<form action="?codigoGe={/Service/Request/Fields/codigoGe}" method="post" id="f">
			<input type="hidden" name="bsServiceName" value="AGA.DeleteComponent" />
			<input type="hidden" name="IDcomponent" id="IDcomponent" />
		</form>
<!-- 
		<form action="?codigoGe={/Service/Request/Fields/codigoGe}" method="post" id="f2">
			<input type="hidden" name="bsServiceName" value="AGA.SaveNewComponent" />
			<input type="hidden" name="IDcomponent" id="IDcomponent" />
		</form>
-->
		  <!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
  -->

	</xsl:template>
</xsl:stylesheet>