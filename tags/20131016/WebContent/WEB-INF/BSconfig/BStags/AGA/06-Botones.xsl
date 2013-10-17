<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">


		<!-- 
		<input value="Grabar" type="button" disabled="1"/>&#160;
		-->
		<input id="ValidarAnalisis" value="Validar Analisis" type="button" onclick="javascript:validarAnalisis()" />
		&#160;
		<input id="imprimirCertificado" value="Imprimir Certificado" disabled="1" type="button" onclick="javascript:printCertificado({/Service/Response/Fields/GetSolicitud/Record/cGaspuro}, '{/Service/Request/Fields/fldID}');"/>

		&#160;

		<input id="imprimirEtiqueta" value="Imprimir Etiqueta" type="button" onclick="javascript:addEtiqueta()" disabled="1" />
		&#160;
		<a href="?bsServiceName=AGA.certificado">Volver...</a>

	</xsl:template>

<!-- 

	<xsl:template name="url">
	<xsl:text disable-output-escaping="yes"><string>javascript:alert('
	<xsl:choose>
		<xsl:when test="/Service/Response/Fields/GetSolicitud/Record/cGaspuro='1'">PdfServletGas</xsl:when>
		<xsl:otherwise>PdfServlet</xsl:otherwise>
	</xsl:choose>');</string></xsl:text>
		xjavascript:self.location.href=&#34;<xsl:choose>
		<xsl:when test="/Service/Response/Fields/GetSolicitud/Record/cGaspuro='1'">PdfServletGas</xsl:when>
		<xsl:otherwise>PdfServlet</xsl:otherwise>
	</xsl:choose>?id=<xsl:value-of select="/Service/Request/Fields/fldID" />&#34;
		
	</xsl:template>
	 -->

</xsl:stylesheet>