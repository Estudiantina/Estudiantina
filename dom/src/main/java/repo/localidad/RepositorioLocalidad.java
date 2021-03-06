/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Rabovich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.localidad;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.localidad.Provincia;

@Named("Localidades")
public class RepositorioLocalidad extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Localidad";
         }
	
	public String iconName() {
		return "Localidad";
	   }

	/**
	 * Alta de Nueva Localidad En el sistema 
	 * @return Localidad ingresada
	 */
	@Named("Nueva Localidad")
	public Localidad ingresoLocalidad(
			@Named("Codigo Postal")@RegEx(validation = "\\d{1,10}")final String codigoPostal,
			@Named("Localidad")final String localidad,
			@Named("Departamento")final Departamento departamento
			){
		
		final Localidad localidades = container.newTransientInstance(Localidad.class);
		localidades.setCodigoPostal(codigoPostal);
		localidades.setDepartamento(departamento);
		localidades.setLocalidad(localidad);
		
		container.persistIfNotAlready(localidades);
		
		return localidades;
	}
	
	@Named("Nuevo Departamento")
	public Departamento ingresoDepartamento(
			@Named("Nombre Del Departamento")final String nombreDepartamento,
			@Named("Provincia")final Provincia provincia
			){
		final Departamento nuevoDepartamento = container.newTransientInstance(Departamento.class);
		nuevoDepartamento.setNombreDepartamento(nombreDepartamento);
		nuevoDepartamento.setProvincia(provincia);		
		container.persistIfNotAlready(nuevoDepartamento);		
		return nuevoDepartamento;
	}
	
	@Named("Nueva Provincia")
	public Provincia ingresoProvincia(
			@Named("Nombre De Provincia")final String nombreProvincia,
			@Named("Imagen del Escudo")final Blob escudo
			)
	{
		final Provincia nuevaProvincia = container.newTransientInstance(Provincia.class);
		nuevaProvincia.setNombreProvincia(nombreProvincia);
		nuevaProvincia.setEscudo(escudo);
		container.persistIfNotAlready(nuevaProvincia);		
		return nuevaProvincia;
	}
	
	@Hidden
	public List<Provincia> autoCompletarProvincia(String busquedaProvincia)
	{
		return allMatches(QueryDefault.create(Provincia.class, "traerPorNombre", "nombre",busquedaProvincia));
	}
	@Hidden
	public List<Localidad> autoCompletarLocalidad(String busquedaLocalidad)
	{
		return allMatches(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",busquedaLocalidad));
	}
	@Hidden
	public Localidad obtenerLocalidadPorCodigo(String busquedaDepartamento)
	{
		return firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",busquedaDepartamento));
	}
	
	@Hidden
	public List<Departamento> autoCompletarDepartamento(String busquedaDepartamento)
	{
		return allMatches(QueryDefault.create(Departamento.class, "traerPorNombre", "nombre",busquedaDepartamento));
	}
	
	public List<Provincia> listaProvincias(){
		return allMatches(QueryDefault.create(Provincia.class, "traerTodo"));
	}
	
	public List<Departamento> listaDepartamentos(){
		return allMatches(QueryDefault.create(Departamento.class, "traerTodo"));
	}
	
	public List<Localidad> listaLocalidades(){
		return allMatches(QueryDefault.create(Localidad.class, "traerTodo"));
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;
}