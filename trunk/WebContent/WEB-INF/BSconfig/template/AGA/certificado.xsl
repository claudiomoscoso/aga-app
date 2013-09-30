<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">


		<h1 class="cTitle">Certificados</h1>


		<form name="f1" id="f1" action="ControlServlet" method="post">
			<input type="hidden" name="bsServiceName" value="AGA.NuevoAnalisis" />

			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<label class="cLabel" for="codigoGe">C&#243;digo GE:</label>
						<input name="codigoGe" id="codigoGe" type="text" maxlength="10" size="10"/>
					</td>

					<td>
						<input type="radio" name="CilindroAGA" id="cilindroAGA" value="1" />
						<label for="cilindroAGA" class="cLabel">Cilindro AGA</label>
					</td>
					<td align="center" valign="center" rowspan="2">
						<input type="button" value="Nuevo..." onclick="javascript:nuevo();" />
					</td>
				</tr>
				<tr>
					<td>&#160;</td>
					<td>
						<input type="radio" name="CilindroAGA" id="cilindroParticular" value="0" />
						<label class="cLabel" for="cilindroParticular">Cilindro Particular/Venta</label>
					</td>

				</tr>
			</table>
		</form>
		<table width="100%">
			<tr>
				<td>
					<hr />
					<table class="cList" cellpadding="0" cellspacing="0">
						<tr>
						<!-- 
							<td class="cHeadTD">ID</td>
							 -->
							<td class="cHeadTD">C&#243;digo</td>
							<td class="cHeadTD">Cliente</td>
							<td class="cHeadTD">Cilindro/Lote</td>
							<td class="cHeadTD">Orden</td>
							<td class="cHeadTD">Fecha</td>
							<td class="cHeadTD">An&#225;lisis</td>
							<td class="cHeadTD">Estado</td>
							<td class="cHeadTD">Usuario</td>
						</tr>
						<xsl:for-each select="/Service/Response/Fields/ListSolicitudes/Record">
						<!-- 
							<xsl:sort select="substring-before(cFechaCreacion, ';')"/>
							 -->
							<tr>
							<!-- 
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cID" />
								</td>
								 -->
								<td class="cDataTD">
									&#160;
									<a href="?bsServiceName=AGA.CargaAnalisis&amp;fldID={cID}&amp;codigoGe={cProducto}">
										<xsl:value-of select="cProducto" />
									</a>
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cNombreCliente" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cLote" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cOrden" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="substring-after(cFechaCreacion, ';')" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cAnalisis" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cEstado" />
								</td>
								<td class="cDataTD">
									&#160;
									<xsl:value-of select="cName" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					<hr />
				</td>
			</tr>
		</table>

		<table width="100%" border="0">
			<tr>
				<td nowrap="" align="left"><label class="cLabel">
					<xsl:value-of select="count(/Service/Response/Fields/ListSolicitudes/Record)" />
					Coincidencia(s).</label>
					<br />
					<br />
				</td>
				<td width="75%">&#160;</td>

			</tr>
		</table>

<!-- 
		<textarea>
			<xsl:copy-of select="/" />
		</textarea>
 -->

		<script type="text/javascript" src="public/js/AGA09/certificado.js?rnd={/Service/Session/@ID}"></script>
	</xsl:template>
</xsl:stylesheet>
