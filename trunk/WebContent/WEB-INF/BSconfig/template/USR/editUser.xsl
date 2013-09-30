<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Modificacion de datos de usuario</h1>
		<form method="POST" action="ControlServlet">

			<input type="hidden" name="bsServiceName" value="USR.updateUser" />
			<input type="hidden" name="fldID" value="{/Service/Response/Fields/SearchUser/cID}" />
			<table border="0">
				<tr>
					<td>
						<table>
							<tr>
								<td class="cLabel">ID</td>
								<td class="cData">
									<xsl:value-of select="/Service/Response/Fields/SearchUser/cID" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Login</td>
								<td>
									<span class="cData">
										<xsl:value-of select="/Service/Response/Fields/SearchUser/cLogin" />
									</span>
									<input type="hidden" name="fldLogin" value="{/Response/Fields/SearchUser/cLogin}" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Nombre</td>
								<td>
									<input type="text" name="fldName" value="{/Service/Response/Fields/SearchUser/cName}" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Mail</td>
								<td>
									<input type="text" name="fldMail" value="{/Service/Response/Fields/SearchUser/cMail}" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Rol</td>
								<td>
									<xsl:choose>
										<xsl:when test="/Service/Response/Session/User/cRol!='00000000000000000000'">
											<select name="fldRol">
												<xsl:for-each select="/Service/Response/Fields/Rol/Record">
													<xsl:if test="cID!='00000000000000000000'">
														<xsl:choose>
															<xsl:when test="/Service/Response/Fields/SearchUser/cRol=cID">
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
													</xsl:if>
												</xsl:for-each>
											</select>
										</xsl:when>
										<xsl:otherwise>
											<select name="fldRol">
												<xsl:for-each select="/Service/Response/Fields/Rol/Record">
													<xsl:choose>
														<xsl:when test="/Service/Response/Fields/SearchUser/cRol=cID">
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
												</xsl:for-each>
											</select>
										</xsl:otherwise>
									</xsl:choose>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Telefono Fijo</td>
								<td>
									<input type="text" name="fldPhone" value="{/Service/Response/Fields/SearchUser/cPhone}" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Teléfono Móvil</td>
								<td>
									<input type="text" name="fldMovil" value="{/Service/Response/Fields/SearchUser/cMovil}" />
								</td>
							</tr>

						</table>
					</td>
					<td valign="top">
						<table border="0">
							<xsl:variable name="x">prpGrupoScout</xsl:variable>
							<xsl:for-each select="/Service/Response/Fields/Fields/Field">
								<tr>
									<td class="cLabel">
										<xsl:value-of select="." />
									</td><!-- 
									klr
									<td class="cLabel">
										<xsl:copy-of select="/Service/Response/Fields/SearchUser/Info/*[name() = string(@ID)]" />
									</td>
									<td class="cLabel">
										<xsl:value-of select="@ID" />
									</td>
 -->
									<td>
										<xsl:call-template name="DragInput">
											<xsl:with-param name="ID" select="@ID" />
										</xsl:call-template>
										<!-- 
											<input type="text" name="{@ID}" value="{/Service/Response/Fields/SearchUser/Info/*[name() = $x]}"/>
										-->
									</td>
								</tr>
							</xsl:for-each>
						</table>
					</td>
				</tr>
			</table>
			<input type="submit" value="Aceptar" />
			&#160;
			<input type="button" value="Cancelar" onclick="redirect('USR.listUser');" />
		</form>
		<hr />

		<input type="button" value="Cambiar Password..."
			onclick="javaScript:redirect('USR.prepareCambioClave&amp;fldID={/Service/Response/Fields/SearchUser/cID}');" />
<!-- 
		<xsl:copy-of select="/" />
 -->
	</xsl:template>
	<xsl:template name="DragInput">
		<xsl:param name="ID" />
		<input type="text" name="{$ID}" value="{/Service/Response/Fields/SearchUser/Info/*[name() = $ID]}" />
	</xsl:template>
</xsl:stylesheet>