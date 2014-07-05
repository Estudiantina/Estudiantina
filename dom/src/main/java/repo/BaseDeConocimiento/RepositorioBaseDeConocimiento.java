package repo.BaseDeConocimiento;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

import dom.BaseDeConocimiento.BaseDeConocimiento;
import dom.Persona.Persona;



@Named("Base de Conocimiento")
public class RepositorioBaseDeConocimiento  extends AbstractFactoryAndRepository {

		
		
	public BaseDeConocimiento ingresarNuevoTema (
			@Named("titulo")String titulo,
			@Named("imagen")String imagen ,
			@Named("descripcion")String descripcion,
			@Named("paginas web")String url,
			//@Named ("categoria")List<String>categorias,
			@Named("fecha ingreso")Date fecha
			
			)
	{
	
	final BaseDeConocimiento baseDeConocimiento = container.newTransientInstance(BaseDeConocimiento.class);
	
	baseDeConocimiento.setTitulo(titulo);
	baseDeConocimiento.setImagen(imagen);
	baseDeConocimiento.setDescripcion(descripcion);
	baseDeConocimiento.setUrl(url);
	//baseDeConocimiento.setCategorias(categorias);
	baseDeConocimiento.setFecha(fecha);

		
	container.persistIfNotAlready(baseDeConocimiento);
	
	return baseDeConocimiento;
	
	}
	

    /**
	 * trae toda la  lista de conocimiento de la base de datos
	 * @return lista de Base de Conocimiento
	 */
    public List<BaseDeConocimiento> listarBaseDeConocimiento() {
        return allMatches(QueryDefault.create(BaseDeConocimiento.class, "traerTodoLaBase"));
    }
	

	@javax.inject.Inject 
    DomainObjectContainer container;

}
