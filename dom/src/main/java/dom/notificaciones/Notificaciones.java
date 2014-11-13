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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Where;
import org.joda.time.LocalDate;

import dom.persona.Persona;

import javax.jdo.annotations.Column;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerNotificaciones", language = "JDOQL", value = "SELECT FROM dom.notificaciones.Notificaciones ORDER BY vista ASCENDING"),
	@javax.jdo.annotations.Query(name = "traerNotificacionesNoLeidas", language = "JDOQL", value = "SELECT FROM dom.notificaciones.Notificaciones WHERE vista == false"),
	@javax.jdo.annotations.Query(name = "traerNotificacionesEntreFechas", language = "JDOQL", value = "SELECT FROM dom.notificaciones.Notificaciones WHERE this.fechaNotificacion >= :fechaAnterior &&  this.fechaNotificacion <= :fechaPosterior ORDER BY vista ASCENDING ")
})

@Bookmarkable
@ObjectType("Notificaciones")
public class Notificaciones {

	private boolean vista;
	private LocalDate fechaNotificacion;
    private Persona persona;
    private String detallesYobservaciones;
	

    public String iconName() {
    	if (this.vista==false)
    	{
    	return "notificacionVista";
    	}
    	else
    	{
    	return "notificacionAtendida";
    	}
    }
    
    @Column(allowsNull="false")
    @Hidden
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	@Column(allowsNull="true")
	@Optional
	public String getDetallesYobservaciones() {
		return detallesYobservaciones;
	}

	public void setDetallesYobservaciones(String detallesYobservaciones) {
		this.detallesYobservaciones = detallesYobservaciones;
	}
	
	@Column(allowsNull="false")
	@Persistent
	public LocalDate getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(LocalDate fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	@Hidden
	@Column(allowsNull="false")
	public boolean isVista() {
		return vista;
	}

	public void setVista(boolean vista) {
		this.vista = vista;
	}
	
	public Notificaciones marcarComoNotificacionAtendida()
	{
		this.setVista(true);
		return this;
	}
	
	public boolean hideMarcarComoNotificacionAtendida()
	{
	     return vista;
	}
}
