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
	ArrayList<String> visitados = new ArrayList<>();
	HashMap<String, String> dfsVisitados = new HashMap<>();
	private int valorSecuencia = 0;
	ArrayList<String> verticess = new ArrayList<>();
	
	public GrafoDirigido dfs(String origen) {
		GrafoDirigido aux = new GrafoDirigido();
		ArrayList<ArrayList<String>> solucion = new ArrayList<>();
		
		if(vertices.containsKey(origen)) {
			for (String g: vertices.keySet()) {
				dfsVisitados.put(g, "blanco");
			}
			
			solucion = dfs_visit(origen, origen);
			
			for (ArrayList<String> arrayList : solucion) {
				for (int i = 0; i < arrayList.size(); i++) {
					aux.agregarVertice(arrayList.get(i));
					if(i+1 < arrayList.size()) {
						aux.agregarArco(arrayList.get(i), arrayList.get(i+1));
					}
				}
			}
			
			return aux;
		} else {
			System.out.println("El genero ingresado no se encontro, pruebe ingresando otro");
			return aux;
		}
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
				aux.add(origen);
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
		if(adyacentes != null) {
			Collections.sort(adyacentes);
			if(adyacentes.size() < cantidad) {
				System.out.println("Debido a que luego de buscar " + genero + " no se han realizado mas busquedas que la cantidad ingresada, "
						+ "esta ha sido modificada de " + cantidad + " a " + adyacentes.size());
				cantidad = adyacentes.size();
			}
			for (int i = 0; i < cantidad; i++) {
				solucion.add(adyacentes.get(i).getVerticeDestino());
			}
		} else {
			System.out.println("El genero no se ha encontrado. Busque otro");
		}
		
		return solucion;
	}
	
	public ArrayList<String> secuenciaMayorValor(String generoOrigen) {
		if(vertices.containsKey(generoOrigen)) {
			return greedy(vertices.get(generoOrigen));
		} else {
			System.out.println("no existe genero");
			return new ArrayList<String>();
		}
		
	}
	
	private ArrayList<String> greedy(ArrayList<Arco> candidatosOrigen) {
		ArrayList<Arco> candidatos = candidatosOrigen;
		ArrayList<String> solucion = new ArrayList<>();
		HashSet<String> visitados = new HashSet<>();
		
		
		String base = candidatosOrigen.get(0).getVerticeOrigen();
		visitados.add(base);
		solucion.add(base);
		this.valorSecuencia += candidatosOrigen.get(0).getEtiqueta();
		
		while(!candidatos.isEmpty()) {
			Arco x = seleccionar(candidatos);
			candidatos.remove(x);
			String destino = x.getVerticeDestino();
			
			if(!visitados.contains(destino)) {
				visitados.add(destino);
				solucion.add(destino);
				this.valorSecuencia += x.getEtiqueta();
				candidatos.clear();
				candidatos = new ArrayList<>(this.vertices.get(destino));
			}
		}
		System.out.println("El valor de secuencia es: " + this.valorSecuencia + " y la secuencia es: ");
		return solucion;
	}

	private Arco seleccionar(ArrayList<Arco> candidatos) {
		Collections.sort(candidatos);
		return candidatos.get(0);
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
		}
		
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
		vertices.get(verticeId1).add(arco);
	}

	
	@Override
	public String toString() {
		String solucion = "";
		for (String s : vertices.keySet()) {
			for (Arco arco : vertices.get(s)) {
				solucion += arco.getVerticeOrigen() + "--" + arco.getEtiqueta() + "-->" + arco.getVerticeDestino() + " ";
			}
		}
		return solucion;
	}

	


}
