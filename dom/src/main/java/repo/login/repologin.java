/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Robobich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Password;
import org.bouncycastle.util.encoders.Hex;

import repo.persona.RepositorioPersona;
import dom.email.CuentaMail;
import dom.email.ServidorDeEmail;
import dom.login.Login;
import dom.login.Permisos;
import dom.login.Rol;
import dom.persona.personagestionable.PersonaGestionable;
@Named("Cuentas")
public class repologin extends AbstractFactoryAndRepository {	
	
	public String iconName()
	{
		return "seguridad";	
	}
	
	@Named("dar de alta a un usuario")
	public Login altaUsuario (@Named("usuario")String usuario,@Named("contraseña")Password password,@Named("Persona (Ingrese CUIL)")PersonaGestionable persona,@Named("rol")Rol rol)
	{
		return altaUsuario(usuario,password.getPassword(),persona,rol);
	}
	
	@Named("dar de alta a un usuario")
	@Hidden
	public Login altaUsuario (@Named("usuario")String usuario,@Named("contraseña")String password,@Named("Persona (Ingrese CUIL)")PersonaGestionable persona,@Named("Rol") Rol rol)
	{
		final Login login = container.newTransientInstance(Login.class);
		login.setPersona(persona);
		login.setUsuario(usuario);
		login.setPassword(password);
		login.setRol(rol);
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
	
	public String modificarClave(@Named("contraseña Actual") Password passwordActual,@Named("contraseña Nueva")Password passwordNuevo)
	{
	Login login = container.firstMatch(QueryDefault.create(Login.class,"buscarPorPersona","persona",repoPersona.verMisDatos()));
	String passwordEncriptadoActual = passwordActual.getPassword();
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	try {
		md.update(passwordEncriptadoActual.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	byte[] digest = md.digest();
	
	passwordEncriptadoActual = new String(Hex.encode(digest));	
	if (passwordEncriptadoActual.equals(login.obtenerPasswordEncriptado()))
	{
	login.setPassword(passwordNuevo.getPassword());
	return "se ha cambiado la clave correctamente del usuario "+login.getUsuario();	
	}
	else
	{
	return "no se ha podido cambiar la clave anterior no coincide";
	}
	
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
	/**
	 * autocompleta el campo de Cuenta de Email
	 * Haciendo una busqueda mediante
	 * un like en JDO a la base de datos
	 * trae la configuracion de 
	 * @param searchPhrase
	 * @return configuracion de la cuenta de correo Electronico
	 */
	@Hidden
	public List<CuentaMail> autoCompletarMail(@Named("Email")String searchPhrase) {        
		return allMatches(QueryDefault.create(CuentaMail.class, "TraerRoles"));
    }
	/**
	 * autocompleta el campo de Servidor de Email
	 * Haciendo una busqueda mediante
	 * un like en JDO a la base de datos
	 * @param searchPhrase
	 * @return Configuracion del Servidor de Email
	 */
	@Hidden
    public List<ServidorDeEmail> autoCompletarServidorMail(@Named("Servidor")String searchPhrase) {        
		return allMatches(QueryDefault.create(ServidorDeEmail.class, "traerLikePorNombreServer","nombreServer",searchPhrase));
    }
	
	@Named("Mail- Crear Nueva Cuenta De Mail")
    public CuentaMail CrearNuevaCuentaDeMail(
    @Named("Nombre De Cuenta") String nombreCuenta,
    @Named("Usuario") String usuario,
    @Named("Contraseña") Password password,
    @Named("Seleccione servidor")ServidorDeEmail servMail
    )
    {
    	final CuentaMail miCuenta = container.newTransientInstance(CuentaMail.class);
		miCuenta.setClave(password.getPassword());
		miCuenta.setUsuario(usuario);
	    miCuenta.setNombreCuenta(nombreCuenta);
	    miCuenta.setServidorDeMail(servMail);
		container.persistIfNotAlready(miCuenta);
		return miCuenta;	
    }
		
	@Named("Mail- Crear Un Nuevo Servidor")
    public ServidorDeEmail CrearNuevoServidorDeMail(
    @Named("Nombre Del Servidor") String nombreServer,
    @Named("Host") String host,
    @Named("Puerto") int port,
    @Named("Auth") boolean auth,
    @Named("tls") boolean starttlsenable,
    @Named("fallback") boolean fallback)
    {
    	final ServidorDeEmail miServidor = container.newTransientInstance(ServidorDeEmail.class);
		miServidor.setAuth(auth);
		miServidor.setFallback(fallback);
		miServidor.setHost(host);
		miServidor.setNombreServer(nombreServer);
		miServidor.setPort(port);
		miServidor.setStarttlsenable(starttlsenable);
		container.persistIfNotAlready(miServidor);
		return miServidor;	
    }
	
	@Hidden
    public Rol buscarRol(@Named("rol")String searchPhrase) {        
		return firstMatch(QueryDefault.create(Rol.class, "traerporNombre","nombre",searchPhrase));
    }
	
	@Hidden
	public List<ServidorDeEmail> listarServidores()
	{
		return allMatches(QueryDefault.create(ServidorDeEmail.class, "traerTodo"));
	}
    @Hidden
	public Permisos aniadirPermiso(Rol rol,@Named("Permiso")String permiso)
	{
		final Permisos miPermiso = container.newTransientInstance(Permisos.class);
		miPermiso.setPermiso(permiso);
		rol.getListaPermiso().add(miPermiso);
		container.persistIfNotAlready(miPermiso);
		return miPermiso;
	}
    @javax.inject.Inject 
    RepositorioPersona repoPersona;
	@javax.inject.Inject 
    DomainObjectContainer container;
}