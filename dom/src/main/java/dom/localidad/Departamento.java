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
package dom.localidad;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.localidad.RepositorioLocalidad;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
@PersistenceCapable(identityType = IdentityType.DATASTORE)

@DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.localidad.Departamento WHERE nombreDepartamento.indexOf(:nombre) >= 0 range 0,4 "),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.localidad.Departamento")})
@ObjectType("Departamentos")
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarDepartamento")
public class Departamento {

	private String nombreDepartamento;
	private Provincia provincia;

	public String iconName() {
		return "Localidad";
	   }
	
	public String title()
	{
		return nombreDepartamento;
	}
		
	@Column(allowsNull="false")
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Column(allowsNull="false")
	@Unique 
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((nombreDepartamento == null) ? 0 : nombreDepartamento
						.hashCode());
		result = prime * result
				+ ((provincia == null) ? 0 : provincia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (nombreDepartamento == null) {
			if (other.nombreDepartamento != null)
				return false;
		} else if (!nombreDepartamento.equals(other.nombreDepartamento))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}	
}