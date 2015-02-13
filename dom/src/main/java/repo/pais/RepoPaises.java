package repo.pais;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import dom.pais.Pais;

@Named("Paises")
public class RepoPaises extends AbstractFactoryAndRepository{

	@Hidden
	public List<Pais> autoCompletarPais(String pais)
	{
		return allMatches(QueryDefault.create(Pais.class, "traerPorNombre","nombre",pais));
	}
	
	public List<Pais> mostrarTodosLosPaises()
	{
		return allMatches(QueryDefault.create(Pais.class, "traerTodosLosPaises"));
	}
	
	public Pais nuevoPais(@Named("nombre del pais")String nombre)
	{
		final Pais pais = container.newTransientInstance(Pais.class);
		pais.setNombrePais(nombre);
		container.persistIfNotAlready(pais);
		return pais;
	}
	
	@Inject
    DomainObjectContainer container;
}
