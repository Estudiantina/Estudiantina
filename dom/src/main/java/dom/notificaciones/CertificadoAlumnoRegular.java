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
package dom.notificaciones;

import java.io.FileNotFoundException;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import net.sf.jasperreports.engine.JRException;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;
import dom.alumno.Alumno;
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("PedidoCertificados")
public class CertificadoAlumnoRegular extends Notificaciones{

	public String title()
	{
		return "Pedido Certificado Alumno Regular -"+this.getPersona().toString();
	}
	
	public Blob imprimirCertificadoAlumnoRegular() throws FileNotFoundException, JRException
	{
		Alumno miAlumno = (Alumno) this.getPersona();
	
		return miAlumno.imprimirCertificadoAlumnoRegular();
		
		
	}
	public boolean hideImprimirCertificadoAlumnoRegular()
	{
		final Alumno miAlumno = (Alumno) this.getPersona();
		return miAlumno.hideImprimirCertificadoAlumnoRegular() || this.isVista();
	}
	
}
