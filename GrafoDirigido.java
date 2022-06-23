package TP2PROG3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {


	HashMap<String, ArrayList<String>> vertices = new HashMap<>();
	ArrayList<Arco> arcos = new ArrayList<>();
	ArrayList<String> visitados = new ArrayList<>();
	ArrayList<String> fila = new ArrayList<>();
	private int tiempo = 0;
	
	
	@Override
	public void agregarVertice(String verticeId) {
		if(!vertices.containsKey(verticeId)) {
			ArrayList<String> adyacentes = new ArrayList<>();
			vertices.put(verticeId, adyacentes);
			System.out.println(vertices);
		}
		
	}

	@Override
	public void borrarVertice(String verticeId) {
		vertices.remove(verticeId);
			this.borrarArcos(verticeId);
	}

	@Override
	public void agregarArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				arco.setEtiqueta(arco.getEtiqueta()+1);
				return;
			}
		}
		
		vertices.get(verticeId1).add(verticeId2);
		Arco<T> arco = new Arco<T>(verticeId1, verticeId2, 1);
		arcos.add(arco);
		
	}

	@Override
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

	@Override
	public boolean contieneVertice(String verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(String verticeId1, String verticeId2) {
		for (Arco arco : arcos) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				return arco;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		return arcos.size();
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		Iterator it = vertices.keySet().iterator();
		return it;
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(String verticeId) {
		Iterator it = vertices.get(verticeId).iterator();
		return it;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Iterator it = arcos.iterator();
		return it;
	}

	@Override
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
		
		
		
	}
	
	
}
