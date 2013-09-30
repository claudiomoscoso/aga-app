<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="public/css/sdmenu.css" />
				<script type="text/javascript" src="public/js/sdmenu.js"></script>
			</head>
			<script type="text/javascript">
				//
				<![CDATA[
			var myMenu;
			window.onload = function() {
				myMenu = new SDMenu("my_menu");
				myMenu.init();
				myMenu.speed = 5;
			};
			// ]]>
			</script>
			<body marginwidth="0" marginheight="0" bgcolor="#EDEDED">

				<div style="float: left" id="my_menu" class="sdmenu">
					<xsl:for-each select="/Service/Session/Menu/Option">
						<div>
							<span>
								<xsl:value-of select="@Label" />
							</span>
							<xsl:for-each select="Option">
								<xsl:if test="@Selected=1">
									<a href="{@url}&amp;Option={@ID}" target="body">
										<xsl:value-of select="@Label" />
									</a>
								</xsl:if>
							</xsl:for-each>

						</div>
					</xsl:for-each>


				</div>
				<!-- 
					<xsl:copy-of select="/" />
				-->
			</body>
		</html>

	</xsl:template>
</xsl:stylesheet>
