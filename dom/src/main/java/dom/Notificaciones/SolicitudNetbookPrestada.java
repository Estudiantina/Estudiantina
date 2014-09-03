package dom.Notificaciones;


import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;


@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudNetbookPrestada")
public class SolicitudNetbookPrestada extends Notificaciones {

	public String title()
	{
		return "Solicitar Netbook Prestada -"+this.getPersona().toString();
	}
   
	
   
	
	
	
	
}
