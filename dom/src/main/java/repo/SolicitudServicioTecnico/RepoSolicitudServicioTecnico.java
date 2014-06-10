package repo.SolicitudServicioTecnico;

import java.util.Date;



import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;

import org.apache.isis.applib.annotation.Optional;


import dom.SolicitudDeServicioTecnico.SolicitudServicioTecnico;


@Named("Servicio Tecnico")
public class RepoSolicitudServicioTecnico extends AbstractFactoryAndRepository {

	public String getId() {
        return "ServicioTecnico";
    }
    public String iconName() {
        return "asistenciatecnica";
    }
	
    
	public SolicitudServicioTecnico solicitarServicioTecnico(
			@Named("motivo de solicitud")String motivoDeSolicitud,
			@Named("fecha de solicitud")Date fechaDeSolicitud,
			@Named("fecha de solucion")Date fechaDeSolucion,
			@Named("prioridad")Integer prioridad,
			@Named("Codigo de Solicitud")String codigoSolicitud,
			@Named("Numero de Tiquet de Registro")String numeroTiquetRegistro,
			@Named("Comentario")@Optional @MultiLine String comentario)
	{
		final SolicitudServicioTecnico servicioTecnico = container.newTransientInstance(SolicitudServicioTecnico.class);
	    servicioTecnico.setCodigoSolicitud(codigoSolicitud);
	    servicioTecnico.setComentario(comentario);
	    servicioTecnico.setFechaDeSolicitud(fechaDeSolicitud);
	    servicioTecnico.setFechaDeSolucion(fechaDeSolucion);
	    servicioTecnico.setMotivoDeSolicitud(motivoDeSolicitud);
	    servicioTecnico.setPrioridad(prioridad);
	    servicioTecnico.setSolucion("");
	    servicioTecnico.setNumeroTiquetRegistro(numeroTiquetRegistro);
	    
		
	    
	    container.persistIfNotAlready(servicioTecnico);
	    
		return servicioTecnico;
		
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;
}
