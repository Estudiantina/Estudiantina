package dom.Alumno;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;

import repo.Alumno.RepositorioAlumno;
import dom.Establecimiento.Establecimiento;
import dom.Persona.Persona;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno WHERE cuil == :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoAlumno", language = "JDOQL", value = "SELECT FROM dom.Alumno.Alumno")})
@AutoComplete(repository = RepositorioAlumno.class, action = "autoComplete")
@Audited



@ObjectType("ALUMNO")
public class Alumno extends Persona{
	
	/**
	 * Identificacion del nombre del icono 
	 * que aparecera en la UI
	 * resources/icono.png
	 * @return String nombre de icono
	 */
	public String iconName() {
        return "alumno";
    }

	private Date fechaIngreso;
	private List<Establecimiento> establecimiento = new ArrayList<Establecimiento>();
	
	private EstadoDeAlumno estadoDeAlumno;
	private Nacionalidad nacionalidad;
	
	
	
	@Title(sequence="9")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	@Title(sequence="10")
	@javax.jdo.annotations.Column(allowsNull="false")	
	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	
	
	@Title(sequence="11")
	@javax.jdo.annotations.Column(allowsNull="false")
	public List<Establecimiento> getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(List<Establecimiento> establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	
	
	
	@Title(sequence="12")
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