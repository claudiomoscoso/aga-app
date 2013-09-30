<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<div id="divUpdateDrag" style="display:none">
			<h2>
				Configuraci&#243;n valor de arrastre <div id="anchorBankName"/></h2>
			<table border="0" width="100%"> 
		 
				<tr>
					<td class="cLabel" valign="top">Banco:</td>
					<td class="cData" id="tdBN"></td>
				</tr>
				 
				<tr>
					<td class="cLabel" valign="top">Fecha:</td>
					<td><input id="dateDrag" onblur="javascript:document.getElementById('divDragDateMessage').innerHTML='';"/><br/><div class="cLabel" id="divDragDateMessage"/></td>
				</tr>
				<tr>
					<td class="cLabel" valign="top">Monto:</td>
					<td><input id="amountDrag" onblur="javascript:document.getElementById('divDragAmountMessage').innerHTML='';"/><div class="cLabel" id="divDragAmountMessage"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="Aceptar" onclick="javascript:saveDragInfo()"/>&#160;&#160;&#160;
						<input type="button" value="Cancelar" onclick="javascript:closeTooltip();"/>
					</td>
				</tr>
			</table>
			<input type="hidden" id="dragInfoId"/>			
		</div>
	</xsl:template>
</xsl:stylesheet>
