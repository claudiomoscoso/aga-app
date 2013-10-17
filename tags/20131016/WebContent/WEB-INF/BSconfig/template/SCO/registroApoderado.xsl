<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Registro de Apoderados</H1>

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
						<input type="text" id="RUT" value="{/Service/Request/Fields/fldRUT}" 
							maxlength="10" size="10"/>
						(12345678-5)*
						<input type="button" id="BotonBuscaApoderado" value="Buscar" onclick="javascript:buscaApoderado()"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Sexo:</td>
					<td class="cData">
						<input Name="Sexo" id="SexoMasculino" type="radio" value="True" CHECKED="" disabled="yes"/>Masculino&#160;
						<input Name="Sexo" id="SexoFemenino" type="radio" value="False" disabled="yes"/>Femenino
					</td>
				</tr>

				<tr>
					<td class="cLabel">Nombres:</td>
					<td class="cData">
						<input id="Nombre" type="text"  readonly="yes"
						value="{/Service/Request/Fields/fldNombre}" maxlength="50" size="50"/>
						*
					</td>
				</tr>
				<tr>
					<td class="cLabel">Apellidos</td>
					<td class="cData">
						Paterno:
						<input id="ApellidoPaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoPaterno}" maxlength="20" size="20"
							readonly="yes"/>
						* Materno:
						<input id="ApellidoMaterno" type="text"
							value="{/Service/Request/Fields/fldApellidoMaterno}" maxlength="20" size="20"
							readonly="yes"/>
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Direcci&#243;n:</td>
					<td class="cData">
						<input id="Direccion" type="text" 
							value="{/Service/Request/Fields/fldDireccion}" maxlength="20" size="20"
							readonly="yes"/>
						* Numero:
						<input id="Numero" type="text" 
							value="{/Service/Request/Fields/fldNumero}" maxlength="5" size="5"
							readonly="yes"/>
						*
					</td>
				</tr>

				<tr>
					<td class="cLabel">Villa / Poblaci&#243;n:</td>
					<td class="cData">
						<input id="Villa" type="text" 
							value="{/Service/Request/Fields/fldVilla}" readonly="yes"
							maxlength="30" size="30"/>
						Block
						<input id="Block" type="text" 
							value="{/Service/Request/Fields/fldBlock}" readonly="yes"
							maxlength="5" size="5"/>
						Depto.
						<input id="Departamento" type="text"
							value="{/Service/Request/Fields/fldDepartamento}" 
							readonly="yes" maxlength="5" size="5"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Comuna:</td>
					<td>
						<select id="Comuna" disabled="yes">
							<xsl:for-each select="/Service/Response/Fields/ListComuna/Record">
								<option value="{cID}">
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
				<tr>
					<td class="cLabel">Telefonos:</td>
					<td class="cData">
						<input id="Telefono" type="text" readonly="yes"
							value="{/Service/Request/Fields/fldTelefono}" 
							maxlength="12" size="12"/>*&#160;
						Celular
						<input id="Celular" type="text" readonly="yes"
							value="{/Service/Request/Fields/fldCelular}"
							maxlength="12" size="12"/>
					</td>
				</tr>

				<tr>
					<td class="cLabel">Correo Electr&#243;nico:</td>
					<td class="cData">
						<input id="Mail" type="text" readonly="yes"
							value="{/Service/Request/Fields/fldMail}" 
							maxlength="50" size="50"/>
					</td>
				</tr>
			</table>
			
 		<br/>
		<input value="Grabar Apoderado" type="button" id="BotonGrabaApoderado" 
			onclick="javascript:grabaApoderado();" disabled=""/>

		<br/>
		<span class="cLabel">* Campos Obligatorios</span><br/>

		<hr width="80%" align="left" />

		<table width="80%" border="0">
			<caption>Hijos / Pupilos</caption>
			<tr>
				<td class="cLabel">R.U.T.:</td>
				<td class="cData">
					<input name="fldRUTPupilo" type="text" id="fldRUTPupilo" value="{/Service/Request/Fields/fldRUT}" 
						maxlength="10" size="10" disabled=""/>
					(12345678-5)*
					<input type="button" value="Buscar" name="BotonBuscaPupilo" id="BotonBuscaPupilo" disabled="" onclick="javascript:buscaMMBB()"/>
				</td>
				 
			</tr>
			<tr>
			<td colspan="2">
				<select size="5" style="width:100%" name="ListaMMBB" id="ListaMMBB"/></td>
			</tr>
		</table>

		<br/>
		<input value="Volver" type="button" onclick="javascript:cancelar();" />
<!-- 
			<hr width="80%" align="left" />
			<input value="Aceptar" type="button" onclick="javascript:aceptar();" />
			&#160;&#160;&#160;

 -->
		 
 <!-- 
		<xsl:copy-of select="/" />
  --> 
		<iframe name="iApoderado" frameborder="0" style="display:none"></iframe>

		<form name="f" id="f" method="post" action="/{/Service/Context/SiteName}/">
<!-- 
		<form name="fApoderado" id="fApoderado" action="ControlServlet" method="post" target="iApoderado">
 -->
			<input type="hidden" name="bsServiceName" id="bsServiceName"/>
			<input type="hidden" name="fldFechaRegistro" id="fldFechaRegistro" value="{/Service/Request/Date}" />
			<input type="hidden" name="fldTabla" id="fldTabla" value="tApoderado" />

			<input type="hidden" name="fldID" id="fldID"/>
			<input type="hidden" name="fldRUT" id="fldRUT"/>
			<input type="hidden" name="fldRUTApoderado" id="fldRUTApoderado"/>
		</form>

		<script src="public/js/SCO/registroApoderado.js" type="text/javascript"/>

<!-- 
		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function cancelar(){ 
					document.getElementById("f").target = "";
					document.getElementById("f").bsServiceName.value = "SCO.ListPersona";
					document.getElementById("f").submit(); 
				}

				function grabaApoderado() {  
					var msg = validaFrom(); 

					if(msg.length > 0){ 
						alert('ATENCION\n\n' + msg); 
					} else {
						document.getElementById("f").target = "iApoderado";
						document.getElementById("f").bsServiceName.value = "SCO.GrabaApoderado";
						document.getElementById("f").submit();
					}
				}

				function buscaApoderado(){
					var validRut = validaRUT(document.getElementById("fldRUT").value);

					if(!validRut){ 
						alert("Rut de apoderado no es v&#225;lido\n"); 
					} else {
						document.getElementById("fApoderado").fldRUT.value = document.getElementById("fldRUT").value;
						document.getElementById("fApoderado").bsServiceName.value = "SCO.BuscaApoderado";
						document.getElementById("fApoderado").submit(); 
					}
				}

				function buscaMMBB(){
					var validRut = validaRUT(document.getElementById("fldRUTPupilo").value);

					if(!validRut){ 
						alert("Rut de Beneficiario no es v&#225;lido\n"); 
					} else {
						document.getElementById("fApoderado").fldRUT.value = document.getElementById("fldRUTPupilo").value;
						document.getElementById("fApoderado").bsServiceName.value = "SCO.BuscaMMBB";
						document.getElementById("fApoderado").fldRUTApoderado.value = document.getElementById("fldRUT").value;
						document.getElementById("fApoderado").submit(); 
					}					
				}

				function validaFrom(){ 
					var out = ""; 

					if(document.getElementById("fldNombre").value.length == 0) out += '- Nombre no fue ingresado \n';
					if(document.getElementById("fldApellidoPaterno").value.length == 0){out += '- Apellido Paterno no fue ingresado \n';}
					if(document.getElementById("fldApellidoMaterno").value.length == 0){out += '- Apellido Materno no fue ingresado \n';}

					if(document.getElementById("fldDireccion").value.length == 0){out += '- Direcci&#243;n no se ingres&#243;\n';}
					if(document.getElementById("fldNumero").value.length == 0){out += '- N&#250;mero de Direcci&#243;n no se ingres&#243;\n';}
					if(document.getElementById("fldTelefono").value.length == 0){out += '- Tel&#233;fono fijo no se ingres&#243;\n';}


					return out;
				}
				
			</xsl:comment>
		</script>
  -->
	</xsl:template>
</xsl:stylesheet>