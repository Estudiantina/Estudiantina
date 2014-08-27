package dom.Email;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Query;

import org.apache.isis.applib.annotation.Audited;
import org.apache.isis.applib.annotation.AutoComplete;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import repo.login.repologin;

@PersistenceCapable()
@ObjectType("Email")
@javax.jdo.annotations.Queries({
@Query(name="traerlikePornombreCuenta", language="JDOQL", value = "SELECT FROM dom.Email.CuentaMail WHERE nombreCuenta.startsWith(:nombreCuenta) range 0, 4") ,
	@javax.jdo.annotations.Query(name = "traerTodo", language = "JDOQL", value = "SELECT FROM dom.Email.CuentaMail ")})
@AutoComplete(repository = repologin.class, action = "autoCompletarMail")
@Audited

@javax.jdo.annotations.Uniques({
    @javax.jdo.annotations.Unique(
            name="Email_Campos_unicos", 
            members={"nombreCuenta","usuario","clave"})
})

@Named("E-Mail")
public class CuentaMail {
	private String nombreCuenta;
	private String usuario;
	private String clave;
	@Column(allowsNull="false")
	@Persistent
	@Hidden
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	private boolean cuentaPorDefecto;
	private ServidorDeEmail servidorDeMail;
	@Column(allowsNull="false")
	public ServidorDeEmail getServidorDeMail() {
		return servidorDeMail;
	}
	public void setServidorDeMail(ServidorDeEmail servidorDeMail) {
		this.servidorDeMail = servidorDeMail;
	}
	public String title()
	{
		return ""+nombreCuenta;
	}
	@Column(allowsNull="false")
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	
	
	@Column(allowsNull="false")
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	@Column(allowsNull="false")
	public boolean isCuentaPorDefecto() {
		return cuentaPorDefecto;
	}
	public void setCuentaPorDefecto(boolean cuentaPorDefecto) {
		this.cuentaPorDefecto = cuentaPorDefecto;
	}
	
	
	
}
