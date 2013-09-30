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
						alert('RUT no existe, favor corregir');
					} else {
						if(table == 'tMMBB'){
							var nombre = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>&#160;<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/>";
							var rut = "<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cRUT"/>";
							var combo = parent.document.getElementById("ListaMMBB");
							var len = combo.length;

							combo.options[len] = new Option(nombre, "");
						}
						if(table == 'tDYG'){
							alert('El RUT corresponde a <xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>&#160;<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/> quien es un Dirigente o Guiadora');
						}
						if(table == 'tApoderado'){
							alert('El RUT corresponde a <xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cNombre"/>&#160;<xsl:value-of select="/Service/Response/Fields/BuscaPorRut/Record/cApellidoPaterno"/> quien es un Apoderado');
						}
					}
				}
			</xsl:comment>
		</script>

		<xsl:copy-of select="/" />


	</xsl:template>
</xsl:stylesheet>
