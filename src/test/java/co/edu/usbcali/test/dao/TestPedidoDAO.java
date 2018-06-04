package co.edu.usbcali.test.dao;

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

import co.edu.usbcali.dao.IPedidoDAO;
import co.edu.usbcali.modelo.Pedido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPedidoDAO {

	private static final Logger log = LoggerFactory.getLogger(TestPedidoDAO.class);

	@Autowired
	private IPedidoDAO pedidoDAO;

	long id = 1;
	Date fechaNacimiento = new Date();

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Rollback(false)
	public void aConsultarPedidoTest() {

		List<Pedido> misPedidos = null;

		misPedidos = pedidoDAO.getPedidoFechaHijoPadre(fechaNacimiento, id);

		log.info("-- PRESENTACION DE HIJOS   --");
		for (Pedido hijo : misPedidos) {
			log.info("ID " + hijo.getId());

		}

	}

}
