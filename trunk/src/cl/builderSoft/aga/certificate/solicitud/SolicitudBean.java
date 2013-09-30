package cl.builderSoft.aga.certificate.solicitud;

import java.util.Calendar;

public class SolicitudBean {
	
	private String id;
	private String codigo;
	private String cliente;
	private String cilindro;
	private String solicitud;
	private Calendar fecha;
	private String analisis;
	private String estado;
	private String usuario;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCilindro() {
		return cilindro;
	}
	public void setCilindro(String cilindro) {
		this.cilindro = cilindro;
	}
	public String getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getAnalisis() {
		return analisis;
	}
	public void setAnalisis(String analisis) {
		this.analisis = analisis;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

}
