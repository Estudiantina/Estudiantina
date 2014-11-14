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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;

import javax.jdo.annotations.Unique;


import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;
import org.bouncycastle.util.encoders.Hex;

import dom.persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "buscarPorUsuario", language = "JDOQL", value = "SELECT FROM dom.Login.Login WHERE usuario== :usuario"),
	@javax.jdo.annotations.Query(name = "todasLasCuentas", language = "JDOQL", value = "SELECT FROM dom.Login.Login")})
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Login_Campos_unicos", 
            members={"usuario","password"})
})
@ObjectType("Login")
public class Login {
private String usuario;
@SuppressWarnings("unused")
private String password;
private Persona persona;
@Unique
@javax.jdo.annotations.Column(allowsNull="False")
public Persona getPersona() {
	return persona;
}
public void setPersona(Persona persona) {
	this.persona = persona;
}
@javax.jdo.annotations.Column(allowsNull="False")
@Title
public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

@javax.jdo.annotations.Column(allowsNull="False")
public void setPassword(String password){
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	try {
		md.update(password.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	byte[] digest = md.digest();
	
	this.password = new String(Hex.encode(digest));	
}

	private Rol rol;

	@Column(allowsNull = "true")
	@Optional
	public Rol getRol() {
		return rol;
	}

	public void setRol(final Rol rol) {
		this.rol = rol;
	}
}
