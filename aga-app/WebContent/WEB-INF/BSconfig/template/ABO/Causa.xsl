<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<link rel="stylesheet" type="text/css" href="a/tabcontent.css" />

		<script type="text/javascript" src="a/tabcontent.js"></script>

		<H1 class="cTitle">Detalle de Causa</H1>

		<ul id="countrytabs" class="shadetabs">
			<li>
				<a href="#" rel="country1" class="selected">Escritos</a>
			</li>
			<li>
				<a href="#" rel="country2">Procedimientos</a>
			</li>
			<li>
				<a href="#" rel="country3">Calendario</a>
			</li>
			<li>
				<a href="#" rel="country4">Honorarios</a>
			</li>
		</ul>

		<div style="border:1px solid gray; width:90%; margin-bottom: 1em; padding: 10px">
			<div id="country1" class="tabcontent">

				<table style="width:100%" border="0">
					<tr>
						<td>
							<table class="cList" cellpadding="0" cellspacing="0" style="width:100%">
								<tr>
									<td class="cHeadTD">Nombre</td>
									<td class="cHeadTD">Fecha</td>
									<td class="cHeadTD">Cliente mira</td>

								</tr>
								<tr>
									<td class="cDataTD">
										<a href="#">Demanda 1</a>
									</td>
									<td class="cDataTD">02/02/2008</td>
									<td class="cDataTD" align="center">
										<input type="checkbox" />
									</td>
								</tr>
								<tr>
									<td class="cDataTD">
										<a href="#">Apelacion</a>
									</td>
									<td class="cDataTD">12/03/2008</td>
									<td class="cDataTD" align="center">
										<input type="checkbox" />
									</td>
								</tr>
							</table>
						</td>
						<td valign="top">
							<span class="cLabel">Nuevo Archivo:</span>
							<input type="file" />
						</td>
					</tr>
				</table>
				<br />
			</div>

			<div id="country2" class="tabcontent">
				<table class="cList" cellpadding="0" cellspacing="0" style="width:100%">
					<tr>
						<td class="cHeadTD">Titulo</td>
						<td class="cHeadTD">Fecha</td>
						<td class="cHeadTD">Fecha Enlace</td>
					</tr>
					<tr>
						<td class="cDataTD">
							<a href="javascript:showDetail()">Se establecen los primeros linamientos...</a><!-- acerca del curso del juicio que se llevara a cabo en la comuna de santiago -->
						</td>
						<td class="cDataTD">12/03/2008</td>
						<td class="cDataTD">15/05/2008</td>
					</tr>
				</table>

			</div>

			<div id="country3" class="tabcontent">
				<table class="cList" cellpadding="0" cellspacing="0" style="width:100%">
					<tr>
						<td class="cHeadTD">Fecha</td>
						<td class="cHeadTD">Evento</td>
					</tr>
					<tr>
						<td class="cDataTD">12/03/2008</td>
						<td class="cDataTD">Reunion con cliente</td>
					</tr>
					<tr>
						<td class="cDataTD">23/04/2008</td>
						<td class="cDataTD">Comparecencia en tribunal</td>
					</tr>
				</table>
			</div>

			<div id="country4" class="tabcontent">
				<table>
<tr>
<td class="cLabel">Forma de Pago:</td><td class="cLabel">30, 60 y 90 dias</td></tr>

</table>
				<table class="cList" cellpadding="0" cellspacing="0" style="width:100%">
					<tr>
						<td class="cHeadTD">Conepto</td>
						<td class="cHeadTD">Fecha</td>
						<td class="cHeadTD">Valor</td>
						<td class="cHeadTD">Saldo</td>
					</tr>
					<tr>
						<td class="cDataTD">Valor inicial</td>
						<td class="cDataTD">01/02/2008</td>
						<td class="cDataTD" align="right">$150000</td>
						<td class="cDataTD" align="right">$150000</td>
					</tr>
					<tr>
						<td class="cDataTD">Abono</td>
						<td class="cDataTD">01/03/2008</td>
						<td class="cDataTD" align="right">$50000</td>
						<td class="cDataTD" align="right">$100000</td>
					</tr>
					<tr>
						<td class="cDataTD">Abono</td>
						<td class="cDataTD">01/04/2008</td>
						<td class="cDataTD" align="right">$50000</td>
						<td class="cDataTD" align="right">$50000</td>
					</tr>
					<tr>
						<td class="cDataTD">Abono</td>
						<td class="cDataTD">01/05/2008</td>
						<td class="cDataTD" align="right">$50000</td>
						<td class="cDataTD" align="right">$0</td>
					</tr>
				</table>
			</div>

		</div>

		<div id="ttcontent" style="display:none">
			<span class="cLabel">
				Se establecen los primeros linamientos acerca del curso del juicio que se llevara a cabo en notaria Juan Urriaga
				en la comuna de santiago.
			</span>
			<br />
			<br />
			<input type="button" value="Cerrar" onclick="javascript:closeTooltip()" />
		</div>
		<script type="text/javascript">
			<![CDATA[
			var countries=new ddtabcontent("countrytabs") 
			countries.setpersist(true); 
			countries.setselectedClassTarget("link");
			countries.init()

function showDetail(){
	showTooltip('ttcontent');
}
]]>
		</script>
	</xsl:template>
</xsl:stylesheet>