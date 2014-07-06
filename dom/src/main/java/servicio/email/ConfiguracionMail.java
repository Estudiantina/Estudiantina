package servicio.email;

import java.util.Properties;

public class ConfiguracionMail {
    private String puerto;
    private String host;
    private boolean tls;
    private String usuario;
    private String password;
    private boolean auth;
    private Properties props = new Properties();
	
    

	public String getPassword() {
		return password;
	}


	public String getPuerto() {
		return puerto;
	}


	public String getHost() {
		return host;
	}

	public boolean isTls() {
		return tls;
	}

	public String getUsuario() {
		return usuario;
	}

	public boolean isAuth() {
		return auth;
	}

	public void mailFactory()
	{
        props.put("mail.smtp.host", this.host);
        props.setProperty("mail.smtp.starttls.enable", ""+this.tls);
        props.setProperty("mail.smtp.port", ""+this.puerto);
        props.setProperty("mail.smtp.user", this.usuario);
        props.setProperty("mail.smtp.auth", ""+this.auth);
		
	}
}
