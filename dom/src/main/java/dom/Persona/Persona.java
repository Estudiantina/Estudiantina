package dom.Persona;

import java.util.Date;

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.DomainObjectContainer;

/**
 * Clase que representa la entidad Persona en el nuestro sistema.
 * 
 */

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)

public abstract	class Persona implements IPersona{

	private Long cuil;
	private String nombre;
	private String apellido;
	private String telefonoCelular;
	private String telefinoFijo;
	private String email;
	private String domicilio;
	private Date fechaNacimiento;
	
	
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