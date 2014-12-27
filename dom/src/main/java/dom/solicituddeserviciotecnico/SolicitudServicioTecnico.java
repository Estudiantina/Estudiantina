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
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.Blob;
import org.isisaddons.wicket.fullcalendar2.applib.CalendarEvent;
import org.isisaddons.wicket.fullcalendar2.applib.CalendarEventable;
import org.joda.time.LocalDate;

import servicio.email.Email;





import dom.EstaBorrado;
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
import dom.solicituddeserviciotecnico.estados.Aceptado;
import dom.solicituddeserviciotecnico.estados.Reparado;
import dom.solicituddeserviciotecnico.estados.Solicitado;
import dom.tecnico.Tecnico;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorPrioridad", language = "JDOQL",
          value = "SELECT FROM repo.netbook.SolicitudServicioTecnico WHERE estaBorrado== 'ACTIVO'"),
          @Query(name="traerSolicitudesPendientes", language="JDOQL",
      	value = "SELECT FROM dom.solicituddeserviciotecnico.SolicitudServicioTecnico WHERE this.persona.establecimiento == :institucion && (this.estado == this.estadoEnviado || this.estado == this.estadoRecibido || this.estado == this.estadoAceptado || this.estado == this.estadoSolicitado || this.estado == this.estadoReparado ) && estaBorrado== 'ACTIVO'"),
    @Query(name="traerHistorial", language="JDOQL",
	value = "SELECT FROM dom.solicituddeserviciotecnico.SolicitudServicioTecnico WHERE netbook == :netbookBusqueda && estaBorrado== 'ACTIVO'"),
          @Query(name="taerTipoDeSoluciones", language="JDOQL", 
	      value = "SELECT FROM dom.solicituddeserviciotecnico.SolicitudServicioTecnico WHERE motivoDeSolicitud.indexOf(:motivoDeSolicitud) >=0 && estaBorrado== 'ACTIVO' range 0, 5")})

@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("SERVICIOTECNICO")
@MemberGroupLayout(columnSpans={3,3,1,6}, left={"Datos Vinculados","Prioridad"},middle={"Datos De Solicitud"},right={"Estado"})
@Bookmarkable
public class SolicitudServicioTecnico implements Comparable<SolicitudServicioTecnico>, CalendarEventable {
    
	//public solicitante integrante de la institucion
	private EstaBorrado estaBorrado;
	private Persona persona;
	private String motivoDeSolicitud;
	private LocalDate fechaDeSolicitud;
	private String solucion;
	private LocalDate fechaDeSolucion;
	private Prioridad prioridad;
	private Netbook netbook ;
	private String codigoSolicitud;
	private String comentario;
	private Cerrado estadoCerrado;
	private EnviadoAlServicioTecnico estadoEnviado;
	private RecibidoDelServicioTecnico estadoRecibido;
	private Aceptado estadoAceptado;
	private Solicitado estadoSolicitado;
    private Tecnico tecnicoAsignado;
    private Reparado estadoReparado;

    @Hidden
    @javax.jdo.annotations.Column(allowsNull="false")
	public EstaBorrado getEstaBorrado() {
		return estaBorrado;
	}

	public void setEstaBorrado(EstaBorrado estaBorrado) {
		this.estaBorrado = estaBorrado;
	}
	
	@Hidden
    @Column(allowsNull="true")
    public Reparado getEstadoReparado() {
		return estadoReparado;
	}

	public void setEstadoReparado(Reparado estadoReparado) {
		this.estadoReparado = estadoReparado;
	}
	@MemberOrder(name="Datos De Solicitud", sequence="1")
	@Column(allowsNull="true")
	public Tecnico getTecnicoAsignado() {
		return tecnicoAsignado;
	}

	public SolicitudServicioTecnico enviarAServicioTecnico()
	{
		this.getEstado().enviarAServicioTecnico();
		container.informUser("cuando se quiera reparar cliquee en asigne un Tecnico");
		return this;
	}
	/**
	 * Oculta el boton EnviarAServicioTecnico
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideEnviarAServicioTecnico()
	{
		return this.getEstado().ocultarEnviarAServicioTecnico();
	}
	/**
	 * ejecuta la accion recibirDelServicioTecnico
	 * mediante el estado en el que este
	 * @return SolicitudServicioTecnico actual
	 */
	public SolicitudServicioTecnico recibirDelServicioTecnico()
	{
		this.getEstado().recibirDeServicioTecnico();
		return this;
	}
	/**
	 * Oculta el boton RecibirDelServicioTecnico
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideRecibirDelServicioTecnico()
	{
		return this.getEstado().ocultarRecibirDelServicioTecnico();
	}
	
	public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
		this.tecnicoAsignado = tecnicoAsignado;
	}

	/**
	 * Oculta la propiedad de TecnicoAsignado
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideTecnicoAsignado()
	{
		return this.getEstado().ocultarTecnicoAsignado();
	}
	
	public SolicitudServicioTecnico asignarTecnico(Tecnico tecnico)
	{
		this.estado.asignarTecnico(tecnico);
		return this;
	}
	/**
	 * Oculta la propiedad de AsignarTecnico
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideAsignarTecnico()
	{
		return this.getEstado().ocultarAsignarTecnico();
	}

	/**
	 * Oculta la el boton de AvisarPorMailQueEstaLista
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideAvisarPorMailQueEstaLista()
	{
		return this.getEstado().ocultarAvisarPorMailQueEstaLista();
	}

	/**
	 * Oculta la el boton de Imprimir
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	public boolean hideImprimir()
	{
		return this.getEstado().ocultarImprimir();
	}
	/**
	 * el estado enviado se mantiene oculto
	 * ya que se persiste en la interfaz
	 * y se muestra el estado por un metodo getNombre
	 * @return
	 */
	@Hidden
	@Column(allowsNull="true")
    public EnviadoAlServicioTecnico getEstadoEnviado() {
		return estadoEnviado;
	}

	public void setEstadoEnviado(EnviadoAlServicioTecnico estadoEnviado) {
		this.estadoEnviado = estadoEnviado;
	}

	@Hidden
	@Column(allowsNull="true")
	public RecibidoDelServicioTecnico getEstadoRecibido() {
		return estadoRecibido;
	}

	public void setEstadoRecibido(RecibidoDelServicioTecnico estadoRecibido) {
		this.estadoRecibido = estadoRecibido;
	}

	@Hidden
	@Column(allowsNull="true")
	public Aceptado getEstadoAceptado() {
		return estadoAceptado;
	}

	public void setEstadoAceptado(Aceptado estadoAceptado) {
		this.estadoAceptado = estadoAceptado;
	}

	public SolicitudServicioTecnico() {
		this.setEstaBorrado(EstaBorrado.ACTIVO);
		this.estadoReparado = new Reparado(this);
		this.estadoSolicitado = new Solicitado(this);
		this.estadoAceptado = new Aceptado(this);
		this.estadoCerrado = new Cerrado(this);
		this.estadoEnviado = new EnviadoAlServicioTecnico(this);
		this.estadoRecibido = new RecibidoDelServicioTecnico(this);
		this.estado = this.estadoSolicitado;
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
	
	private IEstadoSolicitudDeServicioTecnico estado;
	@Persistent(extensions= {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy",
			value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = 
			"dom.solicituddeserviciotecnico.estados.Solicitado"
			+",dom.solicituddeserviciotecnico.estados.Aceptado"
			+",dom.solicituddeserviciotecnico.estados.EnviadoAlServicioTecnico"
			+",dom.solicituddeserviciotecnico.estados.RecibidoDelServicioTecnico"
			+",dom.solicituddeserviciotecnico.estados.Reparado"
			+",dom.solicituddeserviciotecnico.estados.Cerrado"
					)})
	@Disabled
	@Hidden
	/**
	 * PATRON STATE
	 * @return retorna el estado de la solicitud
	 */
	private IEstadoSolicitudDeServicioTecnico getEstado() {
		return estado;
	}
	@Disabled
	public void setEstado(IEstadoSolicitudDeServicioTecnico estadoSolicitud) {
		this.estado = estadoSolicitud;
	}

	@Hidden(where = Where.ALL_TABLES)
	@Named("Estado")
	@MemberOrder(name="Estado", sequence="1")
	public String getNombreEstado()
	{
		return estado.getNombre();
	}
	
	@Column(allowsNull="false")
	@Hidden(where = Where.ALL_TABLES)
	@MemberOrder(name="Datos Vinculados", sequence="1")
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
	@MemberOrder(name="Datos Vinculados", sequence="1")
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	
	@Title
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Datos De Solicitud", sequence="1")
	public String getMotivoDeSolicitud() {
		return motivoDeSolicitud;
	}
	
	public void setMotivoDeSolicitud(String motivoDeSolicitud) {
		this.motivoDeSolicitud = motivoDeSolicitud;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Datos De Solicitud", sequence="1")
	public LocalDate getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}

	public void setFechaDeSolicitud(LocalDate fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}

    @Optional
	@javax.jdo.annotations.Column(allowsNull="true",length=5000)
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
    @MemberOrder(name="Datos De Solicitud", sequence="1")
	public LocalDate getFechaDeSolucion() {
		return fechaDeSolucion;
	}

	public void setFechaDeSolucion(LocalDate fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}

	@Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Prioridad", sequence="1")
	public Prioridad getPrioridad() {
		return prioridad;
	}
    
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}

	@Hidden(where = Where.ALL_TABLES)
	@javax.jdo.annotations.Column(allowsNull="true")
	@Optional
	@MemberOrder(name="Datos De Solicitud", sequence="1")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}

    @Unique
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}


    @Hidden(where = Where.ALL_TABLES)
    @MultiLine
    @javax.jdo.annotations.Column(allowsNull="true")
    @MemberOrder(name="Datos De Solicitud", sequence="1")
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
		try{
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
		catch(Exception ex)
		{	
			Blob archivonulo = new Blob("archivo.txt", "text/plain", "no se pudo generar el reporte verifique que esten todos los datos el escudo de la provincia tambien es necesario".getBytes());
			return archivonulo;
		}
	}
	@Named("Avisar Netbook Reparada")
	public SolicitudServicioTecnico avisarPorMailQueEstaLista(@MultiLine@Named("Solucion") String solucion,@Named("Fecha Solucion") LocalDate fechaDeSolucion)
	{
		try
		{
		String mensaje ="Hola "+this.getPersona().getNombre()+" "+this.getPersona().getApellido()+"\n";
		mensaje += "\n Nos Comunicamos para informale que";
		mensaje += "\n ya se finalizo la reparacion de la Netbook";
		mensaje += "\n el motivo por el cual se habia pedido la reparacion es: \n"+this.comentario+"\n \n";
		mensaje += "\n esperamos que se acerque a nuestro establecimiento \n";	
		final CuentaMail mimail = container.firstMatch(QueryDefault.create(CuentaMail.class, "traerTodo"));
		Email.enviarEmail(mimail,mimail.getUsuario(), this.getPersona().getEmail(), "informe de netbook Reparada -("+this.motivoDeSolicitud+")", mensaje);
		}
		catch (Exception ex)
		{
			container.informUser("no se ha podido enviar el email por favor configurelo");
		}
		finally
		{
		container.informUser("Se Ha enviado un email avisando que la Netbook fue Reparada");
		}
		this.getEstado().avisarNetbookReparada(solucion, fechaDeSolucion);
		return this;
	}
	/**
	 * cierra la solicitud de servicio Tecnico 
	 * 
	 * @return
	 */
	public SolicitudServicioTecnico finalizarSolicitud()
	{
		this.getEstado().finalizarSolicitud();
		return this;
	}
	/**
	 * Oculta la propiedad de Solucion
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
	@Hidden
	public boolean hideSolucion()
	{
		return this.estado.ocultarSolucion();
	}
	
	@Hidden
	public boolean hideFechaDeSolucion()
	{
		return this.getEstado().ocultarFechaDeSolucion();
		
	}
	/**
	 * oculta FinalizarSolicitud en caso de
	 * que 
	 * @return trae todas las solicitudes
	 */
	@Hidden
	public boolean hideFinalizarSolicitud()
	{
		return this.getEstado().ocultarFinalizarSolicitud();
	}

	/**
	 * elimina la SolicitudServicioTecnico actual
	 * @return trae todas las solicitudes
	 */
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar Solicitud")
	public List<SolicitudServicioTecnico> eliminar() {
        this.setEstaBorrado(EstaBorrado.BORRADO);
        container.informUser("las Solicitudes selecionadas fueron eliminadas");

        return this.traerTodas(); 
    }
	
	/**
	 * muestra todas las solicitudes de SolicitudServicioTecnico
	 * existentes en la base de datos.
	 * @return todas Las solicitudes
	 */
    @Programmatic
    public List<SolicitudServicioTecnico> traerTodas() {
        return container.allMatches(
            new QueryDefault<SolicitudServicioTecnico>(SolicitudServicioTecnico.class, 
                    "traerPorPrioridad"));
    }

	/**
	 * Oculta la propiedad de Eliminar
	 * dependiendo del estado en el que este la solicitud
	 * @return true si se oculta false si no se oculta
	 */
    public boolean hideEliminar()
    {
    	if (getNombreEstado()=="Aceptado")
    	{
		return false;
    	}
    	else
    	{
    	return true;
    	}
    }
    
    
	@javax.inject.Inject 
    DomainObjectContainer container;

	@Override
	public int compareTo(final SolicitudServicioTecnico solicitud) {
		return ObjectContracts.compare(this, solicitud, "codigoSolicitud");
	}
	@Programmatic
	@Override
	public String getCalendarName() {
		// TODO Auto-generated method stub
		return "Solicitudes de estado "+this.getNombreEstado();
	}
	/**
	 * 
	 */
    @Hidden
	@Override
	public CalendarEvent toCalendarEvent() {
		// TODO Auto-generated method stub
		 return new CalendarEvent(getFechaDeSolicitud().toDateTimeAtStartOfDay(), getCalendarName(), container.titleOf(this));
	}
	
}