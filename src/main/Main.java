package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import x86dictionary.X86Dictionary;

public class Main {

	public static void main(String[] args) {
		//Create the dictionary
		X86Dictionary whiteLister = new X86Dictionary();
		
		//Read in the text file with the assembly code
		List<String> opCodes = new ArrayList<String>();
		String fileName = "C:/Users/ColbyAdmin/Java-Workspace/Heuristics-Test/OTHER/2bf35a30a9b3b15b7e931a40084c5c6ae4ae07e01306b493b260bc6c4de54975.asm";
		readInAssembly(fileName, opCodes);
		
		//Whitelist the assembly
		opCodes = whiteLister.whiteList(opCodes);
		
		//Print to screen to test
		testOutput(opCodes, 2);
	}
	
	
	
	
	
	
	public static void readInAssembly(String fileName, List<String> opCodes) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] columns = line.split("\t");
				if(columns.length > 2)
					opCodes.add(columns[2]);
			}
			br.close();	
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR");
		}
	}
	
	public static void testOutput(List<String> opCodes, int rule) {
		switch(rule) {
		case 1:
			for(int i = 0; i < opCodes.size(); i++) {
				System.out.println(opCodes.get(i));
				//System.out.println('\n');
			}
			break;
		case 2:
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/ColbyAdmin/Desktop/test.txt"));
				for(int i = 0; i < opCodes.size(); i++) {
					bw.write(opCodes.get(i));
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("ERROR NUMERO DOS");
			}
			break;
		}
	}

}
