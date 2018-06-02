package co.edu.usbcali.dto;

public class PendientesDTO {

	private Long idNino;
	private int pendientes;
	
	public PendientesDTO() {
		super();
	}
	
	public PendientesDTO(Long idNino, int pendientes) {
		super();
		this.idNino = idNino;
		this.pendientes = pendientes;
	}

	public Long getIdNino() {
		return idNino;
	}

	public void setIdNino(Long idNino) {
		this.idNino = idNino;
	}

	public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;
	}	
}
