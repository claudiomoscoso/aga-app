<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<H1 class="cTitle">Contenido Página</H1>

		<form name="f" id="f" method="post" action="ControlServlet">
			<input type="hidden" name="bsServiceName" id="bsServiceName" />
			<input type="hidden" name="fldNewPage" id="fldNewPage" value="{/Service/Request/Fields/fldNewPage}" />
			<span class="cLabel">ID:</span>
			<input type="text" name="fldID" id="fldID" size="5" readonly="">
				<xsl:choose>
					<xsl:when test="/Service/Request/Fields/fldNewPage=1">
						<xsl:attribute name="value">Nuevo</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="value">
							<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cID" />
						</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
			</input>
			&#160;&#160;
			<span class="cLabel">Descripcion:</span>
			<input type="text" size="100" maxlength="100" name="fldDescription" id="fldDescription"
				value="{/Service/Response/Fields/GetPage/Record/cDescription}" />
			<br />

			<span class="cLabel">URL:</span>
			&#160;
			<INPUT type="text" readOnly="" size="100%"
				value="/{/Service/Context/SiteName}/ControlServlet?bsServiceName=ACO.Page&amp;fldID={/Service/Response/Fields/GetPage/Record/cID}" />

			<table border="0">
				<tr>
					<td>
						<input type="checkbox" id="isHTML" onclick="javascript:toggleHTML();" />
						<span class="cLabel">HTML</span>
					</td>
					<td>
						<A href="#" onclick="javascript:save()">
							<img border="0" src="public/img/save.jpg" />
						</A>
					</td>
					<td>&#160;|&#160;</td>
					<td>
						<A href="#" onclick="changeFormat('Bold')">
							<img border="0" src="public/img/bold.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('Italic')">
							<img border="0" src="public/img/italic.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('Underline')">
							<img border="0" src="public/img/underline.JPG" />
						</A>
					</td>
					<td>&#160;|&#160;</td>
					<td>
						<A href="#" onclick="changeFormat('JustifyLeft')">
							<img border="0" src="public/img/left.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('JustifyRight')">
							<img border="0" src="public/img/right.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('JustifyCenter')">
							<img border="0" src="public/img/center.JPG" />
						</A>
					</td>

					<td>&#160;|&#160;</td>
					<td>
						<A href="#" onclick="changeFormat('Cut')">
							<img border="0" src="public/img/cut.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('Copy')">
							<img border="0" src="public/img/copy.JPG" />
						</A>
					</td>
					<td>
						<A href="#" onclick="changeFormat('Paste')">
							<img border="0" src="public/img/paste.JPG" />
						</A>
					</td>

					<td>&#160;|&#160;</td>
					<td>
						<a class="cLabel">Tamaño:</a>
						<select onchange="changeSize(this)">
							<option value="1">Tamaño 1</option>
							<option value="2">Tamaño 2</option>
							<option value="3" selected="">Tamaño 3</option>
							<option value="4">Tamaño 4</option>
							<option value="5">Tamaño 5</option>
							<option value="6">Tamaño 6</option>
							<option value="7">Tamaño 7</option>
						</select>
					</td>

					<td>
						<a class="cLabel">Fuente:</a>
						<select Name="fldFont" ID="fldFont" onchange="changeFont(this)">
							<xsl:for-each select="/Service/Response/Fields/Fonts/Font">
								<option value="{.}">
									<xsl:value-of select="." />
								</option>
							</xsl:for-each>
						</select>
					</td>

				</tr>
			</table>
			<table>
				<tr>
					<td>
						<input type="checkbox" id="isHeadFooter" onclick="javascript:toggleHeadFooter();" />
						<span class="cLabel">Encabezado/Pie</span>
					</td>
					<td>&#160;|&#160;</td>
				</tr>
			</table>
			<textarea name="fldContent" id="fldContent" style="width:100%;display:none" rows="15">
				<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cHTML" />
			</textarea>

			<div id="divHeadFoot" style="display:none;left:200">
				<table style="width:80%">
					<tr>
						<td>
							<span class="cLabel">Encabezado</span>
							<br />
							<textarea name="fldHead" id="fldHead" style="width:80%" rows="5">
								<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cHead" />
							</textarea>
							<br />
							<span class="cLabel">Pie</span>
							<br />
							<textarea name="fldFoot" id="fldFoot" style="width:80%" rows="5">
								<xsl:value-of select="/Service/Response/Fields/GetPage/Record/cFoot" />
							</textarea>
						</td>
					</tr>
				</table>
			</div>

		</form>
		<iframe Name="fldLetterContent" style="width:100%;height:50%" ID="fldLetterContent"></iframe>
		<center>
			<input type="button" Value="Grabar" onclick="javascript:aceptar()" />
			&#160;
			<input type="button" Value="Cancelar" onclick="javascript:cancelar()" />
		</center>
		<script type="text/javascript" src="public/js/BSrish.js">&#160;</script>


		<!-- 
			<xsl:copy-of select="/"/>
		-->
	</xsl:template>
</xsl:stylesheet>