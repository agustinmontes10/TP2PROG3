package TP2PROG3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Lector {
	
	public BufferedReader leerArchivo(String csv) {
		try {
        	File file = new File(csv);

        	return new BufferedReader(
        				new InputStreamReader(
        						new FileInputStream(file),"UTF-8")
        	);
        	        	
        } catch (IOException e) {	
            e.printStackTrace();
        }
		return null;
	}
	
}

