<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">
		<body onload="javascript:onLoadPage()" />


		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
				function onLoadPage(){
					parent.document.getElementById("fldRUT").readOnly = true;
					parent.document.getElementById("BotonBuscaApoderado").disabled = true;
					parent.document.getElementById("BotonGrabaApoderado").disabled = true;
					parent.document.getElementById("fldRUTPupilo").disabled = false;
					parent.document.getElementById("BotonBuscaPupilo").disabled = false;

					alert("Apoderado Grabado!");
				}

			</xsl:comment>
		</script>

	</xsl:template>
</xsl:stylesheet>
