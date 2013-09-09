<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<xsl:variable name="HaveLocal" select="/Service/Response/Fields/GetProductoByCode/Record/cID != ''" />
		<xsl:variable name="HaveFFOO" select="/Service/Response/Fields/GetProductoFFOO/Record/cID != ''" />
		<xsl:variable name="HaveMDB" select="/Service/Response/Fields/Producto/Record/Cuantos &gt; 0" />


		<h1 class="cTitle">
			Mantenimiento de Producto
		</h1>

		<form id="f" action="?bsServiceName=AGA.GetProductoMDB" method="post">
			<table>
				<tr>
					<td>
						<label class="cLabel" for="Producto">Codigo de Producto:</label>
					</td>
					<td>
						<input id="Producto" size="8" maxlength="10" type="text" name="codigoGe" value="{/Service/Request/Fields/codigoGe}" />
					</td>
					<td>
						<input type="submit" value="Buscar" />
					</td>
				</tr>
			</table>
		</form>

		<xsl:if test="/Service/Request/Fields/codigoGe!=''">
			<table border="0" width="70%">
				<tr>
					<td align="center" class="cLabel">Base Local</td>
					<td align="center" class="cLabel">Front Office</td>
					<td align="center" class="cLabel">CODGE97</td>
				</tr>
				<tr>
					<td align="center">

						<xsl:choose>
							<xsl:when test="$HaveLocal">
								<img src="public/img/ok.png" style="width:50" />
							</xsl:when>
							<xsl:otherwise>
								<img src="public/img/error.png" style="width:50" />
							</xsl:otherwise>
						</xsl:choose>

					</td>
					<td align="center">
						<xsl:choose>
							<xsl:when test="$HaveFFOO">
								<img src="public/img/ok.png" style="width:50" />
							</xsl:when>
							<xsl:otherwise>
								<img src="public/img/error.png" style="width:50" />
							</xsl:otherwise>
						</xsl:choose>
					</td>
					<td align="center">

						<xsl:choose>
							<xsl:when test="$HaveMDB">
								<img src="public/img/ok.png" style="width:50" />
							</xsl:when>
							<xsl:otherwise>
								<img src="public/img/error.png" style="width:50" />
							</xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
					<!-- 
						<xsl:value-of select="$HaveLocal"/>
					 -->
						<input type="button" value="Editar definicion" onclick="javascript:document.getElementById('detail').submit();">
							<xsl:choose>
								<xsl:when test="not($HaveLocal) or not($HaveFFOO)"><xsl:attribute name="disabled"/></xsl:when>
							</xsl:choose>
						</input>
						&#160;&#160;&#160;
						<input type="button" value="Copia a base local">
							<xsl:choose>
								<xsl:when test="$HaveLocal or not($HaveLocal)">
									<xsl:attribute name="disabled" />
								</xsl:when>
							</xsl:choose>
						</input>
						&#160;&#160;&#160;
						<input type="button" value="Crear en base local">
							<xsl:choose>
								<xsl:when test="$HaveLocal or not($HaveLocal)">
									<xsl:attribute name="disabled" />
								</xsl:when>
							</xsl:choose>
						</input>
					</td>
				</tr>
			</table>
		</xsl:if>

<form action="?bsServiceName=AGA.EditProduct&amp;codigoGe={/Service/Request/Fields/codigoGe}" method="post" id="detail"/>

<!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
 -->
 
	</xsl:template>
</xsl:stylesheet>