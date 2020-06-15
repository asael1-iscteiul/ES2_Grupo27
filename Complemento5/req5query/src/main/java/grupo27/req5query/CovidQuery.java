package grupo27.req5query;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.util.FileUtils;
import org.eclipse.jgit.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 * 
 * This class will get the file covid19spreading obtained in the git repositories, 
 * @author Andreia Fernandes
 *
 */
public class CovidQuery {

	private static String localPath;
	private static Repository localRepo;
	private static Git git;
	private static String remotePath;
	private String respostaRegiao = "Lisboa";
	private String respostaAssunto = "Testes";

	public CovidQuery() {

	}

	/**
	 * 
	 * This method will obtain the file and place it in the directory specified in the 
	 * @param path 
	 */
	public void getFicheiro(String path) {

		localPath = path; // "C:/Users/Andreia Fernandes/Documents/3ano/2semestre/ES/Query"
		remotePath = "https://github.com/vbasto-iscte/ESII1920.git";
		File file = new File(localPath);
		CloneCommand cloneCmd = git.cloneRepository();

		try {
			cloneCmd.setURI(remotePath).setDirectory(file).call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		try {
			localRepo = new FileRepository(localPath + "/.git");
		} catch (IOException e) {
			e.printStackTrace();
		}
		git = new Git(localRepo);

		PullCommand pullCmd = git.pull();
		try {
			pullCmd.call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 
	 *This method had the objective of obtaining the values ​​selected by the visitor to
	 * later search for the information in the file "covid19spreading.rdf", it was not correctly developed 
	 * because it was unable to access these values ​​in the html file and obtain them in java.
	 * @throws IOException
	 */

	public void getValues() throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader("index.html"));
		String line;
		String a = "result";
		System.out.println("vou entrar no...");
		while ((line = rd.readLine()) != null) {
			// (System.out.println("dentro read line " + line);
			// if(line.contains(a)) {
			// System.out.println("existe " +line);
			// }
		}

	}

	/**
	 * 
	 *Method to search for the desired information selected by the visitor,
	 *in this case examples of responses were given for possible queries, 
	 *since it was not possible to access the values ​​obtained on the website as stated above
	 * @param path
	 */
	public void xml(String path) {
		try {
			File inputFile = new File(path + "/covid19spreading.rdf");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			String query = "/RDF/NamedIndividual/@*";
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile(query);
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				{
					String op = org.apache.commons.lang3.StringUtils.substringAfter(nl.item(i).getNodeValue(), "#");
					if (this.respostaRegiao.equals(op)) {
						if (this.respostaAssunto.equals("Testes")) {

							query = "//*[contains(@about,op)]/Testes/text()";
							System.out.println("Query para obter o número de testes feitos na região " + op + ": ");
							expr = xpath.compile(query);
							System.out.println(expr.evaluate(doc, XPathConstants.STRING));

						} else if (this.respostaAssunto.equals("Infecoes")) {
							query = "//*[contains(@about,op)]/Infecoes/text()";
							System.out.println("Query para obter o número de infeções nna região " + op + ": ");
							expr = xpath.compile(query);
							System.out.println(expr.evaluate(doc, XPathConstants.STRING));

							String ola = "Algarve";
							String ola2 = "Testes";

						} else if (this.respostaAssunto.equals("Internamentos")) {

							query = "//*[contains(@about,op)]/Internamentos/text()";
							System.out.println("Query para obter o número de internamentos na região " + op + ": ");
							expr = xpath.compile(query);
							System.out.println(expr.evaluate(doc, XPathConstants.STRING));
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * This method deletes a diretory and its files 
	 * @param file
	 * @throws IOException
	 */
	public static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();

		}
	}
	/**
	 * 
	 *this method creates a directory where the obtained file will be placed 
	 *and deletes (using the method above) if it already exists to be able to create it again
	 * @return
	 */
	public String criarDir() {
		CovidQuery qq = new CovidQuery();
		String currentDir = System.getProperty("user.dir");
		File directory = new File(currentDir + "/ficheiro");
		if (!directory.exists()) {
			qq.getFicheiro(currentDir + "/ficheiro");
		} else {
			try {
				delete(directory);
				qq.getFicheiro(currentDir + "/ficheiro");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return currentDir + "/ficheiro";
	}

	public static void main(String[] args)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		CovidQuery qq = new CovidQuery();
		String dir = qq.criarDir();
		qq.getValues();

		qq.xml(dir);
		File f = new File("index.html");
		Desktop ambiente = Desktop.getDesktop();
		ambiente.open(f);
	}

}
