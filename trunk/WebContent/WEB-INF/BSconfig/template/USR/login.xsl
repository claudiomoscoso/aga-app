<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:value-of select="/Service/Response/User"/>
	<span class="cLabel">Este es el xsl para mostrar que el usuario es valido, el menu y todo...</span>
	</xsl:template>
</xsl:stylesheet>