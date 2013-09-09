<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">
		<xsl:element name="html">
			<body>
				Buildersoft
				<br />
				<form action="ControlServlet" method="post">
					<input type="hidden" name="bsServiceName" value="USR.validLogin" />
					Login:
					<input type="text" name="fldLogin" id="fldLogin" />
					<br />
					Password:
					<input type="password" name="fldPassword" id="fldPassword" />
					<input type="submit" value="Ingresar..." />
				</form>
			</body>


			<!--
				<xsl:element name="script"> self.location.href =
				"ControlServlet?bsServiceName=ACO.Page" + "&#38;".charAt(0) +
				"fldID=0"; </xsl:element>
			-->
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
