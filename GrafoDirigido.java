package TP2PROG3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T>{


	HashMap<String, ArrayList<Arco>> vertices = new HashMap<>();
	ArrayList<Arco> arcos = new ArrayList<>();
	ArrayList<String> visitados = new ArrayList<>();
	ArrayList<String> fila = new ArrayList<>();
	private int tiempo = 0;
	
	
	public void agregarVertice(String verticeId) {
		if(!vertices.containsKey(verticeId)) { //si no existe el vertice
			ArrayList<Arco> arcos = new ArrayList<>(); //creo la lista de arcos vacia
			vertices.put(verticeId, arcos);	//agrego el vertice con su lista de arco
			System.out.println(vertices);
		}
		
	}

	public void borrarVertice(String verticeId) {
		vertices.remove(verticeId);
			this.borrarArcos(verticeId);
	}


	public void agregarArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				arco.setEtiqueta(arco.getEtiqueta()+1);
				System.out.println("-------------" + arco + "//////////////////");
				return;
			}
		}
		Arco<T> arco = new Arco<T>(verticeId1, verticeId2, 1);
		arcos.add(arco);
		vertices.get(verticeId1).add(arco);
	}

	public void borrarArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				arcos.remove(arco);
			}
		}
	}
	
	public void borrarArcos(String verticeId1) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1)) {
				arcos.remove(arco);
			}
		}
	}

	
	public boolean contieneVertice(String verticeId) {
		return vertices.containsKey(verticeId);
	}

	
	public boolean existeArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				return true;
			}
		}
		return false;
	}

	public Arco<T> obtenerArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				return arco;
			}
		}
		return null;
	}

	public int cantidadVertices() {
		return vertices.size();
	}

	public int cantidadArcos() {
		return arcos.size();
	}

	public Iterator<Integer> obtenerVertices() {
		Iterator it = vertices.keySet().iterator();
		return it;
	}

	public Iterator<Integer> obtenerAdyacentes(String verticeId) {
		Iterator it = vertices.get(verticeId).iterator();
		return it;
	}

	public Iterator<Arco<T>> obtenerArcos() {
		Iterator it = arcos.iterator();
		return it;
	}

	public Iterator<Arco<T>> obtenerArcos(String verticeId) {
		ArrayList<Arco> aux = new ArrayList<>();
		for (Arco arco : aux) {
			if(arco.getVerticeOrigen().equals(verticeId)) {
				aux.add(arco);
			}
		}
		Iterator it = aux.iterator();
		return it;
	}

	/*
	public void bfs() {
		visitados.clear();
		fila.clear();
		for (String v : vertices.keySet()) {
			if(!visitados.contains(v)) {
				bfs_visit(v);
			}	
		}
	}
	
	public void bfs_visit(String v) {
		visitados.add(v);
		fila.add(v);
		if(!fila.isEmpty()) {
			String x = fila.get(0);
			for (String vAdyacente: vertices.get(x)) {
				if(!visitados.contains(vAdyacente)) {
					visitados.add(vAdyacente);
					fila.add(vAdyacente);
				}
			}
		}
	}*/
	
	
}
