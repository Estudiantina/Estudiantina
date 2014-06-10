package dom.Persona;

import java.util.Date;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;

public interface IPersona {
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@Title(sequence="1")
    @MemberOrder(sequence="1")
	public Long getCuil();
	public void setCuil(Long cuil);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre();
	public void setNombre(String nombre);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido();
	
	public void setApellido(String apellido);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefonoCelular();
	
	public void setTelefonoCelular(String telefonoCelular);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefinoFijo();
	public void setTelefinoFijo(String telefinoFijo);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail();
	public void setEmail(String email);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDomicilio();
	public void setDomicilio(String domicilio);
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaNacimiento();
	
	public void setFechaNacimiento(Date fechaNacimiento);
	

}