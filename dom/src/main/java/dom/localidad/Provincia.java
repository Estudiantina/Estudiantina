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

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;

import repo.localidad.RepositorioLocalidad;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@ObjectType("Provincias")
@Queries({
	@Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.localidad.Provincia WHERE nombreProvincia.indexOf(:nombre) >= 0 range 0, 4"),
	@Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.localidad.Provincia")})
@AutoComplete(repository = RepositorioLocalidad.class, action = "autoCompletarProvincia")
public class Provincia {
	
	private Blob escudo;
	private String nombreProvincia;
	
	public String iconName() {
		return "Localidad";
	   }
	
	public String title()
	{
		return this.nombreProvincia;
	}
	
	@Column(allowsNull="true")
	@Persistent
	public Blob getEscudo() {
		return escudo;
	}
	public void setEscudo(Blob escudo) {
		this.escudo = escudo;
	}
	
	@Unique
	@Column(allowsNull="false")
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombreProvincia == null) ? 0 : nombreProvincia.hashCode());
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
		Provincia other = (Provincia) obj;
		if (nombreProvincia == null) {
			if (other.nombreProvincia != null)
				return false;
		} else if (!nombreProvincia.equals(other.nombreProvincia))
			return false;
		return true;
	}
}