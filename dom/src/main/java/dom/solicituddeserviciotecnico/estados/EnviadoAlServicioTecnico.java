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
package dom.solicituddeserviciotecnico.estados;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;
import org.joda.time.LocalDate;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
import dom.tecnico.Tecnico;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnviado" )
@Uniques({ @Unique(name = "enviadoUnique" , members = { "idEnviado" } ) })
@ObjectType("ENVIADOALSERVICIOTECNICO")
public class EnviadoAlServicioTecnico implements IEstadoSolicitudDeServicioTecnico {

	/**
	 * titulo del estado
	 * return titulo ENVIADO AL SERVICIO TECNICO
	 */
	public String getNombre()
	{
		return "ENVIADO AL SERVICIO TECNICO";
	}
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
	public EnviadoAlServicioTecnico(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}
	
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}

	@Hidden
	@Override
	public boolean ocultarImprimir() {
		return true;
	}

	@Hidden
	@Override
	public boolean ocultarSolucion() {
		return true;
	}
	
	@Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
		return true;
	}
	
	@Hidden
	@Override
	public boolean ocultarFechaDeSolucion() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarTecnicoAsignado() {
		
		return false;
	}
	@Hidden
	@Override
	public void recibirDeServicioTecnico() {
		this.getSolicitud().setEstado(this.getSolicitud().getEstadoRecibido());
	}
	@Hidden
	@Override
	public void enviarAServicioTecnico() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void avisarNetbookReparada(String solucion,LocalDate fechaDeSolucion) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Override
	public void finalizarSolicitud() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void asignarTecnico(final Tecnico tecnico,final String codigoSolicitud) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Override
	public boolean ocultarAsignarTecnico() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		return true;
	}

	@Override
	public boolean ocultarEnviarAServicioTecnico() {
		return true;
	}

	@Override
	public boolean ocultarRecibirDelServicioTecnico() {
		return false;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}