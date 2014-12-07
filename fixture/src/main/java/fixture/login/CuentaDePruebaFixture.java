package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import dom.login.Login;
import repo.login.repologin;
import repo.persona.RepositorioPersona;

public class CuentaDePruebaFixture extends FixtureScript {

	public CuentaDePruebaFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		if (estaVacio(executionContext))
		{
			
			create("admin", "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", "33333", executionContext);
			
		}
	}
	
	private Login create(final String usuario,final String password,final String persona,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoLogin.altaUsuario(usuario, password, repositorioPersona.autoComplete(persona).get(0)));
	}
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLogin.verUsuarios().size()==0);
	}
	
	@Inject
	private RepositorioPersona repositorioPersona;
    @Inject
	private repologin repoLogin;
}
