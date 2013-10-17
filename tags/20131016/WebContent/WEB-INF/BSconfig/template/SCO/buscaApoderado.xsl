<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<body onload="javascript:onLoadPage()"></body>

		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){
					var table = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cTabla"/>";
//alert(table);
					if(table == ''){

//						document.parent.getElementById("fldSexoMasculino").disabled = false;
						parent.document.getElementById("SexoMasculino").disabled = false;
						parent.document.getElementById("SexoFemenino").disabled = false;
						parent.document.getElementById("Nombre").readOnly = false;
						parent.document.getElementById("ApellidoPaterno").readOnly = false;
						parent.document.getElementById("ApellidoMaterno").readOnly = false;
						parent.document.getElementById("Direccion").readOnly = false;
						parent.document.getElementById("Numero").readOnly = false;
						parent.document.getElementById("Villa").readOnly = false;
						parent.document.getElementById("Block").readOnly = false;
						parent.document.getElementById("Departamento").readOnly = false;
						parent.document.getElementById("Comuna").disabled = false;
						parent.document.getElementById("Telefono").readOnly = false;
						parent.document.getElementById("Celular").readOnly = false;
						parent.document.getElementById("Mail").readOnly = false;
						parent.document.getElementById("RUT").readOnly = true;
						parent.document.getElementById("BotonBuscaApoderado").disabled = true;
						parent.document.getElementById("BotonGrabaApoderado").disabled = false;

						alert('Apoderado no existe, ahora ser&#225; ingresado...');
					} else {
						if(table == 'tMMBB'){
							alert('El RUT corresponde a <xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>&#160;<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/> quien es un Beneficiario');
						}
						if(table == 'tDYG'){
							alert('El RUT corresponde a <xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>&#160;<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/> quien es un Dirigente o Guiadora');
						}
						if(table == 'tApoderado'){
							if("<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cMasculino"/>" == "1"){
								parent.document.getElementById("f").fldSexoMasculino.checked = true;
							} else {
								parent.document.getElementById("f").fldSexoFemenino.checked = true;
							}

							parent.document.getElementById("f").fldNombre.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>";
							parent.document.getElementById("f").fldApellidoPaterno.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/>";
							parent.document.getElementById("f").fldApellidoMaterno.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoMaterno"/>";
							parent.document.getElementById("f").fldDireccion.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cDireccion"/>";
							parent.document.getElementById("f").fldNumero.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNumero"/>";
							parent.document.getElementById("f").fldVilla.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cVilla"/>";
							parent.document.getElementById("f").fldBlock.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cBlock"/>";
							parent.document.getElementById("f").fldDepartamento.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cDepartamento"/>";
							parent.document.getElementById("f").fldComuna.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cComuna"/>";
							parent.document.getElementById("f").fldTelefono.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cTelefono"/>";
							parent.document.getElementById("f").fldCelular.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cCelular"/>";
							parent.document.getElementById("f").fldMail.value = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cMail"/>";
							parent.document.getElementById("fldRUT").disabled = true;
							parent.document.getElementById("BotonBuscaApoderado").disabled = true;

							parent.document.getElementById("fldRUTPupilo").disabled = false;
							parent.document.getElementById("BotonBuscaPupilo").disabled = false;

							var combo = parent.document.getElementById("ListaMMBB");
							var nombre = null;
							<xsl:for-each select="/Service/Response/Fields/BuscaMMBBs/Record">
								nombre = "<xsl:value-of select="cNombre"/>&#160;<xsl:value-of select="cApellidoPaterno"/>&#160;<xsl:value-of select="cApellidoMaterno"/>";
								combo.options[<xsl:value-of select="position()-1"/>] = new Option(nombre, "");
							</xsl:for-each>


						}
					}
				}
			</xsl:comment>
		</script>

		<xsl:copy-of select="/" />


	</xsl:template>
</xsl:stylesheet>
