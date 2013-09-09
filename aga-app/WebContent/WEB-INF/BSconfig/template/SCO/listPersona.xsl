<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8" />
	<xsl:template match="/">

		<H1 class="cTitle">Listado de Miembros Registrados</H1>
		<xsl:variable name="GrupoUsuario" select="/Service/Session/User/Info/prpGrupoScout" />
		<xsl:variable name="GrupoHabilitado" select="/Service/Response/Fields/ListGrupos/Record[cID = $GrupoUsuario]/cHabilitado = 1" />
		<xsl:variable name="IsAdmin" select="string-length(/Service/Session/User/Info/prpGrupoScout) = 0" />
		<xsl:variable name="GrupoFiltro" select="/Service/Request/Fields/fldGrupo" />

 <!-- 
		<span class="cLabel">
			GrupoUsuario#
			<xsl:value-of select="$GrupoUsuario" />
			#
		</span><br/>
		<span class="cLabel">
			GrupoHabilitado#
			<xsl:value-of select="$GrupoHabilitado" />
			#
		</span><br/>
		<span class="cLabel">
			IsAdmin#
			<xsl:value-of select="$IsAdmin" />
			#
		</span><br/>
 		<span class="cLabel">
			GrupoFiltro#
			<xsl:value-of select="$GrupoFiltro" />
			#
		</span><br/>
 -->
		<xsl:if test="$IsAdmin">
			<table>
				<caption>Filtros</caption>
				<tr>
					<td class="cLabel">Grupo:  </td>
					<td>
						<select id="GrupoFiltro" onchange="javascript:filtra()">
							<option value="">- Todos -</option>
							<xsl:for-each select="/Service/Response/Fields/ListGrupos/Record">
								<option value="{cID}">
									<xsl:if test="cID = $GrupoFiltro">
										<xsl:attribute name="selected"/>
									</xsl:if>
									<xsl:value-of select="cNombre" />
								</option>
							</xsl:for-each>
						</select>
					</td>
				</tr>
			</table>
		</xsl:if>

		<table class="cList" cellpadding="0" cellspacing="0">
			<tr><td class="cHeadTD" align="center">No</td>
				<td class="cHeadTD">RUT</td>
				<td class="cHeadTD">Nombre</td>
				<td class="cHeadTD">Apellidos</td>
				<td class="cHeadTD">Unidad</td>
				<td class="cHeadTD">Tel&#233;fono</td>
				<td class="cHeadTD">Tipo</td>
			</tr>
		
			<xsl:choose>
				<xsl:when test="$IsAdmin and string-length($GrupoFiltro)=0">

					<xsl:for-each select="/Service/Response/Fields/ListScout/Record">
						<xsl:sort data-type="text" select="cNombrePersona" order="ascending" />
						<xsl:call-template name="ShowRecord">
							<xsl:with-param name="NumeroFila" select="position()"/>
							<xsl:with-param name="Fila" select="."/>
						</xsl:call-template>
					</xsl:for-each>

				</xsl:when>

				<xsl:when test="$IsAdmin and string-length($GrupoFiltro) &gt; 0">

					<xsl:for-each select="/Service/Response/Fields/ListScout/Record[cGrupo = $GrupoFiltro] ">
						<xsl:sort data-type="text" select="cNombrePersona" order="ascending" />
						<xsl:call-template name="ShowRecord">
							<xsl:with-param name="NumeroFila" select="position()"/>
							<xsl:with-param name="Fila" select="."/>
						</xsl:call-template>
					</xsl:for-each>

				</xsl:when>

				<xsl:otherwise>
					<xsl:for-each select="/Service/Response/Fields/ListScout/Record[cGrupo = $GrupoUsuario] ">
						<xsl:sort data-type="text" select="cNombrePersona" order="ascending" />
						<xsl:call-template name="ShowRecord">
							<xsl:with-param name="NumeroFila" select="position()"/>
							<xsl:with-param name="Fila" select="."/>
						</xsl:call-template>

					</xsl:for-each>
				</xsl:otherwise>
			</xsl:choose>
	
		</table>
		<br />

		<center>
			<input type="button" value="Nuevo Miembro Beneficiario..." onclick="javascript:nuevoMMBB();" >
				<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
					<xsl:attribute name="disabled"/>
				</xsl:if>
			</input>
			&#160;&#160;&#160;
			<input type="button" value="Nuevo Dirigente o Guiadora..." onclick="javascript:nuevoDyG();" >
				<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
					<xsl:attribute name="disabled"/>
				</xsl:if>
			</input>
			&#160;&#160;&#160;
			<input type="button" value="Registro de Apoderados..." onclick="javascript:apoderado();" >
				<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
					<xsl:attribute name="disabled"/>
				</xsl:if>
			</input>
		</center>

		<form name="f" id="f" method="post" action="ControlServlet">
			<input type="hidden" name="bsServiceName" id="bsServiceName"/>
			<input type="hidden" name="fldGrupo" id="fldGrupo"/>
		</form>
		<xsl:if test="not($GrupoHabilitado) and not($IsAdmin)">
			<span class="cLabel" style="color:red">* Grupo no habilitado para realizar registro, comuniquece con la oficina distrital.</span>
		</xsl:if>

<!-- 
 		<xsl:copy-of select="/" />
 -->

		<script language="JavaScript1.1" type="text/javascript">
			<xsl:comment>
			function onLoadPage(){
			
			}
		
			function filtra(){ 
				document.getElementById("f").fldGrupo.value = document.getElementById("GrupoFiltro").value;
				document.getElementById("f").bsServiceName.value = "SCO.ListPersona";
				document.getElementById("f").submit(); 
			}	
	
			function nuevoMMBB(){ 
				document.getElementById("f").bsServiceName.value = "SCO.FormNuevoMMBB";
				document.getElementById("f").submit(); 
			}
			
			function nuevoDyG(){ 
				document.getElementById("f").bsServiceName.value = "SCO.FormNuevoDYG";
				document.getElementById("f").submit(); 
			}

			function apoderado(){
				document.getElementById("f").bsServiceName.value = "SCO.FormApoderado";
				document.getElementById("f").submit(); 
			}
			</xsl:comment>
			</script>
	 
	</xsl:template>

	<xsl:template name="ShowRecord">
		<xsl:param name="NumeroFila"/>
		<xsl:param name="Fila"/>
			<tr>
				<td class="cDataTD" align="center"><xsl:value-of select="$NumeroFila"/></td>
				<td class="cDataTD">
<!-- 
					<xsl:choose>
						<xsl:when test="cTabla='tMMBB' or cTabla='tDYG'">
							<a href = "ControlServlet?bsServiceName=SCO.EditPersona&amp;fldRUT={$Fila/cRUT}">
								<xsl:value-of select="cRUT" />
							</a>
						</xsl:when>
						<xsl:otherwise><xsl:value-of select="cRUT" /></xsl:otherwise>
					</xsl:choose>
-->
					<xsl:value-of select="cRUT" />
				</td>
				<td class="cDataTD">
					<xsl:choose>
						<xsl:when test="cTabla='tMMBB' or cTabla='tDYG'">
							<a href = "ControlServlet?bsServiceName=SCO.EditPersona&amp;fldID={$Fila/cID}">
								<xsl:value-of select="cNombrePersona" />
							</a>
						</xsl:when>
						<xsl:otherwise><xsl:value-of select="cNombrePersona" /></xsl:otherwise>
					</xsl:choose>
				</td>
				<td class="cDataTD">
					<xsl:value-of select="cApellidoPaterno" />
					&#160;
					<xsl:value-of select="cApellidoMaterno" />
				</td>
				<td class="cDataTD">
					<xsl:choose>
						<xsl:when test="cNombreUnidad='unknow'">Sin Unidad</xsl:when>
						<xsl:otherwise><xsl:value-of select="cNombreUnidad" /></xsl:otherwise>
					</xsl:choose>
				</td>
				<td class="cDataTD">
					<xsl:value-of select="cTelefono" />
				</td>
				<td class="cDataTD">
					<xsl:choose>
						<xsl:when test="cTabla='tMMBB'">Beneficiario</xsl:when>
						<xsl:when test="cTabla='tDYG'">Dir. o Guia.</xsl:when>
						<xsl:otherwise>Apoderado</xsl:otherwise>
					</xsl:choose>
				</td>
			</tr>

	</xsl:template>
</xsl:stylesheet>
