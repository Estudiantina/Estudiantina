package dom.Netbook;


import java.util.Date;


import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;




import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("NETBOOK")
public class Netbook {
	
	private String idNetbook;
	private String modelo;
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String situacionDeNetbook;
	
	
	
    public String iconName() {
        return "netbook";
    }
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDireccionMac() {
		return direccionMac;
	}
	public void setDireccionMac(String direccionMac) {
		this.direccionMac = direccionMac;
	}
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
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
    @SuppressWarnings("unused")
    private DomainObjectContainer container;
	
}
