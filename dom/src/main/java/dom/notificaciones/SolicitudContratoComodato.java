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
import java.util.HashMap;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.directivo.Directivo;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.persona.Persona;
import dom.tutor.Tutor;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudContratoComodato")
public class SolicitudContratoComodato extends Notificaciones{

	public String title()
	{
		return "Solicitud Contrato Comodato -"+this.getPersona().toString();
	}
	
	   private Tutor tutor;

	   @Column(allowsNull="true")
	   public Tutor getTutor() {
		   return tutor;
	   }

	   public void setTutor(Tutor tutor) {
		   this.tutor = tutor;
	   }

	   private Directivo directivo;
	   @Column(allowsNull="true")
	   public Directivo getDirectivo() {
		return directivo;
	   }

	   public void setDirectivo(Directivo directivo) {
		   this.directivo = directivo;
	   }

	public Blob imprimir() throws JRException, FileNotFoundException  
	    {
	    	HashMap<String,Object> parametros = new HashMap<String, Object>();
	    	
	    	if (this.getPersona() != null){
	    		
	    		Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
		    	Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
		    	parametros.put("distritoEscolar", establecimiento.getDistritoEscolar());
		    	parametros.put("ciudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());	    	
		    	parametros.put("nombreEstablecimiento", establecimiento.getNombre());
		    	parametros.put("domicilioEstablecimiento", establecimiento.getDireccion());
		    	parametros.put("nombreTutor", this.getTutor().getNombre());
		    	Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
		    	Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
		    	parametros.put("nombreDirector", this.getDirectivo().getNombre());
		    	parametros.put("dniDirector", this.getDirectivo().getCuil());
		    	parametros.put("domicilioEstablecimiento", establecimiento.getDireccion());
		    	parametros.put("provincia", departamento.getProvincia().getNombreProvincia());
		    	parametros.put("DniAlumno", this.getPersona().getCuil());
		    	parametros.put("dniDirector", this.getDirectivo().getCuil());
		    	
		    	//PARAMETROS DEL TUTOR	    	
		    	parametros.put("dniTutor",this.getTutor().getCuil());
		    	parametros.put("domicilio",this.getTutor().getDomicilio());
		    	parametros.put("domicilio",this.getTutor().getDomicilio());
		    	
		    	
		    	//parametros.put("dniDirector", directivo.getCuil()+"");
		    	//consulta establecimiento

		    	parametros.put("nombreEstablecimiento",establecimiento.getNombre());
	    		
	    		
	    	}else{
	    		  //	Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
	             //	Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
	    	parametros.put("distritoEscolar", "");
	    	parametros.put("ciudadEstablecimiento", "");	    	
	    	parametros.put("nombreEstablecimiento", "");
	    	parametros.put("domicilioEstablecimiento","");
	    	parametros.put("nombreTutor", "");
	           //   	Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
	          //  	Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
	    	parametros.put("nombreDirector", "");
	    	parametros.put("dniDirector", "");
	    	parametros.put("domicilioEstablecimiento", "");
	    	parametros.put("provincia","");
	    	parametros.put("DniAlumno", "");
	    	parametros.put("dniDirector", "");
	    	
	    	//PARAMETROS DEL TUTOR	    	
	    	parametros.put("dniTutor", "");
	    	parametros.put("domicilio","");
	    	parametros.put("domicilio","");
	    	
	    	
	    	//parametros.put("dniDirector", directivo.getCuil()+"");
	    	//consulta establecimiento

	    	parametros.put("nombreEstablecimiento","");
	    	}
	  
	    	
		   
	    	return servicio.reporte.GeneradorReporte.generarReporte("reportes/contratoComodato.jrxml", parametros, "ContratoComodato");
			
	    }
	
	
	
		@javax.inject.Inject 
	    DomainObjectContainer container;
	
	
	
	
	
	
}
