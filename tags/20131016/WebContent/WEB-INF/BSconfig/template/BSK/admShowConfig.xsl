<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">
		<h1 class="cTitle">Configuracion</h1>
		<h2 class="cTitle2">Tipos de imagenes</h2>
		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">C@oacute;digo</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Tipo</td>
				<td class="cHeadTD">Accion</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/Images/Record">
				<xsl:sort select="cTable" order="descending" />

				<xsl:choose>
					<xsl:when test="/Service/Request/Fields/Action='EDIT' and cID=/Service/Request/Fields/fldID">
						<tr>
							<td class="cDataTD">
								<input type="hidden" name="cID" value="{cID}" />
								<b>
									<xsl:value-of select="cID" />
								</b>
							</td>
							<td class="cDataTD">
								<input type="text" name="cName" value="{cName}" />
							</td>
							<td>
								<select name="cTable">
									<option value="tProductType">
										<xsl:if test="cTable='tProductType'">
											<xsl:attribute name="selected" />
										</xsl:if>
										Categoria
									</option>
									<option value="tProduct">
										<xsl:if test="cTable='tProduct'">
											<xsl:attribute name="selected" />
										</xsl:if>
										Producto
									</option>
								</select>

							</td>
							<td class="cDataTD">
								<a href="#"
									onclick="javascript:FRM.Action.value='NONE';FRM.bsServiceName.value='BSK.UpdateImage';FRM.fldID.value=cID.value;FRM.fldName.value=cName.value;FRM.fldTable.value=cTable.value;FRM.submit();">
									Aceptar...
								</a>
								&#160;&#160;&#160;
								<a href="#"
									onclick="javascript:FRM.Action.value='NONE';FRM.bsServiceName.value='BSK.ReadConfig';FRM.submit();">
									Cancelar
								</a>
							</td>
						</tr>

					</xsl:when>
					<xsl:otherwise>
						<tr>
							<td class="cDataTD">
								<xsl:value-of select="cID" />
							</td>
							<td class="cDataTD">
								<xsl:value-of select="cName" />
							</td>
							<td class="cDataTD">
								<xsl:choose>
									<xsl:when test="cTable='tProductType'">Categoria</xsl:when>
									<xsl:otherwise>Producto</xsl:otherwise>
								</xsl:choose>

							</td>
							<td class="cDataTD">
								<a href="#"
									onclick="javascript:FRM.Action.value='EDIT';FRM.bsServiceName.value='BSK.ReadConfig';FRM.fldID.value={cID};FRM.submit();">
									Editar...
								</a>
								&#160;&#160;&#160;
								<a href="#"
									onclick="javascript:FRM.Action.value='NONE';FRM.bsServiceName.value='BSK.DeleteImage';FRM.fldID.value={cID};if(confirm('¿Esta seguro de eliminar este registro?'))FRM.submit();">
									Eliminar
								</a>
							</td>

						</tr>
					</xsl:otherwise>
				</xsl:choose>

			</xsl:for-each>
			<xsl:choose>
				<xsl:when test="/Service/Request/Fields/Action='ADD'">
					<tr>
						<td class="cDataTD">[Nuevo]</td>
						<td class="cDataTD">
							<input type="text" name="cName" />
						</td>
						<td>
							<select name="cTable">
								<option value="tProductType">Categoria</option>
								<option value="tProduct">Producto</option>
							</select>

						</td>
						<td class="cDataTD">
							<a href="#"
								onclick="javascript:FRM.Action.value='NONE';FRM.bsServiceName.value='BSK.SaveImage';FRM.fldName.value=cName.value;FRM.fldTable.value=cTable.value;FRM.submit();">
								Aceptar
							</a>
							&#160;&#160;&#160;
							<a href="#"
								onclick="javascript:FRM.Action.value='NONE';FRM.bsServiceName.value='BSK.ReadConfig';FRM.submit();">
								Cancelar
							</a>
						</td>
					</tr>
				</xsl:when>
			</xsl:choose>
		</table>
		<br />

		<!-- 
			<span class="cLabel">
			*
			<xsl:copy-of select="/Service/Request/Fields/Action" />
			*
			</span>
		-->

		<xsl:if test="not(/Service/Request/Fields/Action) or /Service/Request/Fields/Action='NONE'">
			<a href="#" onclick="javascript:FRM.Action.value='ADD';FRM.bsServiceName.value='BSK.ReadConfig';FRM.submit();">
				Nuevo...
			</a>
		</xsl:if>

		<form action="ControlServlet" name="FRM" method="post">
			<input type="hidden" Name="bsServiceName" />
			<input type="hidden" name="fldName" />
			<input type="hidden" name="fldID" />
			<input type="hidden" name="fldTable" />
			<input type="hidden" name="Action" />
		</form>

		<hr />
		<!-- 
			<xsl:copy-of select="/" />
		-->
	</xsl:template>
</xsl:stylesheet>
