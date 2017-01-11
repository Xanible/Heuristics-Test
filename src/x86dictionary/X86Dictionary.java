package x86dictionary;

import java.util.ArrayList;

public class X86Dictionary {
	
	//Variables
	@SuppressWarnings("unchecked")
	ArrayList<String>[] diction = (ArrayList<String>[])new ArrayList[26];
	
	
	//Constructor
	public X86Dictionary() {
		//Open dictionary text file
		
		//Loop through text file
		for(int i = 0;i < 26;i++) {
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
			
		}
		
	}
	
	//Methods
	
	
}
