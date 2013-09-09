<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">

		<xsl:element name="div">
			<xsl:element name="select">
				<xsl:attribute name="name"><xsl:value-of select="/Service/TAG/NombreCampo" />Hora</xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="/Service/TAG/NombreCampo" />Hora</xsl:attribute>

				<xsl:call-template name="for">
					<xsl:with-param name="current">1</xsl:with-param>
					<xsl:with-param name="end">24</xsl:with-param>
					<xsl:with-param name="step">1</xsl:with-param>
				</xsl:call-template>


			</xsl:element>
			:
			<xsl:element name="select">
				<xsl:attribute name="name"><xsl:value-of select="/Service/TAG/NombreCampo" />Minuto</xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="/Service/TAG/NombreCampo" />Minuto</xsl:attribute>

				<xsl:call-template name="for">
					<xsl:with-param name="current">0</xsl:with-param>
					<xsl:with-param name="end">60</xsl:with-param>
					<xsl:with-param name="step">5</xsl:with-param>
				</xsl:call-template>

			</xsl:element>

		</xsl:element>


	</xsl:template>

	<xsl:template name="for">
		<xsl:param name="current" />
		<xsl:param name="end" />
		<xsl:param name="step" />

		<xsl:if test="$current &#60;= $end">
			<xsl:element name="option">
				<xsl:attribute name="value">
					<xsl:value-of select="$current" />
				</xsl:attribute>
				<xsl:choose>
					<xsl:when test="$current &#60; 10">
						0<xsl:value-of select="$current" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$current" />
					</xsl:otherwise>
				</xsl:choose>
			</xsl:element>
			<xsl:call-template name="for">
				<xsl:with-param name="current" select="$current+$step" />
				<xsl:with-param name="end" select="$end" />
				<xsl:with-param name="step" select="$step" />
			</xsl:call-template>
		</xsl:if>

	</xsl:template>

</xsl:stylesheet>
