<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">

		<h1 class="cTitle">Nuevo archivo</h1>
		<form name="f1" id="f1" action="ControlServlet" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bsServiceName" value="ACO.SaveFile" />

			<table border="0" width="100%">
				<tr>
					<td class="cLabel">Código:</td>
					<td class="cData">[nuevo]</td>
				</tr>
				<tr>
					<td class="cLabel">Descripción:</td>
					<td>
						<input type="text" name="fldDesc" />
					</td>
				</tr>

				<tr>
					<td class="cLabel">Archivo:</td>
					<td class="cData">
						<input type="file" name="fldFile" />
					</td>
				</tr>
			</table>

			<hr />
			<input type="button" value="Aceptar" onclick="javascript:aceptar();" />
			&#160;&#160;
			<input type="button" value="Cancelar" onclick="javascript:cancelar();" />
		</form>

		<form name="f2" id="f2" action="ControlServlet" method="post">
			<input type="hidden" name="bsServiceName" value="ACO.ListPages" />

		</form>

		<!-- 
			<xsl:copy-of select="/" />
		-->
		<script type="text/javascript">
			<xsl:comment>

				function cancelar(){document.getElementById("f2").submit();}

				function aceptar(){

				var desc = document.getElementById("f1").fldDesc;

				var fileName = document.getElementById("f1").fldFile;

				if(desc.value.length == 0){ alert("Descripcion no se ha especificado"); return;}

				if(fileName.value.length == 0){ alert("Archivo no especificado"); return;}

				document.getElementById("f1").submit(); }

			</xsl:comment>
		</script>

	</xsl:template>
</xsl:stylesheet>