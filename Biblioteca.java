package TP2PROG3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Biblioteca {

	private GrafoDirigido grafoGeneros;
	
	public Biblioteca() {
		grafoGeneros = new GrafoDirigido();
	}
	
	public void getGeneros(){
		String separador = ",";
		Lector lector = new Lector();
		BufferedReader bufferLectura = lector.leerArchivo("dataset1tp2.csv");
		
	 try {
		 int contador = 0;
		 String linea = bufferLectura.readLine();
		 while (linea != null) {
			 String[] generos = linea.split(separador);
			 if(contador > 0) {
				   
				 for (int i = 0; i < generos.length; i++) {
					 //creo vertice
					 grafoGeneros.agregarVertice(generos[i]);
					 //si no es el ultimo
					 if(generos.length-1 > i) {
						 grafoGeneros.agregarArco(generos[i], generos[i+1]);
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
	
}
