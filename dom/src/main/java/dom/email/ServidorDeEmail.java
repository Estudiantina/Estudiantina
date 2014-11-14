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
import javax.jdo.annotations.Query;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;

import repo.login.repologin;

@PersistenceCapable()
@ObjectType("ServidorDeEmail")
@javax.jdo.annotations.Queries({
@Query(name="traerLikePorNombreServer", language="JDOQL", value = "SELECT FROM dom.email.ServidorDeEmail WHERE nombreServer.startsWith(:nombreServer) range 0, 4") ,
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.email.ServidorDeEmail ")})
@AutoComplete(repository = repologin.class, action = "autoCompletarServidorMail")
@Named("Servidor De Email")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Servidor_Campos_unicos", 
            members={"host","nombreServer"})
})

public class ServidorDeEmail {

	private String nombreServer;
	private String host;
	private int port;
	private boolean auth;
	private boolean starttlsenable;
	
	public String title()
	{
		return nombreServer;
	}
	
	@Column(allowsNull="false")
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	private boolean fallback;
	@Column(allowsNull="false")
	public boolean isFallback() {
		return fallback;
	}
	public void setFallback(boolean fallback) {
		this.fallback = fallback;
	}
	@Column(allowsNull="false")
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Column(allowsNull="false")
	public String getNombreServer() {
		return nombreServer;
	}
	public void setNombreServer(String nombreServer) {
		this.nombreServer = nombreServer;
	}
	
	@Column(allowsNull="false")
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	@Column(allowsNull="false")
	public boolean isStarttlsenable() {
		return starttlsenable;
	}
	public void setStarttlsenable(boolean starttlsenable) {
		this.starttlsenable = starttlsenable;
	}
}
