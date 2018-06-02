package co.edu.usbcali.dto;

public class SemanaDTO {
	
	private long idHijo;
	private long idPadre;	
	private DiaDTO[] diasDTO;
	
	private int codigoError;
	private String mensajeError;

	public SemanaDTO() {
		super();
		this.idHijo = 0L;
		this.idPadre = 0L;
		this.diasDTO = new DiaDTO[5];
		this.codigoError = 0;
		this.mensajeError = "";
	}	
	public SemanaDTO(long idHijo, long idPadre, DiaDTO[] diasDTO) {
		super();
		this.idHijo = idHijo;
		this.idPadre = idPadre;
		this.diasDTO = diasDTO;
	}

	public long getIdHijo() {
		return idHijo;
	}

	public void setIdHijo(long idHijo) {
		this.idHijo = idHijo;
	}

	public long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
	}

	public DiaDTO[] getDiasDTO() {
		return diasDTO;
	}

	public void setDiasDTO(DiaDTO[] diasDTO) {
		this.diasDTO = diasDTO;
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
