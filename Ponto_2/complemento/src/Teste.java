
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Teste {

	public static WebDriver driver;
	public static File file = new File("C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/template.html"); 
	public String content = "";
	public int login;
	public int acedida;

	//	@BeforeAll
	//	static void setUpBeforeClass() throws Exception {

	//System.setProperty("webdriver.chrome.driver", "C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/chromedriver_win32/chromedriver.exe");
	//				System.setProperty("webdriver.gecko.driver", "C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/geckodriver-v0.26.0-win64/geckodriver.exe");

	//driver = new ChromeDriver();
	//		driver = new FirefoxDriver();
	//	}

	//	@AfterAll
	//	static void tearDownAfterClass() throws Exception {
	//						 driver.close();   
	// close the tab it has opened
	//				 driver.quit();  
	// close the browser
	//	}

	@Test
	public void test() throws IOException, InterruptedException {
		try {
			try {
				System.setProperty("webdriver.gecko.driver", "C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/geckodriver-v0.26.0-win64/geckodriver.exe");
				driver = new FirefoxDriver();
				System.out.println("firefox");
			} catch (Exception e) {
				System.setProperty("webdriver.chrome.driver", "C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/chromedriver_win32/chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("chrome");
			}

//			System.out.println("abre");
			if(! (file.exists() && !file.isDirectory()) ) { 
	            BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/template.html"));
	            bw.write("<html><head><title>New Page</title></head><body><p> pagina funciona, acedida: 1; </p> <p>login funciona, efetuados: 1; </p>"
						+ "<p> existem repositórios; </p> <p> existem contactos; </p></body></html>");
	            bw.close();
	            System.out.println("ficheiro não existe, criar novo");
	        } 
			System.out.println("ficheiro existe");

			BufferedReader in = new BufferedReader(new FileReader("C:/Users/Rodrigo/Desktop/Esteves/escola/ES2/template.html"));
			System.out.println("aberto o ficheiro");
	        String str;
	        String str2= "";
	        while ((str = in.readLine()) != null) {
	            str2 +=str;
//	            System.out.println(str);
	        }
	        
	        content = str2.replaceAll("\\<.*?>","");
	        String aux[] = content.split(",");
	        
	        //vezes que a pagina foi acedida
	        String aux2[] = aux[1].split(":");
	        String aux3[] = aux2[1].split(";");
	       
//	        System.out.println(aux3[0]);
	        acedida = Integer.parseInt(aux3[0].replace(" ",""));
	        
//	        System.out.println("yooooo");
	       
	        //logins efetuados
	        
	        String aux4[] = aux[2].split(":");
//	        System.out.println(aux[2]);
	        
	        String aux5[]= aux4[1].split(";");
//	        System.out.println(aux4[1]);
//	        System.out.println(aux5[0]);
	        login = Integer.parseInt(aux5[0].replace(" ",""));
	        
	        
        
	       System.out.println("informaçao atualizada");
	        
//	        System.out.println(content + "ff" + acedida );
			
			driver.get("https://www.google.pt");
			driver.get("http://192.168.99.100:8000/");
			acedida ++;

			System.out.println("site aberto");
			WebElement element = driver.findElement(By.linkText("Sample Page"));
            element.click();
			
           System.out.println("contact");
            
			element = driver.findElement(By.className("contact"));
			
			
			
			 System.out.println("june");
			element = driver.findElement(By.linkText("June 2020"));
			
			
			 System.out.println("june done");

			driver.get("http://192.168.99.100:8000/wp-login.php");

			
			
			element =	driver.findElement(By.id("user_login"));
			element.sendKeys("rmves");
			element.click();

			element = driver.findElement(By.id("user_pass"));
			element.sendKeys("Soraka99Main99");
			element.click();

			element.submit();
			login ++;
			
		 System.out.println("login efetuado");
			

			System.out.println("Page title is: " + driver.getTitle());
			
//			throw new Exception();
			
		} catch (Exception e) {


			// Recipient's email ID needs to be mentioned.
			String to = "rodrigo99esteves@gmail.com";

			// Sender's email ID needs to be mentioned
			String from = "diana.e.irelia@gmail.com";
			String pass = "Soraka99Main99";
			String user = "diana.e.irelia@gmail.com";



			// Get system properties
			Properties properties =  new Properties();

			// Setup mail server
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			//		properties.put("mail.smtp.connectiontimeout", "100000");
			//		properties.put("mail.smtp.timeout", "100000");

			
			// Get the default Session object.
			//			Session session = Session.getDefaultInstance(properties);
			Session session = Session.getInstance(properties, 
					new javax.mail.Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			});
			System.out.println("sessão criada");
			
			try {
				System.out.println("try ");
				Transport transport = session.getTransport("smtp");

				transport.connect("smtp.gmail.com",user , pass);


				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("erro!!!");

				// Now set the actual message
				message.setText("ocorreu um erro...");

				System.out.println("transport");

				// Send message
				transport.sendMessage(message, message.getAllRecipients()); 

				
				System.out.println("Sent message successfully....");


			} catch (MessagingException mex) {
				mex.printStackTrace();
			}

		}
		finally {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("<html><head><title>New Page</title></head><body><p> página funciona, acedida:" + acedida + "; </p> <p>login funciona, efetuados:" + login + "; </p>"
					+ "<p> existem repositórios; </p> <p> existem contactos; </p></body></html>");
			bw.close();
			Thread.sleep(5000);
		driver.close();
		System.out.println("over ");
//		driver.quit();
		}
		
		
	}

}
