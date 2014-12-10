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
package repo.notificaciones;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.annotation.Named;
import org.joda.time.LocalDate;
import repo.persona.RepositorioPersona;
import dom.login.Login;
import dom.notificaciones.CertificadoAlumnoRegular;
import dom.notificaciones.Notificaciones;
import dom.notificaciones.SolicitudNetbookPrestada;
import dom.notificaciones.SolicitudTramiteDeMigracion;

@Named("Solicitudes")
public class RepoNotificaciones extends AbstractFactoryAndRepository {
	
	public String getId() {
        return "Notificaciones";
    }

	public String pedirCertificadoAlumnoRegular(@Named("detalles") @Optional String detalles)
	{
		final CertificadoAlumnoRegular certificadoAlumnoRegular = container.newTransientInstance(CertificadoAlumnoRegular.class);
		certificadoAlumnoRegular.setDetallesYobservaciones(detalles);	
		String usuarioActual = container.getUser().getName();
		Login login = firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuarioActual));
		LocalDate fecha = new LocalDate();
		certificadoAlumnoRegular.setFechaNotificacion(fecha);
		certificadoAlumnoRegular.setPersona(login.getPersona());
		certificadoAlumnoRegular.setEstablecimiento(login.getPersona().getEstablecimiento());
		certificadoAlumnoRegular.setVista(false);//la notificacion todavia no esta vista
		container.persistIfNotAlready(certificadoAlumnoRegular);
		container.informUser("Se Ha solicitado un nuevo certificado De Alumno Regular");
		return "Se Ha solicitado un nuevo certificado";
	}

	public String pedirNetbookPrestada(@Named("Motivo de Prestamo") @MultiLine @Optional String detalles)
	{
		final SolicitudNetbookPrestada solicitudNetbookPrestada = container.newTransientInstance(SolicitudNetbookPrestada.class);
		solicitudNetbookPrestada.setDetallesYobservaciones(detalles);	
		String usuarioActual = container.getUser().getName();
		Login login = firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuarioActual));
		LocalDate fecha = new LocalDate();
		solicitudNetbookPrestada.setFechaNotificacion(fecha);
		solicitudNetbookPrestada.setPersona(login.getPersona());
		solicitudNetbookPrestada.setEstablecimiento(login.getPersona().getEstablecimiento());
		solicitudNetbookPrestada.setVista(false);//la notificacion todavia no esta vista
		container.persistIfNotAlready(solicitudNetbookPrestada);
		container.informUser("Se Ha solicitado el prestamo de una Netbook a su nombre");
		return "Se Ha solicitado una Nueva Netbook de Forma Correcta";
	}
	
	public String solicitarTramiteDeMigracion(@Named("Motivo de Migracion") @MultiLine @Optional String detalles)
	{
		final SolicitudTramiteDeMigracion solicitudTramiteDeMigracion = container.newTransientInstance(SolicitudTramiteDeMigracion.class);
		solicitudTramiteDeMigracion.setDetallesYobservaciones(detalles);	
		String usuarioActual = container.getUser().getName();
		Login login = firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuarioActual));
		LocalDate fecha = new LocalDate();
		solicitudTramiteDeMigracion.setFechaNotificacion(fecha);
		solicitudTramiteDeMigracion.setPersona(login.getPersona());
		solicitudTramiteDeMigracion.setEstablecimiento(login.getPersona().getEstablecimiento());
		solicitudTramiteDeMigracion.setVista(false);//la notificacion todavia no esta vista
		container.persistIfNotAlready(solicitudTramiteDeMigracion);
		container.informUser("Se Ha solicitado el Tramite de Migracion Correctamente");
		return "Se Ha solicitado el Tramite De Migracion";
	}	
  	
	@Named("Notificaciones No Leidas")
	public List<Notificaciones> verNotificacionesNoLeidas()
	{	
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificacionesNoLeidas","institucion",repoPersona.VerMisDatos().getEstablecimiento()));
		
	}
	
	public List<Notificaciones> verTodasLasNotificaciones()
	{
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificaciones"));
	}

	@Named("Notificaciones de Hoy")
	public List<Notificaciones> verNotificacionesDeHoy()
	{	
		LocalDate fechaAnterior = LocalDate.now().minusDays(2); 
 
		LocalDate fechaPosterior = LocalDate.now().minusDays(-1);
		
		return allMatches(QueryDefault.create(Notificaciones.class, "traerNotificacionesEntreFechas","fechaAnterior",fechaAnterior,"fechaPosterior",fechaPosterior,"institucion",repoPersona.VerMisDatos().getEstablecimiento()));
		
	}
	@Inject
	RepositorioPersona repoPersona;
	@javax.inject.Inject 
    DomainObjectContainer container;
}