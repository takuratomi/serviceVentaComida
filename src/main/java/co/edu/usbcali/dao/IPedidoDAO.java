package co.edu.usbcali.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.usbcali.modelo.Pedido;

public interface IPedidoDAO {

	public void crear(Pedido entity);
	public void modificar(Pedido entity);
	public void borrar(Pedido entity);
	public Pedido consultarPorId(BigDecimal idAlmuerzo);
	public Pedido consultarPorIdentificacion(Integer idPedido);
	public List<Pedido> consultarTodos();
	
	public Long getConsecutivo(); 
	public List<Pedido> getPedidoFechaHijoPadre(Date fechaCreacion,long id_padre);
	
	
}
