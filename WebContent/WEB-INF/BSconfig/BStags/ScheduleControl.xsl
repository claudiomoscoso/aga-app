<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">

		<xsl:element name="OBJECT">
			<xsl:attribute name="classid">clsid:D27CDB6E-AE6D-11cf-96B8-444553540000</xsl:attribute>
			<xsl:attribute name="codebase">
				<xsl:text>http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0</xsl:text>
			</xsl:attribute>
			<xsl:attribute name="WIDTH">100%</xsl:attribute>
			<xsl:attribute name="HEIGHT">400</xsl:attribute>
			<xsl:attribute name="id">myMovieName</xsl:attribute>
			<xsl:element name="PARAM">
				<xsl:attribute name="NAME">movie</xsl:attribute>
				<xsl:attribute name="VALUE">public/MED/grid.swf</xsl:attribute>
			</xsl:element>
			<xsl:element name="PARAM">
				<xsl:attribute name="NAME">quality</xsl:attribute>
				<xsl:attribute name="VALUE">high</xsl:attribute>
			</xsl:element>
			<xsl:element name="PARAM">
				<xsl:attribute name="NAME">bgcolor</xsl:attribute>
				<xsl:attribute name="VALUE">#FFFFFF</xsl:attribute>
			</xsl:element>
			<xsl:element name="EMBED">
				<xsl:attribute name="src">public/MED/grid.swf</xsl:attribute>
				<xsl:attribute name="quality">high</xsl:attribute>
				<xsl:attribute name="bgcolor">#FFFFFF</xsl:attribute>
				<xsl:attribute name="WIDTH">550</xsl:attribute>
				<xsl:attribute name="HEIGHT">100%</xsl:attribute>
				<xsl:attribute name="NAME">myMovieName</xsl:attribute>
				<xsl:attribute name="ALIGN"></xsl:attribute>
				<xsl:attribute name="TYPE">application/x-shockwave-flash</xsl:attribute>
				<xsl:attribute name="PLUGINSPAGE">http://www.macromedia.com/go/getflashplayer</xsl:attribute>
			</xsl:element>
		</xsl:element>


	</xsl:template>
</xsl:stylesheet>
