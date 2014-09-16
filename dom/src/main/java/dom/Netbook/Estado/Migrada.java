package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbooksEstadoDeMigracion")
@ObjectType("NetbookEnEstadoMigrada")
public class Migrada implements SituacionDeNetbook {

	@Override
	public String title() {
		// TODO Apéndice de método generado automáticamente
		return "Migrada";
	}

}
