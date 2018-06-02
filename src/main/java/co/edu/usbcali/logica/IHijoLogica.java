package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Usuario;

public interface IHijoLogica {
	
	public void crearHijo(Hijo hijo) throws Exception;
	public void modificarHijo(Hijo hijo) throws Exception;
	public Hijo consultarHijoPorId(long id) throws Exception;
	public List<Hijo>consultarHijoTodos() throws Exception;
	
	public List<Hijo> consultarMisHijo(long numIdentificaicon) throws Exception;

}
