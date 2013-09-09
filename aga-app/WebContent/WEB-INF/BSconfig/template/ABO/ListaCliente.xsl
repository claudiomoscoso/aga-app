<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Listado de Clientes</H1>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr>
				<td class="cHeadTD">ID</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">
					Fecha Incorporaci&#243;n
				</td>
			<td class="cHeadTD">Acciones</td>
	
			</tr>
			<tr>
				<td class="cDataTD">38292</td>
				<td class="cDataTD"><a href="#">Juan Perez Perez</a></td>
				<td class="cDataTD">23/08/2007</td>
				<td class="cDataTD"><a href="#" onclick="jacascript:confirm('Seguro de borrar el cliente y todos sus antecedentes?');">Eliminar</a></td>
			</tr>
			<tr>
				<td class="cDataTD">38293</td>
				<td class="cDataTD"><a href="#">Jose Insotroza</a></td>
				<td class="cDataTD">03/09/2007</td>
				<td class="cDataTD"><a href="#" onclick="jacascript:confirm('Seguro de borrar el cliente y todos sus antecedentes?');">Eliminar</a></td>
			</tr>
		</table>
		<br/>
		<center>
			<input type="button" value="Nuevo..."/>
		</center>
	</xsl:template>
</xsl:stylesheet>