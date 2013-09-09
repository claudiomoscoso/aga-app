<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<h3 class="cTitle">Datos del Cliente</h3>
		<table border="1" cellpadding="0" cellspacing="0" width="100%">

			<tr>
				<td class="cLabel">Cliente c&#243;digo:</td>
				<td>
					<input name="RUT" id="RUT" maxlength="20" size="20" value="{normalize-space(/Service/Response/Fields/GetSolicitud/Record/cCliente)}"/>
					<input value="-&gt;" type="button" onclick="javascript:buscarCliente()" />
					<input name="NombreCliente" id="NombreCliente" value="{/Service/Response/Fields/GetSolicitud/Record/cNombreCliente}" size="45">
						<xsl:attribute name="readonly" />
					</input>
				</td>
			</tr>
			<tr>
				<td class="cLabel">Contacto:</td>
				<td>
					<input id="NombreContacto" maxlength="100" size="100" 
					onFocus="javascript:currentValue=this.value;" 
					value="{/Service/Response/Fields/GetSolicitud/Record/cContacto}" 
					onBlur="javascript:updateContacto(this);"/>
				</td>
			</tr>
		</table>



	</xsl:template>
</xsl:stylesheet>