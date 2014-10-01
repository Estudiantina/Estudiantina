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
package dom.localidad;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Unique;


import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;

import repo.localidad.RepositorioLocalidad;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Query;
@PersistenceCapable(identityType = IdentityType.DATASTORE)

@DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY)
@Queries({
	@Query(name = "traerPorCodigoPostal", language = "JDOQL", value = "SELECT FROM dom.localidad.Localidad WHERE codigoPostal == :codigo"),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.localidad.Localidad")
	})
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarLocalidad")
@ObjectType("Localidades")
public class Localidad {
	private String codigoPostal;
	private String localidad;
	private Departamento departamento;


	public String iconName() {
		return "Localidad";
	   }
	
	@Column(allowsNull="false")
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String toString()
	{
		return this.codigoPostal+" "+this.localidad;
	}
	public String title()
	{
		return this.codigoPostal+" "+this.localidad;
	}

    @Unique
    @Column(allowsNull="false")    
	public String getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Column(allowsNull="false")
	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result
				+ ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result
				+ ((localidad == null) ? 0 : localidad.hashCode());
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
		Localidad other = (Localidad) obj;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		return true;
	}



	
	

}
