package repo.Establecimiento;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;


import dom.Establecimiento.Establecimiento;

@Named("Administrar Establecimientos")
public class RepositorioEstablecimiento extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Establecimiento";
    }
    
	/**
	 * muestra los curso 
	 * @return lista de los Curso
	 */
	public Establecimiento ingresarEstablecimiento (
			@Named("direccion")final String direccion,
			@Named("nombre")final String nombre,
			@Named("Telefono")final String telefono,
			@Named("email")final String email
			)
	{
		
		final Establecimiento establecimiento = container.newTransientInstance(Establecimiento.class);
		establecimiento.setDireccion(direccion); 
		establecimiento.setNombre(nombre);
		establecimiento.setTelefono(telefono);
		establecimiento.setEmail(email);
		container.persistIfNotAlready(establecimiento);
		
		return establecimiento;
		
	}
	
	@Hidden
    public List<Establecimiento> autoComplete(String searchPhrase) {        
    	return allMatches(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",searchPhrase));
    }
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
