package dom.Directivo;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Persona.RepositorioPersona;
import dom.Persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.Directivo.Directivo WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTodoDirectivo", language = "JDOQL", value = "SELECT FROM dom.Directivo.Directivo"),
	@javax.jdo.annotations.Query(name = "traerDirectivoPorEstablecimiento", language = "JDOQL", value = "SELECT FROM dom.Directivo.Directivo WHERE establecimiento == :institucion")
})
@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@ObjectType("DIRECTIVO")
public class Directivo extends Persona {

	
	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * @return devuelve como titulo el cuil del Directivo
	 */
	public String title()
	{
		
		return this.getNombre().toString()+" "+this.getApellido().toString();
	}
	
}
