/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Rabovich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package servicio.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dom.email.CuentaMail;

public class Email {
	/**
	 * envia un email para avisar a la persona
	 * que ha sucedido un evento
	 * @param cuentaDeEmail
	 * @param from
	 * @param to
	 * @param Asunto
	 * @param mensajeActual
	 */
	public static void enviarEmail(CuentaMail cuentaDeEmail,String from,String to,String Asunto,String mensajeActual)
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", cuentaDeEmail.getServidorDeMail().getHost());
        props.setProperty("mail.smtp.port", ""+cuentaDeEmail.getServidorDeMail().getPort());
        props.setProperty("mail.smtp.auth", ""+cuentaDeEmail.getServidorDeMail().isAuth());
        props.setProperty("mail.smtp.starttls.enable", ""+cuentaDeEmail.getServidorDeMail().isStarttlsenable());
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", cuentaDeEmail.getServidorDeMail().isFallback());

		AutentificacionSMTP auth = new AutentificacionSMTP();
		auth.setClave(cuentaDeEmail.getClave());
		auth.setUsuario(cuentaDeEmail.getUsuario());
		Session session = Session.getInstance(props, auth);
		
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