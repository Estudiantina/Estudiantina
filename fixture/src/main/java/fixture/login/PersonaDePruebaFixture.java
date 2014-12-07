package fixture.login;

import java.util.Date;

import javax.inject.Inject;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.establecimiento.RepositorioEstablecimiento;
import repo.localidad.RepositorioLocalidad;
import repo.persona.RepositorioPersona;
import dom.establecimiento.Establecimiento;
import dom.localidad.Localidad;
import dom.persona.Sexo;
import dom.tecnico.Tecnico;

public class PersonaDePruebaFixture extends FixtureScript {

	public PersonaDePruebaFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		if (estaVacio(executionContext))
		{
			Date fechaNacimiento = new Date();
			Long cuil = (long) 3333;
			create(repositorioEstablecimiento.traerPorCue("33356"), cuil, "Nombre", "Prueba", "29915444", "444444", "matias@informaticos.com", "peru", 81, "",repositorioLocalidad.autoCompletarLocalidad("8300").get(0) ,fechaNacimiento , Sexo.MASCULINO, executionContext);
			
		}
	}
	
	private Tecnico create(final Establecimiento establecimiento,
			final Long cuil,
			final String nombre,
			final String apellido,
			final String telefonoCelular,
			final String telefinoFijo,
			final String email,
			final String domicilio,
			final int alturaDomicilio,
			final String piso,
			final Localidad localidad,
			final Date fechaNacimiento,
			final Sexo sexo,
			ExecutionContext executionContext) {		
		
		
		return executionContext.add(this,
				repositorioPersona.ingresarTecnico(establecimiento, cuil, nombre, apellido, telefonoCelular, telefonoCelular, email, domicilio, alturaDomicilio, piso,localidad ,fechaNacimiento , sexo));
				
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repositorioPersona.listarPersonas().size()==0);
	}
	
	@Inject
	private RepositorioLocalidad repositorioLocalidad;
	@Inject
	private RepositorioEstablecimiento repositorioEstablecimiento;
	@Inject
	private RepositorioPersona repositorioPersona;
}
