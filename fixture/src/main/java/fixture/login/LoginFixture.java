package fixture.login;
import org.apache.isis.applib.fixturescripts.FixtureScript;

import repo.login.repologin;
import dom.login.Rol;

public class LoginFixture extends FixtureScript{

	public LoginFixture() {
		// TODO Auto-generated constructor stub
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	// //////////////////////////////////////
	@Override
	protected void execute(ExecutionContext executionContext) {
		create("Perez",executionContext);
		

	}

	// //////////////////////////////////////

	private Rol create(final String rol,
			ExecutionContext executionContext) {
		return executionContext.add(this,
				usuarios.altaRol(rol));
	}

	@javax.inject.Inject
	private repologin usuarios;
}
