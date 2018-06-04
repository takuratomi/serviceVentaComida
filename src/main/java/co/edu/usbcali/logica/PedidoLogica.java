package co.edu.usbcali.logica;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IAlmuerzoDAO;
import co.edu.usbcali.dao.IBebidaDAO;
import co.edu.usbcali.dao.IPedidoDAO;
import co.edu.usbcali.dao.IPrincipioDAO;
import co.edu.usbcali.dao.IProteinaDAO;
import co.edu.usbcali.dao.ISopaDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.dto.SemanaDTO;
import co.edu.usbcali.modelo.Almuerzo;
import co.edu.usbcali.modelo.Bebida;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Padre;
import co.edu.usbcali.modelo.Pedido;
import co.edu.usbcali.modelo.Principio;
import co.edu.usbcali.modelo.Proteina;
import co.edu.usbcali.modelo.Sopa;

@Service
@Scope("singleton")
public class PedidoLogica implements IPedidoLogica {

	@Autowired
	private IPedidoDAO pedidoDAO;

	@Autowired
	private IAlmuerzoDAO almuerzoDAO;

	@Autowired
	private IHijoLogica hijoLogica;

	@Autowired
	private IPadreLogica padreLogica;

	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Autowired
	private ISopaDAO sopaDAO;

	@Autowired
	private IBebidaDAO bebidaDAO;

	@Autowired
	private IProteinaDAO proteinaDAO;

	@Autowired
	private IPrincipioDAO principioDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void crearPedido(SemanaDTO semanaDTO) throws Exception {

		Pedido pedido = new Pedido();
		Almuerzo[] arrayAlmuerzos = null;
		;
		Padre padre = new Padre();
		List<Hijo> misHijos = null;
		Hijo hijo = new Hijo();
		int estado = 1;
		int cantidadAlmuerzos = getCantidadAlmuerzos(semanaDTO);

		
		
		if (semanaDTO.getIdPadre() > 0) {
			padre = padreLogica.consultarPadrePorIdentificacion(semanaDTO.getIdPadre());
		
		}
		
		// validacion de un pedido por semana
		if(pedidoDAO.getPedidoFechaHijoPadre(new Date(), padre.getId()).size() > 0) throw new Exception("No se puede crear otro pedido para esta semana");
			
		if (semanaDTO.getIdHijo() > 0) {
			misHijos = hijoLogica.consultarMisHijo(semanaDTO.getIdPadre());
			for (Hijo hijo_ : misHijos) {
				if (hijo_.getUsuario().getNumIdentificacion() == semanaDTO.getIdHijo()) {
					hijo = hijo_;
				}
			}
		}

		pedido.setId(new BigDecimal(pedidoDAO.getConsecutivo()));

		pedido.setCantAlmuerzos(cantidadAlmuerzos);
		pedido.setEstado(estado);
		pedido.setPadre(padre);
		pedido.setFechaCreacion(new Date());

		arrayAlmuerzos = constructMenuSemana(semanaDTO, hijo, pedido);

		if (arrayAlmuerzos != null) {
			pedidoDAO.crear(pedido);
			for (Almuerzo almur : arrayAlmuerzos) {
				if(almur != null)
				{				
					almur.setId(new BigDecimal(almuerzoDAO.getConsecutivo()));
					almuerzoDAO.crear(almur);
				}
				
			}
		}
	}

	public int getCantidadAlmuerzos(SemanaDTO semanaDTO) {
		int contadorAlmuerzos = 0;
		int totalDias = semanaDTO.getDiasDTO().length;
		if (semanaDTO != null && semanaDTO.getDiasDTO() != null) {
			for (int i = 0; i < totalDias; i++) {
				if (semanaDTO.getDiasDTO()[i] != null) {
					if (semanaDTO.getDiasDTO()[i].getPosition_sopa() > 0
							|| semanaDTO.getDiasDTO()[i].getPosition_bebida() > 0
							|| semanaDTO.getDiasDTO()[i].getPosition_principio() > 0
							|| semanaDTO.getDiasDTO()[i].getPosition_proteina() > 0) {
						contadorAlmuerzos++;
					}
				}
			}
		}
		return contadorAlmuerzos;
	}

	public Almuerzo[] constructMenuSemana(SemanaDTO semanaDTO, Hijo hijo, Pedido pedido) {
		Integer estado = 1;
		Almuerzo[] menuSemana = null;
		Sopa sopa = null;
		Bebida bebida = null;
		Proteina proteina = null;
		Principio principio = null;
		Almuerzo mSemana = null;

		Map<Integer, String> diasSemana = new HashMap<>();
		diasSemana.put(1, "L");
		diasSemana.put(2, "M");
		diasSemana.put(3, "MI");
		diasSemana.put(4, "J");
		diasSemana.put(5, "V");

		Date creacion = new Date();

		int totalDias = semanaDTO.getDiasDTO().length;
		if (semanaDTO != null && semanaDTO.getDiasDTO() != null) {
			menuSemana = new Almuerzo[totalDias];
			
			for (int i = 0; i < totalDias; i++) {
				boolean booProducto = false;
				mSemana = new Almuerzo();
				/**
				 * getDiasDTO[n]
				 * 
				 * n:0 -> LUNES n:1 -> MARTES n:2 -> MIERCOLES n:3 -> JUEVES n:4
				 * -> VIERNES
				 */

				if (semanaDTO.getDiasDTO()[i].getPosition_sopa() > 0) {
					sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
					mSemana.setSopa(sopa);
					booProducto = true;
				}
				if (semanaDTO.getDiasDTO()[i].getPosition_bebida() > 0) {
					bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
					mSemana.setBebida(bebida);
					booProducto = true;
				}
				if (semanaDTO.getDiasDTO()[i].getPosition_principio() > 0) {
					principio = principioDAO
							.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());
					mSemana.setPrincipio(principio);
					booProducto = true;
				}
				if (semanaDTO.getDiasDTO()[i].getPosition_proteina() > 0) {
					proteina = proteinaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
					mSemana.setProteina(proteina);
					booProducto = true;
				}

				// LUNES
				if (i == 0) {
					mSemana.setDia(diasSemana.get(1));
				}
				// MARTES
				if (i == 1) {
					mSemana.setDia(diasSemana.get(2));
				}
				// MIERCOLES
				if (i == 2) {
					mSemana.setDia(diasSemana.get(3));
				}
				// JUEVES
				if (i == 3) {
					mSemana.setDia(diasSemana.get(4));
				}
				// VIERNES
				if (i == 4) {
					mSemana.setDia(diasSemana.get(5));
				}
				
				mSemana.setHijo(hijo);
				mSemana.setPedido(pedido);
				mSemana.setEstado(estado);
				mSemana.setFechaCreacion(creacion);
				
				if(booProducto)
				{
					menuSemana[i] = mSemana;
				}
				else
				{
					menuSemana[i] = null;
				}				
			}
		}
		return menuSemana;
	}

}
