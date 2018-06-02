package co.edu.usbcali.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.dto.SemanaDTO;
import co.edu.usbcali.logica.IPedidoLogica;

@RestController
@RequestMapping("/PedidoRest")
public class ControllerPedido {

	private static final Logger log = LoggerFactory.getLogger(ControllerPedido.class);

	@Autowired
	private IPedidoLogica pedidoLogica;

	@RequestMapping(value = "/crearPedido", method = RequestMethod.POST)
	public SemanaDTO crearPedidoRest(@RequestBody SemanaDTO semanaDTO) throws Exception {

		log.info("** LLAMADO CONTROLLERPEDIDO/CREARPEDIDO");
		SemanaDTO response = new SemanaDTO();

		response.setIdHijo(0L);
		response.setIdPadre(0L);
		response.setMensajeError("Operacion Exitosa");
		response.setCodigoError(0);
		try {

			pedidoLogica.crearPedido(semanaDTO);

		} catch (Exception e) {

			response.setCodigoError(91);
			response.setMensajeError("ERRO:" + e.getMessage());

		}
		return response;
	}
}
