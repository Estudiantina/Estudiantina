package servicio.email;

import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfiguracionMail {
    private String puerto;
    private String host;
    private boolean tls;
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
        props.setProperty("mail.smtp.port", ""+configuracionMail.puerto);
        props.setProperty("mail.smtp.auth", ""+configuracionMail.auth);
        props.setProperty("mail.smtp.starttls.enable", ""+configuracionMail.tls);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        context.close();
        return props;
	}
}
