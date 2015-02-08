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
package dom.login;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;

import repo.login.repologin;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@Queries({
@Query(name="TraerRoles", language="JDOQL", value = "SELECT FROM dom.Login.Rol"),
@Query(name="traerporNombre", language="JDOQL", value = "SELECT FROM dom.Login.Rol WHERE rol== :nombre")
})
@AutoComplete(repository = repologin.class, action = "autoCompletarRol")
@ObjectType("Rol")

public class Rol {
	
private String rol;

@javax.jdo.annotations.Column(allowsNull="False")
@Title
public String getRol() {
	return rol;
}
public void setRol(String rol) {
	this.rol = rol;
}
    @javax.jdo.annotations.Column(allowsNull="true")//permite que no tenga ningun permiso
	private List<Permisos> listaPermiso = new ArrayList<Permisos>();
    @Render(Type.EAGERLY)// lista la lista de permisos cuando a penas se carga
	public List<Permisos> getListaPermiso() {
		return listaPermiso;
	}
    
	public void setListaPermiso(List<Permisos> listaPermiso) {
		this.listaPermiso = listaPermiso;
	}
    
	   public String iconName() {
	    	   return "rol";
	   }
 
	public Rol aniadirPermiso(@Named("Permiso")String permiso)
	{
		
		final Permisos mipermiso = container.newTransientInstance(Permisos.class);
		mipermiso.setPermiso(permiso);
		container.persistIfNotAlready(mipermiso);
		this.listaPermiso.add(mipermiso);
		return this;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}