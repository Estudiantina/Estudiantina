package dom.netbook;


import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;
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
import dom.netbook.Estado.Asignada;
import dom.netbook.Estado.Robada;
import dom.netbook.Estado.SituacionDeNetbook;
import dom.persona.Persona;
import repo.netbook.RepositorioNetbook;
import javax.jdo.annotations.Extension;
import org.apache.isis.applib.annotation.CssClass;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable()
@ObjectType("NETBOOK")
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorId", language = "JDOQL", value = "SELECT FROM dom.netbook.Netbook WHERE idNetbook== :idNetbook"),
@Query(name="traerlikePorId", language="JDOQL", value = "SELECT FROM dom.netbook.Netbook WHERE idNetbook.indexOf(:idNetbook) >=0 range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.netbook.Netbook ")})
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
	private ModeloNetbook modelo; //TODO reemplazar por marca desde dominio
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String situacionDeNetbook;
	private SituacionDeNetbook estadoNetbook;
	private String numeroDeActaDeRobo;


	private Persona persona ;
	@javax.jdo.annotations.Column(allowsNull = "true")
	public Persona getPersona() {
		return persona;
	}





	public void setPersona(Persona persona) {
		this.persona = persona;
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
	
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("")
	@CssClass("icono-eliminar")//agregar icono mediante CSS al boton eliminar
	public List<Netbook> eliminar() {
        container.removeIfNotAlready(this);
        container.informUser("las netbook selecionadas fueron eliminadas");

        return this.traerTodas(); 
    }
	
	
	
    @Programmatic
    public List<Netbook> traerTodas() {
        return container.allMatches(
            new QueryDefault<Netbook>(Netbook.class, 
                    "traerTodo"));
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
	
	
	
	
	
	@MemberOrder(name="Informacion General",sequence="2")
	@Column(allowsNull="false",length=10)
	public String getSituacionDeNetbook() {
		return situacionDeNetbook;
	}
	public void setSituacionDeNetbook(String situacionDeNetbook) {
		this.situacionDeNetbook = situacionDeNetbook;
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
	
	@javax.jdo.annotations.Persistent(extensions = {
			@Extension(vendorName = "datanucleus", key = "mapping-strategy", value = "per-implementation"),
			@Extension(vendorName = "datanucleus", key = "implementation-classes", value = "dom.netbook.Estado.Asignada"
					+ ",dom.netbook.Estado.Robada"
					+ ",dom.netbook.Estado.Deposito"
					+ ",dom.netbook.Estado.DepositoRota"
					+ ",dom.netbook.Estado.Desasignada"
					+ ",dom.netbook.Estado.Migrada"
					+ ",dom.netbook.Estado.Prestada"
					+ ",dom.netbook.Estado.ServicioTecnicoBSAS"
					) }, columns = {
			@Column(name = "idAsignada"), @Column(name = "idRobada"), 
			@Column(name = "idDeposito"),@Column(name = "idDepositoRota"),
			@Column(name = "idDesasignada"),@Column(name = "idMigrada"),
			@Column(name = "idPrestada"),
			@Column(name = "idServicioTecnicoBSAS"),
			})
	@Column(allowsNull="false",length=20)
	@Named("Situacion de Netbook")
	@MemberOrder(name="Estado", sequence="1")
	public SituacionDeNetbook getEstadoNetbook() {
		return estadoNetbook;
	}
	public void setEstadoNetbook(SituacionDeNetbook estadoNetbook) {
		this.estadoNetbook = estadoNetbook;
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
      * TODO Generar contrato de comodato
      * archivo incompleto para imprimir
      * el metodo esta incompleto solo para prueba
      * @return Reporte a imprimir
      * @throws JRException 
      * @throws FileNotFoundException 
      */
     
    public Blob imprimirActaMigracion() throws JRException, FileNotFoundException
 	{
 		
 		HashMap<String,Object> parametros = new HashMap<String, Object>();
 		//Persona per = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil", persona.getCuil() ));
 		
 		parametros.put("nombreDirector", this.getModelo() );
 		parametros.put("netbookModelo", this.getModelo());
 		parametros.put("numeroSerieNetbook", this.getNumeroDeSerie());
 		
 		return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaMigracion.jrxml", parametros, "Solicitud");
 		
 	}
     
    /**
     * TODO ImprimirReporte
     * TODO Generar contrato de comodato
     * archivo incompleto para imprimir
     * el metodo esta incompleto solo para prueba
     * @return Reporte a imprimir
     * @throws JRException 
     * @throws FileNotFoundException 
     */
    
   public Blob imprimirActaPrestamo() throws JRException, FileNotFoundException
	{
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		//Persona per = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil", persona.getCuil() ));
		
	//	parametros.put("nombreDirector", this.getModelo() );
		parametros.put("marcaNetbook", this.getModelo());
		parametros.put("serieNetbook", this.getNumeroDeSerie());
		
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaAutorizacionPrestamoNet.jrxml", parametros, "Solicitud");
		
	}
   






	@javax.inject.Inject
    private DomainObjectContainer container;
	@Override
	public int compareTo(Netbook o) {
		return ObjectContracts.compare(this, o, "idNetbook");
	}

	
}
