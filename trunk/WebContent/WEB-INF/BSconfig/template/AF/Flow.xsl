<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:decimal-format name="NUM-CL" decimal-separator="," grouping-separator="." minus-sign="-" />
	<xsl:template match="/">
		<h1 class="cTitle">Flujo empresas</h1>

		<form id="f" method="post" action="/{/Service/Context/SiteName}/">
			<input name="bsServiceName" id="bsServiceName" value="AF.Flow" type="hidden" />
			<input name="InitDate" id="InitDate" type="hidden" />
			<input name="fldID" id="fldID" type="hidden" />
		</form>
		<table border="0">
			<tr>
				<td class="cLabel">Fecha Limite:</td>
				<td>
					<input id="inputInitDate" onBlur="javascript:validDate(this.id, 'divInitDate');">
						<xsl:attribute name="value">
					<xsl:choose>
						<xsl:when test="/Service/Request/Fields/InitDate">
							<xsl:value-of select="/Service/Request/Fields/InitDate" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="/Service/Request/Date/@Day" />/<xsl:value-of select="/Service/Request/Date/@Month" />/<xsl:value-of
							select="/Service/Request/Date/@Year" />
						</xsl:otherwise>
					</xsl:choose>
					</xsl:attribute>

					</input>
					<div class="cLabel" id="divInitDate" />
				</td>
				<!-- 
				<td class="cLabel"> Fecha T&#233;rmino:</td>
				<td>
					<input name="EndDate"/>
				</td>
	 -->
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input type="button" value="Buscar" onclick="javascript:doFilter()" />
				</td>
			</tr>
		</table>

		<div id="divFlow">
			<table class="cList" cellpadding="0" cellspacing="0" id="tableFlow">
				<tr>
					<td class="cHeadTD">&#160;</td>
					<xsl:for-each select="/Service/Session/Flow/Dates/Date">
						<td class="cHeadTD" align="center">
							<xsl:value-of select="@Day" />/<xsl:value-of select="@Month" />/<xsl:value-of select="@Year" />
						</td>
					</xsl:for-each>
				</tr>

				<xsl:call-template name="ProcessNode">
					<xsl:with-param name="ParentNode" select="/Service/Session/Flow/Tree" />
				</xsl:call-template>
			</table>
		</div>


		<table border="0" width="100%">
			<tr>
				<td>
					<a href="javascript:skipDate(-7);">&lt;&lt;-</a>&#160;&#160;&#160;&#160;<a href="javascript:skipDate(-1);">&lt;-</a></td>
		<td align="right"><a href="javascript:skipDate(1);">-&gt;</a>&#160;&#160;&#160;&#160;<a href="javascript:skipDate(7);">-&gt;&gt;</a></td>
		</tr>
		</table>
		
<!--
		<table border="0" width="100%">
		<tr>
		<td><a href="javascript:skipDate(-1);">&lt;-Anterior</a></td>
		<td align="right"><a href="javascript:skipDate(1);">Siguiente-&gt;</a></td>
		</tr>
		</table>
  -->

		
			
		<hr/>
		
		  <a href="javascript:interenterpriseDialog();">Movimiento entre empresas...</a>
		 
		  <!-- 
 		<xsl:element name="textarea">
			<xsl:copy-of select="/" />
		</xsl:element>
 		   -->
 		 
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AF/InterMove.xsl"/>
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AF/UpdateDrag.xsl"/>
		<BS2 Class="cl.builderSoft.framework.service.ParseXSL" Filename="AF/ShowDetail.xsl"/>
		
		<script type="text/javascript" src="public/js/AF/flow/flow.js?rnd={/Service/Session/@ID}"></script>

		  <!--  
		<textarea><xsl:copy-of select="/"/></textarea>
		  -->
		
	</xsl:template>
	
	
	<xsl:template name="ProcessNode">
		<xsl:param name="ParentNode"/>
		
		<xsl:for-each select="$ParentNode/TreeNode">					
			<xsl:element name="tr">
				<xsl:element name="td">
					<xsl:attribute name="class">cDataTD</xsl:attribute>
					<xsl:attribute name="nowrap"/>

					<xsl:call-template name="EspacioEnBlanco">
						<xsl:with-param name="Cuantos" select="@cLevel"/>
					</xsl:call-template>

					<xsl:choose>
						<xsl:when test="@cIsLast=0 and count(./TreeNode) = 0">
							<a href="javascript:getChild('{@cID}');">
								<img src="public/img/mas.gif" border="0"/>
							</a>
						</xsl:when>
						<xsl:when test="@cIsLast=0 and count(./TreeNode) > 0">
							<a href="javascript:delChild('{@cID}');">
								<img src="public/img/menos.gif" border="0"/>
							</a>
						</xsl:when>
						<xsl:when test="@cIsLast=1">*&#160;</xsl:when>
					</xsl:choose>

					<xsl:choose>
						<xsl:when test="@cIsDrag=1 and @cLevel=2">
							<a href="javascript:dragDialog('{@cID}', '{@cEntityName}');">
								<xsl:value-of select="@cEntityName" />
							</a>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="@cEntityName" />
						</xsl:otherwise>
					</xsl:choose>			

					<xsl:call-template name="ShowValues">
						<xsl:with-param name="Fechas" select="."/>
						<xsl:with-param name="IsLast" select="@cIsLast"/>
					</xsl:call-template>
<!-- 					
					<xsl:for-each select="Dates/Date">
						<td class="cDataTD" align="right">
							<xsl:attribute name="nowrap"/>
							<xsl:attribute name="style">color:
							<xsl:choose>
								<xsl:when test=".&gt;=0">black</xsl:when>
								<xsl:otherwise>red</xsl:otherwise>							
							</xsl:choose>
							</xsl:attribute>
							<xsl:value-of select="format-number(., '###.###', 'NUM-CL')"/>
						</td>
					</xsl:for-each>
 -->
 					
					
					
					
				</xsl:element>
			</xsl:element>

			<xsl:call-template name="ProcessNode">
				<xsl:with-param name="ParentNode" select="."/>
			</xsl:call-template>

		</xsl:for-each>
		
	</xsl:template>
	
	<xsl:template name="ShowValues">
		<xsl:param name="Fechas"/>
		<xsl:param name="IsLast"/>
		<xsl:for-each select="$Fechas/Dates/Date">
			<td class="cDataTD" align="right">
				<xsl:attribute name="nowrap"/>
				<xsl:choose>
					<xsl:when test="$IsLast=1 and .!= 0">

						<div onclick="javascript:showDetail('{$Fechas/@cID}', '{./@Year}', '{./@Month}', '{./@Day}');">
							<xsl:attribute name="style">font-size:12;cursor:pointer;color:
								<xsl:choose>
									<xsl:when test=".&gt;=0">black</xsl:when>
									<xsl:otherwise>red</xsl:otherwise>							
								</xsl:choose>
							</xsl:attribute>
							<u><xsl:value-of select="format-number(., '###.###', 'NUM-CL')"/></u>
						</div>
					</xsl:when>
					<xsl:otherwise>
							<xsl:attribute name="style">color:
								<xsl:choose>
									<xsl:when test=".&gt;= 0">black</xsl:when>
									<xsl:otherwise>red</xsl:otherwise>							
								</xsl:choose>
							</xsl:attribute>
					
							<xsl:value-of select="format-number(., '###.###', 'NUM-CL')"/>
					</xsl:otherwise>
				</xsl:choose>
			</td>
		</xsl:for-each>
 	</xsl:template>
	
	
	<xsl:template name="EspacioEnBlanco">
		<xsl:param name="Cuantos"/>
 		<xsl:choose>
			<xsl:when test="$Cuantos &gt; 0">
				&#160;&#160;&#160;&#160; 
				<xsl:call-template name="EspacioEnBlanco">
					<xsl:with-param name="Cuantos" select="$Cuantos - 1"/>
				</xsl:call-template>

			</xsl:when>		
			<xsl:otherwise>
				
			</xsl:otherwise>
		</xsl:choose>
 		
	</xsl:template>
	
</xsl:stylesheet>