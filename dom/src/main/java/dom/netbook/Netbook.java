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
package dom.netbook;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
import dom.netbook.situacion.Asignada;
import dom.netbook.situacion.EnStock;
import dom.netbook.situacion.Entregada;
import dom.netbook.situacion.ISituacionDeNetbook;
import dom.netbook.situacion.Prestada;
import dom.netbook.situacion.Robada;
import dom.netbook.situacion.SituacionDeNetbook;
import dom.persona.Persona;
import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
import repo.netbook.RepositorioNetbook;
import repo.solicitudserviciotecnico.RepoSolicitudServicioTecnico;

import org.apache.isis.applib.annotation.CssClass;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable()
@ObjectType("NETBOOK")
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorId", language = "JDOQL", value = "SELECT FROM dom.netbook.Netbook WHERE idNetbook== :idNetbook && establecimiento == :institucion"),
@Query(name="traerlikePorId", language="JDOQL", value = "SELECT FROM dom.netbook.Netbook WHERE establecimiento == :institucion && idNetbook.indexOf(:idNetbook) >=0 range 0, 4"),	
@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.netbook.Netbook WHERE establecimiento == :institucion")})
@AutoComplete(repository = RepositorioNetbook.class, action = "autoComplete")
@Audited
@MemberGroupLayout(columnSpans={3,3,0,6}, left={"Informacion De Hardware","Datos De Software"},middle={"Informacion General","Estado"} )
@Bookmarkable
@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Netbook_Campos_unicos", 
            members={"numeroDeSerie","idNetbook","numeroLicenciaWindows"})
})

public class Netbook implements Comparable<Netbook> {
	
	private String idNetbook;
	private ModeloNetbook modelo;
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String numeroDeActaDeRobo;
	private Persona persona ;
	private Establecimiento establecimiento;
	
	//propiedades de situación de Netbook
	private Asignada asignada;
	private EnStock enStock;
	private Entregada entregada;
	private Prestada prestada;
	private Robada robada;
	
	
	
	public Netbook() {
		this.enStock = new EnStock(this);
		this.asignada = new Asignada(this);
		this.entregada = new Entregada(this);
		this.prestada = new Prestada(this);
		this.robada = new Robada(this);
		this.situacionDeNetbook = this.getEnStock();
	}
	
	
	private ISituacionDeNetbook situacionDeNetbook;
	@Persistent(extensions= {
			@Extension(vendorName = "datanucleous", key = "mapping-strategy",
			value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-clases", value = 
			"dom.netbook.situacion.EnStock"
			+",dom.netbook.situacion.Asignada"
			+",dom.netbook.situacion.Entregada"
			+",dom.netbook.situacion.Prestada"
			+",dom.netbook.situacion.Robada"
					)})
	@Disabled
	@Hidden
	private ISituacionDeNetbook getSituacionDeNetbook() {
		return situacionDeNetbook;
	}
	
	
	public void setSituacionDeNetbook(ISituacionDeNetbook situacionDeNetbook) {
		this.situacionDeNetbook = situacionDeNetbook;
	}


	public Netbook desasignarNetbook()
	{
		this.getSituacionDeNetbook().desasignarNetbookDePersona();
		return this;
	}
	public boolean hideDesasignarNetbook()
	{
		return this.getSituacionDeNetbook().ocultarDesasignarNetbookDePersona();
	}

	
	public SituacionDeNetbook getSituacion()
	{
		return this.getSituacionDeNetbook().getNombreSituacion();
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Asignada getAsignada() {
		return asignada;
	}
	public void setAsignada(Asignada asignada) {
		this.asignada = asignada;
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public EnStock getEnStock() {
		return enStock;
	}
	public void setEnStock(EnStock enStock) {
		this.enStock = enStock;
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Entregada getEntregada() {
		return entregada;
	}
	public void setEntregada(Entregada entregada) {
		this.entregada = entregada;
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Prestada getPrestada() {
		return prestada;
	}
	public void setPrestada(Prestada prestada) {
		this.prestada = prestada;
	}
	@Hidden
	@javax.jdo.annotations.Column(allowsNull="true")
	public Robada getRobada() {
		return robada;
	}
	public void setRobada(Robada robada) {
		this.robada = robada;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public Persona getPersona() {
		return persona;
	}
	@Hidden
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Netbook asignarPersona(Persona persona) {
		this.situacionDeNetbook.asignarPersona(persona);
		return this;
	}
	/**
	 * oculta accion de Asignar Persona segun
	 * que situacion se encuentre la netbook
	 * @return
	 */
    public boolean hideAsignarPersona()
    {
    	return this.getSituacionDeNetbook().ocultarAsignarPersona();
    }
	
	public void modifyPersona(Persona p) {
        if(p==null || persona==p) return;
        if(persona != null) {
            persona.removeFromNetbooks(this);
        }
        p.addToNetbooks(this);
    }
	public void clearPersona() {
        if(persona==null) return;
        persona.removeFromNetbooks(this);
    }
		
	public String iconName() {
        return "netbook";
    }
    
    @Column(allowsNull="false",length=17)
	@MemberOrder(name="Informacion De Hardware", sequence="1")
    @RegEx(validation = "[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]+:+[A-Fa-f0-9]+[A-Fa-f0-9]")
    @Hidden(where = Where.ALL_TABLES)//no la muestra la direccion mac cuando estan todas las tablas
    public String getDireccionMac() {
		return direccionMac;
	}
    
	public void setDireccionMac(String direccionMac) {
		this.direccionMac = direccionMac;
	}
	@Column(allowsNull="false")
	@MemberOrder(name="Informacion De Hardware", sequence="2")
	public ModeloNetbook getModelo() {
		return modelo;
	}
	public void setModelo(ModeloNetbook modelo) {
		this.modelo = modelo;
	}
	
	@Column(allowsNull="false",length=30)
	@MemberOrder(name="Datos De Software", sequence="1")
	@Hidden(where = Where.ALL_TABLES)
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	
	@Column(allowsNull="false",length=30)
	@MemberOrder(name="Datos De Software",sequence="2")
	@Hidden(where = Where.ALL_TABLES)
	public String getNumeroLicenciaWindows() {
		return numeroLicenciaWindows;
	}
	public void setNumeroLicenciaWindows(String numeroLicenciaWindows) {
		this.numeroLicenciaWindows = numeroLicenciaWindows;
	}
	
	@Column(allowsNull="true")
	@Optional
	@MemberOrder(name="Informacion General",sequence="3")
	@Hidden(where = Where.ALL_TABLES)
	public Date getFechaDeExpiracion() {
		return fechaDeExpiracion;
	}
	public void setFechaDeExpiracion(Date fechaDeExpiracion) {
		this.fechaDeExpiracion = fechaDeExpiracion;
	}
	

	
	
	@Unique
	@Column(allowsNull="false",length=10)
    @Title(sequence="1")
	@MaxLength(10)
    @MemberOrder(name="Informacion General",sequence="1")
	public String getIdNetbook() {
		return idNetbook;
	}
	public void setIdNetbook(String idNetbook) {
		this.idNetbook = idNetbook;
	}
	
	@Optional
	@Column(allowsNull="true",length=30)
	@MemberOrder(name="Informacion General",sequence="4")
	@Hidden(where = Where.ALL_TABLES)//oculta el numero de acta de robo en las tablas
	public String getNumeroDeActaDeRobo() {
		return numeroDeActaDeRobo;
	}
	
	public void setNumeroDeActaDeRobo(String numeroDeActaDeRobo) {
		this.numeroDeActaDeRobo = numeroDeActaDeRobo;
	}



	
      /**
      * TODO ImprimirReporte
      * TODO Generar acta de migracion de la netbook 
      * el metodo esta incompleto 
      * @return Reporte a imprimir
      * @throws JRException 
      * @throws FileNotFoundException 
      */ 
    public Blob imprimirActaMigracion() throws JRException, FileNotFoundException
 	{
 		
 		HashMap<String,Object> parametros = new HashMap<String, Object>();
 		 		
 		if (this.getPersona() != null){
 		Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
 				
 		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
 		
 		parametros.put("distrito", establecimiento.getDistritoEscolar());
 		parametros.put("cue", establecimiento.getCue());
 		parametros.put("emailEstablecimiento", establecimiento.getEmail());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
 		
 		parametros.put("establecimiento", establecimiento.getNombre());
 		parametros.put("localidad", establecimiento.getLocalidad());
 		parametros.put("domicilio", establecimiento.getDireccion());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
 		
      	parametros.put("alumno", persona.getNombre()+", "+persona.getApellido());
        parametros.put("cuil_alumno", persona.getCuil());
 		parametros.put("netbookModelo", this.getModelo());
 		parametros.put("numeroSerieNetbook", this.getNumeroDeSerie());
 		
 		    if (establecimiento.getDirectivo() != null){
 		       parametros.put("DierctorCedente", establecimiento.getDirectivo().getApellido()+ ",  "+establecimiento.getDirectivo().getNombre());
 		       parametros.put("nroDniDirector", establecimiento.getDirectivo().getCuil());
 		       }
 		       else {
 			        parametros.put("DierctorCedente", "");
 	 		        parametros.put("nroDniDirector", "");
 		             }
 		
 		} else {
 			
 			parametros.put("distrito", "");
 			parametros.put("cue", "");
 			parametros.put("emailEstablecimiento", "");
 	 		parametros.put("telefonoEstablecimiento", "");
 	 		parametros.put("establecimiento", "");
 	 		parametros.put("localidad", "");
 	 		parametros.put("domicilio", "");
 	 		parametros.put("DierctorCedente", "");
 	 		parametros.put("nroDniDirector", "");
 	 		
 	      	parametros.put("alumno", "");
 	        parametros.put("cuil_alumno", "");
 	 		parametros.put("nombreDirector", "");
 			
 			parametros.put("netbookModelo", this.getModelo());
 	 		parametros.put("numeroSerieNetbook", this.getNumeroDeSerie());
 		}
 		return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaMigracion.jrxml", parametros, "Solicitud");
 		
 	}

    
    public boolean hideImprimirActaMigracion()
    {
    	return this.getSituacionDeNetbook().ocultarImprimirActaMigracion();
    }
    

    /**
     * TODO ImprimirReporte
     * TODO Generar acta de prestamo de netbook
     * el metodo esta incompleto solo para prueba
     * @return Reporte a imprimir
     * @throws JRException 
     * @throws FileNotFoundException 
     */
    
   public Blob imprimirActaPrestamo() throws JRException, FileNotFoundException
	{
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		
		if (this.getPersona() != null){
		
		Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil", this.getPersona().getCuil(),"institucion",this.establecimiento ));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
		
		parametros.put("nombreAlumno", persona.getNombre() +", "+persona.getApellido() );
		parametros.put("cursoAlumno", "");
		parametros.put("divisionAlumno", "");
		parametros.put("marcaNetbook", this.getModelo());
		parametros.put("serieNetbook", this.getNumeroDeSerie());
		parametros.put("nombreTutor","");
					
		      if (establecimiento.getDirectivo() != null){
		
	          parametros.put("nombreDirector", establecimiento.getDirectivo().getApellido()+ ",  "+establecimiento.getDirectivo().getNombre() );
		 
		      }else{
		    	  parametros.put("nombreDirector", "" );
		      }
		 
			 
		}else{
			
			parametros.put("nombreAlumno", "");
			parametros.put("cursoAlumno", "");
			parametros.put("divisionAlumno", "");
			parametros.put("nombreTutor","");
			parametros.put("nombreDirector", "" );
			parametros.put("marcaNetbook", this.getModelo());
			parametros.put("serieNetbook", this.getNumeroDeSerie());
		}
		
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaAutorizacionPrestamoNet.jrxml", parametros, "Solicitud");
		}
   /**
    * oculta la accion de Imprimir Acta de Prestamo
    * dependiendo de la situacion en que
    * se encuentre la netbook
    * @return true oculta false no oculta
    */
   public boolean hideImprimirActaPrestamo()
   {
	   return this.getSituacionDeNetbook().ocultarImprimirActaPrestamo();
   }
   
   
    /**
    * TODO ImprimirReporte
    * TODO Generar acta de recepcion de equipo
    * el metodo esta incompleto solo para prueba
    * @return Reporte a imprimir
    * @throws JRException 
    * @throws FileNotFoundException 
    */
  
  public Blob imprimirActaRecepcionDeNetbook() throws JRException, FileNotFoundException
	{
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		
		if (this.getPersona() != null){
		
		Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil", this.getPersona().getCuil() ));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
		
		parametros.put("nombreAlumno", persona.getNombre() +", "+persona.getApellido() );
		parametros.put("modeloNetbook", this.getModelo());
		parametros.put("marcaNetbook", "");
		parametros.put("serieNetbook", this.getNumeroDeSerie());
		parametros.put("establecimientoEducativo", establecimiento.getNombre());
		parametros.put("ciudad", establecimiento.getLocalidad());
		parametros.put("departamento", establecimiento.getLocalidad().getDepartamento());
	
			 
		}else{
			
			parametros.put("nombreAlumno", "");
			parametros.put("modeloNetbook", this.getModelo());
			parametros.put("marcaNetbook", "");
			parametros.put("serieNetbook", this.getNumeroDeSerie());
			parametros.put("establecimientoEducativo", "");
			parametros.put("ciudad", "");
			parametros.put("departamento","");
		
		}
		
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/reciboNetbook.jrxml", parametros, "Solicitud");
		
	}
  
  /**
   * oculta la accion de Acta de Recepcion
   * dependiendo de la situacion en que
   * se encuentre la netbook
   * @return true oculta false no oculta
   */
    public boolean hideImprimirActaRecepcionDeNetbook()
    {
    	return this.getSituacionDeNetbook().ocultarImprimirActaRecepcionDeNetbook();
    }
    
    /**
     * lista una lista con el historial de reparaciones 
     * en el viewer
     * @return historial de reparaciones
     */
    @Render(Type.EAGERLY)
  	public List<SolicitudServicioTecnico> getHistorialDeReparaciones()
  	{
  		return repoServicioTecnico.verHistorialReparaciones(this);
  	}
  
  	@javax.inject.Inject
  	private RepoSolicitudServicioTecnico repoServicioTecnico;
  	
	@javax.inject.Inject
    private DomainObjectContainer container;
	
	@Override
	public int compareTo(Netbook o) {
		return ObjectContracts.compare(this, o, "idNetbook");
	}
}
