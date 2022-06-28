package TP2PROG3;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T>{

	HashMap<String, ArrayList<Arco>> vertices = new HashMap<>();
	ArrayList<Arco> arcos = new ArrayList<>();
	ArrayList<String> visitados = new ArrayList<>();
	ArrayList<String> fila = new ArrayList<>();
	HashMap<String, String> dfsVisitados = new HashMap<>();
	
	private int tiempo = 0;
	
	public ArrayList<ArrayList<String>> dfs(String origen) {
		for (String g: vertices.keySet()) {
			dfsVisitados.put(g, "blanco");
		}
		
		ArrayList<ArrayList<String>> aux = new ArrayList<>();
		
		aux = dfs_visit(origen, origen);
		
		return aux;
	}
	
	public ArrayList<ArrayList<String>> dfs_visit(String genero, String origen) {
		ArrayList<Arco> adyacentes = vertices.get(genero);
		Iterator <Arco> it = adyacentes.iterator();
		ArrayList<ArrayList<String>> solucion = new ArrayList<>();
		dfsVisitados.put(genero, "amarillo");
		
		
		
		while(it.hasNext()) {
			Arco sig = it.next();
			String generoSig = sig.getVerticeDestino();
			
			if(dfsVisitados.get(generoSig).equals("blanco")) {
				ArrayList<ArrayList<String>> aux = dfs_visit(generoSig, origen);
				 for (ArrayList<String> arrayList : aux) {
					arrayList.add(0, genero);
					solucion.add(arrayList);
				}
				 
				
			} else if (generoSig.equals(origen)) {
				ArrayList<String> aux = new ArrayList<>();
				aux.add(genero);
				solucion.add(aux);
				return solucion;
			}
			
			
		}
		
		dfsVisitados.put(genero, "blanco");
		
		
		return solucion;
	}
	
	public ArrayList<String> generosMasBuscados(String genero, int cantidad) {
		ArrayList<String> solucion = new ArrayList<>();
		ArrayList<Arco> adyacentes = this.vertices.get(genero); //arcos que salen de genero 
		Collections.sort(adyacentes);
		for (int i = 0; i < cantidad; i++) {
			solucion.add(adyacentes.get(i).getVerticeDestino());
		}
		return solucion;
	}
	
	public ArrayList<String> secuenciaMayorValor(String generoOrigen) {
		return greedy(vertices.get(generoOrigen));
	}
	
	
	private ArrayList<String> greedy(ArrayList<Arco> candidatosOrigen) {
		ArrayList<Arco> candidatos = candidatosOrigen;
		ArrayList<String> solucion = new ArrayList<>();
		HashSet<String> visitados = new HashSet<>();
		
		
		String base = candidatosOrigen.get(0).getVerticeOrigen();
		visitados.add(base);
		solucion.add(base);
		
		while(!candidatos.isEmpty()) {
			Arco x = seleccionar(candidatos);
			candidatos.remove(x);
			String destino = x.getVerticeDestino();
			
			if(!visitados.contains(destino)) {
				visitados.add(destino);
				solucion.add(destino);
				candidatos.clear();
				candidatos = new ArrayList<>(this.vertices.get(destino));
			}
		}
		
		return solucion;
	}

	private Arco seleccionar(ArrayList<Arco> candidatos) {
		Collections.sort(candidatos);
		return candidatos.get(0);
	}

	public ArrayList<Arco> dameArcos(String origen) {
		ArrayList<Arco> s = this.vertices.get(origen);
		Collections.sort(s);
		return s;
	}
	
	public void getGeneros(String path){
		String separador = ",";
		Lector lector = new Lector();
		BufferedReader bufferLectura = lector.leerArchivo(path);
		
	 try {
		 int contador = 0;
		 String linea = bufferLectura.readLine();
		 while (linea != null) {
			 String[] generos = linea.split(separador);
			 if(contador > 0) {
				   
				 for (int i = 0; i < generos.length; i++) {
					 //creo vertice
					 this.agregarVertice(generos[i]);
					 //si no es el ultimo
					 if(generos.length-1 > i) {
						 this.agregarArco(generos[i], generos[i+1]);
					 }
				}
			 }
			 
			 
		 contador++;
		 linea = bufferLectura.readLine();
	   
		 }
	 } 
	 catch (IOException e) {
	  e.printStackTrace();
	 }
	 finally {
	  if (bufferLectura != null) {
	   try {
	    bufferLectura.close();
	   } 
	   catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	 }
	 
	}
	
	public void agregarVertice(String verticeId) {
		if(!vertices.containsKey(verticeId)) { //si no existe el vertice
			ArrayList<Arco> arcos = new ArrayList<>(); //creo la lista de arcos vacia
			vertices.put(verticeId, arcos);	//agrego el vertice con su lista de arco
			//System.out.println(vertices);
		}
		
	}

	public void borrarVertice(String verticeId) {
		vertices.remove(verticeId);
			this.borrarArcos(verticeId);
	}


	public void agregarArco(String verticeId1, String verticeId2) {
		for (Arco arco : vertices.get(verticeId1)) {
			if(arco.getVerticeOrigen().equals(verticeId1) && arco.getVerticeDestino().equals(verticeId2)) {
				arco.setEtiqueta(arco.getEtiqueta()+1);
				//System.out.println("-------------" + arco + "//////////////////");
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

	
	
}
