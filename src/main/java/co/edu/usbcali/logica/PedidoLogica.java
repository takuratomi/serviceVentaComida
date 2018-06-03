package co.edu.usbcali.logica;

import java.math.BigDecimal;
import java.util.List;

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
		Almuerzo almuerzo = new Almuerzo();
		Padre padre = new Padre();
		List<Hijo> misHijos = null;
		Hijo hijo = new Hijo();
		int estado = 1;
		int cantidadAlmuerzos = getCantidadAlmuerzos(semanaDTO);

		if (semanaDTO.getIdPadre() > 0) {
			padre = padreLogica.consultarPadrePorIdentificacion(semanaDTO.getIdPadre());
		}
		if (semanaDTO.getIdHijo() > 0) {
			misHijos = hijoLogica.consultarMisHijo(semanaDTO.getIdHijo());
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

		almuerzo.setId(new BigDecimal(almuerzoDAO.getConsecutivo()));
		almuerzo.setPedido(pedido);

		almuerzo.setHijo(hijo);
		almuerzo.setEstado(estado);

		// almacenamiento
		pedidoDAO.crear(pedido);
		almuerzoDAO.crear(almuerzo);
	}

	public int getCantidadAlmuerzos(SemanaDTO semanaDTO) {
		int contadorAlmuerzos = 0;
		int totalDias = semanaDTO.getDiasDTO().length;
		if (semanaDTO != null && semanaDTO.getDiasDTO() != null) {
			for (int i = 0; i < totalDias; i++) {
				if(semanaDTO.getDiasDTO()[i] != null)
				{
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
		String estado = "e";
		Almuerzo[] menuSemana = null;
		Sopa sopa = null;
		Bebida bebida = null;
		Proteina proteina = null;
		Principio principio = null;

		int totalDias = semanaDTO.getDiasDTO().length;
		if (semanaDTO != null && semanaDTO.getDiasDTO() != null) {
			menuSemana = new Almuerzo[totalDias];
			for (int i = 0; i < totalDias; i++) {
				Almuerzo mSemana = new Almuerzo();
				/**
				 * getDiasDTO[n]
				 * 
				 * n:0 -> LUNES n:1 -> MARTES n:2 -> MIERCOLES n:3 -> JUEVES n:4
				 * -> VIERNES
				 */
				if (i == 0) {
					if (semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId()
									.compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId()
									.compareTo(BigDecimal.ZERO) > 0) {
						sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
						bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
						proteina = proteinaDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
						principio = principioDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());

						if (sopa != null || sopa.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setSopa(sopa);
						}
						if (bebida != null || bebida.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}
						if (proteina != null || proteina.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setProteina(proteina);
						}
						if (principio != null || principio.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}

						mSemana.setDia("L");
						mSemana.setHijo(hijo);
						mSemana.setPedido(pedido);
						mSemana.setEstado(1);
						mSemana.getFechaCreacion();

					}
				}

				if (i == 1) {
					if (semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId()
									.compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId()
									.compareTo(BigDecimal.ZERO) > 0) {
						sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
						bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
						proteina = proteinaDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
						principio = principioDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());

						if (sopa != null || sopa.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setSopa(sopa);
						}
						if (bebida != null || bebida.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}
						if (proteina != null || proteina.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setProteina(proteina);
						}
						if (principio != null || principio.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}

						mSemana.setDia("M");
						mSemana.setHijo(hijo);
						mSemana.setPedido(pedido);
						mSemana.setEstado(1);
						mSemana.getFechaCreacion();
					}
				}

				if (i == 2) {
					if (semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId()
									.compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId()
									.compareTo(BigDecimal.ZERO) > 0) {
						sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
						bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
						proteina = proteinaDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
						principio = principioDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());

						if (sopa != null || sopa.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setSopa(sopa);
						}
						if (bebida != null || bebida.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}
						if (proteina != null || proteina.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setProteina(proteina);
						}
						if (principio != null || principio.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}

						mSemana.setDia("MI");
						mSemana.setHijo(hijo);
						mSemana.setPedido(pedido);
						mSemana.setEstado(1);
						mSemana.getFechaCreacion();
					}
				}

				if (i == 3) {
					if (semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId()
									.compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId()
									.compareTo(BigDecimal.ZERO) > 0) {
						sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
						bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
						proteina = proteinaDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
						principio = principioDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());

						if (sopa != null || sopa.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setSopa(sopa);
						}
						if (bebida != null || bebida.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}
						if (proteina != null || proteina.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setProteina(proteina);
						}
						if (principio != null || principio.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}

						mSemana.setDia("J");
						mSemana.setHijo(hijo);
						mSemana.setPedido(pedido);
						mSemana.setEstado(1);
						mSemana.getFechaCreacion();
					}
				}

				if (i == 4) {
					if (semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId().compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId()
									.compareTo(BigDecimal.ZERO) > 0
							|| semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId()
									.compareTo(BigDecimal.ZERO) > 0) {
						sopa = sopaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_sopa().getId());
						bebida = bebidaDAO.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_bebida().getId());
						proteina = proteinaDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_proteina().getId());
						principio = principioDAO
								.consultarPorId(semanaDTO.getDiasDTO()[i].getProductoDTO_principio().getId());

						if (sopa != null || sopa.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setSopa(sopa);
						}
						if (bebida != null || bebida.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}
						if (proteina != null || proteina.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setProteina(proteina);
						}
						if (principio != null || principio.getId().compareTo(BigDecimal.ZERO) > 0) {
							mSemana.setBebida(bebida);
						}

						mSemana.setDia("V");
						mSemana.setHijo(hijo);
						mSemana.setPedido(pedido);
						mSemana.setEstado(1);
						mSemana.getFechaCreacion();
					}
				}
				menuSemana[i] = mSemana;
			}
		}
		return menuSemana;
	}

}
