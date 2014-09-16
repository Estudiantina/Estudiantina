package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbookEnEstadoAsignado")
@ObjectType("EstadoAsignada")
public class Asignada implements SituacionDeNetbook {

	@Override
	public String title() {
		return "Asignada";
	}

}
