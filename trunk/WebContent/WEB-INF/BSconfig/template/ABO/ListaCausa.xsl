<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<H1 class="cTitle">Listado de Causas</H1>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">Titulo</td>
				<td class="cHeadTD">Demandante</td>
				<td class="cHeadTD">Demandado</td>
				<td class="cHeadTD">Procedimiento Actual</td>
				<td class="cHeadTD">Fecha Procedimiento</td>
				<td class="cHeadTD">Proximo Procedimiento</td>
				<td class="cHeadTD">Fecha Proximo Procedimiento</td>

			</tr>
			<tr>
				<td class="cDataTD">
					<a href="?bsServiceName=ABO.unacausa">Demanda Pension</a>
				</td>
				<td class="cDataTD">Jorge Instroza</td>
				<td class="cDataTD">Olivia Pacheco</td>
				<td class="cDataTD">Presentacion de Antecedentes</td>
				<td class="cDataTD">03/05/2008</td>
				<td class="cDataTD">Comparecencia</td>
				<td class="cDataTD">31/07/2008</td>
			</tr>
			<tr>
				<td class="cDataTD">
					<a href="#">Divorcio</a>
				</td>
				<td class="cDataTD">Antonio Aguilera</td>
				<td class="cDataTD">Jos&#233; Inostrosa</td>
				<td class="cDataTD">Reparo de antecedentes</td>
				<td class="cDataTD">03/03/2008</td>
				<td class="cDataTD">Presentacion de antecedentes</td>
				<td class="cDataTD">31/08/2008</td>
			</tr>
		</table>



	</xsl:template>
</xsl:stylesheet>