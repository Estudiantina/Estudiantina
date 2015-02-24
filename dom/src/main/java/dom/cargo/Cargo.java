package dom.cargo;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.cargo.RepositorioCargo;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.cargo.Cargo WHERE nombreDeCargo.indexOf(:nombre) >= 0 range 0,4 "),
	@Query(name = "traerTodasLosCargos", language = "JDOQL", value = "SELECT FROM dom.cargo.Cargo ")
	})
@ObjectType("Cargo")

@AutoComplete(repository = RepositorioCargo.class, action = "autoCompletarCargo")
public class Cargo {
	
	
	public String iconName() {
        return "cargo";
    }
	
	
	private String nombreDeCargo;
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombreDeCargo() {
		return nombreDeCargo;
	}

	public void setNombreDeCargo(String nombreDeCargo) {
		this.nombreDeCargo = nombreDeCargo;
	}
	public String title()
	{
		return this.getNombreDeCargo();
		
	}	
}
