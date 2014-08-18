package repo.HistorialReparaciones;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;

import dom.Netbook.Netbook;
import dom.Persona.Persona;
import dom.SolicitudDeServicioTecnico.EstadoDeSolicitud;
import dom.SolicitudDeServicioTecnico.Prioridad;
import dom.SolicitudDeServicioTecnico.SolicitudServicioTecnico;


@Named("Servicio Tecnico")
public class RepoHistorialReparaciones extends AbstractFactoryAndRepository {

	public String getId() {
        return "ServicioTecnico";
    }
    public String iconName() {
        return "asistenciatecnica";
    }
	
    public SolicitudServicioTecnico solicitarServicioTecnico(
			@Named("Persona")final Persona persona ,
			
			@Named("Netbook")final Netbook netbook,
			@Named("motivo de solicitud")String motivoDeSolicitud,
			@Named("fecha de solicitud")Date fechaDeSolicitud,
			@Optional@Named("fecha de solucion")Date fechaDeSolucion,
			@Named("prioridad")Prioridad prioridad,
			@Named("Codigo de Solicitud")String codigoSolicitud,
			@Named("Numero de Tiquet de Registro")String numeroTiquetRegistro,
			@Named("Comentario")@Optional @MultiLine String comentario,
            @Named("Estado de solicitud") final EstadoDeSolicitud estadoDeSolicitud)
	{
		final SolicitudServicioTecnico servicioTecnico = container.newTransientInstance(SolicitudServicioTecnico.class);
	    
		servicioTecnico.setPersona(persona);
		servicioTecnico.setNetbook(netbook);
		servicioTecnico.setCodigoSolicitud(codigoSolicitud);
	    servicioTecnico.setComentario(comentario);
	    servicioTecnico.setFechaDeSolicitud(fechaDeSolicitud);
	    servicioTecnico.setFechaDeSolucion(fechaDeSolucion);
	    servicioTecnico.setMotivoDeSolicitud(motivoDeSolicitud);
	    servicioTecnico.setPrioridad(prioridad);
	    servicioTecnico.setSolucion("");
	    servicioTecnico.setNumeroTiquetRegistro(numeroTiquetRegistro);
	    servicioTecnico.setEstadoDeSolicitud(estadoDeSolicitud);
	    
	    container.persistIfNotAlready(servicioTecnico);
		return servicioTecnico;	
	}
	
	public List<SolicitudServicioTecnico> listadeSolicitudes() {
        return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerPorPrioridad"));
    }
	
	
	/**
	 * metodo que trae todas las reparaciones
	 * con su soluciones
	 * 
	 * @return devuelve como titulo la solucion 
	 */
	@Named("Buscar Soluciones")
		public List<SolicitudServicioTecnico> listaDeSoluciones (@Named ("tema")final String traerPorTema){
			
			return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "taerTipoDeSoluciones", "motivoDeSolicitud", traerPorTema));
		}
		
	
    
	@javax.inject.Inject 
    DomainObjectContainer container;
}
