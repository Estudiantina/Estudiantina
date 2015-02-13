package dom.pais;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.localidad.RepositorioLocalidad;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.pais.Pais WHERE nombreDelPais.indexOf(:pais) >= 0 range 0,4 ")
	})
@ObjectType("Pais")
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarPais")

public class Pais {

	private String nombrePais;
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	public String title()
	{
		return this.nombrePais;
	}

	
	
}
