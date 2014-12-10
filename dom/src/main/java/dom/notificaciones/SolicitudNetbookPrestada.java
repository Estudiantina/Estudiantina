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
import javax.inject.Inject;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import dom.netbook.Netbook;
import dom.persona.Persona;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudNetbookPrestada")
public class SolicitudNetbookPrestada extends Notificaciones {

	public String title()
	{
		return "Solicitar Netbook Prestada -"+this.getPersona().toString();
	}
	
	public Persona asignarNetbook(Netbook netbook)
	{
		if (this.getPersona().getNetbooks().size()>1)
		{
		container.informUser("verifique el estados de las demas netbook si es que existen");
		}
		this.getPersona().anadirNetbook(netbook);
		return this.getPersona();
	}
	
	public boolean hideAsignarNetbook()
	{
		return this.isVista();
	}
	
	@Inject
	DomainObjectContainer container;
	
	
}
