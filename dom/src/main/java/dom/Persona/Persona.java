package dom.Persona;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;

import javax.jdo.annotations.Unique;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotPersisted;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.query.QueryDefault;
import com.danhaywood.isis.wicket.gmap3.applib.Locatable;
import com.danhaywood.isis.wicket.gmap3.applib.Location;
import com.danhaywood.isis.wicket.gmap3.service.LocationLookupService;

import repo.Persona.RepositorioPersona;
import dom.Establecimiento.Establecimiento;
import dom.Netbook.Netbook;

/**
 * Clase que representa la entidad Persona en el nuestro sistema.
 * 
 */

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPersonas", language = "JDOQL", value = "SELECT FROM dom.Persona.Persona"),
	@javax.jdo.annotations.Query(name = "traerPorcuil", language = "JDOQL", value = "SELECT FROM dom.Persona.Persona WHERE cuil== :cuil")	
})

@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Persona_Campos_Unicos", 
            members={"cuil","telefonoCelular","telefino","email"})
})

@AutoComplete(repository = RepositorioPersona.class, action = "autoComplete")
@Audited
@Bookmarkable
@ObjectType("Persona")
public class Persona implements IntegranteDeLaInstitucion,Locatable{

	private Long cuil;
	private String nombre;
	private String apellido;
	private String telefonoCelular;
	private String telefinoFijo;
	private String email;
	private String domicilio;
	private Date fechaNacimiento;
	private List<Netbook> netbook= new ArrayList<Netbook>();
	private Establecimiento establecimiento;
	/**
	 * propiedad necesaria para 
	 * ver la geolocalizacion geografica de una persona
	 */
	public List<Persona> localizacion;
	@NotPersisted	
	public List<Persona> getLocalizacion() {
		List<Persona> persona = new ArrayList<Persona>();
		persona.add(this);
		return persona;
	}

	@Persistent
	private Location location;
    @Hidden
	@Optional
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    
    
	public List<Netbook> getNetbook() {
		return netbook;
	}
	public void setNetbook(List<Netbook> netbook) {
		this.netbook = netbook;
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

	public String title()
	{
		return this.getNombre().toString()+" "+this.getApellido().toString();
		
	}
	@Named("añadir netbook")
	public Persona anadirNetbook(Netbook netbook)
	{
		this.netbook.add(netbook);
		return this;
	}

	@Unique
	public Long getCuil() {
		return cuil;
	}
	public void setCuil(Long cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getTelefinoFijo() {
		return telefinoFijo;
	}
	public void setTelefinoFijo(String telefinoFijo) {
		this.telefinoFijo = telefinoFijo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		LocationLookupService loc = new LocationLookupService();
		setLocation(loc.lookup(domicilio));
		this.domicilio = domicilio;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar netbook")
	public List<Persona> eliminar() {
        container.removeIfNotAlready(this);
        container.informUser("las personas selecionadas fueron eliminadas");

        return this.traerTodas(); 
    }
    @Programmatic
    public List<Persona> traerTodas() {
        return container.allMatches(
            new QueryDefault<Persona>(Persona.class, 
                    "traerPersonas"));
    }
    

    /**
     * esta funcion es para reasignar netbook, por lo cual debera buscar la net 
     * para eliminar de la lista y luego asiganar una nueva
     * @param net
     * @return netbook
     */
    
    
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("Reasignar Netbook")
	public Persona reasignarNetbook(Netbook  net) {
			this.netbook.remove(net);   
			return this;
		}

    /**
     * metodo para geolocalizar
     * persona.
     * @return
     */
    
    
    
	@javax.inject.Inject 
    DomainObjectContainer container;
}