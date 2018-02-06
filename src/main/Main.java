package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dalvikdictionary.DalvikDictionary;

public class Main {

	public static void main(String[] args) {
		//Create the dictionary
		DalvikDictionary whiteLister = new DalvikDictionary();
		//whiteLister.test();
		
		//Read in the text file with the assembly code
		File dirMal = new File("C:\\Users\\colby\\Google Drive\\Disassembled Android Files\\Disassembly-Malware");
		File outputDirMal = new File("C:/Users/colby/Desktop/androidct/cleaned disassembly/Malware");
		File dirBen = new File("C:\\Users\\colby\\Google Drive\\Disassembled Android Files\\Disassembly_Benign");
		File outputDirBen = new File("C:/Users/colby/Desktop/androidct/cleaned disassembly/Benign");
		File[] filesMal = dirMal.listFiles();
		File[] filesBen = dirBen.listFiles();
		/*
		for (File f : filesMal) {
			if(!f.getName().equals("desktop.ini")) {
				List<String> opCodes = new ArrayList<String>();

				//Display file name
				System.out.println(f.getName());

				//Format the opcodes
				readInAssemblyRADARE2(dirMal.getPath() + File.separator + f.getName(), opCodes);

				//Whitelist the assembly
				opCodes = whiteLister.whiteList(opCodes);

				//Output formatted Disassembly
				formattedOutput(outputDirMal.getPath(), f.getName(), opCodes, 3);
			}
		}
		*/
		for (File f : filesBen) {
			if(!f.getName().equals("desktop.ini")) {
				List<String> opCodes = new ArrayList<String>();

				//Display file name
				System.out.println(f.getName());

				//Format the opcodes
				readInAssemblyRADARE2(dirBen.getPath() + File.separator + f.getName(), opCodes);

				//Whitelist the assembly
				opCodes = whiteLister.whiteList(opCodes);

				//Output formatted Disassembly
				formattedOutput(outputDirBen.getPath(), f.getName(), opCodes, 3);
			}
		}
	}
	
	
	
	
	
	
	public static void readInAssemblyRADARE2(String fileName, List<String> opCodes) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				char firstLetter = line.charAt(0);
				
				switch(firstLetter) {
				case '+':
					StringBuilder sb1 = new StringBuilder(line);
					sb1.deleteCharAt(0);
					line = sb1.toString();
					break;
				case '^':
					StringBuilder sb2 = new StringBuilder(line);
					sb2.deleteCharAt(0);
					line = sb2.toString();
					break;
				}
				opCodes.add(line);
			}
			br.close();	
		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR");
		}
	}
	
	public static void readInAssemblyIDA(String fileName, List<String> opCodes) {
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
	
	public static void formattedOutput(String outputDir, String filename, List<String> opCodes, int rule) {
		switch(rule) {
		case 1:
			for(int i = 0; i < opCodes.size(); i++) {
				System.out.println(opCodes.get(i));
				//System.out.println('\n');
			}
			break;
		case 2:
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputDir + File.separator + filename));
				for(int i = 0; i < opCodes.size(); i++) {
					bw.write(opCodes.get(i));
					bw.newLine();
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("ERROR NUMERO DOS");
			}
			break;
		case 3:
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputDir + File.separator + filename));
				for(int i = 0; i < opCodes.size(); i++) {
					bw.write(opCodes.get(i));
					bw.write(" ");
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("ERROR NUMERO DOS");
				e.printStackTrace();
			}
			break;
		}
	}

}
