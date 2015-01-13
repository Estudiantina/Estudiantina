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
package dom.persona.personagestionable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Named;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.CssClass;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;

import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;
import com.danhaywood.isis.wicket.gmap3.service.LocationLookupService;

import repo.persona.RepositorioPersona;
import dom.EstaBorrado;
import dom.establecimiento.Establecimiento;
import dom.localidad.Localidad;
import dom.netbook.Netbook;
import dom.netbook.situacion.SituacionDeNetbook;
import dom.persona.Persona;
import dom.persona.Sexo;

/**
 * Clase que representa la entidad Persona en nuestro sistema.
 * 
 */

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
//el discriminador sirve para ver de que clase viene 
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPersonas", language = "JDOQL", value = "SELECT FROM dom.persona.personagestionable.PersonaGestionable WHERE estaBorrado== 'ACTIVO' "),
	@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.persona.personagestionable.PersonaGestionable WHERE cuil== :cuil && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerPorcuilEstablecimientoActual", language = "JDOQL", value = "SELECT FROM dom.persona.personagestionable.PersonaGestionable WHERE cuil== :cuil && estaBorrado== 'ACTIVO' && establecimiento== :establecimiento")
})

@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@Bookmarkable
@ObjectType("PersonaGestionable")
public class PersonaGestionable extends Persona implements Locatable,Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6133054169102080125L;
	private SortedSet<Netbook> netbooks =  new TreeSet<Netbook>();
	private Establecimiento establecimiento;

	@Render(Type.EAGERLY)
	public SortedSet<Netbook> getNetbooks() {
		return netbooks;
	}
	
	public void setNetbooks(SortedSet<Netbook> netbooks) {
		this.netbooks = netbooks;
	}
	
	public void addToNetbooks(Netbook e) {
      if(e == null || netbooks.contains(e)) return;
        e.asignarPersona(this);
        netbooks.add(e);
    }
	public void removeFromNetbooks(Netbook e) {
        if(e == null || !netbooks.contains(e)) return;
        e.asignarPersona(null);
        netbooks.remove(e);
    }

	


    public PersonaGestionable resignarNetbook(Netbook net)
    {
    	netbooks.remove(net);
		return this;
    }
    
	@javax.jdo.annotations.Column(allowsNull="true")
	@Optional
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	/**
	 * Identificacion del nombre del icono 
	 * que aparecera en la UI
	 * resources/icono.png
	 * @return String nombre de icono
	 */
	public String iconName() {
		
		return "alumno";
    }

	/**
	 * titulo que se muestra en el objeto de la persona
	 * @return nombre y apellido de la persona
	 */
	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
		
	}
	
	/**
	 * Metodo toString SobreEscrito
	 * @return nombre y apellido de la persona
	 */
	public String toString()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
	}
	
	@Named("añadir netbook")
	public PersonaGestionable aniadirNetbook(Netbook net)
	{
		this.netbooks.add(net);		
		net.asignarPersona(this);
		return this;
	}
	
    	
	@javax.inject.Inject 
    DomainObjectContainer container;
}