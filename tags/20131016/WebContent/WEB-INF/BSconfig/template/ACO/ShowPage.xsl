<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="text" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cHead" />
		<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cHTML" />
		<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cFoot" />

	</xsl:template>
</xsl:stylesheet>
