package covid_evolution_diff;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.diff.CommandVisitor;

public class DetectChanges implements CommandVisitor<Character>  {
	
	private static final String palavraEliminada = "<span style=\"background-color: #FB504B\">${text}</span>";
	private static final String palavraNova = "<span style=\"background-color: #45EA85\">${text}</span>";
	private String esq = "";
	private String dir = "";

	public void visitInsertCommand(Character object) {
		String add = "\n".contentEquals(""+ object) ? "<br/>" : "" + object;
		dir = dir + palavraNova.replace("${text}", "" + add);
		
	}

	public void visitKeepCommand(Character object) {
		String add = "\n".contentEquals(""+ object) ? "<br/>" : "" + object;
		esq = esq + add;
		dir = dir + add;		
	}

	public void visitDeleteCommand(Character object) {
		String add = "\n".contentEquals(""+ object) ? "<br/>" : "" + object;
		esq = esq + palavraEliminada.replace("${text}", "" + add);
	}
	
	public void gerarHtml() throws IOException {
		File f = new File ("C:/Users/UX411/Desktop/ES2/finalDiff.html");
		if (f.exists()) {
			f.delete();
		}
	    Files.copy(new File("C:/Users/UX411/Desktop/ES2/difftemplate.html").toPath(), f.toPath());
		String tema = FileUtils.readFileToString(new File("C:/Users/UX411/Desktop/ES2/finalDiff.html"), "utf-8");
		String esquerda = tema.replace("${left}", esq);
		String total = esquerda.replace("${right}", dir);
		FileUtils.write(new File("C:/Users/UX411/Desktop/ES2/finalDiff.html"), total, "utf-8");
	}

}
