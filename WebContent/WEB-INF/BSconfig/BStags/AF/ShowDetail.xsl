<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<div id="divShowDetail" style="display:none">
			<h2>Detalle de valores</h2>


			<div class="content">
				<table class="cList" cellpadding="0" cellspacing="0" id="movesTable">
					<tr>
						<td class="cHeadTD">Detalle</td>
						<td class="cHeadTD">Comentario</td>
						<td class="cHeadTD">Tipo</td>
						<td class="cHeadTD">Monto</td>
					</tr>
				</table>
			</div>
			<br />
			<input type="button" value="Aceptar" onclick="javascript:closeTooltip()" />

		</div>
	</xsl:template>
</xsl:stylesheet>
