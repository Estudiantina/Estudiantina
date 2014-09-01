package dom.Alumno;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;



import repo.Persona.RepositorioPersona;
import dom.Curso.Curso;
import dom.Persona.Persona;
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
//TODO hacer consulta traerPorcuil 
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerAlumnoPorcuil", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarAlumno")
@Audited



@ObjectType("ALUMNO")
public class Alumno extends Persona implements Locatable{
	
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
	    	
	/*@javax.jdo.annotations.Persistent(mappedBy="anoYdivision")*/
	@Persistent
    private List<Curso> cursos = new ArrayList<Curso>();;
    
	   
	

    @Render(Type.EAGERLY)
    @Join
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	
	public Alumno agregarCurso(@Named("curso") Curso curso)
	{
		List<Curso> cursos = this.getCursos();
		cursos.add(curso);
		this.setCursos(cursos);
		return this;
	}

	private Date fechaIngreso;
	
	
	private EstadoDeAlumno estadoDeAlumno;
	private Nacionalidad nacionalidad;
	@Persistent
	private Location location;
    @Optional
    @MemberOrder(name="Datos De Localizacion", sequence = "10")
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
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