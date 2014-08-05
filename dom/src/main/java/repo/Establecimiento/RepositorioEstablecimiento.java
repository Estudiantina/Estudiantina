package repo.Establecimiento;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;
import dom.Establecimiento.Establecimiento;

@Named("Establecimientos")
public class RepositorioEstablecimiento extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Establecimiento";
    }
    
	public String iconName() {
        return "edificio";
    }
	/**
	 * muestra los curso 
	 * @return lista de los Curso
	 */
	public Establecimiento ingresarEstablecimiento (
			@Named("direccion") final String direccion,
		//	@RegEx(validation = "[A-Za-z]+")
			@Named("nombre")final String nombre,
			@RegEx(validation = "[0-9]+")
			@Named("Telefono")final String telefono,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("email")final String email,
			@RegEx(validation = "[0-9]+")
			@Named("CUE") final String cue
			)
	{
		
		final Establecimiento establecimiento = container.newTransientInstance(Establecimiento.class);
		establecimiento.setDireccion(direccion); 
		establecimiento.setNombre(nombre);
		establecimiento.setTelefono(telefono);
		establecimiento.setEmail(email);
		establecimiento.setCue(cue);
		
		container.persistIfNotAlready(establecimiento);
		
		return establecimiento;
		
	}
	/**
	 * retorna una consulta que devuelve la 
	 * lista completa de los establecimentos
	 * estudiantiles que existen en el sistema
	 * @return establecimientos
	 */
	@Named("Ver Establecimientos")
	public List<Establecimiento> listadeEstablecimientos() {
        return allMatches(QueryDefault.create(Establecimiento.class, "traerTodos"));
    }
	
	@Hidden
    public List<Establecimiento> autoComplete(String searchPhrase) {        
    	return allMatches(QueryDefault.create(Establecimiento.class, "traerlikePorNombre","nombre",searchPhrase));
    }
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
