package covid_evolution_diff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class App {
	
	ArrayList<Ref> objDuasTagsAnteriores;
	ArrayList<String> duasTagsAnteriores;
	ArrayList<byte[]> doisFicheirosAnteriores;
	ArrayList<RevCommit> doisFicheirosRecentes;
	private List<String> ficheiroNovo;
	private List<String> ficheiroAntigo;
	
	public void imports() throws InvalidRemoteException, TransportException, GitAPIException, IOException {

		//Eliminar pagina se já houver
		File f = new File("C:/Users/UX411/Desktop/ES2/repo");
		if (f.exists()) {
			FileUtils.cleanDirectory(f);
			FileUtils.forceDelete(f);
		}

		//Clonar repositorio
		
		Git git = Git.cloneRepository()
		  .setURI("https://github.com/vbasto-iscte/ESII1920.git")
		  .setDirectory(new File("C:/Users/UX411/Desktop/ES2/repo"))
		  .call();

		// Lista 2 ultimas tags
		
		List<Ref> allTags = git.tagList().call();

		duasTagsAnteriores = new ArrayList<String>();
		objDuasTagsAnteriores = new ArrayList<Ref>();

		
		allTags.subList(allTags.size() - 2, allTags.size()).forEach(tag -> {

			int n= tag.toString().indexOf('=');
			int i=n+1;
			int f1= tag.toString().indexOf('(') ;

			duasTagsAnteriores.add(tag.toString().substring(i,f1));
			objDuasTagsAnteriores.add(tag);
		});
		
		// 2 Versoes anteriores do ficheiro covid19spreading.rdf
		
		doisFicheirosAnteriores = new ArrayList<>();
		doisFicheirosRecentes = new ArrayList<>();
		
		git.log().addPath("covid19spreading.rdf").call().forEach( commit -> {
			
			if (doisFicheirosRecentes.contains(commit.name().toString())) {
				
				try {
					TreeWalk tw = TreeWalk.forPath(git.getRepository(), "covid19spreading.rdf", commit.getTree());
					ObjectId oi = tw.getObjectId(0);
					
					ObjectReader or = git.getRepository().newObjectReader();
					ObjectLoader ol = or.open(oi);
					doisFicheirosAnteriores.add(ol.getBytes());
					doisFicheirosRecentes.add(commit);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		ficheiroNovo = Arrays.asList(new String(doisFicheirosAnteriores.get(0), StandardCharsets.UTF_8).split("\\r?\\n"));
		ficheiroAntigo = Arrays.asList(new String(doisFicheirosAnteriores.get(1), StandardCharsets.UTF_8).split("\\r?\\n"));
		
		// Colocar strings em ficheiros para poder fazer a deteçao dos ficheiros
		
		File obj1 = new File("fichAntigo.rdf");
		obj1.setWritable(true);
		File obj2 = new File("fichNovo.rdf");
		obj2.setWritable(true);

		FileWriter fich1 = new FileWriter("fichAntigo.rdf");
		for (String s : ficheiroAntigo) {
			fich1.write(s);
		}
		
		FileWriter fich2 = new FileWriter("fichNovo.rdf");
		for (String s : ficheiroNovo) {
			fich2.write(s);
		}
		
		FileDiff fd = new FileDiff(obj1, obj2);
	
	}

	public static void main(String[] args)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		
		App aplication = new App();
		aplication.imports();
		
	    

	}
}
