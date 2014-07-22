package dom.login;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.value.Password;

import dom.Persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("Login")
public class Login {
private String usuario;
private Password password;
private Persona persona;
@Unique
@javax.jdo.annotations.Column(allowsNull="False")
public Persona getPersona() {
	return persona;
}
public void setPersona(Persona persona) {
	this.persona = persona;
}
@javax.jdo.annotations.Column(allowsNull="False")
@Title
public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}
@javax.jdo.annotations.Column(allowsNull="False")
public Password getPassword() {
	return password;
}

public void setPassword(Password password) {
	this.password = password;
}

}
