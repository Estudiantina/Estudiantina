package dom.SolicitudDeServicioTecnico;


import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;


import dom.Netbook.Netbook;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("SERVICIOTECNICO")
public class DominioSolicitudServicioTecnico {
    //public solicitante integrante de la institucion  
	public String motivoDeSolicitud;
	public Date fechaDeSolicitud;
	public String solucion;
	public Date fechaDeSolucion;
	public Integer prioridad;
	public Netbook netbook = new Netbook();
	public String codigoSolicitud;
	public String numeroTiquetRegistro;
	public String comentario;
	
   
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	public DominioSolicitudServicioTecnico add (@Named("id de Netbook")final String idNetbook ,
	@Named("Modelo")final String modelo,
	@Named("Numero De Serie")final String numeroDeSerie,
	@Named("Numero De Licencia de Windows")final String numeroLicenciaWindows,
	@Named("Fecha de Expiracion") @Optional final Date fechaDeExpiracion,
	@Named("Direccion Mac")final String direccionMac)
	{
		final Netbook mynetbook = container.newTransientInstance(Netbook.class);
	    mynetbook.setFechaDeExpiracion(fechaDeExpiracion);
	    mynetbook.setIdNetbook(idNetbook);
	    mynetbook.setDireccionMac(direccionMac);
	    mynetbook.setModelo(modelo);
	    mynetbook.setNumeroDeSerie(numeroDeSerie);
	    mynetbook.setNumeroLicenciaWindows(numeroLicenciaWindows);
	    mynetbook.setSituacionDeNetbook("Entregada");
	    
	    
	    container.persistIfNotAlready(mynetbook);
	    
		this.netbook= mynetbook;
		return this;
	}
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
	public String getSolucion() {
		return solucion;
	}




    
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}




    @javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Integer getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}





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





	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
