package dom.Notificaciones;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.value.Date;

import dom.Persona.Persona;
import javax.jdo.annotations.Column;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerNotificaciones", language = "JDOQL", value = "SELECT FROM dom.Notificaciones.Notificaciones"),
	@javax.jdo.annotations.Query(name = "traerNotificacionesNoLeidas", language = "JDOQL", value = "SELECT FROM dom.Notificaciones.Notificaciones WHERE vista == false"),
	@javax.jdo.annotations.Query(name = "traerNotificacionesEntreFechas", language = "JDOQL", value = "SELECT FROM dom.Notificaciones.Notificaciones WHERE fechaNotificacion.dateValue().after(:fechaAnterior) ||  fechaNotificacion.dateValue().before(:fechaPosterior) PARAMETERS Date fechaAnterior, Date fechaPosterior import org.apache.isis.applib.value.Date ")
})

@Bookmarkable
@ObjectType("Notificaciones")
public class Notificaciones {

	private boolean vista;
	private Date fechaNotificacion;
    private Persona persona;
    private String detallesYobservaciones;
	

    public String iconName() {
        return "notificacionVista";
    }
    
    @Column(allowsNull="false")
    @Hidden
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	@Column(allowsNull="true")
	@Optional
	public String getDetallesYobservaciones() {
		return detallesYobservaciones;
	}

	public void setDetallesYobservaciones(String detallesYobservaciones) {
		this.detallesYobservaciones = detallesYobservaciones;
	}
	@Column(allowsNull="false")
	@Persistent
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	@Column(allowsNull="false")
	public boolean isVista() {	    	
		return vista;
	}
    
	public void setVista(boolean vista) {
		this.vista = vista;
	}
	
	
	
}
