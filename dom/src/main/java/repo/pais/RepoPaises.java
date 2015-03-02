package repo.pais;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;

import dom.pais.Pais;

@Named("Paises")
public class RepoPaises extends AbstractFactoryAndRepository{
	
	public String iconName() {
		return "pais";
	   }

	@Hidden
	public List<Pais> autoCompletarPais(String pais)
	{
		return allMatches(QueryDefault.create(Pais.class, "traerPais","pais",pais));
	}
	
	public List<Pais> mostrarTodosLosPaises()
	{
		return allMatches(QueryDefault.create(Pais.class, "traerTodosLosPaises"));
	}
	
	public Pais nuevoPais(@Named("nombre del pais")@RegEx(validation = "[A-Za-z ]+")String nombre)
	{
		final Pais pais = container.newTransientInstance(Pais.class);
		pais.setNombrePais(nombre);
		container.persistIfNotAlready(pais);
		return pais;
	}
	@Hidden
	public Pais obtenerPais (String nombre)
	{
		return firstMatch(QueryDefault.create(Pais.class, "traerTodosLosPaises","pais",nombre));
	}
	
	@Inject
    DomainObjectContainer container;
}
