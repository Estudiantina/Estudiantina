package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import repo.cargo.RepositorioCargo;
import repo.pais.RepoPaises;
import dom.cargo.Cargo;
import dom.pais.Pais;

public class CargosFixture extends FixtureScript {


	public CargosFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		// TODO Auto-generated method stub
		if(estaVacio(executionContext))
		{
			create("Profesor de Matematica", executionContext);
		}
	}



	private Cargo create(final String nombre,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoCargo.nuevoCargo(nombre));
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoCargo.verTodosLosCargos().size()==0);
	}
	@Inject
	private RepositorioCargo repoCargo;
}
