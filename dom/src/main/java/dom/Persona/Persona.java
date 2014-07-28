package dom.Persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Unique;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;

import repo.Persona.RepositorioPersona;
import dom.Establecimiento.Establecimiento;
import dom.Netbook.Netbook;

/**
 * Clase que representa la entidad Persona en el nuestro sistema.
 * 
 */

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPersonas", language = "JDOQL", value = "SELECT FROM dom.Persona.Persona"),
	@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.Persona.Persona WHERE cuil== :cuil")	
})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@Bookmarkable
@ObjectType("Persona")
public class Persona implements IntegranteDeLaInstitucion{

	private Long cuil;
	private String nombre;
	private String apellido;
	private String telefonoCelular;
	private String telefinoFijo;
	private String email;
	private String domicilio;
	private Date fechaNacimiento;
	private List<Netbook> netbook= new ArrayList<Netbook>();
	private Establecimiento establecimiento;
	public List<Netbook> getNetbook() {
		return netbook;
	}
	public void setNetbook(List<Netbook> netbook) {
		this.netbook = netbook;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="true")
	@Optional
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	/**
	 * Identificacion del nombre del icono 
	 * que aparecera en la UI
	 * resources/icono.png
	 * @return String nombre de icono
	 */
	public String iconName() {
        
		return "alumno";
    }

	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
		
	}
	@Named("añadir netbook")
	public Persona anadirNetbook(Netbook netbook)
	{
		this.netbook.add(netbook);
		return this;
	}

	@Unique
	public Long getCuil() {
		return cuil;
	}
	public void setCuil(Long cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getTelefinoFijo() {
		return telefinoFijo;
	}
	public void setTelefinoFijo(String telefinoFijo) {
		this.telefinoFijo = telefinoFijo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;
}