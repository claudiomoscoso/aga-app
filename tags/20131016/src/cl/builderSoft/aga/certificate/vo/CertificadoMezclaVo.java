package cl.builderSoft.aga.certificate.vo;

public class CertificadoMezclaVo {
	// Datos del cliente
	private String cliente = "";
	private String nroAnalisis = "";
	private String nroCilindro = "";
	private String nroOrden = "";

	// Datos del cilindro
	private String tipoCilindro = "";
	private String conexionValvula = "";
	private String presionLlenado = "";
	private String volumenGas = "";

	Componentes[] componentes=null;

	private String tipoProducto = "";

	private String metodoPreparacion = "";
	private String nivelConfianza = "";
	private String toleranciaPreparacion = "";
	private String estabilidadGarantizada = "";
	private String temperaturaRecomendada = "";
	private String presionMinimaUso = "";
	private String metodoAnalitico = "";
	private String patronEmpleado = "";

	private String codigoMezcla = "";
	private String nombreMezcla = "";
	private String comentario = "";

	// de la produccion
	private String lugarProduccion = "";

	// del responsable
	private String responsable = "";
	private String contacto = "";

	public CertificadoMezclaVo(int componentesSize) {
		this.componentes = new Componentes[componentesSize];

		// componentes[0] = new Componentes();
	}

	// Componentes

	// Datos del producto

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setPatronEmpleado(String patronEmpleado) {
		this.patronEmpleado = patronEmpleado;
	}

	// Comentarios

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getNroAnalisis() {
		return nroAnalisis;
	}

	public void setNroAnalisis(String nroAnalisis) {
		this.nroAnalisis = nroAnalisis;
	}

	public String getNroCilindro() {
		return nroCilindro;
	}

	public void setNroCilindro(String nroCilindro) {
		this.nroCilindro = nroCilindro;
	}

	public String getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
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

	public String getPresionLlenado() {
		return presionLlenado;
	}

	public void setPresionLlenado(String presionLlenado) {
		this.presionLlenado = presionLlenado;
	}

	public String getVolumenGas() {
		return volumenGas;
	}

	public void setVolumenGas(String volumenGas) {
		this.volumenGas = volumenGas;
	}

	public Componentes[] getComponentes() {
		return this.componentes;
	}

	public Componentes getComponentes(int index) {
		return componentes[index];
	}

	public void setComponentes(int index, Componentes componentes) {
		this.componentes[index] = componentes;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getMetodoPreparacion() {
		return metodoPreparacion;
	}

	public void setMetodoPreparacion(String metodoPreparacion) {
		this.metodoPreparacion = metodoPreparacion;
	}

	public String getNivelConfianza() {
		return nivelConfianza;
	}

	public void setNivelConfianza(String nivelConfianza) {
		this.nivelConfianza = nivelConfianza;
	}

	public String getToleranciaPreparacion() {
		return toleranciaPreparacion;
	}

	public void setToleranciaPreparacion(String toleranciaPreparacion) {
		this.toleranciaPreparacion = toleranciaPreparacion;
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

	public String getPresionMinimaUso() {
		return presionMinimaUso;
	}

	public void setPresionMinimaUso(String presionMinimaUso) {
		this.presionMinimaUso = presionMinimaUso;
	}

	public String getMetodoAnalitico() {
		return metodoAnalitico;
	}

	public void setMetodoAnalitico(String metodoAnalitico) {
		this.metodoAnalitico = metodoAnalitico;
	}

	public String getPatronEmpleado() {
		return patronEmpleado;
	}

	/**
	public void setMetodoEmpleado(String patronEmpleado) {
		this.patronEmpleado = patronEmpleado;
	}*/

	public String getCodigoMezcla() {
		return codigoMezcla;
	}

	public void setCodigoMezcla(String codigoMezcla) {
		this.codigoMezcla = codigoMezcla;
	}

	public String getNombreMezcla() {
		return nombreMezcla;
	}

	public void setNombreMezcla(String nombreMezcla) {
		this.nombreMezcla = nombreMezcla;
	}

	public String getLugarProduccion() {
		return lugarProduccion;
	}

	public void setLugarProduccion(String lugarProduccion) {
		this.lugarProduccion = lugarProduccion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getContacto() {
		return contacto;
	}

}
