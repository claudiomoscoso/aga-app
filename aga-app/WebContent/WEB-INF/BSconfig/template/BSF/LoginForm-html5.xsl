<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" encoding="UTF-8" indent="yes" />
	<xsl:template match="/">
		<!-- <![CDATA[<!DOCTYPE html>]]> -->
		<xsl:element name="html">
			<xsl:attribute name="lang">es</xsl:attribute>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

				<meta name="viewport" content="width=device-width, initial-scale=1.0" />
				<meta name="description" content="" />
				<meta name="author" content="" />
				<link href="/{/Service/Context/SiteName}/bootstrap/css/bootstrap.css"
					rel="stylesheet" />

				<style type="text/css">
<![CDATA[
.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
]]>
				</style>

				<title>Ingreso de usuario</title>
			</head>

			<body>


				<div class="container">

					<div class="row">&#160;</div>
					<div class="row">&#160;</div>
					<form class="form-signin" action="ControlServlet" method="post">
						<div class="row">&#160;</div>
						<div class="row text-center">
							<img border="0" src="public/img/logo-aga.gif?{/Service/Session/@ID}"
								style="height:120px" alt="Linde" />
						</div>

						<input type="hidden" name="bsServiceName" value="USR.validLogin" />

						<div class="row">&#160;</div>
						Login:
						<input type="text" name="fldLogin" id="fldLogin" class="input-block-level"
							placeholder="Login de usuario" />
						Clave:
						<input type="password" name="fldPassword" id="fldPassword"
							class="input-block-level" placeholder="Clave de acceso" />

						<button class="btn btn-large btn-primary" type="submit">Acceder...</button>
					</form>

				</div>



				<!-- BuilderSoft. <br /> <form action="ControlServlet" method="post"> 
					<input type="hidden" name="bsServiceName" value="USR.validLogin" /> Login: 
					<input type="text" name="fldLogin" id="fldLogin" /> <br /> Password: <input 
					type="password" name="fldPassword" id="fldPassword" /> <input type="submit" 
					value="Ingresar..." /> </form> -->


				<script src="/{/Service/Context/SiteName}/bootstrap/js/jquery.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-transition.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-alert.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-modal.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-dropdown.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-scrollspy.js"></script>
				<script src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-tab.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-tooltip.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-popover.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-button.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-collapse.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-carousel.js"></script>
				<script
					src="/{/Service/Context/SiteName}/bootstrap/js/bootstrap-typeahead.js"></script>

				<!-- <xsl:value-of select="/Service/Context/SiteName" /> <xsl:copy-of 
					select="/"/> -->

			</body>

		</xsl:element>

	</xsl:template>
</xsl:stylesheet>
