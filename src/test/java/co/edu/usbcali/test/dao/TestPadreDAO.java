package co.edu.usbcali.test.dao;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IPadreDAO;
import co.edu.usbcali.modelo.Padre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPadreDAO {

	private static final Logger log = LoggerFactory.getLogger(TestPadreDAO.class);

	@Autowired
	private IPadreDAO padreDAO;	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void atest() {
		log.info("---------------------------------");
		log.info("------- FUNCIONA LOG  ----------");
		
		List<Padre> listPadre = padreDAO.consultarTodos();
		
		for (Padre padre : listPadre) {
			
			log.info(padre.getUsuario().getPrimerApellido());
			log.info(padre.getUsuario().getPrimerNombre());
			log.info(""+padre.getUsuario().getTipoIdentificacion());
			log.info(""+padre.getUsuario().getNumIdentificacion());
			log.info(""+padre.getUsuario().getRol());
			log.info("---------------------------------");	
		}
		
		

	}	
}
