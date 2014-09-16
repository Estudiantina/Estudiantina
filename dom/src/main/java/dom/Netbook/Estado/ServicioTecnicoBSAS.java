package dom.Netbook.Estado;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;

@PersistenceCapable(identityType = IdentityType.DATASTORE,table="NetbookEnServicioTecnicoBSAS")
@ObjectType("ServicioTecnicoBSAS")
public class ServicioTecnicoBSAS implements SituacionDeNetbook {

	@Override
	public String title() {
		return "en Servicio Tecnico BS.AS";
	}

}
