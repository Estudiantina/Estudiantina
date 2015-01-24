/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Robobich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.persona;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.alumno.Alumno;
import dom.alumno.EstadoDeAlumno;
import dom.alumno.Nacionalidad;
import dom.directivo.Directivo;
import dom.docente.Docente;
import dom.establecimiento.Establecimiento;
import dom.localidad.Localidad;
import dom.login.Login;
import dom.persona.Persona;
import dom.persona.Sexo;
import dom.persona.personagestionable.PersonaGestionable;
import dom.tecnico.Tecnico;
import dom.tutor.Tutor;

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
    /**
     * el metodo retorna los datos del usuario actual
     * @return datos del usuario actual
     */
    @Named("Ver Mis Datos")
    public PersonaGestionable verMisDatos() {
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
	 * @param sexo
	 * 
	 * @return Alumno
	 */
    @Named("ingresar alumno en este establecimiento")
	public Alumno ingresarAlumno (
			@Named("CUIL")Long cuil,
			@RegEx(validation = "[A-Za-z ]+")  @Named("NOMBRE")String nombre,
			@RegEx(validation = "[A-Za-z]+")   @Named("APELLIDO")String apellido,
			@RegEx(validation = "[0-9]+")   @org.apache.isis.applib.annotation.Optional @Named("TELEFONO CELULAR")String telefonoCelular,
		    @RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional @Named("TELEFONO FIJO")String telefonoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+") @Named("CORREO ELECTRONICO")String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@Named("FECHA INGRESO")Date fechaIngreso,
			@RegEx(validation = "[A-Za-z ]+") @Named("NACIONALIDAD")Nacionalidad nacionalidad,
			@Named("SEXO") Sexo sexo,
			@Named("ESTADO DEL ALUMNO") EstadoDeAlumno estadoDeAlumno,
			@Named("Tutor del Alumno") Tutor tutor
			
			)
	{
	
		final Alumno alumno = container.newTransientInstance(Alumno.class);
	alumno.setLocalidad(localidad);
	alumno.setEstablecimiento(this.verMisDatos().getEstablecimiento());
	alumno.setCuil(cuil);
	alumno.setNombre(nombre);
	alumno.setApellido(apellido);
	alumno.setTelefonoCelular(telefonoCelular);
	alumno.setTelefonoFijo(telefonoFijo);
	alumno.setEmail(email);
	alumno.setDomicilio(domicilio);
	alumno.setFechaNacimiento(fechaNacimiento);
	alumno.setFechaIngreso(fechaIngreso);
	alumno.setNacionalidad(nacionalidad);    
    alumno.setEstadoDeAlumno(estadoDeAlumno);
    alumno.setPiso(piso);
    alumno.setSexo(sexo);
    alumno.setAlturaDomicilio(alturaDomicilio);
    alumno.setTutor(tutor);
	container.persistIfNotAlready(alumno);
	
	return alumno;
	}
	
    
    public String validateIngresarDirectivo (
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional @Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+") @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,
			@Named("SEXO") Sexo sexo
			)
	{
    	Date fechaIngreso = new Date();
		if(fechaNacimiento.compareTo(fechaIngreso) >0)
		{
			return "FECHA NACIMIENTO: debe ser menor a la fecha de ingreso";
		}
		else if (Character.isUpperCase(nombre.charAt(0))==false)
		{
			return "El Nombre debe empezar con mayuscula";
		}else 
		if (Character.isUpperCase(apellido.charAt(0))==false)
		{
			return "El Apellido debe empezar con mayuscula";
		}
		else
		{
        return null;
		}
	}
	//*********************** VALIDACION ******************************************//
	/**
	 * metodo que valida los parametros cuando se ingresa un nueva alumno
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
	 * @param sexo
	 * @return Alumno
	 */
	
	public String validateIngresarAlumno(
				final Long cuil,
				final String nombre,
				final String apellido,
				final String telefonoCelular,
				final String telefonoFijo,
				final String email,
				final String domicilio,
				final int alturaDomicilio,
				final  String piso,
				final Localidad localidad,
				final Date fechaNacimiento,
				final Date fechaIngreso,
				final Nacionalidad nacionalidad,
				final Sexo sexo,
				final EstadoDeAlumno estadoDeAlumno,
				final Tutor tutor
				
			) {
		
		
		if(fechaNacimiento.compareTo(fechaIngreso) >0)
		{
			return "FECHA NACIMIENTO: debe ser menor a la fecha de ingreso";
		}
		else if (Character.isUpperCase(nombre.charAt(0))==false)
		{
			return "El Nombre debe empezar con mayuscula";
		}else 
		if (Character.isUpperCase(apellido.charAt(0))==false)
		{
			return "El Apellido debe empezar con mayuscula";
		}
		else
		{
        return null;
		}
    }
	
    //*****************************************************+************//	
	
	/**
	 * muestra un formulario para ingresar un
	 * nuevo Tecnico
	 * @param establecimiento
	 * @param cuil
	 * @param nombre
	 * @param apellido
	 * @param telefonoCelular
	 * @param telefinoFijo
	 * @param email
	 * @param domicilio
	 * @param fechaNacimiento
	 * @param sexo
	 * @return
	 */
	public Tecnico ingresarTecnico (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")  @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,
			@Named("SEXO") Sexo sexo
			)
	{
		final Tecnico tecnico = container.newTransientInstance(Tecnico.class);
		tecnico.setLocalidad(localidad);
		tecnico.setApellido(apellido);
		tecnico.setCuil(cuil);
		tecnico.setDomicilio(domicilio);
		tecnico.setEmail(email);
		tecnico.setFechaNacimiento(fechaNacimiento);
		tecnico.setEstablecimiento(establecimiento);
		tecnico.setNombre(nombre);
		tecnico.setTelefonoFijo(telefinoFijo);
		tecnico.setTelefonoCelular(telefonoCelular);
		tecnico.setSexo(sexo);
		
		container.persistIfNotAlready(tecnico);
	
	return tecnico;
	
	}

	
	public String validateIngresarTecnico (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")  @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,
			@Named("SEXO") Sexo sexo
			)
	{
	
	final Date fecha = new Date();
	if(fechaNacimiento.compareTo(fecha) >0)
	{
		return "FECHA NACIMIENTO: debe ser menor a la fecha de ingreso";
	}
	else if (Character.isUpperCase(nombre.charAt(0))==false)
	{
		return "El Nombre debe empezar con mayuscula";
	}else 
	if (Character.isUpperCase(apellido.charAt(0))==false)
	{
		return "El Apellido debe empezar con mayuscula";
	}
	
	else
	{
    return null;
	}
}
	/**
	 * muestra un formulario para ingresar un
	 * nuevo Tecnico dentro del establecimiento
	 * @param cuil
	 * @param nombre
	 * @param apellido
	 * @param telefonoCelular
	 * @param telefinoFijo
	 * @param email
	 * @param domicilio
	 * @param fechaNacimiento
	 * @param sexo
	 * @return
	 */
	public Tecnico ingresarTecnicoDentroDelEstablecimiento (
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")  @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,
			@Named("SEXO") Sexo sexo
			)
	{
		final Tecnico tecnico = container.newTransientInstance(Tecnico.class);
		tecnico.setLocalidad(localidad);
		tecnico.setApellido(apellido);
		tecnico.setCuil(cuil);
		tecnico.setDomicilio(domicilio);
		tecnico.setEmail(email);
		tecnico.setFechaNacimiento(fechaNacimiento);
		tecnico.setEstablecimiento(this.verMisDatos().getEstablecimiento());
		tecnico.setNombre(nombre);
		tecnico.setTelefonoFijo(telefinoFijo);
		tecnico.setTelefonoCelular(telefonoCelular);
		tecnico.setSexo(sexo);
		
		container.persistIfNotAlready(tecnico);
	
	return tecnico;
	
	}
	
	@Named("ingresar Directivo en este establecimiento")
	public Directivo ingresarDirectivo (
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional @Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+") @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,
			@Named("SEXO") Sexo sexo
			)
	{
		final Directivo directivo = container.newTransientInstance(Directivo.class);
		directivo.setLocalidad(localidad);
		directivo.setApellido(apellido);
		directivo.setCuil(cuil);
		directivo.setDomicilio(domicilio);
		directivo.setEmail(email);
		directivo.setEstablecimiento(this.verMisDatos().getEstablecimiento());
		this.verMisDatos().getEstablecimiento().setDirectivo(directivo);
		directivo.setNombre(nombre);
		directivo.setTelefonoFijo(telefinoFijo);
		directivo.setTelefonoCelular(telefonoCelular);
		directivo.setFechaNacimiento(fechaNacimiento);
		directivo.setSexo(sexo);
		directivo.setAlturaDomicilio(alturaDomicilio);
		container.persistIfNotAlready(directivo);

	return directivo;
	
	}
	@Named("ingresar Docente en este establecimiento")
	public Docente ingresarDocente (
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional	@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+") @org.apache.isis.applib.annotation.Optional  @Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+") @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento,	
			@Named("Cargo") final String cargo,
			@Named("SEXO") Sexo sexo
			
			)
	{
		final Docente docente = container.newTransientInstance(Docente.class);
		docente.setLocalidad(localidad);
		docente.setApellido(apellido);
		docente.setCuil(cuil);
		docente.setDomicilio(domicilio);
		docente.setAlturaDomicilio(alturaDomicilio);
		docente.setPiso(piso);
		docente.setEmail(email);
		docente.setEstablecimiento(this.verMisDatos().getEstablecimiento());
		docente.setNombre(nombre);
		docente.setTelefonoFijo(telefinoFijo);
		docente.setTelefonoCelular(telefonoCelular);
        docente.setFechaNacimiento(fechaNacimiento);
        docente.setCargo(cargo);
        docente.setSexo(sexo);
                
		container.persistIfNotAlready(docente);
	
	return docente;
	
	}
	
	public Tutor ingresarTutor (
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+") @Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+") @Named("APELLIDO")final String apellido,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@RegEx(validation = "[0-9]+")   @org.apache.isis.applib.annotation.Optional 	@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")  @org.apache.isis.applib.annotation.Optional  	@Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+") @Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO") String domicilio,
			@Named("Altura") int alturaDomicilio,
			@Optional @Named("Piso") String piso,
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("SEXO") Sexo sexo
			)
	{
		final Tutor tutor = container.newTransientInstance(Tutor.class);
		tutor.setLocalidad(localidad);
		tutor.setApellido(apellido);
		tutor.setFechaNacimiento(fechaNacimiento);
		tutor.setCuil(cuil);
		tutor.setDomicilio(domicilio);
		tutor.setAlturaDomicilio(alturaDomicilio);
		tutor.setPiso(piso);
		tutor.setEmail(email);
		tutor.setNombre(nombre);
		tutor.setTelefonoFijo(telefinoFijo);
		tutor.setTelefonoCelular(telefonoCelular);
		tutor.setSexo(sexo);        
		container.persistIfNotAlready(tutor);
	
	return tutor;
	
	}
	@Named("Buscar Persona Gestionable")
	@Hidden
    public List<PersonaGestionable> autoComplete(@Named("Ingrese CUIL")String searchPhrase) {        
    	Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(PersonaGestionable.class, "traerPorcuil","cuil",temp,"establecimiento",this.verMisDatos().getEstablecimiento()));
    }
	
    @Hidden
	public List<Alumno> autoCompletarAlumno (@Named("Ingrese CUIL")String searchPhrase)
	{
		Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",temp,"institucion",this.verMisDatos().getEstablecimiento()));
		
	}
    
    
    @Hidden
  	public List<Docente> autoCompletarDocente (@Named("Ingrese CUIL")String searchPhrase)
  	{
  		Long temp = Long.parseLong(searchPhrase);
  		return allMatches(QueryDefault.create(Docente.class, "traerPorcuil","cuil",temp,"institucion",this.verMisDatos().getEstablecimiento()));
  		
  	}
    
    @Hidden
	public List<Tutor> autoCompletarTutor (@Named("Ingrese CUIL")String searchPhrase)
	{
		Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Tutor.class, "traerTutorPorcuil","cuil",temp));
	}

    @Hidden
	public List<Directivo> autoCompletarDirectivo (@Named("Ingrese CUIL")String searchPhrase)
	{
		Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Directivo.class, "traerPorcuil","cuil",temp));
		
	}
    /**
	 * Listar los alumnos por estados.
	 * @return List<Alumno>
	 */
	@Named("Listar Alumnos por Estados")
	public List<Alumno> listaEstadoAlumno(@Named("Seleccione Estado") EstadoDeAlumno estado) {
		
		return allMatches(QueryDefault.create(Alumno.class, "traerPorEstado", "estadoDeAlumno", estado));
	}
	
	/**
	 * Listar los alumnos para los reportes de estadistica.
	 * @return List<Alumno>
	 */
	@Named("Listar Alumnos")
	public List<Alumno> listarAlumnos(){
		final List<Alumno> listaAlumno = this.container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"traerTodoAlumno"));
		if (listaAlumno.isEmpty()){
			this.container.warnUser("No hay alumnos cargados en el sistema");
		}
				return listaAlumno;
	}
    @Hidden
	public PersonaGestionable buscarPorCuil(Long cuil){
		final PersonaGestionable mipersona = this.container.firstMatch(new QueryDefault<PersonaGestionable>(PersonaGestionable.class,
				"traerPorcuilEnTodosLosEstablecimientos","cuil",cuil));
				return mipersona;
	}
    @Named("buscar persona por cuil en este Establecimiento")
    public PersonaGestionable buscarPersonaPorCuilEnEstablecimientoActual(@Named("cuil")Long cuil)
    {
    	final PersonaGestionable mipersona = this.container.firstMatch(new QueryDefault<PersonaGestionable>(PersonaGestionable.class,
				"traerPorcuilEstablecimientoActual","cuil",cuil,"establecimiento",this.verMisDatos().getEstablecimiento()));
				return mipersona;
    }
	
	@Hidden
	public Directivo buscarDirectivoPorCuil(Long cuil){
		final Directivo mipersona = this.container.firstMatch(new QueryDefault<Directivo>(Directivo.class,
				"traerPorcuil","cuil",cuil));
				return mipersona;
	}
 	@javax.inject.Inject 
    DomainObjectContainer container;   
}