package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbooksEstadoDesasignada")
@ObjectType("NetbookEnEstadoDesasignada")
public class Desasignada implements SituacionDeNetbook {

	@Override
	public String title() {
		return "Desasignada";
	}

}
