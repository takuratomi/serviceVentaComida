package co.edu.usbcali.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.dto.HijoDTO;
import co.edu.usbcali.logica.IHijoLogica;
import co.edu.usbcali.logica.IPadreLogica;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Padre;
import co.edu.usbcali.modelo.Usuario;

@RestController
@RequestMapping("/HijoRest")
public class ControllerHijo {

	private static final Logger log = LoggerFactory.getLogger(ControllerHijo.class);
	
	
	@Autowired
	private IPadreLogica padreLogica;
	@Autowired
	private IHijoLogica hijoLogica;

	@RequestMapping(value = "/crearHijo", method = RequestMethod.POST)
	public HijoDTO crearHijoRest(@RequestBody HijoDTO hijoDTO) throws Exception {

		log.info("** LLAMADO CONTROLLERHIJO/CREAR");
		Usuario usuario = new Usuario();
		Hijo hijo = null;
		Padre padre = padreLogica.consultarPadrePorIdentificacion(hijoDTO.getNumIdentificacionPadre());

		if (padre != null) {
			usuario.setPrimerNombre(hijoDTO.getPrimerNombre());
			usuario.setSegundoNombre(hijoDTO.getSegundoNombre());
			usuario.setPrimerApellido(hijoDTO.getPrimerApellido());
			usuario.setSegundoApellido(hijoDTO.getSegundoApellido());
			usuario.setNumIdentificacion(hijoDTO.getNumIdentificacion());
			usuario.setTipoIdentificacion(hijoDTO.getTipoIdentificacion());
			usuario.setPassword(hijoDTO.getPassword());
			usuario.setRol(hijoDTO.getRol());
			usuario.setUsuarioCreacion(hijoDTO.getUsuario());
			usuario.setFechaCreacion(hijoDTO.getFecha());

			hijo = new Hijo();
			hijo.setPadre(padre);

			hijo.setUsuario(usuario);

			hijo.setCurso(hijoDTO.getCurso());
			hijo.setFechaNacimiento(hijoDTO.getFechaNacimiento());

			try {
				hijoLogica.crearHijo(hijo);
				hijoDTO.setCodigoError(0);
				hijoDTO.setMensajeError("Operación Exitosa");

			} catch (Exception e) {
				hijoDTO.setCodigoError(92);
				hijoDTO.setMensajeError("ERROR: " + e.getMessage());
			}

		} else {
			hijoDTO.setCodigoError(90);
			hijoDTO.setMensajeError("El padre No existe");
		}

		return hijoDTO;
	}

	@RequestMapping(value = "/consultarMisHijosRest/{numIdentificacion}", method = RequestMethod.GET)
	public List<HijoDTO> consultarMisHijosRest(@PathVariable("numIdentificacion") long numIdentificacion)
			throws Exception {

		List<Hijo> misHijos = null;
		List<HijoDTO> misHijosDTO = null;

		log.info("** LLAMADO CONTROLLERHIJO/CONSULTARMISHIJOS");
		misHijos = hijoLogica.consultarMisHijo(numIdentificacion);

		if (misHijos != null) {
			log.info("Cantidad hijos: "+misHijos.size());
			misHijosDTO = new ArrayList<HijoDTO>();
			for (Hijo hijo : misHijos) {
				HijoDTO hijoDTO = new HijoDTO();
				hijoDTO.setId_usuario(hijo.getUsuario().getId());
				hijoDTO.setPrimerNombre(hijo.getUsuario().getPrimerNombre());
				hijoDTO.setSegundoNombre(hijo.getUsuario().getSegundoNombre());
				hijoDTO.setPrimerApellido(hijo.getUsuario().getPrimerApellido());
				hijoDTO.setSegundoApellido(hijo.getUsuario().getSegundoApellido());
				hijoDTO.setNumIdentificacion(hijo.getUsuario().getNumIdentificacion());
				hijoDTO.setTipoIdentificacion(hijo.getUsuario().getTipoIdentificacion());
				// hijoDTO.setPassword(String password);
				// hijoDTO.setEmail(String email);
				hijoDTO.setRol(hijo.getUsuario().getRol());
				// hijoDTO.setUsuario(String usuario);
				// hijoDTO.setFecha();
				// hijoDTO.setNumIdentificacionPadre(long
				// numIdentificacionPadre);
				hijoDTO.setFechaNacimiento(hijo.getFechaNacimiento());
				hijoDTO.setCurso(hijo.getCurso());

				misHijosDTO.add(hijoDTO);
			}
		}
		return misHijosDTO;
	}

}
