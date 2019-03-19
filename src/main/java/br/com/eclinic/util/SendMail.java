package br.com.eclinic.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class SendMail {

	private static final String SENHA = "soares@10";
	private static final String USUARIO = "soaresitsolucoes@gmail.com";
	private static final String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
	private static final String JAVAX_NET_SSL_SSL_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
	private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_DEBUG = "mail.debug";
	private static final String MAIL_SMTP_USER = "mail.smtp.user";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String SMTP_PROTOCOL = "smtp";
	private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static final String PORTA = "465";
	private static final String SMTP = "smtp.gmail.com";
	private String mailSMTPServer;
	private String mailSMTPServerPort;

	public SendMail() {
		mailSMTPServer = SMTP;
		mailSMTPServerPort = PORTA;
	}

	public SendMail(String mailSMTPServer, String mailSMTPServerPort) {
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}

	public void enviarEmailSenha(String from) throws MessagingException {

		Properties props = new Properties();

		// quem estiver utilizando um SERVIDOR PROXY descomente essa parte e
		// atribua as propriedades do SERVIDOR PROXY utilizado
		/*
		 * props.setProperty("proxySet","true");
		 * props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
		 * props.setProperty("socksProxyPort","1080"); // Porta do servidor Proxy
		 */

		props.put(MAIL_TRANSPORT_PROTOCOL, SMTP_PROTOCOL);
		props.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
		props.put(MAIL_SMTP_HOST, mailSMTPServer);
		props.put(MAIL_SMTP_AUTH, "true");
		props.put(MAIL_SMTP_USER, "rafael.henrique.soares@gmail.com");
		props.put(MAIL_DEBUG, "true");
		props.put(MAIL_SMTP_PORT, mailSMTPServerPort);
		props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, mailSMTPServerPort);
		props.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, JAVAX_NET_SSL_SSL_SOCKET_FACTORY);
		props.put(MAIL_SMTP_SOCKET_FACTORY_FALLBACK, "false");

		SimpleAuth auth = null;
		auth = new SimpleAuth(USUARIO, SENHA);

		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		InternetAddress fromMessage = new InternetAddress("rafael.henrique.soares@gmail.com");
		message.setFrom(fromMessage);
		InternetAddress to = new InternetAddress(from);
		message.addRecipient(Message.RecipientType.TO, to);
		message.setSubject("Envio de anexos usando o JavaMail.");

		// cria a primeira parte da mensagem
		MimeBodyPart mbp1 = new MimeBodyPart();

		// FIXME rhas mudar o template
		String templateEmail = "<html> <head> <meta http-equiv='Content-Type' content='text/html;charset=utf-8' > <title>GASIP - Gerênciamento autonomo de sistema de iluminação pública</title> <style> a:link { color:#046380; text-decoration:underline; } a:visited { color:#A7A37E; text-decoration:none; } a:hover { color:#002F2F; text-decoration:underline; } a:active { color:#046380; text-decoration:none; } </style> </head> <body> <table align='center' width='600' cellpadding='2' cellspacing='2'> <tr> </tr> </table> <table align='center' width='600' style='border: #666666 1px solid;' cellpadding='0' cellspacing='0'> <tr> <td bgcolor='#efefef' style='border-bottom: #666666 1px solid;' ><br> <TABLE border='0' align='center'> <TR> <TD><p style='font-family: arial,  helvetica, sans-serif;font-size: 20px;color: #666666;'><br>GASIP - Gerência autonoma de sistema de iluminação pública</p></TD> </TR> </TABLE> <br> </td> </tr> <tr> <td><TABLE width='510' border='0' cellpadding='0' cellspacing='0' align='center'> <TR> <TD><p style='font-family: arial,  helvetica, sans-serif;font-size: 20px;color: #666666;'><br> Alteração de senha</p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;'>Olá: Fulano de tal </p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Sua senha no GASIP foi alterada para: <b>1234</b> </p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Acesse o sistema com essas credenciais e altere sua senha para uma senha de sua preferência. </p> <table width='200' align='right' border='0' cellspacing='5' cellpadding='5'> <tr>  </tr> </table> <br> <table width='100%' align='center' cellpadding='5' cellspacing='5' style='border: #666666 1px solid;'> <tr>  <td width='98%'><p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'><b>GASIP - Gerênciamento autonomo de sistema de iluminação pública</b></p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Esta é uma mensagem automática, por favor não responda, qualquer dúvida entre em contato com a central de suporte do sistema.</p> </td> </tr> </table> <br> <font face='Verdana, Arial, Helvetica, sans-serif' color='#666666' size='1'><p>ENERTEC<br /><br />Rua São Vicente, 115 – Tamarineira Recife – PE – CEP: 52051-160</p> <p>Fones: (81) 3268 0815 (81) 3268 8766  FAX: (81) 3268 8766</p> <p>enertec@enertecpe.com.br</p><br /><a href='#'></a></font><br><br> </TD> </TR> </TABLE> </table> </body> </html>";

		mbp1.setContent(templateEmail, "text/html");

		// Cria o Multipart a adiciona as duas partes a ele
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);

		message.setContent(mp);

		Transport.send(message);
	}

	public void enviarEmailOrdem(Object ordemServico, byte[] anexo) throws MessagingException, IOException {

		Properties props = new Properties();

		// quem estiver utilizando um SERVIDOR PROXY descomente essa parte e
		// atribua as propriedades do SERVIDOR PROXY utilizado
		/*
		 * props.setProperty("proxySet","true");
		 * props.setProperty("socksProxyHost","192.168.155.1"); // IP do Servidor Proxy
		 * props.setProperty("socksProxyPort","1080"); // Porta do servidor Proxy
		 */

		props.put(MAIL_TRANSPORT_PROTOCOL, SMTP_PROTOCOL);
		props.put(MAIL_SMTP_STARTTLS_ENABLE, "true");
		props.put(MAIL_SMTP_HOST, mailSMTPServer);
		props.put(MAIL_SMTP_AUTH, "true");
		props.put(MAIL_SMTP_USER, "gasip@gmail.com.br");
		props.put(MAIL_DEBUG, "true");
		props.put(MAIL_SMTP_PORT, mailSMTPServerPort);
		props.put(MAIL_SMTP_SOCKET_FACTORY_PORT, mailSMTPServerPort);
		props.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, JAVAX_NET_SSL_SSL_SOCKET_FACTORY);
		props.put(MAIL_SMTP_SOCKET_FACTORY_FALLBACK, "false");

		SimpleAuth auth = null;
		auth = new SimpleAuth(USUARIO, SENHA);

		Session session = Session.getDefaultInstance(props, auth);
		session.setDebug(true);

		MimeMessage message = new MimeMessage(session);
		InternetAddress fromMessage = new InternetAddress("gasip@gmail.com");
		message.setFrom(fromMessage);
		InternetAddress to = new InternetAddress("");
		message.addRecipient(Message.RecipientType.TO, to);
		message.setSubject("Existe uma ordem de serviço aberta para você.");

		// cria a primeira parte da mensagem
		MimeBodyPart mbp1 = new MimeBodyPart();

		String templateEmail = "<html> <head> <meta http-equiv='Content-Type' content='text/html;charset=utf-8' > <title>GASIP - Gerênciamento autonomo de sistema de iluminação pública</title> <style> a:link { color:#046380; text-decoration:underline; } a:visited { color:#A7A37E; text-decoration:none; } a:hover { color:#002F2F; text-decoration:underline; } a:active { color:#046380; text-decoration:none; } </style> </head> <body> <table align='center' width='600' cellpadding='2' cellspacing='2'> <tr> </tr> </table> <table align='center' width='600' style='border: #666666 1px solid;' cellpadding='0' cellspacing='0'> <tr> <td bgcolor='#efefef' style='border-bottom: #666666 1px solid;' ><br> <TABLE border='0' align='center'> <TR> <TD><p style='font-family: arial,  helvetica, sans-serif;font-size: 20px;color: #666666;'><br>GASIP - Gerência autonoma de sistema de iluminação pública</p></TD> </TR> </TABLE> <br> </td> </tr> <tr> <td><TABLE width='510' border='0' cellpadding='0' cellspacing='0' align='center'> <TR> <TD><p style='font-family: arial,  helvetica, sans-serif;font-size: 20px;color: #666666;'><br> Ordem de Serviço</p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 15px;color: #666666;'>Olá: <b>"
				+ ""
				+ "</b></p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Foi aberta e encaminhada para você a ordem de serviço com o seguinte protocolo: <b>"
				+ ""
				+ "</b> </p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Para visualizar a ordem completa faça o donwload do arquivo PDF no anexo. </p> <table width='200' align='right' border='0' cellspacing='5' cellpadding='5'> <tr>  </tr> </table> <br> <table width='100%' align='center' cellpadding='5' cellspacing='5' style='border: #666666 1px solid;'> <tr>  <td width='98%'><p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'><b>GASIP - Gerênciamento autonomo de sistema de iluminação pública</b></p> <p style='font-family: arial,  helvetica, sans-serif;font-size: 12px;color: #666666;'>Esta é uma mensagem automática, por favor não responda, qualquer dúvida entre em contato com a central de suporte do sistema.</p> </td> </tr> </table> <br> <font face='Verdana, Arial, Helvetica, sans-serif' color='#666666' size='1'><p>ENERTEC<br /><br />Rua São Vicente, 115 – Tamarineira Recife – PE – CEP: 52051-160</p> <p>Fones: (81) 3268 0815 (81) 3268 8766  FAX: (81) 3268 8766</p> <p>enertec@enertecpe.com.br</p><br /><a href='#'></a></font><br><br> </TD> </TR> </TABLE> </table> </body> </html>";

		mbp1.setContent(templateEmail, "text/html");

		// cria a segunda parte da mensagem
		MimeBodyPart mbp2 = new MimeBodyPart();

		// anexa o arquivo à mensagem // Colocar o caminho do arquivo no
		// servidor.
		ByteArrayDataSource ds = new ByteArrayDataSource(anexo, "application/pdf");
		mbp2.setDataHandler(new DataHandler(ds));
		mbp2.setFileName("Ordem servico");

		// Cria o Multipart a adiciona as duas partes a ele
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(mbp2);

		message.setContent(mp);

		Transport.send(message);
	}

}

class SimpleAuth extends Authenticator {
	public String username = null;
	public String password = null;

	public SimpleAuth(String user, String pwd) {
		username = user;
		password = pwd;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
