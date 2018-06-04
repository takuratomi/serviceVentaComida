package co.edu.usbcali.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Almuerzo;

@Repository
@Scope("singleton")
public class AlmuerzoDAO implements IAlmuerzoDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void crear(Almuerzo entity) {
		sessionFactory.getCurrentSession().merge(entity);	
	}

	@Override
	public void modificar(Almuerzo entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	public void borrar(Almuerzo entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public Almuerzo consultarPorId(BigDecimal usuId) {
		return (Almuerzo)sessionFactory.getCurrentSession().get(Almuerzo.class, usuId);
	}

	@Override
	public Almuerzo consultarPorIdentificacion(Integer idAlmuerzo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Almuerzo> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Almuerzo.class).list();
	}
	
	@Override
	public Long getConsecutivo() {
		String sqlName = "ALMUERZO_SEQ";
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

}
