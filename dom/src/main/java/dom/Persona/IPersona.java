package dom.Persona;

import java.util.Date;

import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;

public interface IPersona {
	
	
	@MaxLength(12)
	@javax.jdo.annotations.Column(allowsNull="false")
	@Title(sequence="1")
    @MemberOrder(sequence="1")
	public Long getCuil();
	public void setCuil(Long cuil);
	
	@Title(sequence="2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre();
	public void setNombre(String nombre);
	
	@Title(sequence="3")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido();
	
	public void setApellido(String apellido);
	
	@Title(sequence="6")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefonoCelular();
	public void setTelefonoCelular(String telefonoCelular);
	
	@Title(sequence="7")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefinoFijo();
	public void setTelefinoFijo(String telefinoFijo);
	
	@Title(sequence="5")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail();
	public void setEmail(String email);
	
	@Title(sequence="4")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDomicilio();
	public void setDomicilio(String domicilio);
	
	@Title(sequence="8")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaNacimiento();
	public void setFechaNacimiento(Date fechaNacimiento);
	

}