package dom.Curso;

import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;

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
		
	private String ano;
	private Date cicloLectivo;
	private String division;
	private Turno turno;
		
	@Title(sequence="1")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getAno() {
		return ano;
	}
	public void setAno(final String ano) {
		this.ano = ano;
	}
	
	
	
	@Title(sequence="2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getCicloLectivo() {
		return cicloLectivo;
	}
	public void setCicloLectivo(final Date cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}
	
	
	
	@Title(sequence="3")
	  @MemberOrder(sequence="1")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDivision() {
		return division;
	}
	public void setDivision(final String division) {
		this.division = division;
	}
	
	
	
	@Title(sequence="4")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Turno getTurno() {
		return turno;
	}
	
	public void setTurno(final Turno turno) {
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
