package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.localidad.RepositorioLocalidad;
import dom.localidad.Departamento;


public class DepartamentosFixture extends FixtureScript {

	public DepartamentosFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	

	@Override
	protected void execute(ExecutionContext executionContext) {
        if(estaVacio(executionContext))
        {
		//DEPARTAMENTOS DE RIO NEGRO
		
		//DEPARTAMENTOS DE NEUQUEN
		create("Confluencia", "Neuquen", executionContext);
		
	    //DEPARTAMENTOS DE RIO NEGRO
		create("Adolfo Alsina", "Rio Negro", executionContext);
		create("Avellaneda", "Rio Negro", executionContext);
		create("Bariloche", "Rio Negro", executionContext);
		create("Conesa", "Rio Negro", executionContext);
		create("El Cuy", "Rio Negro", executionContext);
		create("General Roca", "Rio Negro", executionContext);
		create("Nueve de Julio", "Rio Negro", executionContext);
		create("Ñorquincó", "Rio Negro", executionContext);
		create("Pichi Mahuida", "Rio Negro", executionContext);
		create("Pilcaniyeu", "Rio Negro", executionContext);
		create("San Antonio", "Rio Negro", executionContext);
		create("Valcheta", "Rio Negro", executionContext);
		create("Veinticinco de Mayo", "Rio Negro", executionContext);
        }
	}
	
	private Departamento create(final String nombreDepartamento,final String provincia,
			ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLocalidad.ingresoDepartamento(nombreDepartamento, repoLocalidad.autoCompletarProvincia(provincia).get(0)));
	}
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLocalidad.listaDepartamentos().size()==0);
	}
	
	@Inject
	private RepositorioLocalidad repoLocalidad;
}
