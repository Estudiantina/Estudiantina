package fixture.login;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.login.repologin;
import dom.login.Permisos;

public class PermisosFixture extends FixtureScript{

	@Override
	protected void execute(ExecutionContext executionContext) {
		
		//
		//PERMISOS PARA USUARIOS ADMINISTRADORES
		//
		create("usuario_administrador","*",executionContext);
		//
		//PERMISOS PARA ALUMNOS
		//TODO establecer permisos de alumnos
		create("usuario_alumno","dom.alumno:Alumno:cursos:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:cursos:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:estadoDeAlumno:r",executionContext);
		//
		//PERMISOS PARA DIRECTIVOS
		//TODO establecer permisos de directivos
		
		
		//
		//
		//TODO establecer permisos de 
		
	}

	
	private Permisos create(final String rol,String permiso,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoLogin.aniadirPermiso(repoLogin.buscarRol(rol),permiso));
	}

	@javax.inject.Inject
	private repologin repoLogin;
}
