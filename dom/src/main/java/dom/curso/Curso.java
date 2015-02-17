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
package dom.curso;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.alumno.Alumno;
import dom.docente.Docente;
import dom.establecimiento.Establecimiento;
import repo.curso.RepositorioCurso;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.curso.Curso"),
	@javax.jdo.annotations.Query(name = "traerCursoPorlikeAnio", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE anio== :anio && division== :division range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerPorCurso", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE dom.curso.Curso = :curso"),
	@javax.jdo.annotations.Query(name = "buscarPorTodo", language = "JDOQL", value = "SELECT FROM dom.curso.Curso WHERE anio== :anio && division== :division && cicloLectivo== :cicloLectivo && turno==:turno && establecimiento==:establecimiento"),
	
	})

@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Cursos_unicos", 
            members={"anio","cicloLectivo","division","establecimiento","turno"})
})

@ObjectType("CURSO")
@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited
public class Curso implements Comparable<Curso> {
	
	public String iconName() {
        return "curso";
    }
	
	public String title()
	{
		return this.anio+" "+this.division+" "+cicloLectivo;
	}
		
	private Anio  anio;
	private Division division;
	//TODO separar año y division
	private int cicloLectivo;
	private Turno turno;
	private Establecimiento establecimiento;
	
	/*
	 * agregar alumno al curso
	 * 
	 */

	private SortedSet<Alumno> listaAlumnos = new TreeSet<Alumno>();
	
	@Persistent(mappedBy="cursos")
	@Render(Type.EAGERLY)
	public SortedSet<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}
	
	public void setListaAlumnos(SortedSet<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	private SortedSet<Docente> listaDocente = new TreeSet<Docente>();
	
	@Persistent(mappedBy="cursos")
	@Render(Type.EAGERLY)
	public SortedSet<Docente> getListaDocente() {
		return listaDocente;
	}
	
	public void setListaDocente(SortedSet<Docente> listaDocente) {
		this.listaDocente = listaDocente;
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
	
	public Curso agregarDocente(Docente docente) {
	    listaDocente.add(docente);
	    docente.getCursos().add(this);
	    return this;
	}

	public Curso eliminarDocente(Docente docente) {
	    listaDocente.remove(docente);
	    docente.getCursos().remove(this);
	    return this;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@Named("Año")
	public Anio getAnio() {
		return anio;
	}	
	public void setAnio(Anio anio) {
		this.anio = anio;
	}
		
	@javax.jdo.annotations.Column(allowsNull="false")	
	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
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

	@javax.jdo.annotations.Column(allowsNull="false")
	public int getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(int cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	@javax.jdo.annotations.Column(allowsNull="false")
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	@Override
	public int compareTo(Curso curso) {		
		return ObjectContracts.compare(this, curso, "anio");
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
}