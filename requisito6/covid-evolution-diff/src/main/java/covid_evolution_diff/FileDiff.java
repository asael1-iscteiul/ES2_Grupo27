package covid_evolution_diff;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.text.diff.StringsComparator;

public class FileDiff {

	public FileDiff(File ficheiro1, File ficheiro2) throws IOException {

		LineIterator fich1 = FileUtils.lineIterator(ficheiro1, "utf-8");
		LineIterator fich2 = FileUtils.lineIterator(ficheiro2, "utf-8");

		DetectChanges visitor = new DetectChanges();

		while (fich1.hasNext() || fich2.hasNext()) {

			String esq = "";
			String dir = "";

			if (fich1.hasNext()) {
				esq = fich1.nextLine() + "\n";
			} else {
				esq = "\n";
			}

			if (fich2.hasNext()) {
				dir = fich2.nextLine() + "\n";
			} else {
				dir = "\n";
			}

			StringsComparator comparator = new StringsComparator(esq, dir);

//			if (comparator.getScript().getLCSLength() > (Integer.max(esq.length(), dir.length()) * 0.5)) {
				comparator.getScript().visit(visitor);
//			} else {
//				StringsComparator comparadorEsq = new StringsComparator(esq, "\n");
//				comparadorEsq.getScript().visit(visitor);
//				StringsComparator comparadorDir = new StringsComparator("\n", dir);
//				comparadorDir.getScript().visit(visitor);
//			}

		}
		visitor.gerarHtml();
	}
	
	public static void main(String[] args) throws IOException {

	}
}

