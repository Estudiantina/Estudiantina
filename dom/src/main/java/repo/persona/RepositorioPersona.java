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
import org.apache.isis.applib.annotation.MultiLine;
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
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@Named("FECHA INGRESO")Date fechaIngreso,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NACIONALIDAD")Nacionalidad nacionalidad,
			@Named("ESTADO DEL ALUMNO") EstadoDeAlumno estadoDeAlumno 			
			)
	{
	
		final Alumno alumno = container.newTransientInstance(Alumno.class);
	alumno.setLocalidad(localidad);
	alumno.setEstablecimiento(establecimiento);
	alumno.setCuil(cuil);
	alumno.setNombre(nombre);
	alumno.setApellido(apellido);
	alumno.setTelefonoCelular(telefonoCelular);
	alumno.setTelefonoFijo(telefinoFijo);
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
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento
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
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("FECHA NACIMIENTO")final Date fechaNacimiento
			)
	{
		final Directivo directivo = container.newTransientInstance(Directivo.class);
		directivo.setLocalidad(localidad);
		directivo.setApellido(apellido);
		directivo.setCuil(cuil);
		directivo.setDomicilio(domicilio);
		directivo.setEmail(email);
		directivo.setEstablecimiento(establecimiento);
		directivo.setNombre(nombre);
		directivo.setTelefonoFijo(telefinoFijo);
		directivo.setTelefonoCelular(telefonoCelular);
		directivo.setFechaNacimiento(fechaNacimiento);
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
			@Named("Cod Postal Ciudad")Localidad localidad,
			@Named("Cargo") final String cargo
			)
	{
		final Docente docente = container.newTransientInstance(Docente.class);
		docente.setLocalidad(localidad);
		docente.setApellido(apellido);
		docente.setCuil(cuil);
		docente.setDomicilio(domicilio);
		docente.setEmail(email);
		docente.setEstablecimiento(establecimiento);
		docente.setNombre(nombre);
		docente.setTelefonoFijo(telefinoFijo);
		docente.setTelefonoCelular(telefonoCelular);
        docente.setCargo(cargo);
        
		container.persistIfNotAlready(docente);
	
	
	return docente;
	
	}
	
	
	public Tutor ingresarTutor (
			@Named("Establecimiento") final Establecimiento establecimiento,
			@Named("CUIL") final Long cuil,
			@RegEx(validation = "[A-Za-z ]+")
			@Named("NOMBRE")final String nombre,
			@RegEx(validation = "[A-Za-z]+")
			@Named("APELLIDO")final String apellido,
			@Named("FECHA NACIMIENTO")Date fechaNacimiento,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO CELULAR")final String telefonoCelular,
			@RegEx(validation = "[0-9]+")
			@Named("TELEFONO FIJO")final String telefinoFijo,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("CORREO ELECTRONICO")final String email,
			@Named("DOMICILIO")	@MultiLine final String domicilio,
			@Named("Cod Postal Ciudad")Localidad localidad
			)
	{
		final Tutor tutor = container.newTransientInstance(Tutor.class);
		tutor.setLocalidad(localidad);
		tutor.setApellido(apellido);
		tutor.setFechaNacimiento(fechaNacimiento);
		tutor.setCuil(cuil);
		tutor.setDomicilio(domicilio);
		tutor.setEmail(email);
		tutor.setEstablecimiento(establecimiento);
		tutor.setNombre(nombre);
		tutor.setTelefonoFijo(telefinoFijo);
		tutor.setTelefonoCelular(telefonoCelular);
        
        
		container.persistIfNotAlready(tutor);
	
	
	return tutor;
	
	}
    //TODO autocompletar con persona en vez de con alumno
	@Named("Buscar Persona")
	@Hidden
    public List<Persona> autoComplete(@Named("Ingrese CUIL")String searchPhrase) {        
    	Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Persona.class, "traerPorcuil","cuil",temp));
    }
    @Hidden
	public List<Alumno> autoCompletarAlumno (@Named("Ingrese CUIL")String searchPhrase)
	{
		Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",temp));
		
	}
    
    
    @Hidden
	public List<Tutor> autoCompletarTutor (@Named("Ingrese CUIL")String searchPhrase)
	{
		Long temp = Long.parseLong(searchPhrase);
		return allMatches(QueryDefault.create(Tutor.class, "traerTutorPorcuil","cuil",temp));
		
	}
	
	
    
    /**
	 * Listar los alumnos por estados.
	 * @return List<Alumno>
	 */
	@Named("Listar de Alumno por Estados")
	public List<Alumno> listaEstadoAlumno(@Named("Seleccione Estado") EstadoDeAlumno estado) {
		
		return allMatches(QueryDefault.create(Alumno.class, "traerPorEstado", "estadoDeAlumno", estado));
	}
    
    
    
	@javax.inject.Inject 
    DomainObjectContainer container;
    
    
    
}
