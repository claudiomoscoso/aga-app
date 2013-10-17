<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<xsl:element name="textarea">
		<xsl:attribute name="style">width:100%;height:50%</xsl:attribute>
			<xsl:copy-of select="/" />
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>