<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<title>BuilderSoft Demo Software</title>
			</head>

			<frameset rows="*" cols="910,339" framespacing="0" frameborder="no" border="0">
				<frameset rows="110,*" frameborder="no">
					<frame scrolling="no" src="public/top.html" />
					<frame src="ControlServlet?bsServiceName=BSK.ShowProductType" name="principal" id="principal" />
				</frameset>
				<frame src="public/publicidad.html" />
			</frameset>

			<!--
				<xsl:copy-of select="/"/>
			-->
		</html>
	</xsl:template>
</xsl:stylesheet>