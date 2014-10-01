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
package dom.alumno;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.Blob;
import org.eclipse.jdt.core.dom.ThisExpression;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;



import repo.persona.RepositorioPersona;
import servicio.reporte.GeneradorReporte;
import dom.curso.Curso;
import dom.persona.Persona;
import dom.tutor.Tutor;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//TODO hacer consulta traerPorcuil 
//TODO generar pedido certificado alumno regular
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerAlumnoPorcuil", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno"),
	@javax.jdo.annotations.Query(name = "traerPorEstado", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estadoDeAlumno== :estadoDeAlumno")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarAlumno")
@Audited



@ObjectType("ALUMNO")
public class Alumno extends Persona implements Locatable,Comparable<Alumno>{
	
	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * 
	 * @return devuelve como titulo el cuil del alumno 
	 */
	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
		
	}	
	/**
	 * Identificacion del nombre del icono 
	 * que aparecera en la UI
	 * resources/icono.png
	 * @return String nombre de icono
	 */
		
	   public String iconName() {
	       if (getEstadoDeAlumno() == EstadoDeAlumno.REGULAR) {
	    	   return "alumno";
	       } else {
	    	   if (getEstadoDeAlumno() == EstadoDeAlumno.RECURSANTE) {
	        	   return "alumno";
	           } else {
	        	   if (getEstadoDeAlumno()== EstadoDeAlumno.PASE){
	        		   return "atencion";
	        	   }else {
	        		   if (getEstadoDeAlumno()== EstadoDeAlumno.LIBRE){
		        		   return "atencion1";
	        	   }
	           }
	       }
	       }
		return "alumno";
	   }
	    	
    
	   private SortedSet<Curso> cursos = new TreeSet<Curso>();;

	
	
    @Persistent(table="ALUMNO_CURSOS")
    @Join(column="ALUMNO_ID")
    @Element(column ="CURSO_ID")
    @Render(Type.EAGERLY)
    public SortedSet<Curso> getCursos() {
        	 		return cursos;
    	 	}
    public void setCursos(SortedSet<Curso> cursos) {
    		    	 		this.cursos = cursos;
    	 	}

	public Alumno agregarCurso(Curso curso) {
	    cursos.add(curso);
	    curso.getListaAlumnos().add(this);
	    return this;
	}

	public Alumno eliminarCurso(Curso curso) {
	    cursos.remove(curso);
	    curso.getListaAlumnos().remove(this);
	    return this;
	}

	private Tutor tutor;
	@javax.jdo.annotations.Column(allowsNull="true")
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public void modifyTutor(Tutor t) {
        if(t==null || tutor==t) return;
        if(tutor != null) {
            tutor.removeFromAlumnos(this);
        }
        t.addToAlumnos(this);
    }
    public void clearTutor() {
        if(tutor==null) return;
        tutor.removeFromAlumnos(this);
    }

	private Date fechaIngreso;
	
	
	private EstadoDeAlumno estadoDeAlumno;
	private Nacionalidad nacionalidad;

	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")	
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	@Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="false")
	public EstadoDeAlumno getEstadoDeAlumno() {
		return estadoDeAlumno;
	}
	public void setEstadoDeAlumno(EstadoDeAlumno estadoDeAlumno) {
		this.estadoDeAlumno = estadoDeAlumno;
	}

	
	
	public Blob imprimirContratoDeCesion() throws FileNotFoundException, JRException
	{
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
	    
	    parametros.put("modeloNetbook",super.getNetbooks().first().getModelo().toString());
	    parametros.put("numeroSerieNetbook",this.getNetbooks().first().getNumeroDeSerie());
	    parametros.put("Instituto",this.getEstablecimiento().getNombre());
	    parametros.put("ciudadDeEstablecimiento", this.getEstablecimiento().getLocalidad().toString());
	    parametros.put("nombreAlumno", super.getApellido()+" "+super.getNombre());
	    parametros.put("dniAlumno", super.getCuil());
	    
		return GeneradorReporte.generarReporte("reportes/contratoCesion.jrxml",parametros , "contratoCesion");
	}
	
	
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;






	@Override
	public int compareTo(Alumno alumno) {

		return ObjectContracts.compare(this, alumno, "cuil");
	}



	
}