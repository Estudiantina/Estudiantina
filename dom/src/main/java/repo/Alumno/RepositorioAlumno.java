package repo.Alumno;

import java.util.Date;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;

import dom.Alumno.Alumno;

@Named("Administrar Alumno")
public class RepositorioAlumno extends AbstractFactoryAndRepository {

	/*
	public String getId() {
        return "Netbook";
    }
    */
	
	/**
	 * Se realiza la carga de los alumno, con todos sus atributos.
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
			@Named("CUIL")Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")String nombre,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("APELLIDO")String apellido,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")String email,
			@Named("DOMICILIO")String domicilio,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@Named("FECHA INGRESO")Date fechaIngreso,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NACIONALIDAD")String nacionalidad
			)
	{
	
		final Alumno alumno = container.newTransientInstance(Alumno.class);
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
	
	container.persistIfNotAlready(alumno);
	
	return alumno;
	
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
    
    
    
}