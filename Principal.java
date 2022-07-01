package TP2PROG3;

public class Principal {

	public static void main(String[] args) {
		GrafoDirigido b1 = new GrafoDirigido();
		Timer t1 = new Timer();
		String pathLectura = "dataset2tp2.csv";
		b1.getGeneros(pathLectura);
		t1.start();
		//System.out.println(b1.generosMasBuscados("viajes", 3));
		//System.out.println(b1.secuenciaMayorValor("psicología"));
		System.out.println(b1.dfs("cine"));
		System.out.println(t1.stop());
		
		
	}

}
