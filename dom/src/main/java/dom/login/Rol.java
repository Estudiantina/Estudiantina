package dom.login;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;

import repo.Netbook.RepositorioNetbook;
import repo.Persona.RepositorioPersona;
import repo.login.repologin;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@Query(name="TraerRoles", language="JDOQL", value = "SELECT FROM dom.Login.Rol")

@AutoComplete(repository = repologin.class, action = "autoCompletarRol")
@ObjectType("Rol")


public class Rol {
private String rol;

@javax.jdo.annotations.Column(allowsNull="False")
@Title
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}
    @javax.jdo.annotations.Column(allowsNull="true")
	private List<Permisos> listaPermiso = new ArrayList<Permisos>();

	public List<Permisos> getLPermiso() {
		return listaPermiso;
	}

	public void setListaPermiso(final List<Permisos> listaPermiso) {
		this.listaPermiso = listaPermiso;
	}
	
	
	public Rol aniadirPermiso(String permiso)
	{
		
		final Permisos mipermiso = container.newTransientInstance(Permisos.class);
		mipermiso.setPermiso(permiso);
		container.persistIfNotAlready(mipermiso);
		this.listaPermiso.add(mipermiso);
		return this;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}
