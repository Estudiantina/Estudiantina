package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import dom.login.Login;
import dom.login.Rol;
import dom.persona.Persona;
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
			
			create("admin", "123456", (long)3333,repoLogin.autoCompletarRol("usuario_administrador").get(0), executionContext);
			
		}
	}
	
	private Login create(final String usuario,final String password,final Long cuilPersona,final Rol rol,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoLogin.altaUsuario(usuario, password, repositorioPersona.buscarPorCuil(cuilPersona),rol));
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
