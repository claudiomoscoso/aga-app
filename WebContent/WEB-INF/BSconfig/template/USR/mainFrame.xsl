<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- 
			<xsl:value-of select="/Response/Fields/User/cNombre" />
			
			<xsl:copy-of select="/"/>
			
			
			<xsl:choose>
			<xsl:when test="/Response/Fields/User/Record/activo!=0">
			
			
			
			
		-->
		<html>
			<head>
				<title>BuilderSoft Demo Software</title>
			</head>
			<frameset cols="14%,*">
				<frame name='menu' src="ControlServlet?bsServiceName=BSF.menu" scrolling="no" marginwidth="0" marginheight="0" />
				<frame name='body' src="ControlServlet?bsServiceName=BSF.body" />
			</frameset>
		</html>

		<!-- 
			</xsl:when>
			<xsl:otherwise>
			<html>
			<head>
			<title>BuilderSoft Demo Software</title>
			<link rel="stylesheet" type="text/css" href="public/css/default.css" />
			</head>
			<body>
			<center>
			
			<xsl:copy-of select="/" />
			
			<h1>
			<span class="cMessage">Usuario no es válido</span>
			</h1>
			<br />
			<span class="cMessage">
			<a href="/{/Response/Session/ContextInfo/SiteName}">Inicio</a>
			</span>
			</center>
			</body>
			</html>
			
			
			
			
			</xsl:otherwise>
			</xsl:choose>
		-->
	</xsl:template>
</xsl:stylesheet>