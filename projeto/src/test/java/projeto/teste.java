package projeto;

import static org.junit.Assert.*;

import org.junit.Test;

public class teste {

	@Test
	public void test() {
		Operacoes p = new Operacoes(1,2);
			assertEquals(3,p.somar());
			assertEquals(-1, p.subtrair());
			assertEquals(2, p.multiplicar());
		}
	

}
