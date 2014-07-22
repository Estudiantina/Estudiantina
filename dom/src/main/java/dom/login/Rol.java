package dom.login;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("Rol")
public class Rol {
private String rol;
private Permisos permisos;
@javax.jdo.annotations.Column(allowsNull="False")
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}
@javax.jdo.annotations.Column(allowsNull="False")
public Permisos getPermisos() {
	return permisos;
}
public void setPermisos(Permisos permisos) {
	this.permisos = permisos;
}

}
