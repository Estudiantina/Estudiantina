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
package dom.notificaciones;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
import dom.netbook.Netbook;
import dom.persona.Persona;

import repo.establecimiento.RepositorioEstablecimiento;
import repo.login.repologin;
import repo.persona.RepositorioPersona;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudTramiteDeMigracion")
public class SolicitudTramiteDeMigracion extends SolicitudNetbookPrestada{

	public String title()
	{
		return "Migracion De Alumno -"+this.getPersona().toString();
	}
	
	public Blob imprimir() throws FileNotFoundException, JRException
	{

		//try{
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		Persona miPersona = repositorioPersona.buscarPorCuil(this.getPersona().getCuil());
		Establecimiento miEstablecimiento = repositorioEstablecimiento.traerPorCue(miPersona.getEstablecimiento().getCue());
		Netbook netbook = miPersona.getNetbooks().first();
		//Persona miDirectivo = repositorioPersona.buscarPorCuil(miEstablecimiento.getDirectivo().getCuil());
		parametros.put("nombreDirector","");
		parametros.put("dniDirector", "");
		parametros.put("distritoEscolar",miEstablecimiento.getDistritoEscolar());
		parametros.put("provinciaEstablecimiento","");
		parametros.put("domicilioEstablecimiento",miEstablecimiento.getDireccion());
		parametros.put("nombreEstablecimiento",miEstablecimiento.getNombre());
		parametros.put("CUEEstablecimiento",miEstablecimiento.getCue());
		parametros.put("numeroEstablecimiento","");
		parametros.put("ciudadEstablecimiento",miEstablecimiento.getLocalidad().toString());
		parametros.put("nombreEstablecimiento",miEstablecimiento.getNombre());
		parametros.put("ciudadAlumno",miPersona.getLocalidad().toString());
		parametros.put("CUILAlumno",miPersona.getCuil().toString());
		parametros.put("modeloNetbook",netbook.getModelo().toString());
		parametros.put("provincia","");
		parametros.put("numeroSerieNetbook",netbook.getNumeroDeSerie().toString());
		
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/actademigracion.jrxml", parametros, "Solicitud");
		/*}
		catch(Exception ex)
		{	
			Blob archivonulo = new Blob("archivo.txt", "text/plain", "no se pudo generar el reporte verifique que esten todos los datos".getBytes());
			return archivonulo;
		}*/
		
	}
	
	
	public boolean hideImprimir()
	{
		return this.isVista();
	}
	
	@Inject
	private RepositorioEstablecimiento repositorioEstablecimiento;
	@Inject
	private RepositorioPersona repositorioPersona;
}
