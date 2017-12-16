package jsondataset;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDirectoryConfigurator {
	public static void main(String[] args) {
	JSONObject obj = null;
	String output = "";
	String character = "";
	// use whatever parameter you want to use
	String category = "Char";
	// replace with your own directory path
	String newDirPath = "/Users/joshpayne1/desktop/tensorflow-c/";
	// replace with the directory path on your computer
	String datasetPath = "/Users/joshpayne1/downloads/dataset/";	
	// json string generated for dataset
	try {
		obj = new JSONObject(str);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Iterator<?> keys = obj.keys();
	while (keys.hasNext()) {
		String key = (String)keys.next();
		try {
			Object x = obj.getJSONObject(key);
			String n = x.toString();
			JSONObject subObj = null;
			try {
				subObj = new JSONObject(n);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			character = subObj.getString(category);
			File theDir = new File(newDirPath+character);
			if (!theDir.exists()) {
			    try{
			        theDir.mkdir();
			        // to move (save space), use Files.move
			        
			        	Files.copy(Paths.get(datasetPath+key), Paths.get(newDirPath+character+"/"+key), StandardCopyOption.REPLACE_EXISTING);
			    
			    } 
			    catch(SecurityException se){
			        //handle it
			    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        
			} else {
				try {
					File check = new File(newDirPath+character+"/"+key);
		        	if (!check.exists()) Files.copy(Paths.get(datasetPath+key), Paths.get(newDirPath+character+"/"+key), StandardCopyOption.REPLACE_EXISTING);
				} catch(SecurityException se) {
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(key);
		output = output + "'" + key + "', "; 
		//System.out.println(ShapeColor);
	}
	System.out.println(output);
	}
}