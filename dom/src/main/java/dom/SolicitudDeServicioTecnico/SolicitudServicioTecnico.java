package dom.SolicitudDeServicioTecnico;




import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;





import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import servicio.email.Email;





import dom.Email.CuentaMail;
import dom.Establecimiento.Establecimiento;
import dom.Netbook.Netbook;
import dom.Persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorPrioridad", language = "JDOQL",
          value = "SELECT FROM repo.Netbook.SolicitudServicioTecnico"),
	@Query(name="traerHistorial", language="JDOQL",
	value = "SELECT FROM dom.SolicitudDeServicioTecnico.SolicitudServicioTecnico WHERE netbook == :netbookBusqueda"),
          @Query(name="taerTipoDeSoluciones", language="JDOQL", 
	      value = "SELECT FROM dom.SolicitudDeServicioTecnico.SolicitudServicioTecnico WHERE motivoDeSolicitud.indexOf(:motivoDeSolicitud) >=0 range 0, 5")})

@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="SolicitudServicioTecnico_Campos_unicos", 
            members={"codigoSolicitud","numeroTiquetRegistro"})
})
@ObjectType("SERVICIOTECNICO")
@Bookmarkable
public class SolicitudServicioTecnico {
    
	//public solicitante integrante de la institucion
	private Persona persona;
	private String motivoDeSolicitud;
	private Date fechaDeSolicitud;
	private String solucion;
	private Date fechaDeSolucion;
	private Prioridad prioridad;
	private Netbook netbook ;
	private String codigoSolicitud;
	private String numeroTiquetRegistro;
	private String comentario;
	private EstadoDeSolicitud estadoDeSolicitud;
	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String iconName() {
        return "asistenciatecnica";
    }
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	

	
	
	
	@Title
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getMotivoDeSolicitud() {
		return motivoDeSolicitud;
	}





	public void setMotivoDeSolicitud(String motivoDeSolicitud) {
		this.motivoDeSolicitud = motivoDeSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}





	public void setFechaDeSolicitud(Date fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}



    @Optional
	@javax.jdo.annotations.Column(allowsNull="true")
    @MultiLine
	public String getSolucion() {
		return solucion;
	}




    
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}




    @javax.jdo.annotations.Column(allowsNull="true")
    @Optional
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Prioridad getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}




    @Unique
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroTiquetRegistro() {
		return numeroTiquetRegistro;
	}





	public void setNumeroTiquetRegistro(String numeroTiquetRegistro) {
		this.numeroTiquetRegistro = numeroTiquetRegistro;
	}



    @MultiLine
    @javax.jdo.annotations.Column(allowsNull="true")
	public String getComentario() {
		return comentario;
	}

    



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

    
    /**
     * TODO ImprimirReporte
     * archivo incompleto para imprimir
     * el metodo funciona pero esta hardcodeado
     * @return Reporte a imprimir
     * @throws JRException 
     * @throws FileNotFoundException 
     */
	public Blob imprimir() throws JRException, FileNotFoundException
	{
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		parametros.put("motivoSolicitud", this.getMotivoDeSolicitud());
		parametros.put("numeroSerieNetbook", this.getNetbook().getNumeroDeSerie());
		parametros.put("apellidoYnombre", this.getPersona().getApellido()+" "+this.getPersona().getNombre());
		parametros.put("cuilDni", this.getPersona().getCuil());
		Persona per = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",per.getEstablecimiento().getNombre()));
		//TODO establecer parametro de curso y division
		parametros.put("nombreInstitucion", establecimiento.getNombre());
		parametros.put("direccionDeLaInstitucion",establecimiento.getDireccion());
		parametros.put("telefono",establecimiento.getTelefono());
		parametros.put("Email",establecimiento.getEmail());
		SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MMM/yyyy/");
		parametros.put("fechaDeNacimiento", formatofecha.format(this.getPersona().getFechaNacimiento()));		
		
		return servicio.Reporte.GeneradorReporte.generarReporte("reportes/solicitudAsistenciaTecnica.jrxml", parametros, "Solicitud");
		
	}
	@Named("Avisar Netbook Reparada")
	public SolicitudServicioTecnico AvisarPorMailQueEstaLista()
	{
		String mensaje ="Hola "+this.getPersona().getNombre()+" "+this.getPersona().getApellido()+"\n";
		mensaje += "\n Nos Comunicamos para informale que";
		mensaje += "\n ya se finalizo la reparacion de la Netbook";
		mensaje += "\n el motivo por el cual se habia pedido la reparacion es: \n"+this.comentario+"\n \n";
		mensaje += "\n esperamos que se acerque a nuestro establecimiento \n";
		
		final CuentaMail mimail = container.firstMatch(QueryDefault.create(CuentaMail.class, "traerTodo"));
		Email.enviarEmail(mimail,mimail.getUsuario(), this.getPersona().getEmail(), "informe de netbook Reparada -("+this.motivoDeSolicitud+")", mensaje);
	    container.informUser("Se Ha enviado un email avisando que la Netbook fue Reparada");
		
		return this;
	}
	
	
	

	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar Solicitud")
	public List<SolicitudServicioTecnico> eliminar() {
        container.removeIfNotAlready(this);
        container.informUser("las Solicitudes selecionadas fueron eliminadas");

        return this.traerTodas(); 
    }
    @Programmatic
    public List<SolicitudServicioTecnico> traerTodas() {
        return container.allMatches(
            new QueryDefault<SolicitudServicioTecnico>(SolicitudServicioTecnico.class, 
                    "traerPorPrioridad"));
    }

    /**
     * Este atributo es para asignar un estado a la netbook que se va a reparar
     * @return EstadoDeSolicitud
     */
    
	@javax.jdo.annotations.Column(allowsNull="false")
	public EstadoDeSolicitud getEstadoDeSolicitud() {
		return estadoDeSolicitud;
	}

	public void setEstadoDeSolicitud(EstadoDeSolicitud estadoDeSolicitud) {
		this.estadoDeSolicitud = estadoDeSolicitud;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
