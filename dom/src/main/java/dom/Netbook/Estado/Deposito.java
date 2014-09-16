package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbooksEstadoDeDeposito")
@ObjectType("NetbookEnEstadoDeDeposito")
public class Deposito implements SituacionDeNetbook {

	@Override
	public String title() {
		return "Deposito";
	}

}
