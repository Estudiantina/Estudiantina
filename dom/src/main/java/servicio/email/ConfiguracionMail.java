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
    private Properties props = new Properties();
	
    
    
    
    
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







	public Properties getProps() {
		return props;
	}







	public void setProps(Properties props) {
		this.props = props;
	}







	public void mailFactory()
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/configuracionemail.xml");
		ConfiguracionMail configuracionMail = (ConfiguracionMail) context.getBean("gmail");
		this.host=configuracionMail.host;
		this.auth=configuracionMail.auth;
		this.password=configuracionMail.password;
		this.props=configuracionMail.props;
		this.puerto=configuracionMail.puerto;
		this.tls=configuracionMail.tls;
		this.usuario=configuracionMail.usuario;
		context.close();
		props.put("mail.smtp.host", this.host);
        props.setProperty("mail.smtp.starttls.enable", ""+this.tls);
        props.setProperty("mail.smtp.port", ""+this.puerto);
        props.setProperty("mail.smtp.user", this.usuario);
        props.setProperty("mail.smtp.auth", ""+this.auth);
	}
}
