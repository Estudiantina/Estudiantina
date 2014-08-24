package repo.Persona;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.Alumno.Alumno;
import dom.Alumno.EstadoDeAlumno;
import dom.Alumno.Nacionalidad;
import dom.Directivo.Directivo;
import dom.Docente.Docente;
import dom.Establecimiento.Establecimiento;
import dom.Persona.Persona;
import dom.Tecnico.Tecnico;
import dom.login.Login;


@Named("Personas")
public class RepositorioPersona extends AbstractFactoryAndRepository {

	
	public String getId() {
        return "persona";
    }
    
	public String iconName() {
        return "alumno";
    }   
    
    
    
    /**
	 * muestra una lista de todas las Personas que existen
	 * @return lista de Alumnos
	 */
    public List<Persona> listarPersonas() {
        return allMatches(QueryDefault.create(Persona.class, "traerPersonas"));
    }
	
    @Named("Ver Mis Datos")
    public Persona VerMisDatos() {
    	Login log =firstMatch(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",container.getUser().getName()));
    	return log.getPersona();
    }
    
    

    
    
    
    
	/**
	 * Se realiza la carga de los alumnos, con todos sus atributos.
	 * 
	 * @param cuil
	 * @param nombre
	 * @param apellido
	 * @param telefonoCelular
	 * @param telefonoFijo
	 * @param email
	 * @param domicilio
	 * @param fechaNacimiento
	 * @param fechaIngreso
	 * @param nacionalidad
	 * 
	 * @return Alumno
	 */
	public Alumno ingresarAlumno (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL")Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")String email,
		
			@Named("DOMICILIO")	@MultiLine String domicilio,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@Named("FECHA INGRESO")Date fechaIngreso,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NACIONALIDAD")Nacionalidad nacionalidad,
			@Named("ESTADO DEL ALUMNO") EstadoDeAlumno estadoDeAlumno 			
			)
	{
	
		final Alumno alumno = container.newTransientInstance(Alumno.class);
	alumno.setEstablecimiento(establecimiento);
	alumno.setCuil(cuil);
	alumno.setNombre(nombre);
	alumno.setApellido(apellido);
	alumno.setTelefonoCelular(telefonoCelular);
	alumno.setTelefinoFijo(telefinoFijo);
	alumno.setEmail(email);
	alumno.setDomicilio(domicilio);
	alumno.setFechaNacimiento(fechaNacimiento);
	alumno.setFechaIngreso(fechaIngreso);
	alumno.setNacionalidad(nacionalidad);    
    alumno.setEstadoDeAlumno(estadoDeAlumno);

	container.persistIfNotAlready(alumno);
	
	return alumno;
	
	}
	/**
	 * 
	 * @param establecimiento
	 * @param cuil
	 * @param nombre
	 * @param apellido
	 * @param telefonoCelular
	 * @param telefinoFijo
	 * @param email
	 * @param domicilio
	 * @param fechaNacimiento
	 * @return
	 */
	public Tecnico ingresarTecnico (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO")	@MultiLine final String domicilio,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento
			)
	{
		final Tecnico tecnico = container.newTransientInstance(Tecnico.class);
		tecnico.setApellido(apellido);
		tecnico.setCuil(cuil);
		tecnico.setDomicilio(domicilio);
		tecnico.setEmail(email);
		tecnico.setFechaNacimiento(fechaNacimiento);
		tecnico.setEstablecimiento(establecimiento);
		tecnico.setNombre(nombre);
		tecnico.setTelefinoFijo(telefinoFijo);
		tecnico.setTelefonoCelular(telefonoCelular);
		container.persistIfNotAlready(tecnico);
	
	
	return tecnico;
	
	}

	public Directivo ingresarDirectivo (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO")	@MultiLine final String domicilio,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento
			)
	{
		final Directivo directivo = container.newTransientInstance(Directivo.class);
		directivo.setApellido(apellido);
		directivo.setCuil(cuil);
		directivo.setDomicilio(domicilio);
		directivo.setEmail(email);
		directivo.setEstablecimiento(establecimiento);
		directivo.setNombre(nombre);
		directivo.setTelefinoFijo(telefinoFijo);
		directivo.setTelefonoCelular(telefonoCelular);
		container.persistIfNotAlready(directivo);
	
	
	return directivo;
	
	}
	
	
	
	public Docente ingresarDocente (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO")	@MultiLine final String domicilio,
			@Named("Cargo") final String cargo
			)
	{
		final Docente docente = container.newTransientInstance(Docente.class);
		docente.setApellido(apellido);
		docente.setCuil(cuil);
		docente.setDomicilio(domicilio);
		docente.setEmail(email);
		docente.setEstablecimiento(establecimiento);
		docente.setNombre(nombre);
		docente.setTelefinoFijo(telefinoFijo);
		docente.setTelefonoCelular(telefonoCelular);
                docente.setCargo(cargo);
		container.persistIfNotAlready(docente);
	
	
	return docente;
	
	}
	
    //TODO autocompletar con persona en vez de con alumno
	@Named("Buscar Persona")
    public List<Persona> autoComplete(@Named("Ingrese CUIL")String searchPhrase) {        
    	Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Persona.class, "traerPorcuil","cuil",temp));
    }

	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
    
    
    
}
