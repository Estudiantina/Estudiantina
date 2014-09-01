package dom.tutor;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.Persona.RepositorioPersona;

import dom.Persona.Persona;
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.tutor.Tutor WHERE cuil== :cuil"),
	@javax.jdo.annotations.Query(name = "traerTutorPorcuil", language = "JDOQL", value = "SELECT FROM dom.tutor.Tutor")})
@AutoComplete(repository = RepositorioPersona.class, action = "autoCompletarTutor")
@Audited
@ObjectType("Tutor")
public class Tutor extends Persona {

	/**
	 * metodo que indica el titulo en el viewer
	 * super hace referencia a la clase Persona
	 * @return devuelve como titulo el cuil del Tutor
	 */
	public String title()
	{
		return this.getCuil()+" "+this.getNombre().toString()+" "+this.getApellido().toString();
		
	}
	
	
}
