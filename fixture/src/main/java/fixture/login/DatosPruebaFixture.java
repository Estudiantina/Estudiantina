package fixture.login;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.applib.query.QueryDefault;

import repo.establecimiento.RepositorioEstablecimiento;
import repo.localidad.RepositorioLocalidad;
import repo.login.repologin;
import repo.persona.RepositorioPersona;
import dom.alumno.Alumno;
import dom.alumno.EstadoDeAlumno;
import dom.curso.Curso;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.login.Login;
import dom.login.Rol;
import dom.pais.Pais;
import dom.persona.Persona;
import dom.persona.Sexo;
import dom.persona.personagestionable.PersonaGestionable;
import dom.tutor.Tutor;

public class DatosPruebaFixture extends FixtureScript {

	
	

	public DatosPruebaFixture() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {
		if (estaVacio(executionContext)==true)
		{
		Establecimiento establecimiento =this.crearEstablecimiento("C.E.M 17", "Miguel Muñoz 1056", "02994777769 ", "cem17@mailtelefonica.com.ar", "Patagonia", "33569", "8300", executionContext);
		Localidad localidad = repoLocalidad.obtenerLocalidadPorCodigo("8324");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = new Date();
		try {
			 
			fecha = formatter.parse("7-11-1989");
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long cuil = new Long(203568);
		this.crearTutor(establecimiento, cuil, "Jorge", "perez", "1555555", "4444444", "matias@informaticos.com", "peru", 81, null, localidad, fecha, Sexo.MASCULINO, executionContext);
		
		//this.crearAlumno(34954866, "Juan", "Perez", "155555", "4444444", "matias@informaticos.com", "peru", 81, null, container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",8324)), new Date("22-11-1989"), new Date(),container.firstMatch(QueryDefault.create(Pais.class, "traerPorNombre", "nombre","Argentina")) , Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		}
	}
	
	private Login crearLogin(final String usuario,final String password,final PersonaGestionable persona,final Rol rol,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoLogin.altaUsuario(usuario, password, persona,rol));
	}
	
	
	private Alumno crearAlumno(final Long cuil,final String nombre,final String apellido,final String telefonoCelular,final String telefonoFijo,final String email,final String domicilio,final int alturaDomicilio,final String piso,final	Localidad localidad,final Date fechaNacimiento,final Date fechaIngreso,final Pais nacionalidad,final Sexo sexo,final EstadoDeAlumno estadoDeAlumno,final Tutor tutor,final Curso curso,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarAlumno(cuil, nombre, apellido, telefonoCelular, telefonoFijo, email, domicilio, alturaDomicilio, piso, localidad, fechaNacimiento, fechaIngreso, nacionalidad, sexo, estadoDeAlumno, tutor, curso));
	}
	
	private Tutor crearTutor(final Establecimiento establecimiento,
			final Long cuil,
			final String nombre,
			final String apellido,
			final String telefonoCelular,
			final String telefinoFijo,
			final String email,
			final String domicilio,
			int alturaDomicilio,
			final String piso,
			final Localidad localidad,
			final Date fechaNacimiento,
			final Sexo sexo,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarTutor(cuil, nombre, apellido, fechaNacimiento, telefonoCelular, telefinoFijo, email, domicilio, alturaDomicilio, piso, localidad, sexo));
	}
	
	private Establecimiento crearEstablecimiento(String nombre, String direccion,String telefono,String email,String distritoEscolar,String cue, String localidad,
			ExecutionContext executionContext) {
		return executionContext.add(this,
				repositorioEstablecimiento.ingresarEstablecimiento(nombre, direccion, telefono, email, distritoEscolar, cue, repoLocalidad.autoCompletarLocalidad(localidad).get(0)));
	}

	
	
	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLogin.verUsuarios().size()==1);
	}
	
	@javax.inject.Inject
	private repologin repoLogin;
	@javax.inject.Inject
	private RepositorioPersona repoPersona;
	
	private DomainObjectContainer container;
	@javax.inject.Inject
	private RepositorioLocalidad repoLocalidad;
	@Inject
	private RepositorioEstablecimiento repositorioEstablecimiento;
}
