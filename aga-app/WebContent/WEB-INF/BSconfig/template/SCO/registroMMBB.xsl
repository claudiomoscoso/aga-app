<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Registro de Miembro Beneficiario</H1>
		<xsl:variable name="GrupoUsuario" select="/Service/Session/User/Info/prpGrupoScout" />
		<xsl:variable name="IsAdmin" select="string-length(/Service/Session/User/Info/prpGrupoScout) = 0" />

		<form name="f" id="f" method="post" action="/{/Service/Context/SiteName}/">

			<table width="80%">
				<caption>Antecedentes Personales</caption>
				<tr>
					<td class="cLabel">Fecha Registro:</td>
					<td class="cData">
						<xsl:value-of select="/Service/Request/Date" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">R.U.T.:</td>
					<td class="cData">
						<input name="fldRUT" type="text" id="fldRUT" value="{/Service/Request/Fields/fldRUT}" maxlength="10"
							size="10" />
						(12345678-5)*
					</td>
				</tr>
				<tr>
					<td class="cLabel">Sexo:</td>
					<td class="cData">
						<input id="fldSexoMasculino" name="fldSexo" type="radio" value="True" CHECKED="" />
						Masculino &#160;
						<input id="fldSexoFemenino" name="fldSexo" type="radio" value="False" />
						Femenino
					</td>
				</tr>

				<tr>
					<td class="cLabel">Nombres:</td>
					<td class="cData">
						<input id="fldNombre" name="fldNombre" type="text" value="{/Service/Request/Fields/fldNombre}"
							maxlength="50" size="50" />
						*
					</td>
				</tr>
				<tr>
					<td class="cLabel">Apellidos</td>
					<td class="cData">
						Paterno:
						<input id="fldApellidoPaterno" name="fldApellidoPaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoPaterno}" maxlength="20" size="20" />
						* Materno:
						<input id="fldApellidoMaterno" name="fldApellidoMaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoMaterno}" maxlength="20" size="20" />
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Fecha Nacimiento:</td>
					<td class="cData">
						<input type="text" name="fldFechaNacimiento" id="fldFechaNacimiento"
							value="{/Service/Request/Fields/fldFechaNacimiento}" maxlength="10" size="10" />
						*(28/02/1990)
					</td>
				</tr>
				<tr>
					<td class="cLabel">Direcci&#243;n:</td>
					<td class="cData">
						<input id="fldDireccion" name="fldDireccion" type="text" value="{/Service/Request/Fields/fldDireccion}"
							maxlength="20" size="20" />
						* N&#250;mero:
						<input id="fldNumero" name="fldNumero" type="text" value="{/Service/Request/Fields/fldNumero}"
							maxlength="5" size="5" />
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Villa / Poblaci&#243;n:</td>
					<td class="cData">
						<input id="fldVilla" name="fldVilla" type="text" value="{/Service/Request/Fields/fldVilla}" maxlength="30"
							size="30" />
						Block
						<input id="fldBlock" name="fldBlock" type="text" value="{/Service/Request/Fields/fldBlock}" maxlength="5"
							size="5" />
						Depto.
						<input id="fldDepartamento" name="fldDepartamento" type="text"
							value="{/Service/Request/Fields/fldDepartamento}" maxlength="5" size="5" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Comuna:</td>
					<td>
						<select id="fldComuna" name="fldComuna">
							<xsl:for-each select="/Service/Response/Fields/ListComuna/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>

				<tr>
					<td class="cLabel">C&#243;digo Postal:</td>
					<td class="cData">
						<input id="fldCodigoPostal" name="fldCodigoPostal" type="text"
							value="{/Service/Request/Fields/fldCodigoPostal}" maxlength="10" size="10" />
					</td>
				</tr>
				<tr>
					<td class="cLabel">Tel&#233;fonos:</td>
					<td class="cData">
						Fijo
						<input id="fldTelefono" name="fldTelefono" type="text" value="{/Service/Request/Fields/fldTelefono}"
							maxlength="12" size="12" />
						* Fax
						<input id="fldFax" name="fldFax" type="text" value="{/Service/Request/Fields/fldFax}" maxlength="12"
							size="12" />
						Celular
						<input id="fldCelular" name="fldCelular" type="text" value="{/Service/Request/Fields/fldCelular}"
							maxlength="12" size="12" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Confesi&#243;n Religiosa:</td>
					<td>
						<select id="fldReligion" name="fldReligion">
							<xsl:for-each select="/Service/Response/Fields/ListReligion/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Nacionalidad:</td>
					<td>
						<select id="fldNacionalidad" name="fldNacionalidad">
							<xsl:for-each select="/Service/Response/Fields/ListNacionalidad/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Correo Electr&#243;nico:</td>
					<td class="cData">
						<input id="fldMail" type="text" name="fldMail" value="{/Service/Request/Fields/fldMail}" maxlength="50"
							size="50" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Idioma 1:</td>
					<td class="cData">
						<input id="fldIdioma1" type="text" name="fldIdioma1" value="{/Service/Request/Fields/fldIdioma1}"
							maxlength="20" size="20" />
						&#160;
						<input id="fldIdiomaLee1" type="checkbox" name="fldIdiomaLee1" />
						Lee&#160;
						<input id="fldIdiomaHabla1" type="checkbox" name="fldIdiomaHabla1" />
						Habla&#160;
						<input id="fldIdiomaEscribe1" type="checkbox" name="fldIdiomaEscribe1" />
						Escribe
					</td>
				</tr>

				<tr>
					<td class="cLabel">Idioma 2:</td>
					<td class="cData">
						<input id="fldIdioma2" type="text" name="fldIdioma2" value="{/Service/Request/Fields/fldIdioma2}"
							maxlength="20" size="20" />
						&#160;
						<input id="fldIdiomaLee2" type="checkbox" name="fldIdiomaLee2" />
						Lee&#160;
						<input id="fldIdiomaHabla2" type="checkbox" name="fldIdiomaHabla2" />
						Habla&#160;
						<input id="fldIdiomaEscribe2" type="checkbox" name="fldIdiomaEscribe2" />
						Escribe
					</td>
				</tr>
			</table>

			<table width="80%" border="0">
				<caption>Antecedentes Institucionales</caption>
				<tr>
					<td class="cLabel">Grupo:</td>
					
						<xsl:choose>
							<xsl:when test="$IsAdmin">
								<td>
									<select id="fldGrupo" name="fldGrupo">
										<xsl:for-each select="/Service/Response/Fields/ListGrupos/Record">
											<option value="{cID}">
												<xsl:value-of select="cNombre" />
											</option>
										</xsl:for-each>
									</select>
								</td>
							</xsl:when>
							<xsl:otherwise>
								<td class="cData">
									<input type="hidden" name="fldGrupo" value="{/Service/Response/Fields/ListGrupos/Record[cID=$GrupoUsuario]/cID}"/>
									<xsl:value-of select="/Service/Response/Fields/ListGrupos/Record[cID=$GrupoUsuario]/cNombre"/>
								</td>
							</xsl:otherwise>
						</xsl:choose>
					
				</tr>
				<tr>
					<td class="cLabel">Nombre de etapa de progresi&#243;n actual:</td>
					<td class="cData">
						<input id="fldEtapa" type="text" name="fldEtapa" value="{/Service/Request/Fields/fldEtapa}" maxlength="15"
							size="15" />
					</td>

					<td class="cLabel">Unidad:</td>
					<td>
						<select id="fldUnidad" name="fldUnidad">
							<xsl:for-each select="/Service/Response/Fields/ListUnidad/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
			</table>
			<hr width="80%" align="left" />
			<input value="Aceptar" type="button" onclick="javascript:aceptar();" />
			&#160;&#160;&#160;
			<input value="Cancelar" type="button" onclick="javascript:cancelar();" />

			<input name="bsServiceName" type="hidden" />
			<input type="hidden" name="fldFechaRegistro" value="{/Service/Request/Date}" />
			<input type="hidden" name="fldTabla" value="tMMBB" />
		</form>
		<span class="cLabel">* Campos Obligatorios</span>

		<!-- 
			<xsl:copy-of select="/" />
		-->

		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){ 
					var cuenta = "<xsl:value-of select="number(/Service/Response/Fields/ExisteRut/Record/cuenta)" />";
					if(!isNaN(cuenta) &amp;&amp; parseInt(cuenta) &gt; 0){ 
						if("<xsl:value-of select="/Service/Request/Fields/fldSexo" />" == "True"){ 
							document.getElementById("fldSexoMasculino").checked = true; 
						} else {
							document.getElementById("fldSexoFemenino").checked = true; 
						}
	
						document.getElementById("fldComuna").value = "<xsl:value-of select="/Service/Request/Fields/fldComuna" />"; 
						document.getElementById("fldReligion").value = "<xsl:value-of select="/Service/Request/Fields/fldReligion" />"; 
						document.getElementById("fldNacionalidad").value = "<xsl:value-of select="/Service/Request/Fields/fldNacionalidad" />";
	
						document.getElementById("fldIdiomaLee1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaLee1" />" == "on"; 
						document.getElementById("fldIdiomaHabla1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaHabla1" />" == "on"; 
						document.getElementById("fldIdiomaEscribe1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaEscribe1" />" == "on";
	
	
						document.getElementById("fldIdiomaLee2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaLee2" />" == "on"; 
						document.getElementById("fldIdiomaHabla2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaHabla2" />" == "on"; 
						document.getElementById("fldIdiomaEscribe2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaEscribe2" />" == "on";
	
						document.getElementById("fldGrupo").value = "<xsl:value-of select="/Service/Request/Fields/fldGrupo" />"; 
						document.getElementById("fldUnidad").value = "<xsl:value-of select="/Service/Request/Fields/fldUnidad" />";
	
						alert("El RUT ya esta registrado..."); 
					} 
				}

				function cancelar(){ 
					document.getElementById("f").bsServiceName.value = "SCO.ListPersona";
					document.getElementById("f").submit(); 
				}

				function aceptar(){
					$('fldRUT').value = allTrim($('fldRUT').value);
					var msg = validaFrom(); 
					if(msg.length > 0){ 
						alert('ATENCION\n\n' + msg); 
					} else {
						document.getElementById("f").bsServiceName.value = "SCO.GrabarMMBB"; 
						document.getElementById("f").submit(); 
					} 
				}

				function validaFrom(){ 
					var out = "";
					var rutValue = $('fldRUT').value;
					var validRut = true; //validaRUT(rutValue);

					if(!validRut){ 
						out += "- Rut no es v&#225;lido\n"; 
					} 
					
					if(document.getElementById("fldNombre").value.length == 0) 
						out += '- Nombre no se ingres&#243;\n'; 
					if(document.getElementById("fldApellidoPaterno").value.length == 0){out += '- Apellido Paterno no se ingres&#243;\n';} 
					if(document.getElementById("fldApellidoMaterno").value.length == 0){out += '- Apellido Materno no se ingres&#243;\n';}

					var fechaNacimiento = document.getElementById("fldFechaNacimiento").value; 
					fechaNacimiento = replaceAll(fechaNacimiento, '-', '/'); 
					if(!validaFecha(fechaNacimiento) ){ 
						out += '- Fecha de Nacimiento no v&#225;lida\n'; 
					} else { 
						document.getElementById("fldFechaNacimiento").value = fechaNacimiento; 
					} 
					fechaNacimiento = null;

					if(document.getElementById("fldDireccion").value.length == 0){out += '- Direcci&#243;n no se ingres&#243;\n';}
					if(document.getElementById("fldNumero").value.length == 0){out += '- N&#250;mero de Direccion no se ingres&#243;\n';}
					if(document.getElementById("fldTelefono").value.length == 0){out += '- Tel&#233;fono fijo no se ingres&#243;\n';}
/*					if(document.getElementById("fldEtapa").value.length == 0){out += '- Etapa de progresión no se ingres&#243;\n';}*/

					return out; 
				}

			</xsl:comment>
		</script>

	</xsl:template>
</xsl:stylesheet>