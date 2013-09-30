<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Modificacion de datos de un patron</h1>
		<form method="POST" action="ControlServlet">

			<input type="hidden" name="bsServiceName" value="AGA.update2Patron" />
			<input type="hidden" name="fldID" value="{/Service/Response/Fields/GetPatron/Record/cID}" />
			<table border="0">
				<tr>
					<td>
						<table>
							<tr>
								<td class="cLabel">ID</td>
								<td class="cData">
									<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cID" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Numero</td>
								<td> 
									<span class="cData">
										<xsl:value-of select="/Service/Response/Fields/GetPatron/Record/cNumero" />
									</span>
									<input type="hidden" name="fldNumero" value="{/Service/Response/Fields/GetPatron/Record/cNumero}" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Compuesto</td>
								<td>
									<input type="text" name="fldCompuesto" value="{/Service/Response/Fields/GetPatron/Record/cCompuesto}" size="50" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Composicion</td>
								<td>
									<input type="text" name="fldComposicion" value="{/Service/Response/Fields/GetPatron/Record/cComposicion}" size="35" maxlength="35"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Cilindro</td>
								<td>
									<input type="text" name="fldCilindro" value="{/Service/Response/Fields/GetPatron/Record/cCilindro}" size="15" maxlength="15"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Fecha Expiracion</td>
								<td>
									<input type="date" name="fldFechaExpiracion" value="{substring-after(/Service/Response/Fields/GetPatron/Record/cFechaExpiracion, ';')}" size="10" maxlength="10"/>
								</td>
							</tr>

						</table>
					</td>
				</tr>
			</table>
			<input type="submit" value="Aceptar" />
			&#160;
			<input type="button" value="Cancelar" onclick="redirect('AGA.listPatron');" />
		</form>
<!-- 
		<xsl:copy-of select="/" />
 -->
	</xsl:template>

</xsl:stylesheet>