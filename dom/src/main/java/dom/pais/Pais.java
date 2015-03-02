package dom.pais;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.RegEx;

import repo.pais.RepoPaises;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.pais.Pais WHERE nombrePais.indexOf(:pais) >= 0 range 0,4 "),
	@Query(name = "traerPais", language = "JDOQL", value = "SELECT FROM dom.pais.Pais WHERE nombrePais == :pais"),
	@Query(name = "traerTodosLosPaises", language = "JDOQL", value = "SELECT FROM dom.pais.Pais ")
	})
@ObjectType("Pais")
@javax.jdo.annotations.Unique(
            name="nombrePais", 
            members={"nombrePais"})
@AutoComplete(repository = RepoPaises.class, action = "autoCompletarPais")

public class Pais {

	private String nombrePais;
	@javax.jdo.annotations.Column(allowsNull="false")
	@Named("Nombre Del Pais")
	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(@RegEx(validation = "[A-Za-z ]+")String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	public String iconName()
	{
		return "banderas/"+nombrePais;
	}
	public String title()
	{
		return this.nombrePais;
	}

}
