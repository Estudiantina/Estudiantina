package repo.Localidad;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.Localidad.Departamento;
import dom.Localidad.Localidad;
import dom.Localidad.Provincia;

@Named("Administrar Localidad")
public class RepositorioLocalidad extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Localidad";
         }
	
	/**
	 * Alta de Nueva Localidad En el sistema 
	 * @return Localidad ingresada
	 */
	@Named("Nueva Localidad")
	public Localidad ingresoLocalidad(
			@Named("Codigo Postal")final String codigoPostal,
			@Named("Localidad")final String localidad,
			@Named("Provincia")final Departamento departamento
			){
		
		final Localidad localidades = container.newTransientInstance(Localidad.class);
		localidades.setCodigoPostal(codigoPostal);
		localidades.setDepartamento(departamento);
		localidades.setLocalidad(localidad);
		
		container.persistIfNotAlready(localidades);
		
		return localidades;
	}
	
	public List<Localidad> listaLocalidades(){
		return allMatches(QueryDefault.create(Localidad.class, "traerTodo"));
		
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
	


