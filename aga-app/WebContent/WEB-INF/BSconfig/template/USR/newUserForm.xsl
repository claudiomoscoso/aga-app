<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Datos de nuevo usuario</h1>
		<form method="POST" action="ControlServlet">

			<input type="hidden" name="bsServiceName" value="USR.saveNewUser" />
			<table border="0">
				<tr>
					<td>
						<table border="0">
							<tr>
								<td class="cLabel">ID</td>
								<td class="cData">Nuevo</td>
							</tr>
							<tr>
								<td class="cLabel">Login</td>
								<td>
									<input type="text" name="fldLogin" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Password</td>
								<td>
									<input type="password" name="fldPassword1" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Confirma Pasword</td>
								<td>
									<input type="password" name="fldPassword2" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Nombre</td>
								<td>
									<input type="text" name="fldName" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Mail</td>
								<td>
									<input type="text" name="fldMail" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Rol</td>
								<td>
									<xsl:choose>
										<xsl:when test="/Service/Session/User/cRol!='00000000000000000000'">
											<select name="fldRol">
												<xsl:for-each select="/Service/Response/Fields/Rol/Record">
													<xsl:if test="cID!='00000000000000000000'">
														<xsl:choose>
															<xsl:when
																test="/Service/Response/Fields/SearchUser/cRol=cID">
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
									<input type="text" name="fldPhone" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Tel&#233;fono M&#243;vil</td>
								<td>
									<input type="text" name="fldMovil" />
								</td>
							</tr>

						</table>
					</td>
					<td valign="top">
						<table border="0">
							<xsl:for-each select="/Service/Response/Fields/Fields/Field">
								<tr>
									<td class="cLabel">
										<xsl:value-of select="." />
									</td>
									<td>
										<input type="text" name="{@ID}" />
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
		<!-- 
			<xsl:copy-of select="/"/>
		-->
	</xsl:template>
</xsl:stylesheet>