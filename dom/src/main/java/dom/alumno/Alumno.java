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
package dom.alumno;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.Blob;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import repo.persona.RepositorioPersona;
import dom.curso.Curso;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.persona.personagestionable.PersonaGestionable;
import dom.tutor.Tutor;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//TODO hacer consulta traerPorcuil 
//TODO generar pedido certificado alumno regular
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerAlumnoPorcuil", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE cuil== :cuil && establecimiento==:institucion && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "buscarAlumnoPorcuilYNombre", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE (cuil== :cuil || nombre.indexOf(:nombre) >= 0 || apellido.indexOf(:apellido) >= 0 ) && establecimiento==:institucion && estaBorrado== 'ACTIVO' range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerPorEstado", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estadoDeAlumno== :estadoDeAlumno && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerTodosLosAlumnoDelEstablecimientoActual", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estaBorrado== 'ACTIVO' && establecimiento== :establecimiento")
})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarAlumno")
@Audited
@ObjectType("ALUMNO")
public class Alumno extends PersonaGestionable implements Locatable,Comparable<Alumno>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6321349546542111123L;

	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * 
	 * @return devuelve como titulo el cuil del alumno 
	 */
	public String title()
	{
		return this.getCuil()+" "+this.getNombre().toString()+" "+this.getApellido().toString();
		
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

    /**
     * 
     * @param curso
     * @return
     */
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
	private Date fechaIngreso;
	private EstadoDeAlumno estadoDeAlumno;
	private Nacionalidad nacionalidad;
	
	@javax.jdo.annotations.Column(allowsNull="false")
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
	
	///////////////////////////////////////
	//imprimir reporte para certificado de alumno regular
	//////////////////////////////////////
	@Named("Imprimir Certificado Alumno Regular")
	public Blob imprimirCertificadoAlumnoRegular() throws JRException, FileNotFoundException  
    {

			try
			{
				HashMap<String,Object> parametros = new HashMap<String, Object>();
    	
    	 	Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",this.getCuil(),"institucion",this.getEstablecimiento()));
	    	Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",alumno.getEstablecimiento().getNombre()));
	    	parametros.put("establecimiento", establecimiento.getNombre());
	    	  	
	    	Localidad localidadEstablecimiento = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",this.getEstablecimiento().getLocalidad().getCodigoPostal()));
		    Departamento departamentoEstablecimiento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidadEstablecimiento.getDepartamento().getNombreDepartamento()));
		  
		    Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
	    	Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
	    	
		    parametros.put("dniAlumno", super.getCuil());
		    parametros.put("alumno", super.getApellido()+" "+super.getNombre());
	    	parametros.put("anoydivision", this.getCursos().first().getAnio()+ "  "+this.cursos.first().getDivision()) ;
	    	//parametros.put("division", this.cursos.first().getDivision());
	    	SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MMM/yyyy/");
	    	Date fechahoy = new Date();
			parametros.put("fechahoy", formatofecha.format(fechahoy));	
		     	    	
			return servicio.reporte.GeneradorReporte.generarReporte("reportes/CertAlumnoRegular.jrxml", parametros, "CertAlumnoRegular");
			}
			catch(Exception ex)
			{	
				Blob archivonulo = new Blob("archivo.txt", "text/plain", "no se pudo generar el reporte verifique que esten todos los datos".getBytes());
				return archivonulo;
			}
		
    }
	

	
	@javax.inject.Inject 
    DomainObjectContainer container;

	@Override
	public int compareTo(Alumno alumno) {

		return ObjectContracts.compare(this, alumno, "cuil");
	}
}