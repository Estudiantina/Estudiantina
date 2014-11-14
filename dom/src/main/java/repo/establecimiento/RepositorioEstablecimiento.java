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
package repo.establecimiento;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;
import dom.establecimiento.Establecimiento;
import dom.localidad.Localidad;

@Named("Establecimientos")
public class RepositorioEstablecimiento extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Establecimiento";
    }
    
	public String iconName() {
        return "edificio";
    }
	/**
	 * muestra los curso 
	 * @return lista de los Curso
	 */
	public Establecimiento ingresarEstablecimiento (
			@Named("nombre")final String nombre,
			@Named("direccion") final String direccion,
			@RegEx(validation = "[0-9]+") @Named("Telefono")final String telefono,
			@RegEx(validation = "(\\w+\\-)*(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+")
			@Named("email")final String email,
			@Named("Distrito Escolar") final String distritoEscolar,
			@RegEx(validation = "[0-9]+")
			@Named("CUE") final String cue,
			@Named("Cod Postal Ciudad")Localidad localidad
			)
	{
		
		final Establecimiento establecimiento = container.newTransientInstance(Establecimiento.class);
		establecimiento.setDireccion(direccion); 
		establecimiento.setNombre(nombre);
		establecimiento.setTelefono(telefono);
		establecimiento.setEmail(email);
		establecimiento.setCue(cue);
		establecimiento.setLocalidad(localidad);
		establecimiento.setDistritoEscolar(distritoEscolar);
		container.persistIfNotAlready(establecimiento);
		
		return establecimiento;
		
	}
	/**
	 * retorna una consulta que devuelve la 
	 * lista completa de los establecimentos
	 * estudiantiles que existen en el sistema
	 * @return establecimientos
	 */
	@Named("Ver Establecimientos")
	public List<Establecimiento> listadeEstablecimientos() {
        return allMatches(QueryDefault.create(Establecimiento.class, "traerTodos"));
    }
	
	@Hidden
    public List<Establecimiento> autoComplete(String searchPhrase) {        
    	return allMatches(QueryDefault.create(Establecimiento.class, "traerlikePorNombre","nombre",searchPhrase));
    }
	
	@javax.inject.Inject 
    DomainObjectContainer container;

}