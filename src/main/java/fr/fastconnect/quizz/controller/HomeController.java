package fr.fastconnect.quizz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.fastconnect.quizz.dao.UserDao;
import fr.fastconnect.quizz.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"user", "user_pass"})
public class HomeController {

	private static final Log logger = LogFactory.getLog(HomeController.class);

	@Resource(name="userDao")
	private UserDao userDao;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/home"})
	public String home(final Locale locale) {
		return "home";
	}
  
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register")
	public String register() {
		return "register";
	}
    
	private static final char [] ALL_CHARS = {'a','b','c','d','e','f','g','h','i','j','k','m','n','o','p','q','r','s','t','u','v','w','x','y','z','2','3','4','5','6','7','8','9'};
	private static final Random RANDOM = new Random();
	private static String generatePassword()
	{
		final StringBuilder str = new StringBuilder();
		for (int i=0 ; i<8 ; i++){
			str.append(ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)]);
		}
		return str.toString();
	}
	
	public static String hashPassword(final String password) throws NoSuchAlgorithmException  {
		final MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(password.getBytes());
		BigInteger hash = new BigInteger(1, md5.digest());
		return hash.toString(16);
	}

	@RequestMapping(value = "/confirm_registration")
	public String confirmRegistration(final HttpServletRequest request, final Model model) 
	{
		final String email = request.getParameter("email").trim().toLowerCase();
		
		if (userDao.findFirstByEmail(email)!=null){
			model.addAttribute("errorMessage", "Sorry, user already exists!");
			return "register";
		}

		logger.info("New user registation.");

		final String password = generatePassword();
		String hashedPassword;
		try {
			hashedPassword = HomeController.hashPassword(password.trim());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getStackTrace());
			hashedPassword = password;
		}
		final User user = new User();
		user.setEmail(email);
		user.setPassword(hashedPassword);
		model.addAttribute("user", user);
		 
		logger.info("New user registation (email: "+email+").");
		logger.trace("password: "+password);
			
		final Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.fastconnect.fr");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		final Authenticator auth = new SMTPAuthenticator();
		final Session mailSession = Session.getDefaultInstance(props, auth);
		//mailSession.setDebug(true);

		final String homePage = "http://devoxxquizz.fastconnect.fr:8080/fastcontest/qcm?j_username="+email+"&amp;j_password="+password;
		final StringBuilder str=new StringBuilder();
		str.append("<HTML><BODY>")
		   .append("<P>Welcome to fastcontest Devoxx, try our amazing fastcontest: ")
		   .append("<A href=\"" + homePage + "\">Click here.</A> Have fun! </P>")
		   .append("<P>Username: " + StringEscapeUtils.escapeHtml(email) + "<br />")
		   .append("Password: " + StringEscapeUtils.escapeHtml(password) + "</P>")
		   .append("</BODY></HTML>");
		
		final MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setContent(str.toString(), "text/html");
			message.setFrom(new InternetAddress("no-reply@fastconnect.fr"));
			message.setSubject("FastContest - account creation");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			final Transport transport = mailSession.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		}
		catch (MessagingException ex)
		{
			model.addAttribute("errorMessage", ex.getMessage());
			return "login";
		}
		
		userDao.save(user);
		
		return "confirm_registration";
	}

	private class SMTPAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			final String username = "username";
			final String password = "password";
			return new PasswordAuthentication(username, password);
		}
	}
}
