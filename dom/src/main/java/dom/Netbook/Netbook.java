package dom.Netbook;


import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.services.eventbus.EventBusService;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.When;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;





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
@Bookmarkable
public class Netbook {
	
	
	private String idNetbook;
	private ModeloNetbook modelo; //TODO reemplazar por marca desde dominio
	private String numeroDeSerie;
	private String numeroLicenciaWindows;
	private Date fechaDeExpiracion;
	private String direccionMac;
	private String situacionDeNetbook;
	private SituacionDeNetbook estado;
	
	private String numeroDeActaDeRobo;
	

	
    public String iconName() {
        return "netbook";
    }
    
	@javax.jdo.annotations.Column(allowsNull="false",length=12)
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
	
	
	@javax.jdo.annotations.Column(allowsNull="false",length=50)
	@MemberOrder(name="Datos De Software", sequence="1")
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	@Bulk //para que ejecute la accion en una lista masiva de objetos
	@PublishedAction // para que muestre la accion en la lista de objetos
	@Named("eliminar netbook")
	public List<Netbook> eliminar() {
		//if (confirmar==true)
		//{
        container.removeIfNotAlready(this);
        container.informUser("las netbook selecionadas fueron eliminadas");
		/*}
		else
		{
			container.informUser("se ha cancelado la eliminacion");	
		}*/
        // invalid to return 'this' (cannot render a deleted object)
        return this.traerTodas(); 
    }
	
	
	
    @Programmatic
    public List<Netbook> traerTodas() {
        return container.allMatches(
            new QueryDefault<Netbook>(Netbook.class, 
                    "traerTodo"));
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
	
	
	
	@Unique
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


	@Optional
	@javax.jdo.annotations.Column(allowsNull="true")
	@MemberOrder(name="Informacion General",sequence="4")
	public String getNumeroDeActaDeRobo() {
		return numeroDeActaDeRobo;
	}
	
	public void setNumeroDeActaDeRobo(String numeroDeActaDeRobo) {
		this.numeroDeActaDeRobo = numeroDeActaDeRobo;
	}
	
     public boolean hideNumeroDeActaDeRobo() {
		
		if (estado.equals(SituacionDeNetbook.ROBADA))
		{
		 return false;
		}
		else
		{
		
		return true;
		}
	}


    


	@javax.inject.Inject
    private DomainObjectContainer container;

	//TODO	Andresrabo Agregar historial de reparaciones
	
}
