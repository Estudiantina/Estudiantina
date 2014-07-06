package servicio.email;

import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfiguracionMail {
    private String puerto;
    private String host;
    private boolean tls;
    private String usuario;
    private String password;
    private boolean auth;
    
	
    
    
    
    
	public String getPuerto() {
		return puerto;
	}







	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}







	public String getHost() {
		return host;
	}







	public void setHost(String host) {
		this.host = host;
	}







	public boolean isTls() {
		return tls;
	}







	public void setTls(boolean tls) {
		this.tls = tls;
	}







	public String getUsuario() {
		return usuario;
	}







	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	public boolean isAuth() {
		return auth;
	}







	public void setAuth(boolean auth) {
		this.auth = auth;
	}







	public static Properties mailFactory()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/configuracionemail.xml");
		ConfiguracionMail configuracionMail = (ConfiguracionMail) context.getBean("gmail");
	    Properties props = new Properties();
		props.put("mail.smtp.host", configuracionMail.host);
        props.setProperty("mail.smtp.starttls.enable", ""+configuracionMail.tls);
        props.setProperty("mail.smtp.port", ""+configuracionMail.puerto);
        props.setProperty("mail.smtp.user", configuracionMail.usuario);
        props.setProperty("mail.smtp.auth", ""+configuracionMail.auth);
        context.close();
        return props;
	}
}
