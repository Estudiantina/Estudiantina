package repo.login;



import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Password;
import dom.Persona.Persona;
import dom.login.Login;
import dom.login.Rol;
@Named("Cuentas")
public class repologin extends AbstractFactoryAndRepository {	
	public String iconName()
	{
		return "seguridad";	
	}
	@Named("dar de alta a un usuario")
	public Login altaUsuario (@Named("usuario")String usuario,@Named("contraseña")String password,@Named("Persona")Persona persona)
	{
		final Login login = container.newTransientInstance(Login.class);
		login.setPersona(persona);
		login.setUsuario(usuario);
		login.setPassword(password);
		container.persistIfNotAlready(login);
		return login;
	}
	
	@Named("dar de alta a un rol")
	public Rol altaRol (@Named("usuario")String rol)
	{
		final Rol mirol = container.newTransientInstance(Rol.class);
		mirol.setRol(rol);
		container.persistIfNotAlready(mirol);
		return mirol;
	}
	/**
	 * 
	 * @return
	 */
	@Named("listar Roles")
	public List<Rol> verRoles ()
	{
		return allMatches(QueryDefault.create(Rol.class, "TraerRoles"));
	}
	/**
	 * muestra una lista de todos los usuarios 
	 * de usuarios 
	 * @return
	 */
	@Named("listar Usuarios")
	public List<Login> verUsuarios ()
	{
		return allMatches(QueryDefault.create(Login.class, "todasLasCuentas"));
	}
	
	/**
	 * metodo que busca un usuario
	 * para que lo pueda modificar 
	 * @param usuario
	 * @return
	 */
	public List<Login> modificarUsuario(@Named("usuario")String usuario)
	{
		return allMatches(QueryDefault.create(Login.class, "buscarPorUsuario","usuario",usuario));
		
	}
	
	/**TODO en vez de traer todos los roles corregir para que traiga solo por un rol
	 *  
	 * @param searchPhrase
	 * @return
	 */
	@Named("Buscar Rol")
    public List<Rol> autoCompletarRol(@Named("Ingrese Rol")String searchPhrase) {        
    	
		return allMatches(QueryDefault.create(Rol.class, "TraerRoles"));
    }
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;

	
}
