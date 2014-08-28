package dom.Localidad;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;


import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Localidad.RepositorioLocalidad;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Query;
@PersistenceCapable(identityType = IdentityType.DATASTORE)

@DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@Queries({
	@Query(name = "traerPorCodigoPostal", language = "JDOQL", value = "SELECT FROM dom.Localidad.Localidad WHERE codigoPostal == :codigo"),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Localidad.Localidad")
	})
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarLocalidad")
@ObjectType("Localidades")
public class Localidad {
	private String codigoPostal;
	private String localidad;
	private Departamento departamento;
	
	public String iconName() {
		return "Localidad";
	   }
	
	@Column(allowsNull="false")
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String title()
	{
		return this.codigoPostal+" "+this.localidad;
	}

    @Unique
    @Column(allowsNull="false")
	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Column(allowsNull="false")
	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	
	

}
