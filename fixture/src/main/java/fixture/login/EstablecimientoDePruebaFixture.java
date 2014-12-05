package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import repo.establecimiento.RepositorioEstablecimiento;
import repo.localidad.RepositorioLocalidad;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;

public class EstablecimientoDePruebaFixture extends FixtureScript {

	@Override
	protected void execute(ExecutionContext executionContext) {

		if(estaVacio(executionContext))
		{
		create("ifes", "Santa Fe 332", "4484857", "ifes@ifes.com.ar", "ifes", "333", "Neuquen", executionContext);
		}
	}

	
	private Establecimiento create(String nombre, String direccion,String telefono,String email,String distritoEscolar,String cue, String localidad,
			ExecutionContext executionContext) {
		return executionContext.add(this,
				repositorioEstablecimiento.ingresarEstablecimiento(nombre, direccion, telefono, email, distritoEscolar, cue, repoLocalidad.autoCompletarLocalidad(localidad).get(0)));
	}
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repositorioEstablecimiento.listadeEstablecimientos().size()==0);
	}
	@Inject
	RepositorioLocalidad repoLocalidad;
	@Inject
	RepositorioEstablecimiento repositorioEstablecimiento;
}
