package servicio.email;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class email {

	
	
	
	public static void enviaremail(String from,String to,String Asunto,String mensajeActual)
	{
		
		Session session = Session.getDefaultInstance(ConfiguracionMail.mailFactory());
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
	
	}
	
	
	
}
