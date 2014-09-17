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
package servicio.email;

import javax.mail.PasswordAuthentication;

class AutentificacionSMTP extends javax.mail.Authenticator {
    
	private String usuario;
	private String clave;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.usuario, this.clave);//autentifica tanto el correo como la contraseña
    }
}
