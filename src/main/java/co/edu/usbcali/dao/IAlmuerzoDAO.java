package co.edu.usbcali.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.modelo.Almuerzo;

public interface IAlmuerzoDAO {

	public void crear(Almuerzo entity);
	public void modificar(Almuerzo entity);
	public void borrar(Almuerzo entity);
	public Almuerzo consultarPorId(BigDecimal idAlmuerzo);
	public Almuerzo consultarPorIdentificacion(Integer idAlmuerzo);
	public List<Almuerzo> consultarTodos();
	
	public Long getConsecutivo() ;	
}
