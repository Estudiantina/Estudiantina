/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Robobich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.solicitudserviciotecnico;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;

import dom.netbook.Netbook;
import dom.persona.Persona;
import dom.solicituddeserviciotecnico.Prioridad;
import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;


@Named("Servicio Tecnico")
public class RepoSolicitudServicioTecnico extends AbstractFactoryAndRepository {

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
			@Named("Comentario")@Optional @MultiLine String comentario
            )
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
	    
	   
	    container.persistIfNotAlready(servicioTecnico);
		return servicioTecnico;	
	}
	
	public List<SolicitudServicioTecnico> listadeSolicitudes() {
        return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerPorPrioridad"));
    }
	
	@Named("Historial de Reparaciones")
	public List<SolicitudServicioTecnico> verHistorialReparaciones(Netbook netbook) {
	
		return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerHistorial", "netbookBusqueda", netbook));
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
