package dom.Localidad;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.ObjectType;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@ObjectType("Provincias")
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
