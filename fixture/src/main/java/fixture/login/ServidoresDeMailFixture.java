package fixture.login;

import javax.inject.Inject;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import dom.email.ServidorDeEmail;
import repo.login.repologin;


public class ServidoresDeMailFixture extends FixtureScript{

	public ServidoresDeMailFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	@Override
	protected void execute(ExecutionContext arg0) {
		if(estaVacio(arg0))
		{
		create("gmail", "smtp.gmail.com", 465, true, true, false,arg0);
		}
	}

	public ServidorDeEmail create(String nombreServer, String host,int port,boolean auth,boolean starttlsenable,boolean fallback,ExecutionContext executionContext)
	{
		return executionContext.add(this,repoLogin.CrearNuevoServidorDeMail(nombreServer, host, port, auth, starttlsenable, fallback));
	}
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLogin.verUsuarios().size()==0);
	}
	
	@Inject
	private repologin repoLogin;
}
