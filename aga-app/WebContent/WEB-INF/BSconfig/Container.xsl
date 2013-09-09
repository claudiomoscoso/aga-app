<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<!-- 
			<BS2 Class="cl.builderSoft.framework.html.GetHtmlHead" />
		-->
		<html>
			<head>
				<title>Linde AGA - Software</title>
				<!-- 
					<xsl:element name="meta">
					<xsl:attribute name="http-equiv">Content-Type</xsl:attribute>
					<xsl:attribute name="content">text/html; charset=UTF-8</xsl:attribute>
					</xsl:element>
					<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
				-->
				<xsl:element name="link">
					<xsl:attribute name="rel">stylesheet</xsl:attribute>
					<xsl:attribute name="type">text/css</xsl:attribute>
					<xsl:attribute name="href">public/css/default.css?rnd=<xsl:value-of select="/Service/Session/@ID"/></xsl:attribute>
				</xsl:element>

				<xsl:element name="link">
					<xsl:attribute name="rel">stylesheet</xsl:attribute>
					<xsl:attribute name="type">text/css</xsl:attribute>
					<xsl:attribute name="media">all</xsl:attribute>
					<xsl:attribute name="href">public/dCalendar/calendar-yellow.css</xsl:attribute>
					<xsl:attribute name="title">win2k-cold-1</xsl:attribute>
				</xsl:element>

				<xsl:element name="link">
					<xsl:attribute name="rel">stylesheet</xsl:attribute>
					<xsl:attribute name="type">text/css</xsl:attribute>
					<xsl:attribute name="href">public/css/sdmenu.css</xsl:attribute>
					<xsl:attribute name="title">win2k-cold-1</xsl:attribute>
				</xsl:element>

				<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/sdmenu.js</xsl:attribute>
				//</xsl:element>
 <xsl:comment/>
				<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/BSEffect.js?rnd=<xsl:value-of select="/Service/Session/@ID"/></xsl:attribute>
				//</xsl:element>
 <xsl:comment/>
				<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/BSServiceData.js</xsl:attribute>
				//</xsl:element>
<xsl:comment/>
				<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/BSajax.js?rnd=<xsl:value-of select="/Service/Session/@ID"/></xsl:attribute>
				</xsl:element>

				<xsl:element name="script">
					<xsl:attribute name="language">JavaScript1.1</xsl:attribute>
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/framework.js?rnd=<xsl:value-of select="/Service/Session/@ID"/></xsl:attribute>
				</xsl:element>

				<!-- 
					<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/BSrish.js</xsl:attribute>
					</xsl:element>


				<xsl:element name="script">
					<xsl:attribute name="type">text/javascript</xsl:attribute>
					<xsl:attribute name="src">public/js/prototype151.js</xsl:attribute>
				</xsl:element>
				-->

				<script language="JavaScript1.1"
					type="text/javascript">
					<xsl:comment>
						var myMenu;

						window.onload = function() {
					//	alert("menuStatus=" + getCookie("menuStatus"));
						/*
						alert("a");
						alert(fix(1));
						
						alert("B");
						
						alert(document.cookie);
						
						alert(getCookie("menuStatus"));
						*/
						//alert(1);
							if(getCookie("menuStatus")=="h"){
								hideMenu();
							} else {
								showMenu();
							}	
						
							myMenu = new SDMenu("my_menu");
							myMenu.init();
							myMenu.speed = 5;

						//myMenu.expandMenu(myMenu.submenus[0]);
						// loadFormat();

							try{	
								onLoadPage();
							} catch(e){}

						};
						
						function hideMenu(){
							document.getElementById("my_menu").style.display = "none";
							document.getElementById("divHideMenu").style.display = "none";
							document.getElementById("divShowMenu").style.display = "";
							setCookie("menuStatus", "h", 3);
							
						}
						function showMenu(){
							document.getElementById("my_menu").style.display = "";
							document.getElementById("divHideMenu").style.display = "";
							document.getElementById("divShowMenu").style.display = "none";
							setCookie("menuStatus", "s", 3);
						}
						
					</xsl:comment>
				</script>
			</head>

			<body>
				<div id="TOOLTIP_CONTAINER"
					style="display:none;position:absolute;top:0;left:0;height:0;width:0;background-color:#CCCCCC;opacity:0.5;z-index:1;"
					align="center">
				</div>

				<div id="TOOLTIP_CONTENT"
					style="display:none;z-index:2;position:absolute;" align="center">
					<br />
					<br />
					<table border="0" style="background-color:white;border: #000000 solid"
						width="60%" xheight="50%">
						<tr>
							<td align="center" width="10">&#160;</td>
							<td>&#160;</td>
							<td align="center" width="10">
								<a href="javascript:closeTooltip()" id="_CLOSER_TOOTLTIP_"
									onmousemove="javascript:window.status='Cerrar'">
									(X)
								</a>
							</td>
						</tr>
						<tr>
							<td align="center" width="10">&#160;</td>
							<td xheight="90%" align="center"
								valign="center" id="TOOLTIP_CELL_CONTAINER">

							</td>
							<td align="center" width="10">&#160;</td>
						</tr>
						<tr>
							<td align="center" width="10">&#160;</td>
							<td>&#160;</td>
							<td align="center" width="10">&#160;</td>
						</tr>
					</table>
				</div>

				<p>
					<table border="0" style="width:100%">
						<tr>
							<td>
								<img border="0"
									src="public/img/logo-aga.gif" style="height:70px"
									alt="Linde AGA" />
								<!--
									<img border="0" src="public/img/logo.gif" style="height:70px" alt="Buildersoft" />
								-->
							</td>
							<td align="right">
								<a
									href="ControlServlet?bsServiceName=BSF.logoff">
									<img border="0"
										src="public/img/logout.gif" alt="Salir" />
								</a>
							</td>
						</tr>
					</table>

					<table border="0">
						<tr>
							<td valign="top">
								<div style="float: left" id="my_menu"
									class="sdmenu">
									<xsl:for-each
										select="/Service/Session/Menu/Option[@Selected=1]">
										<div>
											<span>
												<xsl:value-of
													select="@Label" />
											</span>
											<xsl:for-each
												select="Option[@Selected=1]">
												<xsl:if
													test="@Selected=1">
													<a
														href="{@url}&amp;Option={@ID}">
														<xsl:value-of
															select="@Label" />
													</a>
												</xsl:if>
											</xsl:for-each>

										</div>
									</xsl:for-each>
								</div>
							</td>
							<td valign="top">
							<div id="divHideMenu">
<a href="javascript:hideMenu()" style="cursor:pointer" class="cLabel">&lt;<br/>&lt;<br/>&lt;<br/></a></div>
<div id="divShowMenu" style="display:none">
<a href="javascript:showMenu()" style="cursor:pointer" class="cLabel">&gt;<br/>&gt;<br/>&gt;<br/></a></div>
							
							</td>
							<td valign="top" width="100%">

								<BS Container="" />

							</td>
						</tr>
					</table>
					<br />
					<hr id="TOOLTIP_LIMIT"/>
					<!--
						<a target="_open"
						href="http://www.buildersoft.cl/index.php?option=com_content&amp;task=view&amp;id=10&amp;Itemid=12">
						http://www.buildersoft.cl
						</a>
					-->
				</p>
				<!-- 
<textarea>
<xsl:copy-of select="/"/>
</textarea>
 -->
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
