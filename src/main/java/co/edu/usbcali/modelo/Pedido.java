package co.edu.usbcali.modelo;
// Generated 03-jun-2018 13:45:35 by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pedido generated by hbm2java
 */
public class Pedido implements java.io.Serializable {

	private BigDecimal id;
	private Padre padre;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Integer estado;
	private Integer cantAlmuerzos;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private String usuarioModifica;
	private Date fechaModifica;
	private Set<Almuerzo> almuerzos = new HashSet<Almuerzo>(0);

	public Pedido() {
	}

	public Pedido(BigDecimal id, Padre padre) {
		this.id = id;
		this.padre = padre;
	}

	public Pedido(BigDecimal id, Padre padre, Date fechaPedido, Date fechaEntrega, Integer estado,
			Integer cantAlmuerzos, String usuarioCreacion, Date fechaCreacion, String usuarioModifica,
			Date fechaModifica, Set<Almuerzo> almuerzos) {
		this.id = id;
		this.padre = padre;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.cantAlmuerzos = cantAlmuerzos;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioModifica = usuarioModifica;
		this.fechaModifica = fechaModifica;
		this.almuerzos = almuerzos;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Padre getPadre() {
		return this.padre;
	}

	public void setPadre(Padre padre) {
		this.padre = padre;
	}

	public Date getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getCantAlmuerzos() {
		return this.cantAlmuerzos;
	}

	public void setCantAlmuerzos(Integer cantAlmuerzos) {
		this.cantAlmuerzos = cantAlmuerzos;
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

	public Set<Almuerzo> getAlmuerzos() {
		return this.almuerzos;
	}

	public void setAlmuerzos(Set<Almuerzo> almuerzos) {
		this.almuerzos = almuerzos;
	}

}
