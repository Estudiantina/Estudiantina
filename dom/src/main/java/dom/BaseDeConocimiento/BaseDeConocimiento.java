package dom.BaseDeConocimiento;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.ObjectType;

import repo.BaseDeConocimiento.RepositorioBaseDeConocimiento;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
//TODO hacer consulta traerPorcuil 
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerTodoLaBase",
    language = "JDOQL", value = "SELECT FROM dom.BaseDeConocimiento.BaseDeConocimiento")})


@AutoComplete(repository = RepositorioBaseDeConocimiento.class, action = "autoComplete")
@Audited

@ObjectType("NOVEDAD")
public class BaseDeConocimiento {
	
	private String titulo;
   	private String imagen;
	private String descripcion;
	private String url;
	private List<String> categorias;
    private Date fecha;
	
    @javax.jdo.annotations.Column(allowsNull="false")
    public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public List<String> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
  
	
	
	@javax.inject.Inject
    private DomainObjectContainer container;
}
