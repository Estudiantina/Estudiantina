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

import repo.cargo.RepositorioCargo;
import repo.curso.RepositorioCurso;
import repo.establecimiento.RepositorioEstablecimiento;
import repo.localidad.RepositorioLocalidad;
import repo.login.repologin;
import repo.pais.RepoPaises;
import repo.persona.RepositorioPersona;
import dom.alumno.Alumno;
import dom.alumno.EstadoDeAlumno;
import dom.cargo.Cargo;
import dom.curso.Anio;
import dom.curso.Curso;
import dom.curso.Division;
import dom.curso.Turno;
import dom.directivo.Directivo;
import dom.docente.Docente;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.login.Login;
import dom.login.Rol;
import dom.pais.Pais;
import dom.persona.Persona;
import dom.persona.Sexo;
import dom.persona.personagestionable.PersonaGestionable;
import dom.tecnico.Tecnico;
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
		Establecimiento establecimientoDos =this.crearEstablecimiento("C.E.M 15", "San Rafael 180", "02994791728", "cem15rn@yahoo.com.ar", "Patagonia", "33579", "8300", executionContext);
		Localidad localidad = repoLocalidad.obtenerLocalidadPorCodigo("8324");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = new Date();
		try {
			 
			fecha = formatter.parse("7-11-1989");
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Long cuil = new Long(203568);
		Tutor tutor = this.crearTutor(establecimiento, cuil, "Jorge", "perez", "1555555", "4444444", "matias@informaticos.com", "peru", 81, null, localidad, fecha, Sexo.MASCULINO, executionContext);
		//CREAR CURSOS ESTABLECIMIENTO UNO
		Curso curso = this.repoCurso.ingresarCurso(establecimiento, Anio.PRIMERO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.PRIMERO, Division.SEGUNDA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimiento, Anio.PRIMERO, Division.TERCERA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimiento, Anio.PRIMERO, Division.CUARTA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimiento, Anio.SEGUNDO, Division.PRIMERA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimiento, Anio.SEGUNDO, Division.SEGUNDA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimiento, Anio.TERCERO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.TERCERO, Division.SEGUNDA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.CUARTO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.CUARTO, Division.SEGUNDA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.QUINTO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimiento, Anio.QUINTO, Division.SEGUNDA, 2015, Turno.Mañana);
		//
		Curso cursoDos = this.repoCurso.ingresarCurso(establecimientoDos, Anio.PRIMERO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.PRIMERO, Division.SEGUNDA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.PRIMERO, Division.TERCERA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.PRIMERO, Division.CUARTA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.SEGUNDO, Division.PRIMERA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.SEGUNDO, Division.SEGUNDA, 2015, Turno.Tarde);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.TERCERO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.TERCERO, Division.SEGUNDA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.CUARTO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.CUARTO, Division.SEGUNDA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.QUINTO, Division.PRIMERA, 2015, Turno.Mañana);
		this.repoCurso.ingresarCurso(establecimientoDos, Anio.QUINTO, Division.SEGUNDA, 2015, Turno.Mañana);
		
		
		//CREACION DE ALUMNOS DE PRUEBA Establecimiento UNO
		Long cuil2 = new Long(33658);
		this.crearAlumno(establecimiento,cuil2, "Juan", "Perez", "155555", "4444444", "matias@informaticos.com", "peru", 81, "", localidad, fecha, new Date(), repoPaises.autoCompletarPais("Argentina").get(0), Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		cuil2 = new Long(3366875);
		this.crearAlumno(establecimiento,cuil2, "Cristian", "Brabo", "155555", "4444444", "matias@informaticos.com", "Don Bosco", 818, "", localidad, fecha, new Date(), repoPaises.autoCompletarPais("Argentina").get(0), Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		cuil2 = new Long(24964567);
		this.crearAlumno(establecimiento,cuil2, "Diego", "Espinosa", "155555", "4444444", "matias@informaticos.com", "Primeros Pobladores", 338, "", localidad, fecha, new Date(), repoPaises.autoCompletarPais("Argentina").get(0), Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		cuil2 = new Long(2496698);
		this.crearAlumno(establecimiento,cuil2, "Gonzalo", "Rodriguez", "155555", "4444444", "matias@informaticos.com", "Esmeralda", 131, "", localidad, fecha, new Date(), repoPaises.autoCompletarPais("Argentina").get(0), Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		Long cuil3 = new Long(336585);
		
		
		//CREACION DE ALUMNOS DE PRUEBA Establecimiento DOS
		Long cuilEstablecimientoDos = new Long(9865472);
		this.crearAlumno(establecimientoDos,cuilEstablecimientoDos, "Juan", "Perez", "155555", "4444444", "matias@informaticos.com", "peru", 81, "", localidad, fecha, new Date(), repoPaises.autoCompletarPais("Argentina").get(0), Sexo.MASCULINO, EstadoDeAlumno.REGULAR, tutor, curso, executionContext);
		
		
		//CREAR DIRECTIVO DEL PRIMER ESTABLECIMIENTO
		final Directivo directivo =this.crearDirectivo(establecimiento,cuil3, "Norma", "Directora", "155555", "444444", "matias@informaticos.com", "Menguelle",1865 , null, localidad, fecha, Sexo.FEMENINO, executionContext);
		this.crearLogin("directivo", "directivo",directivo,repoLogin.buscarRol("usuario_directivo"), executionContext);
		//CREAR DIRECTIVO DEL SEGUNDO ESTABLECIMIENTO
		Long cuilDirectivoDos = new Long(136585);
		final Directivo directivodos =this.crearDirectivo(establecimientoDos,cuilDirectivoDos, "Norma", "Directora", "155555", "444444", "matias@informaticos.com", "Menguelle",1865 , null, localidad, fecha, Sexo.FEMENINO, executionContext);
		this.crearLogin("directivodos", "directivodos",directivodos,repoLogin.buscarRol("usuario_directivo"), executionContext);
		//TECNICO PARA EL PRIMER ESTABLECIMIENTO
		Long cuilTecnicoUno = new Long(1111);
		final Tecnico tecnico =this.crearTecnico(establecimiento, cuilTecnicoUno, "Jose Luis", "Troche", "155555", "444444", "matias@informaticos.com", "Menguelle", 856, null, localidad, fecha, Sexo.MASCULINO, executionContext);
		//TECNICO PARA EL SEGUNDO ESTABLECIMIENTO
		Long cuilTecnicoDos = new Long(11156);
		final Tecnico tecnicoDos =this.crearTecnico(establecimientoDos, cuilTecnicoDos, "Jorge Demian", "Modica", "155555", "444444", "matias@informaticos.com", "Bolivia", 856, null, localidad, fecha, Sexo.MASCULINO, executionContext);
		this.crearLogin("tecnicodos", "tecnicodos",tecnicoDos,repoLogin.buscarRol("usuario_tecnico"), executionContext);
		
		Long cuil5 = new Long(1112);
		this.crearLogin("tecnico", "tecnico",tecnico,repoLogin.buscarRol("usuario_tecnico"), executionContext);
		final Docente docente = this.crearDocente(cuil5, "Amanda", "Ivancich", "155555", "444444", "matias@informaticos.com", "Santa Fe", 338, null, localidad, fecha,repoCargo.traerPorCargo("Profesor de Matematica") , Sexo.FEMENINO, executionContext);
		this.crearLogin("docente", "docente",docente,repoLogin.buscarRol("usuario_docente"), executionContext);
		
		
		}
	}
	
	private Login crearLogin(final String usuario,final String password,final PersonaGestionable persona,final Rol rol,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoLogin.altaUsuario(usuario, password, persona,rol));
	}
	
	
	
	private Alumno crearAlumno(final Establecimiento establecimiento,final Long cuil,final String nombre,final String apellido,final String telefonoCelular,final String telefonoFijo,final String email,final String domicilio,final int alturaDomicilio,final String piso,final	Localidad localidad,final Date fechaNacimiento,final Date fechaIngreso,final Pais nacionalidad,final Sexo sexo,final EstadoDeAlumno estadoDeAlumno,final Tutor tutor,final Curso curso,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarAlumnoEnCualquierEstablecimiento(establecimiento,cuil, nombre, apellido, telefonoCelular, telefonoFijo, email, domicilio, alturaDomicilio, piso, localidad, fechaNacimiento, fechaIngreso, nacionalidad, sexo, estadoDeAlumno, tutor, curso));
	}
	
	private Docente crearDocente(final Long cuil,
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
			final Cargo cargo,
			final Sexo sexo,
			ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarDocente(cuil, nombre, apellido, telefonoCelular, telefinoFijo, email, domicilio, alturaDomicilio, piso, localidad, fechaNacimiento, cargo, sexo));
	}
	
	
	private Tecnico crearTecnico(final Establecimiento establecimiento,final Long cuil,final String nombre,final String apellido,final String telefonoCelular,final String telefinoFijo,final String email,final String domicilio,final int alturaDomicilio,final String piso,Localidad localidad,final Date fechaNacimiento,final Sexo sexo,ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarTecnico(establecimiento, cuil, nombre, apellido, telefonoCelular, telefinoFijo, email, domicilio, alturaDomicilio, piso, localidad, fechaNacimiento, sexo));
	}
	
	private Directivo crearDirectivo(final Establecimiento establecimiento,final Long cuil,final String nombre,final String apellido,final String telefonoCelular,final String telefonoFijo,final String email,final String domicilio,final int alturaDomicilio,final String piso,final Localidad localidad,final Date fechaNacimiento,final Sexo sexo,ExecutionContext executionContext) {		
		
		return executionContext.add(this,
				repoPersona.ingresarDirectivoACualquierEstablecimiento(establecimiento,cuil, nombre, apellido, telefonoCelular, telefonoFijo, email, domicilio, alturaDomicilio, piso, localidad, fechaNacimiento, sexo));
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
	private RepositorioCargo repoCargo;
	@javax.inject.Inject
	private repologin repoLogin;
	
	@javax.inject.Inject
	private RepositorioPersona repoPersona;
	@javax.inject.Inject
	private RepositorioCurso repoCurso;
	@javax.inject.Inject
	private RepositorioLocalidad repoLocalidad;
	@Inject
	private RepoPaises repoPaises;
	

	@Inject
	private RepositorioEstablecimiento repositorioEstablecimiento;
}
