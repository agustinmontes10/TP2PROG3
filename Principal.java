package TP2PROG3;

public class Principal {

	public static void main(String[] args) {
		GrafoDirigido b1 = new GrafoDirigido();
		String pathLectura = "dataset2tp2.csv";
		b1.getGeneros(pathLectura);
		
		//System.out.println(b1.generosMasBuscados("cine", 3));
		//System.out.println(b1.secuenciaMayorValor("cine"));
		//System.out.println(b1.vertices.get("cine"));
		//System.out.println(b1.dfs("novela"));
		//System.out.println(b1.dameArcos("psicología"));
	}

}
