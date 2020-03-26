import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class CovidData {
	Hashtable<String,Integer> data;
	public CovidData(String filename) {
		data = new Hashtable<String,Integer>();
		try {
			Scanner in = new Scanner(new File(filename));
			// Add your code here 
			while(in.hasNext()) {
				String[] splitInput = in.nextLine().split(",");
				data.put(splitInput[0],Integer.parseInt(splitInput[1]));
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public int find(String d) {
		// Add your code here 
		if (data.containsKey(d)) {
			return data.get(d);
		}
		return -1;
	}
}
