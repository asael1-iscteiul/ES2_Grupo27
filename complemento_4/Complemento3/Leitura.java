package Covid19;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	private boolean existe = false;


	public boolean leitura(String path) throws IOException {
		BufferedReader rd = new BufferedReader(new FileReader("metadata.html"));
		String line;
		String a = "<a href='"+path+"'>";
		try {
			System.out.println("vou entrar no...");
			while ((line = rd.readLine()) != null) {
				System.out.println("dentro read line " + line);
				if(line.contains(a)) {
					System.out.println("existe ");
					existe = true;
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("ficheiro...");
		}
		return existe;
	}
	
	
//	public static void main(String[] args) {
//		try {
//			boolean b = new Leitura().leitura("C:/Users/Aladje Sanha/OneDrive/Documentos/Universidade/3ºAno_2ºSemestre/ES 2/Ficheiro/biology-09-00094.pdf");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}