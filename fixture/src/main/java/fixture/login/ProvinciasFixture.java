package fixture.login;


import javax.inject.Inject;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.localidad.RepositorioLocalidad;
import dom.localidad.Provincia;

public class ProvinciasFixture extends FixtureScript {

	public ProvinciasFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
        if(estaVacio(executionContext))
        {
		create("Neuquen", executionContext);
		create("Rio Negro", executionContext);
		create("Buenos Aires", executionContext);
		create("Santa Fe", executionContext);
		create("Chaco", executionContext);
		create("Formosa", executionContext);
		create("Cordoba", executionContext);
		create("Misiones", executionContext);
		create("Entre Rios", executionContext);
		create("La Rioja", executionContext);
		create("Jujuy", executionContext);
		create("La Pampa", executionContext);
		create("Chubut", executionContext);
		create("Santa Cruz", executionContext);
		create("Tierra Del Fuego", executionContext);
        }
	}
	
	private Provincia create(final String nombreProvincia,ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoLocalidad.ingresoProvincia(nombreProvincia, null));
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLocalidad.listaProvincias().size()==0);
	}
	
	@Inject
	RepositorioLocalidad repoLocalidad;
}
