package dom.Localidad;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Localidad.RepositorioLocalidad;
import repo.Netbook.RepositorioNetbook;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
@PersistenceCapable(identityType = IdentityType.DATASTORE)

@DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.Localidad.Departamento WHERE nombreDepartamento.indexOf(:nombre) >= 0 range 0,4 "),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Localidad.Departamento")})
@ObjectType("Departamentos")
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarDepartamento")
public class Departamento {

	private String nombreDepartamento;
	private Provincia provincia;

	@Column(allowsNull="false")
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Column(allowsNull="false")
	@Unique 
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	
	public String title()
	{
		return nombreDepartamento;
		
	}
	
}
