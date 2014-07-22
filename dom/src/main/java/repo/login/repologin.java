package repo.login;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

import org.apache.isis.applib.value.Password;
@Named("Administracion De Cuentas")
public class repologin  {

	@Named("dar de alta a un usuario")
	public String altaUsuario (String usuario,Password password)
	{
		
		return "el usuario se ha creado correctamente";
	}
	
}
