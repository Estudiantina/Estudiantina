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
package dom.email;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import repo.login.repologin;

@PersistenceCapable()
@ObjectType("Email")
@javax.jdo.annotations.Queries({
@Query(name="traerlikePornombreCuenta", language="JDOQL", value = "SELECT FROM dom.email.CuentaMail WHERE nombreCuenta.startsWith(:nombreCuenta) range 0, 4") ,
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.email.CuentaMail ")})
@AutoComplete(repository = repologin.class, action = "autoCompletarMail")
@Audited

@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Email_Campos_unicos", 
            members={"nombreCuenta","usuario","clave"})
})

@Named("E-Mail")
public class CuentaMail {
	
	public String title()
	{
		return ""+nombreCuenta;
	}
	
	private String nombreCuenta;
	private String usuario;
	private String clave;
	private ServidorDeEmail servidorDeMail;
		
	@Column(allowsNull="false")
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	@Column(allowsNull="false")
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	@Column(allowsNull="false")
	@Persistent
	@Hidden
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
		
	@Column(allowsNull="false")
	public ServidorDeEmail getServidorDeMail() {
		return servidorDeMail;
	}
	public void setServidorDeMail(ServidorDeEmail servidorDeMail) {
		this.servidorDeMail = servidorDeMail;
	}	
}