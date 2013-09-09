<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h1 class="cTitle">Se ha producido un error</h1>
		
		<input type="hidden" name="bsServiceName" value="BSF.body"/>
		<span class="cLabel">Reintente la acci@oacute;n</span>
		<br/>
		<br/>
		<br/>
		<span class="cData">[<xsl:value-of select="/Response/Error/Description"/>]
		</span>
<!--
<xsl:copy-of select="/"/>
-->

	</xsl:template>
</xsl:stylesheet>