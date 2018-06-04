package co.edu.usbcali.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Pedido;


@Repository
@Scope("singleton")
public class PedidoDAO implements IPedidoDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void crear(Pedido entity) {
		sessionFactory.getCurrentSession().save(entity);		
	}

	@Override
	public void modificar(Pedido entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void borrar(Pedido entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public Pedido consultarPorId(BigDecimal usuId) {
		return (Pedido)sessionFactory.getCurrentSession().get(Pedido.class, usuId);
	}

	@Override
	public Pedido consultarPorIdentificacion(Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Pedido.class).list();
	}
	
	@Override
	public Long getConsecutivo() {
		String sqlName = "PEDIDO_SEQ";
		Long consecutivo = null;
		List qlist = null;
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(sqlName);
			qlist = query.list();
			consecutivo = (Long) qlist.get(0);
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}

	@Override
	public List<Pedido> getPedidoFechaHijoPadre(Date fechaCreacion, long id_padre) {
		// AND p.Padre.id='"+id_padre+"'
		List<Pedido> misPedidos = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		String hql = "SELECT DISTINCT p FROM Pedido p, Almuerzo a, Padre pa  WHERE p.fechaCreacion ='"+format.format(fechaCreacion).toString()+"' AND p.id = a.id AND p.padre.id="+id_padre+"" ;
		
		misPedidos = sessionFactory.getCurrentSession().createQuery(hql).list();

		return misPedidos;
	}

}
