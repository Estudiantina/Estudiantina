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

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.ObjectType;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@ObjectType("Permisos")
public class Permisos {
	private  String permiso;
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	
	
}
