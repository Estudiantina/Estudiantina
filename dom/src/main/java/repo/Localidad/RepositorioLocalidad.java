package repo.Localidad;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.Localidad.Localidad;
import dom.Localidad.Provincia;

@Named("Administrar Localidad")
public class RepositorioLocalidad extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Localidad";
         }
	
	/**
	 * ingresar las localidades 
	 * @return lista de las localidades
	 */
	public Localidad ingresoLocalidad(
			@Named("Provincia")final Provincia provincia,
			@Named("Localidad")final String localidad
			){
		
		final Localidad localidades = container.newTransientInstance(Localidad.class);
				localidades.setProvincia(provincia);
		localidades.setLocalidad(localidad);
		
		container.persistIfNotAlready(localidades);
		
		return localidades;
	}
	
	public List<Localidad> listaLocalida(){
		return allMatches(QueryDefault.create(Localidad.class, "traerTodo"));
		
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
	


