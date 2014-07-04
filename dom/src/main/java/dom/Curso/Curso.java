package dom.Curso;


import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;

import org.apache.isis.applib.annotation.ObjectType;


import repo.Curso.RepositorioCurso;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column = "version")

@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)

@ObjectType("CURSO")

@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited


public class Curso {
	
	private String anoYdivision;
	//TODO separar año y division
	private int cicloLectivo;
	private Turno turno;
	
	
	public String iconName() {
        return "curso";
    }
	
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
		return anoYdivision+" "+turno.toString();
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
