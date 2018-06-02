package co.edu.usbcali.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
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

import co.edu.usbcali.dao.IHijoDAO;
import co.edu.usbcali.dao.IPadreDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Padre;
import co.edu.usbcali.modelo.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHijoDAO {
	
	private static final Logger log=LoggerFactory.getLogger(TestHijoDAO.class);
	
	@Autowired
	private IHijoDAO hijoDAO;
	@Autowired
	private IPadreDAO padreDAO;
	@Autowired	

	 long id = 90103054906L;
//	 Padre padre;
//	 Usuario usuario;
	 Date fechaNacimiento = new Date(2012, 10, 30);
	 String curso ="PRIMARIA";
	 String usuarioCreacion = "TAKURATOMI";
	 Date fechaCreacion = new Date();	 
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Rollback(false)
	public void aConsultarMisHijosTest() {
		
		List<Hijo> misHijos = null;
		
		misHijos = hijoDAO.consultarMisHijo(2311234L);
		
		log.info("-- PRESENTACION DE HIJOS   --");
		for (Hijo hijo : misHijos) {
			log.info("ID "+hijo.getId());
			log.info("PRIMER APELLIDO "+hijo.getUsuario().getPrimerApellido());
			log.info("PRIMER NOMBRE "+hijo.getUsuario().getPrimerNombre());
			log.info("CURSO "+hijo.getCurso());
			log.info("NUM_IDENTIFICACION "+hijo.getUsuario().getNumIdentificacion());
		}
		
				
	}


}
