package dom.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;


import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.value.Password;
import org.bouncycastle.util.encoders.Hex;

import dom.Persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "buscarPorUsuario", language = "JDOQL", value = "SELECT FROM dom.Login.Login WHERE usuario== :usuario"),
	@javax.jdo.annotations.Query(name = "todasLasCuentas", language = "JDOQL", value = "SELECT FROM dom.Login.Login")})
@ObjectType("Login")
public class Login {
private String usuario;
private String password;
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
public void setPassword(String password){
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	try {
		md.update(password.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	byte[] digest = md.digest();
	
	this.password = new String(Hex.encode(digest));	
}

}
