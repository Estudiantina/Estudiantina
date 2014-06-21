package dom.Establecimiento;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Alumno.RepositorioAlumno;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@AutoComplete(repository = RepositorioAlumno.class, action = "autoComplete")
@Audited

@ObjectType("Establecimiento")
public class Establecimiento {
	
	private String nombre;
	private String direccion;
	private String telefono;
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
