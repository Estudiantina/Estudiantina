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
package dom.curso;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Named;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.Establecimiento.Establecimiento;
import dom.Persona.Persona;
import dom.alumno.Alumno;


import repo.Curso.RepositorioCurso;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.curso.Curso"),
	@javax.jdo.annotations.Query(name = "traerCursoPorlikeAnio", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE anoYdivision.indexOf(:anoYdivision) >=0 range 0, 4")
	})
@ObjectType("CURSO")
@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited
public class Curso implements Comparable<Curso> {
	
	private String anoYdivision;
	//TODO separar año y division
	private int cicloLectivo;
	private Turno turno;
	private Establecimiento establecimiento;
	private SortedSet<Alumno> listaAlumnos = new TreeSet<Alumno>();
	
	@Persistent(mappedBy="cursos")
	@Render(Type.EAGERLY)
	public SortedSet<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(SortedSet<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public Curso agregarAlumno(Alumno alumno) {
	    listaAlumnos.add(alumno);
	    alumno.getCursos().add(this);
	    return this;
	}

	public Curso eliminarAlumno(Alumno alumno) {
	    listaAlumnos.remove(alumno);
	    alumno.getCursos().remove(this);
	    return this;
	}

	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar curso")
	public String eliminar() {
        container.removeIfNotAlready(this);
        container.informUser("Los Cursos selecionados fueron eliminados");
        return "Los Cursos Seleccionados Fueron Eliminados"; 
    }
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String iconName() {
        return "curso";
    }
	
	@Unique
	@javax.jdo.annotations.Column(allowsNull="false")	
	public String getAnoYdivision() {
		return anoYdivision;
	}

	

	public void setAnoYdivision(String anoYdivision) {
		this.anoYdivision = anoYdivision;
	}

	@javax.jdo.annotations.Column(allowsNull="false")
	public int getCicloLectivo() {
		return cicloLectivo;
	}


	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}


	public String title()
	{
		return anoYdivision+" "+cicloLectivo;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	
  private DomainObjectContainer container;

 /**
  * 
  */
	protected DomainObjectContainer getContainer()	{
		return container;
	}
	
	/**
	 * 
	 */
	public void setDomainObjectContainer(final DomainObjectContainer container){
		this.container = container;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anoYdivision == null) ? 0 : anoYdivision.hashCode());
		result = prime * result + cicloLectivo;
		result = prime * result
				+ ((establecimiento == null) ? 0 : establecimiento.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (anoYdivision == null) {
			if (other.anoYdivision != null)
				return false;
		} else if (!anoYdivision.equals(other.anoYdivision))
			return false;
		if (cicloLectivo != other.cicloLectivo)
			return false;
		if (establecimiento == null) {
			if (other.establecimiento != null)
				return false;
		} else if (!establecimiento.equals(other.establecimiento))
			return false;
		if (turno != other.turno)
			return false;
		return true;
	}

	@Override
	public int compareTo(Curso curso) {
		// TODO Apéndice de método generado automáticamente
		
		return ObjectContracts.compare(this, curso, "anoYdivision");
	}
}
