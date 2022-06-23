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
					 grafoGeneros.agregarVertice(generos[i]);
					 if(generos[i+1] != null) { //cuando está en el ultimo genero, el siguiente no da null, sino que no lo lee directamente
						 grafoGeneros.agregarVertice(generos[i+1]);
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
