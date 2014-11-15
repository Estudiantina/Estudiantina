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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.localidad.Provincia;
import dom.persona.Persona;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudNetbookPrestada")
public class SolicitudNetbookPrestada extends Notificaciones {

	public String title()
	{
		return "Solicitar Netbook Prestada -"+this.getPersona().toString();
	}
	
	public Blob imprimir()
	{

		try{
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/actademigracion.jrxml", parametros, "Solicitud");
		}
		catch(Exception ex)
		{	
			Blob archivonulo = new Blob("archivo.txt", "text/plain", "no se pudo generar el reporte verifique que esten todos los datos".getBytes());
			return archivonulo;
		}
		
	}
	
	public boolean hideImprimir()
	{
		return this.isVista();
	}
}
