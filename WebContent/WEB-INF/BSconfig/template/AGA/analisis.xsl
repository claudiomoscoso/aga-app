<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<h1 class="cTitle">
			Ingrese todos los datos del certificado 
		</h1>

		<input type="hidden" id="ID">
			<xsl:attribute name="value"><xsl:call-template name="GetID" /></xsl:attribute>
		</input>
		<input type="hidden" id="GasPuro" value="{/Service/Response/Fields/GetSolicitud/Record/cGaspuro}"/>

		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/01-DatosProducto.xsl" />
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/02-DatosCliente.xsl" />
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/03-DatosCilindro.xsl" />
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/04-DatosAnalisis.xsl" />
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/05-DatosMetodo.xsl" />
		<br />
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AGA/06-Botones.xsl" />

		<script type="text/javascript" src="public/js/AGA09/analisis.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/01-DatosProducto.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/02-DatosCliente.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/03-DatosCilindro.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/04-DatosAnalisis.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/05-DatosMetodo.js?rnd={/Service/Session/@ID}"></script>
		<script type="text/javascript" src="public/js/AGA09/06-Botones.js?rnd={/Service/Session/@ID}"></script>

		<br />
		<br />
		<br />

<!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
-->

	</xsl:template>
	<xsl:template name="GetID">
		<xsl:choose>
			<xsl:when test="count(/Service/Response/Fields/CreateSolicitud)&gt;0">
				<xsl:value-of select="/Service/Response/Fields/CreateSolicitud" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="/Service/Request/Fields/fldID" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>


</xsl:stylesheet>