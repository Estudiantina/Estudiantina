package dom.login;

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@ObjectType("Permisos")
public class Permisos {
	private  String permiso;
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	
	
}
