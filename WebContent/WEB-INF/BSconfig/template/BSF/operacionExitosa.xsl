<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Operacion exitosa</h1>
		
		<input type="button" value="Aceptar" onclick="redirect('{/Response/NextService}');"/>
<br/>
		<input type="hidden" Name="fldNextService" ID="fldNextService" value="{/Response/NextService}"/>
		<span class="cLabel" Name="fldMessage" ID="fldMessage"/>

	</xsl:template>
</xsl:stylesheet>