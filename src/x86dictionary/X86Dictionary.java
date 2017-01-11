package x86dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class X86Dictionary {
	
	//Variables
	@SuppressWarnings("unchecked")
	ArrayList<String>[] diction = (ArrayList<String>[])new ArrayList[26];
	
	
	//Constructor
	public X86Dictionary() {
		//Open dictionary text file
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:/Users/ColbyAdmin/Java-Workspace/Heuristics-Test/OTHER/Dictionary_v3.txt"));
		} catch (IOException e) {
			System.out.println("ERROR OPENING DICTIONARY TEXT");
		}
		
		String entry = null;
		
		//Loop through text file
		for(int i = 0;i < 26;i++) {
			try {
				// Check if current entry is null
				if(entry == null || entry.length() == 0) {
					//If so, Read in next line from text file
					entry = br.readLine();
				}
				
				//Read in next line from text file
				
				//Set first letter of entry as current first letter

				boolean changeLetter = false;
				diction[i] = new ArrayList<String>();
				
				//Add entry to current part of dictionary
				
				//Begin looping through remaining entries
				while(!changeLetter) {
					//Read in next entry
					//check to see if first letters match
						//if they do not, set first letter to be changed
					if(!changeLetter) {
						//Add entry to current part of dictionary
					}
				}
			} catch (IOException e) {
				System.out.println("ERROR READING FROM DICTIONARY TEXT");
			}
			
		}
		
	}
	
	//Methods
	
	
}
