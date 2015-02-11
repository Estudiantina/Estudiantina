/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Rabovich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package dom.persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;
import com.danhaywood.isis.wicket.gmap3.service.LocationLookupService;
import repo.persona.RepositorioPersona;
import dom.EstaBorrado;
import dom.localidad.Localidad;
/**
 * Clase que representa la entidad Persona en nuestro sistema.
 * 
 */

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Discriminator(
        strategy = DiscriminatorStrategy.CLASS_NAME,
        column = "discriminator")
//el discriminador sirve para ver de que clase viene 
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPersonas", language = "JDOQL", value = "SELECT FROM dom.persona.Persona WHERE estaBorrado== 'ACTIVO' "),
	@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.persona.personagestionable.Persona WHERE cuil== :cuil && estaBorrado== 'ACTIVO'"),
	@javax.jdo.annotations.Query(name = "traerPorcuilEstablecimientoActual", language = "JDOQL", value = "SELECT FROM dom.persona.Persona WHERE cuil== :cuil && estaBorrado== 'ACTIVO' && establecimiento== :establecimiento")
})

@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Persona_Campos_Unicos", 
            members={"cuil","telefonoCelular","telefonoFijo","email"})
})

@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@Bookmarkable
@ObjectType("PersonaGestionable")
public abstract class Persona implements Locatable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5632539687009387987L;
	
	private EstaBorrado estaBorrado = EstaBorrado.ACTIVO;
	private Long cuil;
	private String nombre;
	private String apellido;
	private String telefonoCelular;
	private String telefonoFijo;
	private String email;
	private String domicilio;
	private int alturaDomicilio;
	private String piso ;
	private Date fechaNacimiento;
	private Localidad localidad;
	private Sexo sexo;
	@Column(allowsNull="false")
	@Hidden
	public EstaBorrado getEstaBorrado() {
		return estaBorrado;
	}

	public void setEstaBorrado(EstaBorrado estaBorrado) {
		this.estaBorrado = estaBorrado;
	}
	
	@Column(allowsNull="true")
	public int getAlturaDomicilio() {
		return alturaDomicilio;
	}

	public void setAlturaDomicilio(int alturaDomicilio) {
		this.alturaDomicilio = alturaDomicilio;
	}
	@Column(allowsNull="true")
	@Hidden(where = Where.ALL_TABLES)//no la muestra el piso cuando esta en las tablas
	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}
		
	@Column(allowsNull="true")
	@Hidden(where = Where.ALL_TABLES)//no la muestra la localidad cuando esta en las tablas
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	/**
	 * propiedad necesaria para 
	 * ver la geolocalizacion geografica de una o varias 
	 * personas
	 * Se persiste en la base de datos
	 */
	@Persistent
	private Location location;
    
	/**
     * metodo para geolocalizar
     * persona.
     * @return objeto con cordenadas
     */	
	@MemberOrder(name="Localizacion", sequence = "10")
    @Optional
    @Hidden
    public Location getLocation() {
		if (this.domicilio!="")
    	{
		String algo = this.domicilio+" "+this.alturaDomicilio+", "+this.getLocalidad().getLocalidad();
		LocationLookupService loc = new LocationLookupService();
    	this.location=loc.lookup(algo);
    	}
		
		return location;
    }
	
    /**
     * metodo para obtener localizacion
     * @return
     */
    public List<Persona> getLocalizacion()
    {
    	List<Persona> lisPer = new ArrayList<Persona>();
    	lisPer.add(this);
    	return lisPer;
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
	
	@MaxLength(12)
	@javax.jdo.annotations.Column(allowsNull="false")
    @MemberOrder(sequence="1")
	@Unique
	public Long getCuil() {
		return cuil;
	}
	public void setCuil(Long cuil) {
		this.cuil = cuil;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@Hidden(where = Where.ALL_TABLES) // no muestra el telefono celular en todas las tablas
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	@Hidden(where = Where.ALL_TABLES)//no la muestra el telefono fijo cuando esta en las tablas
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefinoFijo) {
		this.telefonoFijo = telefinoFijo;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@Hidden(where = Where.ALL_TABLES)//no la muestra el domicilio cuando estan todas las tablas
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {	
			
		this.domicilio = domicilio;
	}

	@javax.jdo.annotations.Column(allowsNull="true")
	@Hidden(where = Where.ALL_TABLES)//no la muestra el sexo cuando esta en las tablas
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@Hidden(where = Where.ALL_TABLES) // no muestra la fecha de nacimiento en las tablas
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar netbook")
	@CssClass("icono-eliminar")
	@Hidden
	public List<Persona> eliminar() {
		this.setEstaBorrado(EstaBorrado.BORRADO);
        container.informUser("las personas selecionadas fueron eliminadas");
        return this.traerTodas(); 
    }
	
    @Programmatic
    public List<Persona> traerTodas() {
        return container.allMatches(
            new QueryDefault<Persona>(Persona.class, 
                    "traerPersonas"));
    }
    	
	@javax.inject.Inject 
    DomainObjectContainer container;
}