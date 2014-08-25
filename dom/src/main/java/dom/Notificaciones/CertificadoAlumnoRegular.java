package dom.Notificaciones;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.ObjectType;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("PedidoCertificados")
public class CertificadoAlumnoRegular extends Notificaciones{

	public String title()
	{
		return this.getPersona().toString()+" "+this.getPersona().toString();
	}
}
