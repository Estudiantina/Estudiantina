package dom.Netbook;


import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;
import repo.Netbook.RepositorioNetbook;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("NETBOOK")
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorId", language = "JDOQL", value = "SELECT FROM dom.Netbook.Netbook WHERE idNetbook== :idNetbook"),
@Query(name="traerlikePorId", language="JDOQL", value = "SELECT FROM dom.Netbook.Netbook WHERE idNetbook.startsWith(:idNetbook) range 0, 4"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Netbook.Netbook ")})
@AutoComplete(repository = RepositorioNetbook.class, action = "autoComplete")
@Audited
@MemberGroupLayout(columnSpans={3,3,0,6}, left={"Informacion De Hardware","Datos De Software"},middle={"Informacion General"} )
public class Netbook {
	
	private String idNetbook;
	private ModeloNetbook modelo; //TODO cambiar modelo por marca
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String situacionDeNetbook;
	private SituacionDeNetbook estado;
	
    public String iconName() {
        return "netbook";
    }
    @MaxLength(12)
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Informacion De Hardware", sequence="1")
    public String getDireccionMac() {
		return direccionMac;
	}
    
	public void setDireccionMac(String direccionMac) {
		this.direccionMac = direccionMac;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Informacion De Hardware", sequence="2")
	public ModeloNetbook getModelo() {
		return modelo;
	}
	public void setModelo(ModeloNetbook modelo) {
		this.modelo = modelo;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Datos De Software", sequence="1")
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Datos De Software",sequence="2")
	public String getNumeroLicenciaWindows() {
		return numeroLicenciaWindows;
	}
	public void setNumeroLicenciaWindows(String numeroLicenciaWindows) {
		this.numeroLicenciaWindows = numeroLicenciaWindows;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="true")
	@MemberOrder(name="Informacion General",sequence="3")
	public Date getFechaDeExpiracion() {
		return fechaDeExpiracion;
	}
	public void setFechaDeExpiracion(Date fechaDeExpiracion) {
		this.fechaDeExpiracion = fechaDeExpiracion;
	}
	
	
	
	
	@MemberOrder(name="Informacion General",sequence="2")
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getSituacionDeNetbook() {
		return situacionDeNetbook;
	}
	public void setSituacionDeNetbook(String situacionDeNetbook) {
		this.situacionDeNetbook = situacionDeNetbook;
	}
	
	
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
    @Title(sequence="1")
    @MemberOrder(name="Informacion General",sequence="1")
	public String getIdNetbook() {
		return idNetbook;
	}
	public void setIdNetbook(String idNetbook) {
		this.idNetbook = idNetbook;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	@MemberOrder(name="Informacion General",sequence="2")
	public SituacionDeNetbook getEstadoNetbook() {
		return estado;
	}
	public void setEstadoNetbook(SituacionDeNetbook estadoNetbook) {
		this.estado = estadoNetbook;
	}

	public String accion(Netbook net)
	{
		return net.numeroDeSerie;
	}


	@javax.inject.Inject
    private DomainObjectContainer container;
	
	
}
