package repo.BaseDeConocimiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;




import com.sun.activation.viewers.ImageViewer;

import dom.BaseDeConocimiento.BaseDeConocimiento;


@Named("BASE DE CONOCIMIENTO")
public class RepositorioBaseDeConocimiento  extends AbstractFactoryAndRepository {

	public String getId() {
        return "BaseDeConocimiento";
    }
		
		
	public BaseDeConocimiento ingresarNuevoTema (
			@Named("titulo")final String titulo,
			@Named("imagen")final String imagen ,
			@Named("descripcion")final String descripcion,
			@Named("paginas web")final String url,
			//@Named ("categoria")final List<String> categorias,
			@Named("fecha ingreso")final Date fecha,
			@Named("imagens") ImageViewer imagenes
			
			)
	{
	
	final BaseDeConocimiento baseDeConocimiento = container.newTransientInstance(BaseDeConocimiento.class);
	
	baseDeConocimiento.setTitulo(titulo);
	baseDeConocimiento.setImagen(imagen);
	baseDeConocimiento.setDescripcion(descripcion);
	baseDeConocimiento.setUrl(url);
	//baseDeConocimiento.setCategorias(categorias);
	baseDeConocimiento.setFecha(fecha);
	baseDeConocimiento.setImagenes(imagenes);

		
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
