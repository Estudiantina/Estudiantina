package fixture.login;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import repo.localidad.RepositorioLocalidad;
import repo.login.repologin;
import dom.localidad.Localidad;
import dom.login.Permisos;

public class LocalidadesFixture extends FixtureScript{

	public LocalidadesFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {

		if(estaVacio(executionContext))
		{
			create("8324", "Cipolletti", "General Roca", executionContext);
			create("8300", "Neuquen", "Confluencia", executionContext);
		}
				
	}

	
	private Localidad create(String codigoPostal, String localidad, String departamento,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoLocalidad.ingresoLocalidad(codigoPostal,localidad ,repoLocalidad.autoCompletarDepartamento(departamento).get(0)));
	}

	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLocalidad.listaLocalidades().size()==0);
	}
	
	@javax.inject.Inject
	private RepositorioLocalidad repoLocalidad;
}
