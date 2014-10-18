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
package dom.solicituddeserviciotecnico;




import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.CssClass;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;


import servicio.email.Email;





import dom.email.CuentaMail;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
import dom.localidad.Provincia;
import dom.netbook.Netbook;
import dom.persona.Persona;
import dom.solicituddeserviciotecnico.estados.Cerrado;
import dom.solicituddeserviciotecnico.estados.EnviadoAlServicioTecnico;
import dom.solicituddeserviciotecnico.estados.IEstadoSolicitudDeServicioTecnico;
import dom.solicituddeserviciotecnico.estados.RecibidoDelServicioTecnico;
import dom.solicituddeserviciotecnico.estados.Reparando;
import dom.solicituddeserviciotecnico.estados.Solicitado;
import dom.tecnico.Tecnico;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorPrioridad", language = "JDOQL",
          value = "SELECT FROM repo.netbook.SolicitudServicioTecnico"),
	@Query(name="traerHistorial", language="JDOQL",
	value = "SELECT FROM dom.solicituddeserviciotecnico.SolicitudServicioTecnico WHERE netbook == :netbookBusqueda"),
          @Query(name="taerTipoDeSoluciones", language="JDOQL", 
	      value = "SELECT FROM dom.solicituddeserviciotecnico.SolicitudServicioTecnico WHERE motivoDeSolicitud.indexOf(:motivoDeSolicitud) >=0 range 0, 5")})

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
	private IEstadoSolicitudDeServicioTecnico estadoSolicitud;
	private Cerrado estadoCerrado;
	private EnviadoAlServicioTecnico enviado;
	private RecibidoDelServicioTecnico recibido;
	private Reparando reparando;
	private Solicitado estadoSolicitado;
    private Tecnico tecnicoAsignado;
    


    @Hidden
    @Column(allowsNull="true")
	public Tecnico getTecnicoAsignado() {
		return tecnicoAsignado;
	}



	public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
		this.tecnicoAsignado = tecnicoAsignado;
	}

	
	public SolicitudServicioTecnico asignarTecnico(Tecnico tecnico)
	{
		this.estadoSolicitud.asignarTecnico(tecnico);
		return this;
	}


	@Hidden
	@Column(allowsNull="true")
	public EnviadoAlServicioTecnico getEnviado() {
		return enviado;
	}



	public void setEnviado(EnviadoAlServicioTecnico enviado) {
		this.enviado = enviado;
	}

    @Hidden
	@Column(allowsNull="true")
	public RecibidoDelServicioTecnico getRecibido() {
		return recibido;
	}



	public void setRecibido(RecibidoDelServicioTecnico recibido) {
		this.recibido = recibido;
	}

    @Hidden
	@Column(allowsNull="true")
	public Reparando getReparando() {
		return reparando;
	}



	public void setReparando(Reparando reparando) {
		this.reparando = reparando;
	}


	
	public SolicitudServicioTecnico() {
		this.estadoSolicitado = new Solicitado(this);
		this.estadoCerrado = new Cerrado(this);
		this.enviado = new EnviadoAlServicioTecnico(this);
		this.recibido = new RecibidoDelServicioTecnico(this);
		this.estadoSolicitud = this.estadoSolicitado;
	}

	
	
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Cerrado getEstadoCerrado() {
		return estadoCerrado;
	}

	public void setEstadoCerrado(Cerrado estadoCerrado) {
		this.estadoCerrado = estadoCerrado;
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Solicitado getEstadoSolicitado() {
		return estadoSolicitado;
	}

	public void setEstadoSolicitado(Solicitado estadoSolicitado) {
		this.estadoSolicitado = estadoSolicitado;
	}

	@Persistent(extensions= {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy",
			value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = "dom.solicituddeserviciotecnico.estados.EnviadoAlServicioTecnico"
			+",dom.solicituddeserviciotecnico.estados.RecibidoDelServicioTecnico"
			+",dom.solicituddeserviciotecnico.estados.Reparando"
			+",dom.solicituddeserviciotecnico.estados.Solicitado"
			+",dom.solicituddeserviciotecnico.estados.Cerrado"
					)} , columns = {
			@Column(name= "idEnvidado"),
			@Column(name= "idRecibido"),
			@Column(name= "idReparando"),
			@Column(name= "idSolicitado"),
			@Column(name= "idCerrado")
	})
	@Optional
	@Hidden(where = Where.PARENTED_TABLES)
	public IEstadoSolicitudDeServicioTecnico getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(IEstadoSolicitudDeServicioTecnico estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	@Column(allowsNull="false")
	@Hidden(where = Where.ALL_TABLES)
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String iconName() {
        return "asistenciatecnica";
    }
	
	@Column(allowsNull="false")
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
    @Hidden(where = Where.ALL_TABLES)
	public String getSolucion() {
		return solucion;
	}
    
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}



	@Hidden(where = Where.ALL_TABLES)
    @javax.jdo.annotations.Column(allowsNull="true")
    @Optional
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}



	@Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="false")
	public Prioridad getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}



	@Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}




    @Unique
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}



    @Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroTiquetRegistro() {
		return numeroTiquetRegistro;
	}





	public void setNumeroTiquetRegistro(String numeroTiquetRegistro) {
		this.numeroTiquetRegistro = numeroTiquetRegistro;
	}


	@Hidden(where = Where.ALL_TABLES)
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
     * @throws IOException 
     */
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("Imprimir")
	@CssClass("boton-imprimir")
	public Blob imprimir() throws JRException, IOException
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
		//TODO establecer provincia eliminar metodo harcodeado
		Localidad localidad= container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal())); 
		Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre" ,localidad.getDepartamento().getNombreDepartamento()));
		Provincia prov = container.firstMatch(QueryDefault.create(Provincia.class, "traerPorNombre","nombre",departamento.getProvincia().getNombreProvincia()));
		parametros.put("provincia", prov.getNombreProvincia());
		//convertir a input stream para que se matchee el la imagen del escudo
		InputStream is = new ByteArrayInputStream(prov.getEscudo().getBytes());
		parametros.put("imagen", is);
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/solicitudAsistenciaTecnica.jrxml", parametros, "Solicitud");
		
	}
	@Named("Avisar Netbook Reparada")
	public SolicitudServicioTecnico avisarPorMailQueEstaLista()
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
	@Hidden
	public boolean hideAvisarPorMailQueEstaLista()
	{
		return this.estadoSolicitud.ocultarAvisarPorMailQueEstaLista();
	}
	
	@Hidden
	public boolean hideSolucion()
	{
		return this.estadoSolicitud.ocultarSolucion();
	}
	
	@Hidden
	public boolean hideFechaDeSolucion()
	{
		return this.estadoSolicitud.ocultarFechaDeSolucion();
		
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

	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
