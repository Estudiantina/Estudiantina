package dom.Localidad;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Localidad.RepositorioLocalidad;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@ObjectType("Provincias")
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.Localidad.Provincia WHERE nombreProvincia.indexOf(:nombre) >= 0 range 0, 4"),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Localidad.Provincia")})
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarProvincia")
public class Provincia {
	
	private String nombreProvincia;
	
	@Unique
	@Column(allowsNull="false")
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}





	public String title()
	{
		return this.nombreProvincia;
	}

}
