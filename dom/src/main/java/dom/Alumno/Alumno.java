package dom.Alumno;


import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;



import repo.Persona.RepositorioPersona;
import dom.Persona.Persona;
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
//TODO hacer consulta traerPorcuil 
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited



@ObjectType("ALUMNO")
public class Alumno extends Persona{
	
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
	
	
	
	
	
	
	
	
	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public EstadoDeAlumno getEstadoDeAlumno() {
		return estadoDeAlumno;
	}
	public void setEstadoDeAlumno(EstadoDeAlumno estadoDeAlumno) {
		this.estadoDeAlumno = estadoDeAlumno;
	}

	
	
	
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;



	
}