package servicio.email;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class email  {
	
	public final static void enviaremail(String from,String to,String Asunto,String mensajeActual)
	{
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/configuracionemail.xml");
		Authenticator auth= (Authenticator) context.getBean("cuentadeprueba");
		Session session = Session.getInstance(ConfiguracionMail.mailFactory(), auth);
		
		 try{

	         MimeMessage message = new MimeMessage(session);
	         
	         
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         // Asunto
	         message.setSubject(Asunto);
	         // mensaje actual
	         message.setText(mensajeActual);
	         // enviar mensaje
	         Transport.send(message);
	         
		 }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	    context.close();
	}
	
	
	
}
