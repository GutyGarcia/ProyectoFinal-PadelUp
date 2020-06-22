package proyectoPadelUp;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import proyectoPadelUp.controladores.ControladorJornadaEnJuego;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Usuario;

public class Email implements Runnable{

	private List<String> emails;
		
	public Email()
	{
	}
	
	public Email(List<String> emails)
	{
		this.emails = emails;
	}
	
	public void sendMail(String recepient) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		Usuario usuario = new Usuario();
		UsuariosDao usuariosDao = new UsuariosDao();
		usuario = usuariosDao.buscarUsuarioPorId(AplicacionPadelUp.getUserId());

		final String cuenta = usuario.getEmail();
		final String contraseña = usuario.getContraseñaEmail();

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(cuenta, contraseña);
			}
		});

		Message mensaje = prepareMessage(session, cuenta, recepient);
		Transport.send(mensaje);
	}

	private Message prepareMessage(Session session, String cuenta, String recepient) {
		try {

			Message mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(cuenta));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			mensaje.setSubject("Convocatoria");
			mensaje.setText(ControladorJornadaEnJuego.getCuerpoEmail());
			return mensaje;
		} catch (Exception ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public boolean mailCorrecto(String correo) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
		mat = pat.matcher(correo);
		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run(){
		for(String email : emails)
		{
			try {
				sendMail(email);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
