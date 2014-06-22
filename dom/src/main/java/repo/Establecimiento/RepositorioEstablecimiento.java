package repo.Establecimiento;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;


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
			@Named("Telefono")final String telefono
			)
	{
		
		final Establecimiento establecimiento = container.newTransientInstance(Establecimiento.class);
		establecimiento.setDireccion(direccion); 
		establecimiento.setNombre(nombre);
		establecimiento.setTelefono(telefono);
		
		container.persistIfNotAlready(establecimiento);
		
		return establecimiento;
		
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
