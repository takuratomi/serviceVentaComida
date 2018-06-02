
package co.edu.usbcali.test.utilitys;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.SimpleFormatter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.dto.ProductoDTO;

public class EmailTest {

	private static Logger log = LoggerFactory.getLogger(EmailTest.class);

	@Test
	public void test() {
	}
	// Recipient's email ID needs to be mentioned.
	// String to = "toshkura@gmail.com";
	//
	// // Sender's email ID needs to be mentioned
	// String from = "toshkura@gmail.com";
	//
	// // Assuming you are sending email from localhost
	// String host = "localhost";
	//
	// // Get system properties
	// Properties properties = System.getProperties();
	//
	// // Setup mail server
	// properties.put("mail.smtp.host", "smtp.gmail.com");
	// properties.put("mail.smtp.socketFactory.port", "465");
	// properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	// properties.put("mail.smtp.auth", "true");
	// properties.put("mail.smtp.port", "465");
	//
	// properties.setProperty("mail.user", "toshkura@gmail.com");
	// properties.setProperty("mail.password", "Objet5313485");
	//
	// // Get the default Session object.
	// Session session = Session.getDefaultInstance(properties);
	//
	//
	// try {
	// // Create a default MimeMessage object.
	// MimeMessage message = new MimeMessage(session);
	//
	// // Set From: header field of the header.
	// message.setFrom(new InternetAddress(from));
	//
	// // Set To: header field of the header.
	// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	//
	// // Set Subject: header field
	// message.setSubject("This is the Subject Line!");
	//
	// // Now set the actual message
	// message.setText("Correo electronico de prueba");
	//
	// // Send message
	// Transport.send(message);
	// System.out.println("Sent message successfully....");
	// } catch (MessagingException mex) {
	// mex.printStackTrace();
	// System.out.println();
	//
	// }

	@Test
	public void hasmapToArrayTest() {

		BigDecimal id = new BigDecimal(0);
		String name = "PRODUCTO_";
		Map<String, ProductoDTO> hasMapProductosDTO;
		ProductoDTO productoDTO;

		hasMapProductosDTO = new HashMap<>();
		productoDTO = new ProductoDTO();
		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);

		productoDTO = new ProductoDTO();
		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);

		productoDTO = new ProductoDTO();

		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);

		productoDTO = new ProductoDTO();

		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);

		productoDTO = new ProductoDTO();

		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);

		productoDTO = new ProductoDTO();

		productoDTO.setId(id.add(new BigDecimal(1)));
		productoDTO.setNombre(name + id.toString());
		hasMapProductosDTO.put(id.toString(), productoDTO);
		Object[] arrayProd = null;
		try {
			arrayProd = hasMapProductosDTO.values().toArray();
		} catch (Exception e) {
			e.getMessage();
		}

		// for (Objet productoDTO2 : arrayProd) {
		//
		// log.info("id " + productoDTO2.getId() + " nombre " +
		// productoDTO2.getNombre());
		//
		// }

	}

	@Test
	public void calendarTest() {
		// 1,2,3,4,5 descartar 6 y 7
		String[] days = new String[] { "D", "L", "M", "MI", "J", "V", "S" };

		String[] dateDays = new String[5];		

		SimpleDateFormat patter = new SimpleDateFormat("dd-MM-YYYY");
		Calendar cal = Calendar.getInstance();
		Date actualDAte = cal.getTime();

		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day == 2) {
			dateDays[0] = "L-" + patter.format(cal.getTime());
			dateDays[1] = "M-" + (Integer.parseInt(dateDays[0].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[2] = "MI-" + (Integer.parseInt(dateDays[1].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[3] = "J-" + (Integer.parseInt(dateDays[2].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[4] = "V-" + (Integer.parseInt(dateDays[3].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
		}
		if (day == 3) {
            dateDays[1] = "M-" + patter.format(cal.getTime());
            dateDays[0] = "L-" + (Integer.parseInt(dateDays[1].split("-")[1]) - 1) + "-" + dateDays[1].split("-")[2]
                    + "-" + dateDays[1].split("-")[3];
            dateDays[2] = "MI-" + (Integer.parseInt(dateDays[1].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
                    + "-" + dateDays[0].split("-")[3];
            dateDays[3] = "J-" + (Integer.parseInt(dateDays[2].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
                    + "-" + dateDays[0].split("-")[3];
            dateDays[4] = "V-" + 01 + "-" + "06"
                    + "-" + dateDays[0].split("-")[3];
        }
		if (day == 4) {
			dateDays[2] = "MI-" + patter.format(cal.getTime());
			dateDays[1] = "M-" + (Integer.parseInt(dateDays[2].split("-")[1]) - 1) + "-" + dateDays[2].split("-")[2]
					+ "-" + dateDays[2].split("-")[3];
			dateDays[0] = "L-" + (Integer.parseInt(dateDays[1].split("-")[1]) - 1) + "-" + dateDays[1].split("-")[2]
					+ "-" + dateDays[1].split("-")[3];
			dateDays[3] = "J-" + (Integer.parseInt(dateDays[2].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[4] = "V-" + (Integer.parseInt(dateDays[3].split("-")[1]) + 1) + "-" + dateDays[0].split("-")[2]
					+ "-" + dateDays[0].split("-")[3];
		}
		if (day == 5) {	
						
			dateDays[3] = "J-" + patter.format(cal.getTime());
			dateDays[2] = "MI-" + (Integer.parseInt(dateDays[3].split("-")[1]) - 1) + "-" + dateDays[3].split("-")[3]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[1] = "M-" + (Integer.parseInt(dateDays[2].split("-")[1]) - 1) + "-" + dateDays[2].split("-")[2]
					+ "-" + dateDays[2].split("-")[3];
			dateDays[0] = "L-" + (Integer.parseInt(dateDays[1].split("-")[1]) - 1) + "-" + dateDays[1].split("-")[2]
					+ "-" + dateDays[1].split("-")[3];
			dateDays[4] = "V-" + (Integer.parseInt(dateDays[3].split("-")[1]) + 1) + "-" + dateDays[3].split("-")[2]
					+ "-" + dateDays[3].split("-")[3];
		}
		if (day == 5) {
			dateDays[4] = "V-" + patter.format(cal.getTime());
			dateDays[3] = "J-" + (Integer.parseInt(dateDays[4].split("-")[1]) - 1) + "-" + dateDays[4].split("-")[2]
					+ "-" + dateDays[4].split("-")[3];
			dateDays[2] = "MI-" + (Integer.parseInt(dateDays[3].split("-")[1]) - 1) + "-" + dateDays[3].split("-")[3]
					+ "-" + dateDays[0].split("-")[3];
			dateDays[1] = "M-" + (Integer.parseInt(dateDays[2].split("-")[1]) - 1) + "-" + dateDays[2].split("-")[2]
					+ "-" + dateDays[2].split("-")[3];
			dateDays[0] = "L-" + (Integer.parseInt(dateDays[1].split("-")[1]) - 1) + "-" + dateDays[1].split("-")[2]
					+ "-" + dateDays[1].split("-")[3];			
		}
		
		
		for (String string : dateDays) {
			System.out.println(string);
		}

	}

}
