package repo.cargo;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;

import dom.cargo.Cargo;


@Named("Cargo")
public class RepositorioCargo extends AbstractFactoryAndRepository{
	
	public String iconName() {
        return "cargo";
    }
	
	@Hidden
	public List<Cargo> autoCompletarCargo(String cargo)
	{
		return allMatches(QueryDefault.create(Cargo.class, "traerPorNombre","nombre",cargo));
	}
	
	public List<Cargo> verTodosLosCargos()
	{
		return allMatches(QueryDefault.create(Cargo.class, "traerTodasLosCargos"));
	}
	@Hidden
	public Cargo traerPorCargo(String cargo)
	{
		return firstMatch(QueryDefault.create(Cargo.class, "traerPorCargo","nombre",cargo));
	}
	
	public Cargo nuevoCargo(@Named("nombre del cargo")@RegEx(validation = "[A-Za-z ]+")String nombre)
	{
		final Cargo cargo = container.newTransientInstance(Cargo.class);
		cargo.setNombreDeCargo(nombre);
		container.persistIfNotAlready(cargo);
		return cargo;
	}
	
	
	
	
	@Inject
    DomainObjectContainer container;
}
