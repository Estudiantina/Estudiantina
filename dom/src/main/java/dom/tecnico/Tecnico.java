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
package dom.tecnico;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.persona.RepositorioPersona;
import dom.persona.personagestionable.PersonaGestionable;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.tecnico.Tecnico WHERE cuil== :cuil && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "buscarTecnicoPorcuilYNombre", language = "JDOQL", value = "SELECT FROM dom.tecnico.Tecnico WHERE (cuil== :cuil || nombre.indexOf(:nombre) >= 0 || apellido.indexOf(:apellido) >= 0 ) && establecimiento==:institucion range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodoDocente", language = "JDOQL", value = "SELECT FROM dom.tecnico.Tecnico WHERE estaBorrado== 'ACTIVO'")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarTecnico")
@Audited
@ObjectType("TECNICO")
public class Tecnico extends PersonaGestionable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8413385210614716678L;

	public String title()
	{
		return this.getCuil()+" "+this.getNombre().toString()+" "+this.getApellido().toString();
		
	}	
}