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
