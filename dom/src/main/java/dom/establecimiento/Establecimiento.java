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
package dom.establecimiento;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import javax.jdo.annotations.Column;

import dom.curso.Curso;
import dom.directivo.Directivo;
import dom.localidad.Localidad;
import dom.netbook.Netbook;
import repo.establecimiento.RepositorioEstablecimiento;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorNombre", language = "JDOQL", value = "SELECT FROM dom.establecimiento.Establecimiento WHERE nombre== :nombre"),
	@javax.jdo.annotations.Query(name = "traerPorcue", language = "JDOQL", value = "SELECT FROM dom.establecimiento.Establecimiento WHERE cue== :cue"),
	@Query(name="traerTodos", language="JDOQL", value = "SELECT FROM dom.establecimiento.Establecimiento ") ,
			@Query(name="traerlikePorNombre", language="JDOQL", value = "SELECT FROM dom.establecimiento.Establecimiento WHERE nombre.indexOf(:nombre) >= 0 range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.establecimiento.Establecimiento ")})
@AutoComplete(repository = RepositorioEstablecimiento.class, action = "autoComplete")
@Audited

@ObjectType("Establecimiento")
public class Establecimiento {
	
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String cue;
	private Localidad localidad;
	private String distritoEscolar;
	private Directivo directivo;
	@javax.jdo.annotations.Persistent(mappedBy="establecimiento")
	private SortedSet<Curso> cursos =  new TreeSet<Curso>();
	
	public SortedSet<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(SortedSet<Curso> cursos) {
		this.cursos = cursos;
	}
	@Column(allowsNull="true")
	public Directivo getDirectivo() {
		return directivo;
	}
	public void setDirectivo(Directivo directivo) {
		this.directivo = directivo;
	}
	
	@Column(allowsNull="false")
	public String getDistritoEscolar() {
		return distritoEscolar;
	}
	public void setDistritoEscolar(String distritoEscolar) {
		this.distritoEscolar = distritoEscolar;
	}
	@Column(allowsNull="false")
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String title()
	{
		return this.nombre;
		
	}
	public String iconName() {
        return "edificio";
    }
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString()
	{
		return nombre;		
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
		/**
	 * la CUE es la identificacion la escuela solo en la provincia de neuquen
	 * **/
	@Unique
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCue() {
		return cue;
	}
	public void setCue(String cue) {
		this.cue = cue;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cue == null) ? 0 : cue.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result
				+ ((distritoEscolar == null) ? 0 : distritoEscolar.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
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
		Establecimiento other = (Establecimiento) obj;
		if (cue == null) {
			if (other.cue != null)
				return false;
		} else if (!cue.equals(other.cue))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (distritoEscolar == null) {
			if (other.distritoEscolar != null)
				return false;
		} else if (!distritoEscolar.equals(other.distritoEscolar))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
}
