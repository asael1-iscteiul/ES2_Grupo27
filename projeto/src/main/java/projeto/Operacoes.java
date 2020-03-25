package projeto;

public class Operacoes {

	private int x;
	private int y;
	
	public Operacoes(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int somar() {
		return x+y;
	}
	
	public int subtrair() {
		return x-y;
	}
	
	public int multiplicar() {
		return x*y;
	}
	
	public int dividir() {
		return x/y;
	}
	
	public static void main(String[] args) {
		Operacoes p = new Operacoes(3,4);
		System.out.println(p.somar());
		System.out.println(p.subtrair());
		System.out.println(p.multiplicar());
		System.out.println(p.dividir());
	}
}
