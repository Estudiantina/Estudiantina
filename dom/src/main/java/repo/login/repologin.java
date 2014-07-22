package repo.login;

import javax.inject.Named;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.value.Password;

import dom.Curso.Curso;
import dom.Persona.Persona;
import dom.login.Login;
@Named("Administracion De Cuentas")
public class repologin extends AbstractFactoryAndRepository {	
	public String iconName()
	{
		return "seguridad";	
	}
	@Named("dar de alta a un usuario")
	public String altaUsuario (@Named("usuario")String usuario,@Named("contraseña")Password password,@Named("Persona")Persona persona)
	{
		final Login login = container.newTransientInstance(Login.class);
		login.setPersona(persona);
		login.setUsuario(usuario);
		login.setPassword(password);
		container.persistIfNotAlready(login);
		return "el usuario se ha creado correctamente";
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;

	
}
