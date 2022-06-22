package TP2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Codigo.Genero;
import Codigo.Libro;

public class Biblioteca {

	private GrafoDirigido grafoGeneros;
	
	public Biblioteca() {
		
	}
	
	public void getGeneros(){
		String separador = ",";
		BufferedReader bufferLectura = null;
	 try {
	  bufferLectura = new BufferedReader(new FileReader("dataset4.csv"));
	  String linea = bufferLectura.readLine();
	  
	  while (linea != null) {
		  if(!linea.equals("Generos")) {
			  String[] generos = linea.split(separador); 
	   
			  for (int i = 0; i < generos.length; i++) {
				  grafoGeneros.agregarVertice(generos[i]);
				  if(generos[i+1] != null) {
					  grafoGeneros.agregarVertice(generos[i+1]);
					  grafoGeneros.agregarArco(generos[i], generos[i+1]);
				  }
			  }
		  }
	   
	
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
