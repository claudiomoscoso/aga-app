<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Atributos de Categorias</h1>
		<table>
			<tr>
				<td class="cLabel">Nombre:</td>
				<td class="cData">
					<xsl:value-of select="/Service/Response/Fields/ProductType/Record/cName" />
				</td>
			</tr>
		</table>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">C@oacute;digo</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Acciones</td>
			</tr>
			<xsl:for-each select="/Service/Response/Fields/Attributes/Record">
				<xsl:choose>
					<xsl:when test="/Service/Request/Fields/Action='EDIT' and /Service/Request/Fields/fldAttribute=cID">
						<tr>
							<td class="cDataTD" nowrap="">
								<xsl:value-of select="cID" />
							</td>
							<td class="cDataTD">
								<input type="text" id="NameAttribute" value="{cName}" />
							</td>
							<td class="cDataTD">
								<a href="#"
									onclick="javascript:SaveForm.bsServiceName.value='BSK.UpdateAttribute';SaveForm.fldName.value=NameAttribute.value;SaveForm.fldAttribute.value={cID};SaveForm.submit();">
									Aceptar
								</a>
								&#160;&#160;
								<a href="#"
									onclick="javascript:SaveForm.bsServiceName.value='BSK.EditAttribute';SaveForm.submit();">
									Cancelar
								</a>
							</td>
						</tr>
					</xsl:when>
					<xsl:otherwise>
						<tr>
							<td class="cDataTD" nowrap="">
								<xsl:value-of select="cID" />
							</td>
							<td class="cDataTD">
								<xsl:value-of select="cName" />
							</td>
							<td class="cDataTD">
								<a href="#"
									onclick="javascript:SaveForm.bsServiceName.value='BSK.EditAttribute';SaveForm.Action.value='EDIT';SaveForm.fldAttribute.value='{cID}';SaveForm.submit();">
									Editar
								</a>
								&#160;
								<a href="#"
									onclick="javascript:SaveForm.bsServiceName.value='BSK.DeleteAttribute';SaveForm.Action.value='NONE';SaveForm.fldAttribute.value='{cID}';if(confirm('¿Esta seguro de borrar el atributo?'))SaveForm.submit();">
									Eliminar
								</a>
							</td>
						</tr>
					</xsl:otherwise>
				</xsl:choose>

			</xsl:for-each>
			<xsl:if test="/Service/Request/Fields/Action='ADD'">
				<tr>
					<td class="cDataTD">[Nuevo]</td>
					<td class="cDataTD">
						<input type="text" id="NameAttribute" />
					</td>
					<td class="cDataTD">
						<a href="#"
							onclick="javascript:SaveForm.bsServiceName.value='BSK.SaveAttribute';SaveForm.fldName.value=NameAttribute.value;SaveForm.submit();">
							Aceptar
						</a>
						&#160;&#160;
						<a href="#" onclick="javascript:SaveForm.bsServiceName.value='BSK.EditAttribute';SaveForm.submit();">
							Cancelar
						</a>
					</td>
				</tr>
			</xsl:if>
		</table>
		<hr />
		<xsl:choose>
			<xsl:when test="/Service/Request/Fields/Action='NONE'">
				<a
					href="ControlServlet?bsServiceName=BSK.EditAttribute&amp;fldProductType={/Service/Request/Fields/fldProductType}&amp;Action=ADD">
					Nuevo atributo...
				</a>
				&#160;&#160;&#160;
			</xsl:when>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="/Service/Response/Fields/ProductType/Record/cParent='unknow'">
				<a href="ControlServlet?bsServiceName=BSK.categoriaIndex&amp;fldProduct=">Volver...</a>
			</xsl:when>
			<xsl:otherwise>
				<a
					href="ControlServlet?bsServiceName=BSK.categoriaIndex&amp;fldProduct={/Service/Response/Fields/ProductType/Record/cParent}">
					Volver...
				</a>
			</xsl:otherwise>
		</xsl:choose>

		<form action="ControlServlet" method="post" id="SaveForm">
			<input type="hidden" name="bsServiceName" />
			<input type="hidden" name="fldAttribute" />
			<input type="hidden" name="fldName" />
			<input type="hidden" name="fldProductType" value="{/Service/Request/Fields/fldProductType}" />
			<input type="hidden" name="Action" value="NONE" />
		</form>

		<br />
		<br />

<!-- 
		<xsl:copy-of select="/" />
 -->

	</xsl:template>
</xsl:stylesheet>