package x86dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class X86Dictionary {
	
	//Variables
	@SuppressWarnings("unchecked")
	public ArrayList<String>[] diction = (ArrayList<String>[])new ArrayList[26];
	private BufferedWriter notInDiction;
	private ArrayList<String> NID = new ArrayList<String>();
	
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
		Character first = null;
		boolean changeLetter = false;
		
		//Loop through text file
		for(int i = 0;i < 26;i++) {
			try {
				// Check if current entry is null
				if(entry == null || entry.length() == 0) {
					//If so, Read in next line from text file
					entry = br.readLine();
					first = entry.charAt(0);
					
					//Initialize next section
					diction[i] = new ArrayList<String>();
				}
				
				//Set first letter of entry as current first letter
				while(first != entry.charAt(0)) {
					
					//Change first letter to next alphabetically
					first++;
					
					//See if first letters match now
					if(first == entry.charAt(0)) {				
						//Set to loop
						changeLetter = false;

						//Initialize next section
						diction[i] = new ArrayList<String>(); 
					} else {
						//Initialize next section
						diction[i] = new ArrayList<String>();
						
						//Increment to next section
						i++;
					}
				}
				
				//Add entry to current part of dictionary
				diction[i].add(entry);
				
				//Begin looping through remaining entries
				while(!changeLetter) {
					//Read in next entry
					entry = br.readLine();
				
					//Check for end
					if(entry == null || entry.length() == 0) {
						//Finish incrementing remaining empty loops
						i++;
						for(;i < 26;i++) {
							first++;
							
							//Initialize next section
							diction[i] = new ArrayList<String>();
						}
						break;
					}
					
					//Check to see if first letters match
					if(first != entry.charAt(0)) {
						//if they do not, set first letter to be changed
						changeLetter = true;
					}
					
					//If letter isn't changed
					if(!changeLetter) {
						//Add entry to current part of dictionary
						diction[i].add(entry);
					}
				}
			} catch (IOException e) {
				System.out.println("ERROR READING FROM DICTIONARY TEXT");
			}
		}
		
	}
	
	//Methods
	public List<String> whiteList(List<String> opCodes) {
		String current;
		List<String> whiteListed = new ArrayList<String>();
		try {
			notInDiction = new BufferedWriter(new FileWriter("C:/Users/ColbyAdmin/Desktop/NotInDictionary.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//For loop to go through the given list
		for(int i = 0; i < opCodes.size(); i++) {
			//Store the current entry
			current = opCodes.get(i);
			if(current.length() != 0) {
				current =  current.split(" ")[0];
				if(inDictionary(current)) {
					whiteListed.add(current);
				}
			}
		}
		
		try {
			for(String s: NID) {
				notInDiction.write(s);
				notInDiction.write(System.lineSeparator());
			}
			notInDiction.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return whiteListed;
	}
	
	public boolean inDictionary(String word) {
			//Get the first letter of the word
			char firstLetter = word.charAt(0);

			//Search the appropriate section for the word
			if(firstLetter > 96 && firstLetter < 123) {
				if(diction[firstLetter - 'a'].contains(word.toUpperCase())) {
					return true;
				} else {
					if(!NID.contains(word))
						NID.add(word);
					return false;
				}
			}
			return false;
	}
	
	
}
