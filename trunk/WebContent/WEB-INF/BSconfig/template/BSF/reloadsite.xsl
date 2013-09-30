<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<body onload="f.submit()">
			<form id="f" action="/{/Service/Context/SiteName}/" target="_top">
				<input TYPE="hidden" name="bsServiceName" value="BSK.root" />
			</form>
		</body>
	</xsl:template>
</xsl:stylesheet>