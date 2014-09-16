package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbooksEstadoPrestada")
@ObjectType("NetbookEnEstadoPrestada")
public class Prestada implements SituacionDeNetbook{

	@Override
	public String title() {
		return "Prestada";
	}

}
