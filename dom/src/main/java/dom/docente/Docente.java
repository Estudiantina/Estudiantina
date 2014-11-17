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
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;
import repo.persona.RepositorioPersona;
import dom.curso.Curso;
import dom.establecimiento.Establecimiento;
import dom.persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.docente.Docente WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoDocente", language = "JDOQL", value = "SELECT FROM dom.docente.Docente")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@ObjectType("DOCENTE")
public class Docente extends Persona implements Comparable<Docente>{
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
	
	private SortedSet<Curso> cursos = new TreeSet<Curso>();;
	
    @Persistent(table="DOCENTES_CURSOS")
    @Join(column="DOCENTE_ID")
    @Element(column ="CURSO_ID")
    @Render(Type.EAGERLY)
    public SortedSet<Curso> getCursos() {
        	 		return cursos;
    }
    public void setCursos(SortedSet<Curso> cursos) {
    		    	 		this.cursos = cursos;
    }

	public Docente agregarCurso(Curso curso) {
	    cursos.add(curso);
	    curso.getListaDocente().add(this);
	    return this;
	}

	public Docente eliminarCurso(Curso curso) {
	    cursos.remove(curso);
	    curso.getListaDocente().remove(this);
	    return this;
	}
    
	@javax.inject.Inject 
    DomainObjectContainer container;

	@Override
	public int compareTo(Docente docente) {
		return ObjectContracts.compare(this, docente , "cuil");
	}
}