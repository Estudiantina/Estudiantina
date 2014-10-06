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

import dom.alumno.Alumno;
import dom.establecimiento.Establecimiento;
import dom.persona.Persona;


import repo.curso.RepositorioCurso;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.curso.Curso"),
	@javax.jdo.annotations.Query(name = "traerCursoPorlikeAnio", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE anio.indexOf(:anio) &&  division.indexOf(:division) >=0 range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerPorCurso", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE dom.curso.Curso = :curso")
	
	})
@ObjectType("CURSO")
@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited
public class Curso implements Comparable<Curso> {
	
	private String anio;
	private String division;
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
	@javax.jdo.annotations.Column(allowsNull="false")	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}
	@javax.jdo.annotations.Column(allowsNull="false")	
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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
	


	@javax.jdo.annotations.Column(allowsNull="false")
	public int getCicloLectivo() {
		return cicloLectivo;
	}


	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}


	public String title()
	{
		return this.anio+" "+this.division+" "+cicloLectivo;
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
	public int compareTo(Curso curso) {
		// TODO Apéndice de método generado automáticamente
		
		return ObjectContracts.compare(this, curso, "anio");
	}
}
