<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">
		<xsl:variable name="valor">
			<xsl:choose>
				<xsl:when test="string-length(/Service/TAG/Value)=0">
					<xsl:value-of select="/Service/Request/Date/@Day" />-<xsl:value-of select="/Service/Request/Date/@Month" />-<xsl:value-of select="/Service/Request/Date/@Year" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="/Service/TAG/Value" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<!-- 
			<xsl:variable name="Anio" select="substring-before($valor, '-')"/>
			<xsl:variable name="MesDia" select="substring-after($valor, '-')"/>
			<xsl:variable name="Mes" select="substring-before($MesDia, '-')"/>
			<xsl:variable name="Dia" select="substring-after($MesDia, '-')"/>
		-->
		<!-- 
			<xsl:value-of select="$Anio"/>
			<xsl:value-of select="$MesDia"/>
		 
		+<xsl:value-of select="$valor" />+
		  
			<xsl:element name="br"/>
			
			<xsl:value-of select="$Dia"/>-
			<xsl:value-of select="$Mes"/>-
			<xsl:value-of select="$Anio"/>
		-->

		<xsl:element name="span">
			<xsl:attribute name="id"><xsl:value-of select="/Service/TAG/FieldNameDisp" />Disp</xsl:attribute>
		</xsl:element>

		<xsl:element name="input">
			<xsl:attribute name="id">
				<xsl:value-of select="/Service/TAG/FieldName" />
			</xsl:attribute>
			<xsl:attribute name="type">text</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="$valor" />
			</xsl:attribute>
		</xsl:element>
		&#160;
		<xsl:element name="img">
			<xsl:attribute name="id">miboton</xsl:attribute>
			<xsl:attribute name="src">public/img/calendar.png</xsl:attribute>
			<xsl:attribute name="onclick">javascript:showCalendar();</xsl:attribute>
			<xsl:attribute name="style">cursor:hand</xsl:attribute>
		</xsl:element>

		<xsl:element name="script">

			function showCalendar(){

			Calendar.setup({

			displayArea : '<xsl:value-of select="/Service/TAG/FieldName" />Disp',

			inputField : '<xsl:value-of select="/Service/TAG/FieldName" />',

			ifFormat : '%d-%m-%Y',

			daFormat : '%d-%m-%Y',

			button : 'miboton'

			});

			}
		</xsl:element>


	</xsl:template>
</xsl:stylesheet>
