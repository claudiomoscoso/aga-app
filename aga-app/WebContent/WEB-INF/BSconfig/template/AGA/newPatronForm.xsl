<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">
		<h1 class="cTitle">Datos del nuevo patr&#243;n</h1>
		<form method="POST" action="ControlServlet">

			<input type="hidden" name="bsServiceName" value="AGA.saveNewPatron" />
			<table border="0">
				<tr>
					<td>
						<table border="0">
							<tr>
								<td class="cLabel">ID</td>
								<td class="cData">Nuevo</td>
							</tr>
							<tr>
								<td class="cLabel">Numero</td>
								<td>
									<input type="text" name="fldNumero" size="5" maxlength="5"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Compuesto</td>
								<td>
									<input type="text" name="fldCompuesto" size="50" maxlength="50" />
								</td>
							</tr>
							<tr>
								<td class="cLabel">Composicion</td>
								<td>
									<input type="text" name="fldComposicion" size="35" maxlength="35"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Cilindro</td>
								<td>
									<input type="text" name="fldCilindro"  size="15" maxlength="15"/>
								</td>
							</tr>
							<tr>
								<td class="cLabel">Fecha Expiraci&#243;n</td>
								<td>
									<input type="text" name="fldFechaExpiracion" size="10" maxlength="10"/><label class="cLabel"><i>(ejemplo: 15/01/2010)</i></label>
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
			<xsl:copy-of select="/"/>
		-->
	</xsl:template>
</xsl:stylesheet>