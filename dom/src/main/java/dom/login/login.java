package dom.login;

import org.apache.isis.applib.value.Password;

import dom.Persona.Persona;

public class login {
private String usuario;
private Password password;
private Persona persona;

public Persona getPersona() {
	return persona;
}

public void setPersona(Persona persona) {
	this.persona = persona;
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public Password getPassword() {
	return password;
}

public void setPassword(Password password) {
	this.password = password;
}

}
