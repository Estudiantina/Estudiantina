package dom.notificaciones;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.ObjectType;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudTramiteDeMigracion")
public class SolicitudTramiteDeMigracion extends SolicitudNetbookPrestada{

	public String title()
	{
		return "Migracion De Alumno -"+this.getPersona().toString();
	}
}
