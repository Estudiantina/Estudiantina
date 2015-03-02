package fixture.login;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import repo.login.repologin;
import dom.login.Rol;
public class RolesFixture extends FixtureScript{

	public RolesFixture() {
		// TODO Auto-generated constructor stub
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	// //////////////////////////////////////
	@Override
	protected void execute(ExecutionContext executionContext) {
	    if (estaVacio(executionContext))
	    {
		create("usuario_administrador",executionContext);
		create("usuario_alumno",executionContext);
		create("usuario_tecnico",executionContext);
		create("usuario_directivo",executionContext);
		create("usuario_docente",executionContext);
	    }
	}

	// //////////////////////////////////////

	private Rol create(final String rol,
			ExecutionContext executionContext) {
		return executionContext.add(this,
				usuarios.altaRol(rol));
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				usuarios.verRoles().size()==0);
	}

	@javax.inject.Inject
	private repologin usuarios;
}
