package dom.Localidad;

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
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Localidad.Localidad")})

@ObjectType("Localidad")

@AutoComplete(repository =  RepositorioCurso.class, action = "autoComplete")
@Audited

public class Localidad {
	
	private String Localidad;
	private Provincia provincia;
	
	
	public String title()
	{
		return Localidad+" "+provincia.toString();
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getLocalidad() {
		return Localidad;
	}
	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}
	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
