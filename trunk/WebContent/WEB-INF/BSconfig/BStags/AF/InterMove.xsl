<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
<xsl:output method="html" indent="yes" encoding="UTF-8" />
<xsl:template match="/">
		<div id="divMoveInterenterprise" style="display:none">
			<h2>Transferencia entre empresas</h2>
			<table border="0" width="100%"> 
			<tr>
			<td align="center">
			<table border="0">
				<caption>Empresa Origen</caption>
				<tr>
					<td class="cLabel">Empresa:</td>
					<td>
						<select id="SourceEnterprise">
							<xsl:for-each select="/Service/Response/Fields/GetEnterprises/Record">
		  						<option value="{cID}"><xsl:value-of select="cName"/></option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
				<tr style="display:xnone">
					<td class="cLabel">Banco:</td>
					<td>
						<select id="SourceBank">
							<xsl:for-each select="/Service/Response/Fields/GetBank/Record">
		  						<option value="{cID}"><xsl:value-of select="cName"/></option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
			</table>
			</td>
			 
			<td align="center">
			<table border="0">
				<caption>Empresa Destino</caption>
				<tr>
					<td class="cLabel">Empresa:</td>
					<td>
						<select id="TargetEnterprise">
							<xsl:for-each select="/Service/Response/Fields/GetEnterprises/Record">
		  						<option value="{cID}"><xsl:value-of select="cName"/></option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
				<tr style="display:xnone">
					<td class="cLabel">Banco:</td>
					<td>
						<select id="TargetBank">
							<xsl:for-each select="/Service/Response/Fields/GetBank/Record">
		  						<option value="{cID}"><xsl:value-of select="cName"/></option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
			</table>
			
			</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
			
			<table border="0">
				<tr>
					<td class="cLabel">Fecha:</td>
					<td colspan="4"><input id="MoveDate"/><div id="dateMoveInvlid"/></td>
				</tr>
				<tr>
					<td class="cLabel">Monto:</td>
					<td><input id="MoveAmount"/></td>
					<td><input type="button" value="Aceptar" onclick="javascript:transferAmount()"/></td>
					<td><input type="button" value="Cancelar" onclick="javascript:closeTooltip();"/></td>
				</tr>
			</table>
					
			</td>
			</tr>
			</table>
		</div>
</xsl:template>
</xsl:stylesheet>
