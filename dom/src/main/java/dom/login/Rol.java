package dom.login;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@Query(name="TraerRoles", language="JDOQL", value = "SELECT FROM dom.Login.Rol")

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

	private List<Permisos> listaPermiso = new ArrayList<Permisos>();

	public List<Permisos> getListaPermiso() {
		return listaPermiso;
	}

	public void setListaPermiso(final List<Permisos> listaPermiso) {
		this.listaPermiso = listaPermiso;
	}



}
