package repo.Notificaciones;

import java.util.List;



import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Date;
import org.apache.isis.applib.annotation.Named;

import dom.Notificaciones.CertificadoAlumnoRegular;
import dom.Notificaciones.Notificaciones;
import dom.login.Login;


@Named("Notificaciones")
public class RepoNotificaciones extends AbstractFactoryAndRepository {

	
	public String getId() {
        return "Notificaciones";
    }

	public String pedirCertificado(@Named("detalles") @Optional String detalles)
	{
		final CertificadoAlumnoRegular certificadoAlumnoRegular = container.newTransientInstance(CertificadoAlumnoRegular.class);
		certificadoAlumnoRegular.setDetallesYobservaciones(detalles);	
		String usuarioActual = container.getUser().getName();
		Login login = firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuarioActual));
		Date fecha = new Date();
		certificadoAlumnoRegular.setFechaNotificacion(fecha);
		certificadoAlumnoRegular.setPersona(login.getPersona());
		certificadoAlumnoRegular.setVista(false);//la notificacion todavia no esta vista
		container.persistIfNotAlready(certificadoAlumnoRegular);
		container.informUser("Se Ha solicitado un nuevo certificado De Alumno Regular");
		return "Se Ha solicitado un nuevo certificado";
	}
	
	
	@Named("Notificaciones No Leidas")
	public List<Notificaciones> verNotificacionesNoLeidas()
	{		
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificacionesNoLeidas"));
		
	}
	
	public List<Notificaciones> verTodasLasNotificaciones()
	{

		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificaciones"));
		
	}



	@javax.inject.Inject 
    DomainObjectContainer container;
}
