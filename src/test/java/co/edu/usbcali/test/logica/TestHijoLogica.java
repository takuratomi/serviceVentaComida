package co.edu.usbcali.test.logica;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;

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

import co.edu.usbcali.logica.IHijoLogica;
import co.edu.usbcali.logica.IPadreLogica;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Padre;
import co.edu.usbcali.modelo.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestHijoLogica {

	private static final Logger log = LoggerFactory.getLogger(TestProductoLogica.class);

	@Autowired
	private IPadreLogica padreLogica;
	@Autowired
	private IHijoLogica hijoLogica;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void atest() {

		try {
//			// Padre padre = padreLogica.consultarPadrePorId(25L);
//			Padre padre = padreLogica.consultarPadrePorIdentificacion(2311234L);
//
//			Usuario usuario = new Usuario();
//			usuario.setPrimerNombre("Camilo");
//			usuario.setPrimerApellido("SAndobal");
//			usuario.setSegundoApellido("Castrillon");
//			usuario.setNumIdentificacion(1112345L);
//			usuario.setTipoIdentificacion(3);
//			usuario.setRol(2);
//
//			Hijo hijo = new Hijo();
//
//			hijo.setPadre(padre);
//			hijo.setUsuario(usuario);
//			hijo.setFechaNacimiento(new Date());
//			hijo.setCurso("PRIAMRIA");
//
//			hijoLogica.crearHijo(hijo);

		} catch (Exception e) {
			log.info("ERROR: " + e.getMessage());
		}
	}	
	
	@Test
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void bConsultarMisHijostest() {
		
//		List<Hijo> misHijos = null;
//		try {
//			misHijos = hijoLogica.consultarMisHijo(2311234L);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}
}
