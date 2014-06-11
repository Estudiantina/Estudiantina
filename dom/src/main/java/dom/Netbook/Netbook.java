package dom.Netbook;


import java.util.Date;



import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;




import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;

import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MinLength;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;


import repo.Netbook.RepositorioNetbook;



@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("NETBOOK")
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorId", language = "JDOQL", value = "SELECT FROM dom.Netbook.Netbook WHERE idNetbook== :idNetbook"),
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Netbook.Netbook ")})
@AutoComplete(repository = RepositorioNetbook.class, action = "autoComplete")
@Audited
public class Netbook {
	
	private String idNetbook;
	private Modelo modelo;
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String situacionDeNetbook;
	
    public String iconName() {
        return "netbook";
    }
    @MaxLength(12)
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDireccionMac() {
		return direccionMac;
	}
    
	public void setDireccionMac(String direccionMac) {
		this.direccionMac = direccionMac;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroLicenciaWindows() {
		return numeroLicenciaWindows;
	}
	public void setNumeroLicenciaWindows(String numeroLicenciaWindows) {
		this.numeroLicenciaWindows = numeroLicenciaWindows;
	}
	@javax.jdo.annotations.Column(allowsNull="true")
	public Date getFechaDeExpiracion() {
		return fechaDeExpiracion;
	}
	public void setFechaDeExpiracion(Date fechaDeExpiracion) {
		this.fechaDeExpiracion = fechaDeExpiracion;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getSituacionDeNetbook() {
		return situacionDeNetbook;
	}
	public void setSituacionDeNetbook(String situacionDeNetbook) {
		this.situacionDeNetbook = situacionDeNetbook;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
    @Title(sequence="1")
    @MemberOrder(sequence="1")
	public String getIdNetbook() {
		return idNetbook;
	}
	public void setIdNetbook(String idNetbook) {
		this.idNetbook = idNetbook;
	}
	
	@javax.inject.Inject
    private DomainObjectContainer container;
	
	
}
