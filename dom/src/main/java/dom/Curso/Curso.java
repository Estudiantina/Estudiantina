package dom.Curso;


import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Render;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render.Type;

import dom.Alumno.Alumno;
import dom.Establecimiento.Establecimiento;
import dom.Netbook.Netbook;
import dom.Persona.Persona;


import repo.Curso.RepositorioCurso;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Curso.Curso"),
	@javax.jdo.annotations.Query(name = "traerCursoPorlikeAnio", language = "JDOQL", value = "SELECT FROM dom.Curso.Curso WHERE anoYdivision.indexOf(:anoYdivision) >=0 range 0, 4")
	})
@ObjectType("CURSO")
@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited
public class Curso {
	
	private String anoYdivision;
	//TODO separar año y division
	private int cicloLectivo;
	private Turno turno;
	private Establecimiento establecimiento;
	private List<Alumno> listaAlumnos = new ArrayList<Alumno>();;
	
	@Persistent
	@Render(Type.EAGERLY)
	@Join
	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
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
	
}
