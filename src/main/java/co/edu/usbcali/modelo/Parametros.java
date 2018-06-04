package co.edu.usbcali.modelo;
// Generated 03-jun-2018 13:45:35 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * Parametros generated by hbm2java
 */
public class Parametros implements java.io.Serializable {

	private long id;
	private String nombre;
	private String valor;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModifica;
	private Date fechaModifica;

	public Parametros() {
	}

	public Parametros(long id) {
		this.id = id;
	}

	public Parametros(long id, String nombre, String valor, String usuarioCreacion, Date fechaCreacion,
			String usuarioModifica, Date fechaModifica) {
		this.id = id;
		this.nombre = nombre;
		this.valor = valor;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModifica = usuarioModifica;
		this.fechaModifica = fechaModifica;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioModifica() {
		return this.usuarioModifica;
	}

	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Date getFechaModifica() {
		return this.fechaModifica;
	}

	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}

}
