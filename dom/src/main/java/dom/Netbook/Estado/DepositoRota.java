package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbookEnDepositoRota")
@ObjectType("DepositoRota")
public class DepositoRota implements SituacionDeNetbook {

	@Override
	public String title() {
		
		return "Rota en Deposito";
	}

	
}
