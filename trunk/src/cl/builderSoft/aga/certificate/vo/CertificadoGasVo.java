package cl.builderSoft.aga.certificate.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import cl.builderSoft.aga.AGAAbstract;
import cl.builderSoft.utilpdf.Resources;

public class CertificadoGasVo extends AGAAbstract{

	private String nombreProducto = "";
	private String producto = "99.999% min.";
	private String codigoProducto = "";
	private String numeroLote = "";
	private String numeroOrden = "";
	private String tipoCilindro = "";
	private String conexionValvula = "";
	private String estabilidadGarantizada = "";
	private String temperaturaRecomendada = "";
	private String presionLlenado = "";
	private String volumenGas = "";
	private String presionMinUso = "";
	private String responsable = "";
	private String certificado = "";

	private ComponentesBySolicitud[] componentesBySolicitudes;

	private ListLote[] envases;

	private String comentarios = "";
	private String metodosAnalisis = "";
	private String lugarProduccion = "PLANTA GASES ESPECIALES MAIPU";

	private String piePagina = "PLANTA GASES ESPECIALES MAIPU";

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getResponsable() {
		return responsable;
	}

	public ComponentesBySolicitud[] getComponentesBySolicitudes() {
		return componentesBySolicitudes;
	}

	public void setComponentesBySolicitudes(ComponentesBySolicitud[] componentesBySolicitudes) {
		this.componentesBySolicitudes = componentesBySolicitudes;
	}

	public ListLote[] getEnvases() {
		return envases;
	}

	public void setEnvases(ListLote[] envases) {
		this.envases = envases;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getTipoCilindro() {
		return tipoCilindro;
	}

	public void setTipoCilindro(String tipoCilindro) {
		this.tipoCilindro = tipoCilindro;
	}

	public String getConexionValvula() {
		return conexionValvula;
	}

	public void setConexionValvula(String conexionValvula) {
		this.conexionValvula = conexionValvula;
	}

	public String getEstabilidadGarantizada() {
		return estabilidadGarantizada;
	}

	public void setEstabilidadGarantizada(String estabilidadGarantizada) {
		this.estabilidadGarantizada = estabilidadGarantizada;
	}

	public String getTemperaturaRecomendada() {
		return temperaturaRecomendada;
	}

	public void setTemperaturaRecomendada(String temperaturaRecomendada) {
		this.temperaturaRecomendada = temperaturaRecomendada;
	}

	public String getPresionLlenado() {
		return presionLlenado;
	}

	public void setPresionLlenado(String presionLlenado) {
		this.presionLlenado = presionLlenado;
	}

	public String getVolumenGas() {
		return Resources.formatNumber(volumenGas, "0.0");
		/**
		 * <code>
		String out = null;
		try {
			double volumenGasDouble = Double.parseDouble(volumenGas);

			NumberFormat formatter = new DecimalFormat("0.0");
			out = formatter.format(volumenGasDouble);
			out = out.replaceAll("[,]", ".");
		} catch (Exception e) {
			out = volumenGas;
		}

		return out;
		</code>
		 */
	}

	public void setVolumenGas(String volumenGas) {
		this.volumenGas = volumenGas;
	}

	public String getPresionMinUso() {
		return presionMinUso;
	}

	public void setPresionMinUso(String presionMinUso) {
		this.presionMinUso = presionMinUso;
	}

	public ComponentesBySolicitud[] getComponentesBySolicitud() {
		return componentesBySolicitudes;
	}

	public void setComponentesBySolicitud(ComponentesBySolicitud[] componentesBySolicitud) {
		this.componentesBySolicitudes = componentesBySolicitud;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getMetodosAnalisis() {
		return metodosAnalisis;
	}

	public void setMetodosAnalisis(String metodosAnalisis) {
		this.metodosAnalisis = metodosAnalisis;
	}

	public String getLugarProduccion() {
		return lugarProduccion;
	}

	public void setLugarProduccion(String lugarProduccion) {
		this.lugarProduccion = lugarProduccion;
	}

	public String getPiePagina() {
		return piePagina;
	}

	public void setPiePagina(String piePagina) {
		this.piePagina = piePagina;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getCertificado() {
		return certificado;
	}

}
