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
package dom.docente;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import repo.persona.RepositorioPersona;

import dom.establecimiento.Establecimiento;
import dom.persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.docente.Docente WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoDocente", language = "JDOQL", value = "SELECT FROM dom.docente.Docente")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@ObjectType("DOCENTE")
public class Docente extends Persona{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2982300213676454180L;

	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * 
	 * @return devuelve como titulo el cuil del Docente
	 */
	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
		
	}

	public String cargo;
	public List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Render(Type.EAGERLY)
    @Join
	@javax.jdo.annotations.Column(allowsNull="true")
	public List<Establecimiento> getEstablecimientos() {
		return establecimientos;
	}

	public void setEstablecimientos(List<Establecimiento> establecimientos) {
		this.establecimientos = establecimientos;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}
