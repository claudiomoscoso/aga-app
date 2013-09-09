<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Permiso de Roles</h1>
		
		<!-- 
		<textarea>
		<xsl:copy-of select="/"/></textarea>
		 -->
		
		<form action="ControlServlet" name="frm" id="frm">
			<input type="hidden" name="bsServiceName" value="USR.savePermiso" />

			
			<table border="0">
				<tr>
					<td colspan="3">
						<span class="cLabel">Grupo:&#160;</span>
						<select name="fldRol" onchange="executeAction(frm, 'USR.permiso');">
							<xsl:for-each select="/Service/Response/Fields/ListRol/Record">
										<option value="{cID}">
										<xsl:if test="/Service/Request/Fields/fldRol=cID">
											<xsl:attribute name="selected"/>
											</xsl:if>
											<xsl:value-of select="cName" />
										</option>
							
							
							<!-- 
								<xsl:choose>
									<xsl:when test="/Service/Request/Fields/fldRol=cID">
										<option selected="" value="{cID}">
											<xsl:value-of select="cName" />
										</option>
									</xsl:when>
									<xsl:otherwise>
										<option value="{cID}">
											<xsl:value-of select="cName" />
										</option>
									</xsl:otherwise>
								</xsl:choose>
 -->


test="/Service/Request/Fields/fldRol=cID"
							</xsl:for-each>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<span class="cLabel">Menu</span>
						<xsl:for-each select="/Service/Response/Fields/Menu/*">
							<xsl:call-template name="processMenu">
								<xsl:with-param name="opcion" select="." />
							</xsl:call-template>
						</xsl:for-each>
					</td>
				</tr>
			</table>
			<br />
			<input type="submit" value="Grabar" class="cInputButton" />
		</form>

		<!-- 
			<xsl:copy-of select="/"/>
		-->
	</xsl:template>

	<xsl:template name="processMenu">
		<xsl:param name="opcion" />
		<blockquote>
			<span class="cLabel">
				<input type="checkbox" name="fldOption">
					<xsl:attribute name="value">
						<xsl:value-of select="$opcion/@ID" />
					</xsl:attribute>
					<xsl:if test="$opcion/@Selected=1">
						<xsl:attribute name="checked" />
					</xsl:if>
				</input>
				<xsl:value-of select="$opcion/@Label" />
			</span>
			<xsl:if test="$opcion/Option">
				<xsl:for-each select="$opcion/*">
					<xsl:call-template name="processMenu">
						<xsl:with-param name="opcion" select="." />
					</xsl:call-template>
				</xsl:for-each>
			</xsl:if>
		</blockquote>
	</xsl:template>
</xsl:stylesheet>