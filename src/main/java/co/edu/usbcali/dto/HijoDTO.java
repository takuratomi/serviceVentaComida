package co.edu.usbcali.dto;

import java.util.Date;

public class HijoDTO {

	/**
	 * datos del usuario 
	 * 
	 */
	private long id_usuario;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Long numIdentificacion;
	private Integer tipoIdentificacion;
	private String password;
	private String email;
	private Integer rol;
	private String usuario;
	private Date fecha;
	
	// Datos del padre
	private long numIdentificacionPadre;


	// Datos del hijo
	private Date fechaNacimiento;
	private String curso;
	
	int codigoError;
	String mensajeError;
	
	public HijoDTO() {
		super();
	}

	public HijoDTO(long id_usuario, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, Long numIdentificacion, Integer tipoIdentificacion, String password, String email,
			Integer rol, String usuario, Date fecha, long numIdentificacionPadre, Date fechaNacimiento, String curso,
			int codigoError, String mensajeError) {
		super();
		this.id_usuario = id_usuario;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.numIdentificacion = numIdentificacion;
		this.tipoIdentificacion = tipoIdentificacion;
		this.password = password;
		this.email = email;
		this.rol = rol;
		this.usuario = usuario;
		this.fecha = fecha;
		this.numIdentificacionPadre = numIdentificacionPadre;
		this.fechaNacimiento = fechaNacimiento;
		this.curso = curso;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Long getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(Long numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	public Integer getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(Integer tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getNumIdentificacionPadre() {
		return numIdentificacionPadre;
	}

	public void setNumIdentificacionPadre(long numIdentificacionPadre) {
		this.numIdentificacionPadre = numIdentificacionPadre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}	
}
