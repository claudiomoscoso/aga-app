<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
	
		<xsl:element name="script">
			var tipoProducto = "<xsl:value-of select="normalize-space(/Service/Response/Fields/GetProducto/Record/cTipoProducto)" />";
		</xsl:element>

		<h3 class="cTitle">Datos del producto</h3>
		<table border="1" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td valign="top">
					<label for="analisis" class="cLabel">No An&#225;lisis:</label>
				</td>
				<td valign="top">
					<input type="text" id="analisis" onFocus="javascript:currentValue=this.value;" value="{/Service/Response/Fields/GetSolicitud/Record/cAnalisis}" onBlur="javascript:updateAnalisis(this);" />
				</td>

				<td class="cLabel" valign="top">
					Nombre del Producto
					<xsl:call-template name="GetCodigoProducto" />
					 
				</td>
			</tr>
			<tr>
				<td valign="top"><label for="orden" class="cLabel" >No Orden:</label></td>
				<td valign="top">
					<input type="text" value="{/Service/Response/Fields/GetSolicitud/Record/cOrden}" id="orden" onFocus="javascript:currentValue=this.value;" onBlur="javascript:updateOrden(this);" />
				</td>
				<td valign="top">
					<textarea cols="70"><xsl:attribute name="readonly">yes</xsl:attribute>
						<xsl:call-template name="GetNombreProducto" />
					</textarea>
				</td>
			</tr>
			<tr>
				<td  valign="top"><label class="cLabel" for="TipoProducto">Tipo Producto:</label></td>
				<td colspan="2" valign="top">
				 
				<label class="cLabel"><xsl:value-of select="/Service/Response/Fields/GetProducto/Record/cNombreTipoProducto"/>
				 <!--  
					<select id="TipoProducto" onBlur="javascript:updateTipoProducto(this);"  onFocus="javascript:currentValue=this.value;" >
						<xsl:for-each select="/Service/Response/Fields/ListTipoProducto/Record">
							<option value="{normalize-space(cID)}">
								<xsl:value-of select="cNombre" />
							</option>
						</xsl:for-each>
					</select>
					
					<label class="cLabel">
					-->
					(<xsl:choose>
						<xsl:when test="/Service/Response/Fields/GetSolicitud/Record/cGaspuro=1">
							Gas puro
						</xsl:when>
						<xsl:otherwise>
							Mezcla
						</xsl:otherwise>
					</xsl:choose>)
					</label>
				</td>
			</tr>
			<tr>
				<td class="cLabel" valign="top">Comentarios</td>
				<td colspan="2" valign="top">
					<textarea cols="102" id="Comentario" onblur="javascript:updateComentario(this);" onkeypress="return imposeMaxLength(this, 100);">
					<!-- <xsl:attribute name="readonly">yes</xsl:attribute>  -->
						<xsl:call-template name="GetComentario" />
					</textarea>
					<br/>
					<label class="cLabel" id="lenComentario"/>
				</td>
			</tr>
		</table>

	</xsl:template>
	<xsl:template name="GetNombreProducto">
		<xsl:choose>
			<xsl:when test="count(/Service/Response/Fields/GetProducto/Record)&gt;0">
				<xsl:value-of select="/Service/Response/Fields/GetProducto/Record/cNombre" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="/Service/Response/Fields/GetProductoById/Record/cNombre" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


	<xsl:template name="GetComentario">
		<xsl:choose>
			<xsl:when test="count(/Service/Response/Fields/GetProducto/Record)&gt;0">
				<xsl:value-of select="/Service/Response/Fields/GetSolicitud/Record/cComentario" />
				<!-- 
				<xsl:value-of select="/Service/Response/Fields/GetProducto/Record/cCertificado" />
				 -->
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="/Service/Response/Fields/GetProductoById/Record/cCertificado" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="GetCodigoProducto">
		(<xsl:choose>
			<xsl:when test="count(/Service/Response/Fields/GetProducto/Record)&gt;0">
				<xsl:value-of select="/Service/Response/Fields/GetProducto/Record/cID" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="/Service/Response/Fields/GetProductoById/Record/cID" />
			</xsl:otherwise>
		</xsl:choose>)
	</xsl:template>



</xsl:stylesheet>