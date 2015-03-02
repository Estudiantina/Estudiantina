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
package dom.tutor;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.persona.RepositorioPersona;
import dom.alumno.Alumno;
import dom.persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerTutorPorcuil", language = "JDOQL", value = "SELECT FROM dom.tutor.Tutor WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "buscarTutorPorcuilYNombre", language = "JDOQL", value = "SELECT FROM dom.tutor.Tutor WHERE (cuil== :cuil || nombre.indexOf(:nombre) >= 0 || apellido.indexOf(:apellido) >= 0 ) range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.tutor.Tutor")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarTutor")

@Audited
@ObjectType("Tutor")
public class Tutor extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495905156954632357L;

	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * @return devuelve como titulo el cuil del Tutor
	 */
	public String title()
	{
		return this.getCuil()+" "+this.getNombre().toString()+" "+this.getApellido().toString();
		
	}
	@javax.jdo.annotations.Persistent(mappedBy="tutor")
	private SortedSet<Alumno> alumnos = new TreeSet<Alumno>();

	public SortedSet<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(SortedSet<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void addToAlumnos(Alumno a) {
        if(a == null || alumnos.contains(a)) return;
        a.setTutor(this);
        alumnos.add(a);
    }
    public void removeFromAlumnos(Alumno a) {
        if(a == null || !alumnos.contains(a)) return;
        a.setTutor(null);
        alumnos.remove(a);
    }
}