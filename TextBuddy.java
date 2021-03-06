import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Assuming that file IO is 	the only commands are delete,display,add,clear and exit. No other invalid input
public class TextBuddy {
	
	private static String outputFileName="null";
	private static boolean contProgram = true;
	private static ArrayList<String> data = new ArrayList<String>();
	private static ArrayList<String> searchList;
	public static void main(String[] args) throws IOException {
		outputFileName= args[0];
		//call createFile method to check if the file exist
		try  
		{
		createFile(outputFileName);
		}catch(IOException e)
				{
		    System.err.println("Error: " + e.getMessage());
		};
		System.out.println("Welcome to TextBuddy. " + outputFileName +" is ready for use");
		Scanner scanner = new Scanner(System.in);
		//User hasn't chosen to exit yet
		while(contProgram){
			System.out.print("command: ");
			String input = scanner.nextLine();
			processInput(input);
		}
		scanner.close();
	}
	
	public static void createFile(String outputFileName) throws IOException{
		//Creates the file if it does not exist, otherwise call fetchFromFile method 
		try  
		{
			File f = new File(outputFileName);
			FileWriter fileCreate = new FileWriter(outputFileName,true);
			if(f.exists()){
				fetchFromFile(f);
			}
			else fileCreate.close();
		}catch(IOException e)
				{
		    System.err.println("Error: " + e.getMessage());
		};
		
	}
	
	public static void fetchFromFile(File f) throws IOException{
		FileReader reader = new FileReader(f);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		//Scanning all lines inside the text file into arrayList
		while ((line = bufferedReader.readLine()) != null) {
			data.add(line);
		}
		reader.close();
		
	}
	public static void processInput(String input) throws IOException{
		if(input.equals("exit"))
			stopProgram();
		else{
			//Getting the user intended action and the parameter provided for the action
			getCommand(input);	
		}	
			
	}
	public static void stopProgram() throws IOException{
		contProgram= false;
		saveFile();
		return;
	}
	
	public static void getCommand(String input) throws IOException{
		
		if(input.contains("add")){
			String argument = getArgument(input);
			addNewEntry(argument);
		}
		else if(input.contains("display")){
			display(data);
		}
		else if(input.contains("delete")){
			String argument = getArgument(input);
			int index = Integer.parseInt(argument)-1;
			deleteEntry(index);
		}
		else if(input.contains("sort")){
			data =  sortList(data);
			display(data);
			saveFile();
		}
		else if(input.contains("search")){
			String argument = getArgument(input);
			searchList = searchList(data, argument);
			display(searchList);
		}
		//The only possible command is clear, since it is neither of the above
		else{
			clearAll();
		}
		
	}
	
	public static void saveFile() throws IOException{
		//Overwriting the entire file with the updated data arrayList
		FileWriter writer = new FileWriter(outputFileName,false);
		for(int i =0;i<data.size();i++){
			writer.write(data.get(i) + System.getProperty( "line.separator" ));
		}
		writer.close();
			
	}
	
	public static void addNewEntry(String arg) throws IOException{
		data.add(arg);
		addToFile(arg);
		System.out.println("added to " +outputFileName + ": \"" + arg +"\"");
	}
	
	public static void addToFile(String arg) throws IOException{
		//Appending to end of the file
		FileWriter writer = new FileWriter(outputFileName,true);	
		writer.write(arg + System.getProperty( "line.separator" ));
		writer.close();
			
	}
	
	
	public static String getArgument(String input){
		//Using the whitespace divider to find the index of the start of the argument
		int indexOfArg = input.indexOf(" ")+1;
		return input.substring(indexOfArg);
	}
	
	
	
	public static void display(ArrayList<String> list){
		//If array is zero
		if(list.size()==0)
			System.out.println(outputFileName + " is empty");
		else{
			for(int i=0;i<list.size();i++){
				System.out.println(i+1 +". " +list.get(i));
			}
		}
	}
	
	public static void deleteEntry(int index) throws IOException{
		String removedEntry = data.get(index);
		data.remove(index);
		saveFile();
		System.out.println("deleted from " +outputFileName +": \"" +removedEntry +"\"");
		
	}
	
	public static void clearAll() throws FileNotFoundException{
		data.clear();
		File f = new File(outputFileName);
		PrintWriter writer = new PrintWriter(f);
		writer.print("");
		writer.close();
		System.out.println("all content deleted from " +outputFileName);
		
	}
	
	public static ArrayList<String> sortList(ArrayList<String> list){
		Collections.sort(list);
		return list;
	}
	
	public static ArrayList<String> searchList(ArrayList<String> list, String word){
		
		ArrayList<String> results = new ArrayList<String>();
		word = word.toLowerCase();
		
		//Looping through the entire list, and calling contains method on each String in the list
		for(int i = 0;i<list.size();i++){
			String currentLine = list.get(i);
			String lowCaseCurrent = currentLine.toLowerCase();
			if(lowCaseCurrent.contains(word)){
				results.add(currentLine);
			}	
		}
		System.out.println("Search results for: " +word);
		return results;
	}
	
}
