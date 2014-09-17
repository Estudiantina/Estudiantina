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
package dom.Persona;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;

import dom.Netbook.Netbook;

public interface IntegranteDeLaInstitucion {
	
	
	@MaxLength(12)
	@javax.jdo.annotations.Column(allowsNull="false")
	@Title(sequence="1")
    @MemberOrder(sequence="1")
	public Long getCuil();
	public void setCuil(Long cuil);
	
	@Title(sequence="2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre();
	public void setNombre(String nombre);
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public List<Netbook> getNetbook();
	public void setNetbook(List<Netbook> netbook);
	
	
	@Title(sequence="3")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido();
	
	public void setApellido(String apellido);
	
	@Title(sequence="6")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefonoCelular();
	public void setTelefonoCelular(String telefonoCelular);
	
	@Title(sequence="7")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefonoFijo();
	public void setTelefonoFijo(String telefinoFijo);
	
	@Title(sequence="5")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail();
	public void setEmail(String email);
	
	@Title(sequence="4")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDomicilio();
	public void setDomicilio(String domicilio);
	
	@Title(sequence="8")
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaNacimiento();
	public void setFechaNacimiento(Date fechaNacimiento);
	

}