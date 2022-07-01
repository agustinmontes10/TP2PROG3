package TP2PROG3;

import java.util.Objects;

public class Arco<T> implements Comparable<Arco>{

	private String verticeOrigen;
	private String verticeDestino;
	private int etiqueta;

	public Arco(String verticeOrigen, String verticeDestino, int etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public String getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public String getVerticeDestino() {
		return verticeDestino;
	}

	public int getEtiqueta() {
		return etiqueta;
	}
	
	public void sumar() {
		etiqueta++;
	}

	public void setEtiqueta(int etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public String toString() {
		return verticeOrigen + "--"+ etiqueta +"-->" + verticeDestino;
	}
	
	@Override
	public int compareTo(Arco a) {
		return a.getEtiqueta() - this.getEtiqueta();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return etiqueta == other.etiqueta && Objects.equals(verticeDestino, other.verticeDestino)
				&& Objects.equals(verticeOrigen, other.verticeOrigen);
	}
	
	
}

