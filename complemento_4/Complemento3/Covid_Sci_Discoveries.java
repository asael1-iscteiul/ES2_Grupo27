package Covid19;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.metadata.model.DocumentAuthor;
/**
 * @author Aladje Sanha
 */
public class Covid_Sci_Discoveries {
	private List<FileInputStream> pdf = new ArrayList<FileInputStream>();

/**
 * @param path esta é a variavel que devolve a pasta corrente local
 * Esta funcao comeca por criar um ficheiro e depois disso abriu para escrever, 
 * Escreve o conteudo html, 
 * Faz a extraçao dos metadados dos pdf que se encontram na pasta local, 
 * Escreve os seus conteudos no html e posterior mente abriu no browser.
 * 
 */
	public Covid_Sci_Discoveries(String path) throws AnalysisException {
		try {	
			File ficheiro = new File("metadata.html");
			BufferedWriter buffer = new BufferedWriter(new FileWriter(ficheiro));

			String html0 ="<!DOCTYPE html><html> <head> <title> Covid_Sci_Discoveries </title>"+
					"<!-- css --> <link rel='stylesheet' href='main.css' type='text/css' media='screen,projection'/>"+
					"</head><body><h1>Metadados dos ficheiros</h1><table><tr>"+
					"<th> Article title </th> <th> Journal name </th> <th> Publication year </th> <th> Authors </th></tr>";
			buffer.write(html0);

			ContentExtractor extractor = new ContentExtractor();
			File dirAtual = new File(path);
			File[] files = dirAtual.listFiles();
			System.out.println("Inicio da leitura dos ficheiros! " + files.toString());
			for (File file : files) { 
				if (file.getName().endsWith((".pdf"))) {
					System.out.println("ficheiros que estao na pasta: " + file.getName());

					String path1 = path+"\\"+ file.getName().toString();	

					FileInputStream inputStream = new FileInputStream(path1);
					extractor.setPDF(inputStream);

					List<DocumentAuthor> listaAutores = extractor.getMetadata().getAuthors();
					String authors = listaAutores.get(0).getName();

					for(DocumentAuthor author: listaAutores) {
						String author1 = author.getName();
						if(!author1.equals(authors)) {
							authors = authors+", "+author1;
						}
					}

					String title = extractor.getMetadata().getTitle().toString();
					System.out.println(title);

					String journal = extractor.getMetadata().getJournal().toString();
					System.out.println(journal);

					String pubYear = extractor.getMetadata().getDate("published").getYear().toString();
					System.out.println(pubYear);

					String html= "<tr>"+
							"<td><a href='"+path1+"' target=_blank>"+title+"</a></td>"+
							"<td>"+ journal +"</td>"+
							"<td>"+ pubYear+"</td>"+
							"<td>"+ authors +"</td> </tr>";
					buffer.write(html);
					System.out.println("Os autores sao : " + authors);	
				}
			}

			String html1= "</table></body></html>";
			buffer.write(html1);
			buffer.close();

			Desktop ambiente = Desktop.getDesktop();
			ambiente.open(ficheiro);

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	public static void main(String[] args)  {
		try {
			String currentDir = System.getProperty("user.dir");
			System.out.println("Pasta atual: "+ currentDir);
			Covid_Sci_Discoveries c = new Covid_Sci_Discoveries(currentDir);
		} catch (AnalysisException e) {
			e.printStackTrace();
		}
	}

}
