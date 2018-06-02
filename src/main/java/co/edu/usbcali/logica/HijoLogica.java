package co.edu.usbcali.logica;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IHijoDAO;
import co.edu.usbcali.dao.IHijoDAO;
import co.edu.usbcali.dao.IUsuarioDAO;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Hijo;
import co.edu.usbcali.modelo.Usuario;

@Service
@Scope("singleton")
public class HijoLogica implements IHijoLogica {

	@Autowired
	private IHijoDAO hijoDAO;

	@Autowired
	private IUsuarioDAO usuarioDAO;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void crearHijo(Hijo hijo) throws Exception {

		String mensaje = "EL campo $ es obligatorio";
		
		if(hijo == null) throw new Exception("El hijo no puede ser nulo");
		
		// Validacion datos como usuario
		if(hijo.getUsuario() == null) throw new Exception("El usuario no puede ser nulo");
		if(hijo.getUsuario().getPrimerNombre() == null || hijo.getUsuario().getPrimerNombre().equals("")) throw new Exception(mensaje.replace("$", "Primer Nombre"));		
		if(hijo.getUsuario().getPrimerApellido() == null || hijo.getUsuario().getPrimerApellido().equals("")) throw new Exception(mensaje.replace("$", "Primer Apellido"));
		if(hijo.getUsuario().getSegundoApellido() == null || hijo.getUsuario().getSegundoApellido().equals("")) throw new Exception(mensaje.replace("$", "Segundo Apellido"));
		if(hijo.getUsuario().getNumIdentificacion() <= 0 ) throw new Exception(mensaje.replace("$", "Número de Identificación"));
		if(hijo.getUsuario().getTipoIdentificacion() <= 0) throw new Exception(mensaje.replace("$", "Tipo de Identificación"));
		if(hijo.getUsuario().getRol() <= 0 ) throw new Exception(mensaje.replace("$", "Rol"));
		
		hijo.getUsuario().setId(usuarioDAO.getConsecutivo());
		
		// Pendiente de validar que el usuario no exista
		
		// Validacion datos como Hijo
//		if(hijo.getFechaCreacion() == null) throw new Exception(mensaje.replace("$", "Fecha Nacimiento"));
		if(hijo.getCurso() == null || hijo.getCurso().equals("")) throw new Exception(mensaje.replace("$", "Curso"));
		
		// Validacion datos como padre		
		if(hijo.getPadre() == null) throw new Exception("El padre no puede ser nulo");
		// Pendiente de validar que el padre exista
		
		// consecutivo hijo
		hijo.setId(hijoDAO.getConsecutivo());
		
		usuarioDAO.crear(hijo.getUsuario());
		hijoDAO.crear(hijo);
		
	}

	@Override
	public void modificarHijo(Hijo hijo) throws Exception {

		String mensaje = "EL campo $ es obligatorio";

	}

	@Override
	public Hijo consultarHijoPorId(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hijo> consultarHijoTodos() throws Exception {
		
		return hijoDAO.consultarTodos();
	}	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	@Override
	public List<Hijo> consultarMisHijo(long numIdentificaicon) throws Exception {
		
		return hijoDAO.consultarMisHijo(numIdentificaicon);
	}

}
