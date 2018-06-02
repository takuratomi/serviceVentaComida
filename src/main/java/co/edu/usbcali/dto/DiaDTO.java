package co.edu.usbcali.dto;

import java.util.Date;

public class DiaDTO {

	public String NAME_DIA_SEMANA = "";
	public Date fecha;

	private ProductoDTO productoDTO_sopa;
	private ProductoDTO productoDTO_bebida;
	private ProductoDTO productoDTO_principio;
	private ProductoDTO productoDTO_proteina;

	private int position_sopa = 0;
	private int position_bebida = 0;
	private int position_principio = 0;
	private int position_proteina = 0;

	public DiaDTO() {
		super();
		NAME_DIA_SEMANA = "";
		this.fecha = null;
		this.productoDTO_sopa = new ProductoDTO();
		this.productoDTO_sopa.setTipoProducto(1);
		this.productoDTO_bebida = new ProductoDTO();
		this.productoDTO_bebida.setTipoProducto(4);
		this.productoDTO_principio = new ProductoDTO();
		this.productoDTO_principio.setTipoProducto(2);
		this.productoDTO_proteina = new ProductoDTO();
		this.productoDTO_proteina.setTipoProducto(3);
	}

	public DiaDTO(String nAME_DIA_SEMANA, Date fecha, ProductoDTO productoDTO_sopa, ProductoDTO productoDTO_bebida,
			ProductoDTO productoDTO_principio, ProductoDTO productoDTO_proteina) {
		super();
		NAME_DIA_SEMANA = nAME_DIA_SEMANA;
		this.fecha = fecha;
		this.productoDTO_sopa = productoDTO_sopa;
		this.productoDTO_bebida = productoDTO_bebida;
		this.productoDTO_principio = productoDTO_principio;
		this.productoDTO_proteina = productoDTO_proteina;
	}

	public String getNAME_DIA_SEMANA() {
		return NAME_DIA_SEMANA;
	}

	public void setNAME_DIA_SEMANA(String nAME_DIA_SEMANA) {
		NAME_DIA_SEMANA = nAME_DIA_SEMANA;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ProductoDTO getProductoDTO_sopa() {
		return productoDTO_sopa;
	}

	public void setProductoDTO_sopa(ProductoDTO productoDTO_sopa) {
		this.productoDTO_sopa = productoDTO_sopa;
	}

	public ProductoDTO getProductoDTO_bebida() {
		return productoDTO_bebida;
	}

	public void setProductoDTO_bebida(ProductoDTO productoDTO_bebida) {
		this.productoDTO_bebida = productoDTO_bebida;
	}

	public ProductoDTO getProductoDTO_principio() {
		return productoDTO_principio;
	}

	public void setProductoDTO_principio(ProductoDTO productoDTO_principio) {
		this.productoDTO_principio = productoDTO_principio;
	}

	public ProductoDTO getProductoDTO_proteina() {
		return productoDTO_proteina;
	}

	public void setProductoDTO_proteina(ProductoDTO productoDTO_proteina) {
		this.productoDTO_proteina = productoDTO_proteina;
	}

	public int getPosition_sopa() {
		return position_sopa;
	}

	public DiaDTO setPosition_sopa(int position_sopa) {
		this.position_sopa = position_sopa;
		return this;
	}

	public int getPosition_bebida() {
		return position_bebida;
	}

	public DiaDTO setPosition_bebida(int position_bebida) {
		this.position_bebida = position_bebida;
		return this;
	}

	public int getPosition_principio() {
		return position_principio;
	}

	public DiaDTO setPosition_principio(int position_principio) {
		this.position_principio = position_principio;
		return this;
	}

	public int getPosition_proteina() {
		return position_proteina;
	}

	public DiaDTO setPosition_proteina(int position_proteina) {
		this.position_proteina = position_proteina;
		return this;
	}
}
