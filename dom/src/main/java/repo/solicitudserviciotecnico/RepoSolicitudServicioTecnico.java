/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Rabovich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.solicitudserviciotecnico;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import repo.persona.RepositorioPersona;
import servicio.vistas.serviciotecnico.VistaDeBusquedaDeSoluciones;
import dom.netbook.Netbook;
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
			@Named("Netbook")final Netbook netbook,
			@Named("motivo de solicitud")final String motivoDeSolicitud,
			@Named("prioridad")Prioridad prioridad,
			@Named("Comentario")@Optional @MultiLine String comentario
            )
	{
		final SolicitudServicioTecnico servicioTecnico = container.newTransientInstance(SolicitudServicioTecnico.class);	
		servicioTecnico.setNetbook(netbook);
		servicioTecnico.setPersona(netbook.getPersona());
	    servicioTecnico.setComentario(comentario);
	    servicioTecnico.setFechaDeSolicitud(new LocalDate());
	    servicioTecnico.setMotivoDeSolicitud(motivoDeSolicitud);
	    servicioTecnico.setPrioridad(prioridad);
	    servicioTecnico.setSolucion("");
	    servicioTecnico.setCodigoSolicitud("");
	   
	    container.persistIfNotAlready(servicioTecnico);
		return servicioTecnico;	
	}
	
	public List<SolicitudServicioTecnico> listaDeSolicitudes() {
        return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerPorPrioridad"));
    }
	
	public List<SolicitudServicioTecnico> listaDeSolicitudesPendientes() {
        return allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerSolicitudesPendientes","institucion",repoPersona.verMisDatos().getEstablecimiento()));
    }
	
	@Named("Historial de Reparaciones")
	@Hidden
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
		public List<VistaDeBusquedaDeSoluciones> listaDeSoluciones (@Named ("tema")final String traerPorTema){
			
			List<SolicitudServicioTecnico> listaSolicitudes = allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerTipoDeSoluciones", "motivoDeSolicitud", traerPorTema));
			List<VistaDeBusquedaDeSoluciones> busqueda = new ArrayList<VistaDeBusquedaDeSoluciones>();
			for (SolicitudServicioTecnico solicitud : listaSolicitudes)
			{
				Memento m = mementoService.create();

				m.set("solucion", solicitud.getSolucion());
				m.set("motivo", solicitud.getMotivoDeSolicitud());
				m.set("comentario", solicitud.getComentario());
				m.set("modelo", solicitud.getNetbook().getModelo());
				m.set("marca", solicitud.getNetbook().getMarca());
				

				busqueda.add(container.newViewModelInstance(VistaDeBusquedaDeSoluciones.class,
						m.asString()));
			}
			return busqueda;
		}
	
	public SolicitudServicioTecnico verUltimaSolicitud()
	{
		Netbook netbook=null;
		try
		{
		netbook =repoPersona.verMisDatos().getNetbooks().first();
		}
		catch (Exception ex)
		{
		}
		
		if (netbook!=null)
		{
		return firstMatch(QueryDefault.create(SolicitudServicioTecnico.class, "traerUltimaSolicitud","netbookBusqueda",netbook));
		}
		else
		{
			return null;
		}
		
	}
	@javax.inject.Inject
	MementoService mementoService;
	@Inject
	RepositorioPersona repoPersona;
	@javax.inject.Inject 
    DomainObjectContainer container;
}