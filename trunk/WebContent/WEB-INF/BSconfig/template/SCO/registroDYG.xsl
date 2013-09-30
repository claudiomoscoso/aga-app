<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Registro de Dirigente o Guiadora</H1>
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
						<input name="fldRUT" type="text" id="fldRUT" value="{/Service/Request/Fields/fldRUT}" 
							maxlength="10" size="10"/>
						(12345678-5)*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Condici&#243;n actual:</td>
					<td class="cData" colspan="2">
						<input type="radio" name="fldGentilicio" VALUE="0" checked=""/>Sr.&#160;
						<input type="radio" name="fldGentilicio" VALUE="1"/>Sra.&#160;
						<input type="radio" name="fldGentilicio" VALUE="2"/>Srta.&#160;
						<input type="radio" name="fldGentilicio" VALUE="3"/>Padre&#160;
						<input type="radio" name="fldGentilicio" VALUE="4"/>Sor&#160;
						<input type="radio" name="fldGentilicio" VALUE="5"/>Hno.&#160;
						<input type="radio" name="fldGentilicio" VALUE="6"/>Presb.

					</td>
				</tr>

				<tr>
					<td class="cLabel">Sexo:</td>
					<td class="cData">
						<input id="fldSexoMasculino" name="fldSexo" type="radio" value="True" CHECKED="" />
						Masculino
						&#160;
						<input id="fldSexoFemenino" name="fldSexo" type="radio" value="False" />Femenino
					</td>
				</tr>

				<tr>
					<td class="cLabel">Nombres:</td>
					<td class="cData">
						<input id="fldNombre" name="fldNombre" type="text" value="{/Service/Request/Fields/fldNombre}" maxlength="50" size="50"/>
						*
					</td>
				</tr>
				<tr>
					<td class="cLabel">Apellidos</td>
					<td class="cData">
						Paterno:
						<input id="fldApellidoPaterno" name="fldApellidoPaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoPaterno}" maxlength="20" size="20"/>
						* Materno:
						<input id="fldApellidoMaterno" name="fldApellidoMaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoMaterno}" maxlength="20" size="20"/>
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Fecha Nacimiento:</td>
					<td class="cData">
						<input type="text" name="fldFechaNacimiento" id="fldFechaNacimiento" maxlength="10" size="10"
							value="{/Service/Request/Fields/fldFechaNacimiento}"/>* (28/02/1990)
					</td>
				</tr>

				<tr>
					<td class="cLabel">Estado Civil:</td>
					<td class="cData">
						Soltero<input type="radio" name="fldEstadoCivil" VALUE="0" checked=""/>&#160;
						Casado<input type="radio" name="fldEstadoCivil" VALUE="1" />&#160;
						Viudo<input type="radio" name="fldEstadoCivil" VALUE="2" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Direcci&#243;n:</td>
					<td class="cData">
						<input id="fldDireccion" name="fldDireccion" type="text" value="{/Service/Request/Fields/fldDireccion}" maxlength="20" size="20"/>
						* N&#250;mero:
						<input id="fldNumero" name="fldNumero" type="text" value="{/Service/Request/Fields/fldNumero}" maxlength="5" size="5"/>
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Villa / Poblaci&#243;n:</td>
					<td class="cData">
						<input id="fldVilla" name="fldVilla" type="text" value="{/Service/Request/Fields/fldVilla}" 
							maxlength="30" size="30"/>
						Block
						<input id="fldBlock" name="fldBlock" type="text" value="{/Service/Request/Fields/fldBlock}" 
							 maxlength="5" size="5"/>
						Depto.
						<input id="fldDepartamento" name="fldDepartamento" type="text"
							value="{/Service/Request/Fields/fldDepartamento}"  maxlength="5" size="5"/>
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
							value="{/Service/Request/Fields/fldCodigoPostal}" 
							maxlength="10" size="10"/>
					</td>
				</tr>
				<tr>
					<td class="cLabel">Tel&#233;fonos:</td>
					<td class="cData">
						Trabajo:
						<input id="fldTelefonoLaboral" name="fldTelefonoLaboral" type="text" 
							value="{/Service/Request/Fields/fldTelefonoLaboral}" 
							 maxlength="12" size="12"/>
						Anexo:
						<input id="fldAnexo" name="fldAnexo" type="text" 
							value="{/Service/Request/Fields/fldAnexo}" 
							 maxlength="4" size="4"/>
						Fijo:
						<input id="fldTelefono" name="fldTelefono" type="text" 
							value="{/Service/Request/Fields/fldTelefono}" 
							 maxlength="12" size="12"/>*
						 Fax:
						<input id="fldFax" name="fldFax" type="text" value="{/Service/Request/Fields/fldFax}" 
							maxlength="12" size="12"/>
						Celular:
						<input id="fldCelular" name="fldCelular" type="text" value="{/Service/Request/Fields/fldCelular}" 
							maxlength="12" size="12"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">N&#250;mero Casilla:</td>
					<td class="cData">
						<input id="fldNumeroCasilla" name="fldNumeroCasilla" type="text" 
							value="{/Service/Request/Fields/fldNumeroCasilla}" 
							 maxlength="10" size="10"/>
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
					<td class="cLabel">Ocupaci&#243;n:</td>
					<td class="cData">
						<input id="fldOcupacion" type="text" name="fldOcupacion" value="{/Service/Request/Fields/fldOcupacion}" 
							maxlength="30" size="30"/>*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Empleador:</td>
					<td class="cData">
						<input id="fldEmpleador" type="text" name="fldEmpleador" value="{/Service/Request/Fields/fldEmpleador}" 
							maxlength="30" size="30"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">T&#237;tulo T&#233;cnico o Profesional:</td>
					<td class="cData">
						<input id="fldTitulo" type="text" name="fldTitulo" value="{/Service/Request/Fields/fldTitulo}" 
							maxlength="30" size="30"/>
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
						<input id="fldMail" type="text" name="fldMail" value="{/Service/Request/Fields/fldMail}" 
							maxlength="50" size="50"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Idioma 1:</td>
					<td class="cData">
						<input id="fldIdioma1" type="text" name="fldIdioma1" value="{/Service/Request/Fields/fldIdioma1}" 
							maxlength="20" size="20"/>&#160;
						<input id="fldIdiomaLee1" type="checkbox" name="fldIdiomaLee1" />Lee&#160;
						<input id="fldIdiomaHabla1" type="checkbox" name="fldIdiomaHabla1" />Habla&#160;
						<input id="fldIdiomaEscribe1" type="checkbox" name="fldIdiomaEscribe1" />Escribe
					</td>
				</tr>

				<tr>
					<td class="cLabel">Idioma 2:</td>
					<td class="cData">
						<input id="fldIdioma2" type="text" name="fldIdioma2" value="{/Service/Request/Fields/fldIdioma2}" 
							maxlength="20" size="20"/>&#160;
						<input id="fldIdiomaLee2" type="checkbox" name="fldIdiomaLee2" />Lee&#160;
						<input id="fldIdiomaHabla2" type="checkbox" name="fldIdiomaHabla2" />Habla&#160;
						<input id="fldIdiomaEscribe2" type="checkbox" name="fldIdiomaEscribe2" />Escribe
					</td>
				</tr>
			</table>

			<table width="80%" border="0">
				<caption>Antecedentes Institucionales</caption>
				<tr>
					<td class="cLabel">Grupo:</td>
					<td>
						<select id="fldGrupo" name="fldGrupo">
							<xsl:for-each select="/Service/Response/Fields/ListGrupos/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
					<td class="cLabel">N&#250;mero Miembro Activo:</td>
					<td class="cData">
						<input id="fldNumeroMiembroActivo" type="text" name="fldNumeroMiembroActivo" 
							value="{/Service/Request/Fields/fldNumeroMiembroActivo}" maxlength="5" size="5"/>**
					</td>
				</tr>
				<tr>
					<td class="cLabel">Nivel de Formaci&#243;n:</td>
					<td class="cData">
						<input id="fldSinFormacion" type="radio" name="fldNivelFormacion" VALUE = "0" checked=""/>Sin Formaci&#243;n&#160;
						<input id="fldNivelBsico" type="radio" name="fldNivelFormacion" VALUE = "1" />Nivel B&#225;sico&#160;
						<input id="fldNivelMedio" type="radio" name="fldNivelFormacion" VALUE = "2"/>Nivel Medio&#160;
						<input id="fldNivelAvanzado" type="radio" name="fldNivelFormacion" VALUE = "3"/>Nivel Avanzado
						 
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

				<tr>
					<td class="cLabel" colspan="4">Indique &#233;l o los cargos que ocupa:</td>
				</tr>

				<xsl:for-each select="/Service/Response/Fields/ListCargo/Record">
					<tr>
						<td colspan="1">&#160;</td>
						<td class="cData" colspan="1">
							<input type="checkbox" name="fldCargo" value="{cID}"/>
							<xsl:value-of select="cNombre"/>	
						</td>
						<td colspan="2">&#160;</td>
					</tr>
				</xsl:for-each>

			</table>
			<hr width="80%" align="left" />
			<input value="Aceptar" type="button" onclick="javascript:aceptar();" />
			&#160;&#160;&#160;
			<input value="Cancelar" type="button" onclick="javascript:cancelar();" />


			<input name="bsServiceName" id="bsServiceName" type="hidden" />
			<input type="hidden" name="fldFechaRegistro" value="{/Service/Request/Date}" />
			<input type="hidden" name="fldTabla" value="tDYG" />
		</form>
		<span class="cLabel">* Campos Obligatorios</span><br/>
		<span class="cLabel">** Ingreso en caso de ser miembro activo</span>
 <!-- 
		<xsl:copy-of select="/" />
  --> 
		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){
					var cuenta = "<xsl:value-of select="number(/Service/Response/Fields/ExisteRut/Record/cuenta)" />";

					if(!isNaN(cuenta) &amp;&amp; parseInt(cuenta) &gt; 0){
						if("<xsl:value-of select="/Service/Request/Fields/fldSexo"/>" == "True"){
							document.getElementById("fldSexoMasculino").checked = true;
						} else {
							document.getElementById("fldSexoFemenino").checked = true;
						}

						var gentilicios = document.getElementsByName("fldGentilicio");
						for(var i = 0; i &lt; gentilicios.length;i+=1){
							if(gentilicios[i].value == "<xsl:value-of select="/Service/Request/Fields/fldGentilicio"/>"){
								gentilicios[i].checked = true;
								i = gentilicios.length + 1;
							}
						}
						gentilicios = null;

						var estadosCiviles = document.getElementsByName("fldEstadoCivil");
						for(var i = 0; i &lt; estadosCiviles.length; i+= 1){
							if(estadosCiviles[i].value == "<xsl:value-of select="/Service/Request/Fields/fldEstadoCivil"/>"){
								estadosCiviles[i].checked = true;
								i = estadosCiviles.length + 1;
							}
						}
						estadosCiviles = null;


						document.getElementById("fldComuna").value = "<xsl:value-of select="/Service/Request/Fields/fldComuna"/>";
						document.getElementById("fldReligion").value = "<xsl:value-of select="/Service/Request/Fields/fldReligion"/>";
						document.getElementById("fldNacionalidad").value = "<xsl:value-of select="/Service/Request/Fields/fldNacionalidad"/>";

						document.getElementById("fldIdiomaLee1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaLee1"/>" == "on";
						document.getElementById("fldIdiomaHabla1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaHabla1"/>" == "on";
						document.getElementById("fldIdiomaEscribe1").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaEscribe1"/>" == "on";

						document.getElementById("fldIdiomaLee2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaLee2"/>" == "on";
						document.getElementById("fldIdiomaHabla2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaHabla2"/>" == "on";
						document.getElementById("fldIdiomaEscribe2").checked = "<xsl:value-of select="/Service/Request/Fields/fldIdiomaEscribe2"/>" == "on";

						document.getElementById("fldGrupo").value = "<xsl:value-of select="/Service/Request/Fields/fldGrupo"/>";
						document.getElementById("fldUnidad").value = "<xsl:value-of select="/Service/Request/Fields/fldUnidad"/>";
						
						<xsl:for-each select="/Service/Request/Fields/fldCargo">
							var cargo = "<xsl:value-of select="."/>";
							var cargos = document.getElementsByName("fldCargo");
							for(var i = 0; i &lt; cargos.length; i+= 1){
								if(cargos[i].value == cargo){
									cargos[i].checked = true;
									i = cargos.length + 1;
								}
							}
							cargos = null;
							cargo = null;
						</xsl:for-each>
						
						var niveles = document.getElementsByName("fldNivelFormacion");
						for(var i = 0; i &lt; niveles.length; i+= 1){
							if(niveles[i].value == "<xsl:value-of select="/Service/Request/Fields/fldNivelFormacion"/>"){
								niveles[i].checked = true;
								i = niveles.length + 1;
							}
						}
						niveles = null;


						alert("El RUT ya esta registrado...");
					}
				}

				function cancelar(){ 
					document.getElementById("f").bsServiceName.value = "SCO.ListPersona";
					document.getElementById("f").submit(); 
				}

				function aceptar(){ 
					var msg = validaFrom(); 
					if(msg.length > 0){ 
						alert('ATENCION\n\n' + msg); 
					} else {
						document.getElementById("f").bsServiceName.value = "SCO.GrabarDYG"; 
						document.getElementById("f").submit(); 
					} 
				}

				function validaFrom(){ 
					var out = ""; 
					var validRut = validaRUT(document.getElementById("f").fldRUT.value);

					if(!validRut){ out += "- Rut no es v&#225;lido\n"; }
					if(document.getElementById("fldNombre").value.length == 0) out += '- Nombre no fue ingresado \n';
					if(document.getElementById("fldApellidoPaterno").value.length == 0){out += '- Apellido Paterno no fue ingresado \n';}
					if(document.getElementById("fldApellidoMaterno").value.length == 0){out += '- Apellido Materno no fue ingresado \n';}

					var fechaNacimiento = document.getElementById("fldFechaNacimiento").value;
					fechaNacimiento = replaceAll(fechaNacimiento, '-', '/');
					if(!validaFecha(fechaNacimiento) ){
						out += '- Fecha de Nacimiento no v&#225;lida\n';
					}else{
						document.getElementById("fldFechaNacimiento").value = fechaNacimiento;
					}
					fechaNacimiento = null;

					if(document.getElementById("fldDireccion").value.length == 0){out += '- Direcci&#243;n no se ingres&#243;\n';}
					if(document.getElementById("fldNumero").value.length == 0){out += '- N&#250;mero de Direcci&#243;n no se ingres&#243;\n';}
					if(document.getElementById("fldTelefono").value.length == 0){out += '- Tel&#233;fono fijo no se ingres&#243;\n';}
					if(document.getElementById("fldOcupacion").value.length == 0){out += '- Ocupaci&#243;n no se ingres&#243;\n';}

					if(document.getElementById("fldNivelMedio").checked || document.getElementById("fldNivelAvanzado").checked){
						if(document.getElementById("fldNumeroMiembroActivo").value.length == 0){out += '- N&#250;mero miembro activo no se ingres&#243;\n';}
					}

					if(cargosSeleccionados() == 0){out += '- Tiene que seleccionar al menos un cargo';}

					return out;
				}
				
				function cargosSeleccionados(){
					var cargos = document.getElementsByName("fldCargo");
					var out = 0;
					for(var i = 0; i &lt; cargos.length; i+= 1){
						out += cargos[i].checked ? 1 : 0;
					}
					cargos = null;
					return out;
				}
				
			</xsl:comment>
		</script>

	</xsl:template>
</xsl:stylesheet>