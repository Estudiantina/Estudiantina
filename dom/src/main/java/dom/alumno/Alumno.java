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
import servicio.reporte.GeneradorReporte;
import dom.curso.Curso;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.persona.Persona;
import dom.tutor.Tutor;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//TODO hacer consulta traerPorcuil 
//TODO generar pedido certificado alumno regular
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerAlumnoPorcuil", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE cuil== :cuil && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerPorEstado", language = "JDOQL", value = "SELECT FROM dom.alumno.Alumno WHERE estadoDeAlumno== :estadoDeAlumno && estaBorrado== 'ACTIVO'")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarAlumno")
@Audited

@ObjectType("ALUMNO")
public class Alumno extends Persona implements Locatable,Comparable<Alumno>{

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
	
	/*
	public String validate(){
		
		if (fechaIngreso.compareTo(getFechaNacimiento()) < getFechaNacimiento().getTime()){
			return "La fecha de Naciomiento debe ser posterior";
		}
		return null;
	}*/
	
	
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
	    	    
	    /*
		TODO agregar domicilioPisoTutor
		TODO agregar domicilioDepartamentoTutor
		TODO agregar domicilioProvinciaTutor
		*/
	    
	    parametros.put("domicilioCiudadTutor", this.getTutor().getLocalidad().toString());
	    parametros.put("modeloNetbook",super.getNetbooks().first().getModelo().toString());
	    // TODO parametros.put("marcaNetbook",super.getNetbooks().first().getMarca());
	    parametros.put("alturaDomicilioTutor", this.getTutor().getAlturaDomicilio());
	    //TODO parametros.put("domicilioProvinciaTutor","")
	    
	    if (this.getTutor().getPiso()==null)
	    {
	    parametros.put("domicilioPisoTutor", "");
	    }
	    else
	    {
	    parametros.put("domicilioPisoTutor", this.getTutor().getPiso());
	    }
	    parametros.put("domicilioDepartamentoTutor",this.getTutor().getLocalidad().getDepartamento().toString());
	    parametros.put("numeroSerieNetbook",this.getNetbooks().first().getNumeroDeSerie());
	    parametros.put("Instituto",this.getEstablecimiento().getNombre());
	    parametros.put("ciudadDeEstablecimiento", this.getEstablecimiento().getLocalidad().toString());
	    parametros.put("nombreAlumno", super.getApellido()+" "+super.getNombre());
	    parametros.put("dniAlumno", super.getCuil());
	    parametros.put("nombreDirector", super.getEstablecimiento().getDirectivo().toString());
	    parametros.put("dniDirector", super.getEstablecimiento().getDirectivo().getCuil());
	    parametros.put("nombreAlumno", super.getApellido()+" "+super.getNombre());
	    parametros.put("distritoEscolar", super.getEstablecimiento().getDistritoEscolar());
	    parametros.put("ciudadDeEstablecimiento", super.getEstablecimiento().getLocalidad().getLocalidad());
	    parametros.put("domicilioEstablecimiento", super.getEstablecimiento().getDireccion());
	    parametros.put("nombreTutor", this.getTutor().getApellido()+" "+this.getTutor().getNombre());
	    parametros.put("dniTutor", this.getTutor().getCuil());	    
	    parametros.put("domicilioTutor", this.getTutor().getDomicilio());
	    parametros.put("cursoAlumno", this.getCursos().first().getAnio());
	    
	    Localidad localidadEstablecimiento = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",this.getEstablecimiento().getLocalidad().getCodigoPostal()));
	    Departamento departamentoEstablecimiento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidadEstablecimiento.getDepartamento().getNombreDepartamento()));
	    parametros.put("provinciaDeEstablecimiento", departamentoEstablecimiento.getProvincia().getNombreProvincia());
	    
	    Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",this.getTutor().getLocalidad().getCodigoPostal()));
	    Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidad.getDepartamento().getNombreDepartamento()));
	    
	    parametros.put("domicilioDepartamentoTutor", departamento.getNombreDepartamento());
	    parametros.put("turnoCursoAlumno", this.cursos.first().getTurno().toString());
	    
	    parametros.put("divisionCursoAlumno", this.cursos.first().getDivision());
	    
	    return GeneradorReporte.generarReporte("reportes/contratoCesion.jrxml",parametros , "contratoCesion");
	}
	
	@Named("Imprimir Constrato Conmodato")
	public Blob imprimir() throws JRException, FileNotFoundException  
    {
    	HashMap<String,Object> parametros = new HashMap<String, Object>();
    	
    	 	Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",this.getCuil()));
	    	Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",alumno.getEstablecimiento().getNombre()));
	    	parametros.put("distritoEscolar", establecimiento.getDistritoEscolar());
	    	parametros.put("ciudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());	    	
	    	parametros.put("nombreEstablecimiento", establecimiento.getNombre());
	    	parametros.put("domicilioEstablecimiento", establecimiento.getDireccion());
	    	parametros.put("Establecimiento", establecimiento.getNombre());
	    	parametros.put("cudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());
	    	
	    	if (this.getTutor()==null)
		    {
		         parametros.put("nombreTutor", "");
		       //PARAMETROS DEL TUTOR	    	
			    	parametros.put("dniTutor","");
			    	parametros.put("domicilio","");
			    	parametros.put("domicilioDpto","");
			    	parametros.put("ciudadTutor","");
			    	parametros.put("ProvinciaTutor","");
			
		    }
		    else
		    {
		          parametros.put("nombreTutor", this.getTutor().getApellido()+" "+this.getTutor().getNombre());
		          //PARAMETROS DEL TUTOR	    	
	    	      parametros.put("dniTutor",this.getTutor().getCuil());
	    	      parametros.put("domicilio",this.getTutor().getDomicilio());
	    	      parametros.put("domicilioDpto",this.getTutor().getPiso());
	    	      parametros.put("ciudadTutor",this.getTutor().getLocalidad().toString());
	    	      parametros.put("ProvinciaTutor","");
	  			    	
		    }
	    	
	    	Localidad localidadEstablecimiento = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",this.getEstablecimiento().getLocalidad().getCodigoPostal()));
		    Departamento departamentoEstablecimiento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidadEstablecimiento.getDepartamento().getNombreDepartamento()));
		  
		    parametros.put("provinciaEstablecimiento", departamentoEstablecimiento.getProvincia().getNombreProvincia());
	    		    		
	    	Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
	    	Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
	    	
	    	if (super.getEstablecimiento().getDirectivo()==null)
		    {
		         parametros.put("nombreDirector", "");
		      	 parametros.put("dniDirector", "");
		    }
		    else
		    {
		    	parametros.put("nombreDirector", super.getEstablecimiento().getDirectivo().toString());
		     	parametros.put("dniDirector", super.getEstablecimiento().getDirectivo().getCuil());
		    }
	    		   
	    	parametros.put("direccionEstablecimiento", establecimiento.getDireccion());
	    	parametros.put("provincia", departamento.getProvincia().getNombreProvincia());
	    	parametros.put("DniAlumno", String(super.getCuil()));
	    	parametros.put("caracterTutor", super.getApellido()+" "+super.getNombre());
	    		    	   		    	
	    	//parametros.put("dniDirector", directivo.getCuil()+"");
	    	//consulta establecimiento

	    	parametros.put("nombreEstablecimiento",establecimiento.getNombre());
	    	parametros.put("Curso", this.getCursos().first().getAnio());
	    	parametros.put("Turno", this.cursos.first().getTurno().toString());
	    	parametros.put("division", this.cursos.first().getDivision());
        		
	    	//Netbook
	    	if (super.getNetbooks()==null)
		    {
	    		parametros.put("modeloNetbook","");
		    	  parametros.put("numeroSerieNetbook","");
		    }
		    else
		    {
		    	parametros.put("modeloNetbook",super.getNetbooks().first().getModelo().toString());
		    	parametros.put("numeroSerieNetbook",this.getNetbooks().first().getNumeroDeSerie());
		    }
	    	  
	    	
    	return servicio.reporte.GeneradorReporte.generarReporte("reportes/contratoComodato.jrxml", parametros, "ContratoComodato");
    }
	
	private Object Long(Long cuil) {
		// TODO Auto-generated method stub
		return null;
	}
	private Object String(Long cuil) {
		// TODO Auto-generated method stub
		return null;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;

	@Override
	public int compareTo(Alumno alumno) {

		return ObjectContracts.compare(this, alumno, "cuil");
	}
}