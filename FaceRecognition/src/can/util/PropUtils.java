package can.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

public final class PropUtils {
	
	public static PropUtils utils;
	private Properties convLayer;
	private Properties masterParameters;
	
	private PropUtils() throws FileNotFoundException, IOException {
			
		masterParameters = new Properties();
		masterParameters.load(new FileInputStream("/Java/eclipse-workspace/FaceRecognition/config/MasterParameters.config"));
		
		convLayer = new Properties();
		convLayer.load(new FileInputStream("/Java/eclipse-workspace/FaceRecognition/config/ConvultionalLayer1.config"));
	}
	
	public static PropUtils getInstance() {
		if (utils == null) {
			try {
				utils = new PropUtils();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return utils;
	}
	
	public Integer getStride(){
		return new Integer(masterParameters.getProperty("STRIDE"));
	}
	
	public Integer getNoFilters(){
		return new Integer(masterParameters.getProperty("FILTERS"));
	}
	
	
	public HashMap<Integer, Integer[][]> getFilters(){
		Enumeration<?> e = convLayer.propertyNames();
		HashMap<Integer, Integer[][]> result = new HashMap<Integer, Integer[][]>();
		
		while(e.hasMoreElements()) {
			Integer key = new Integer((String)e.nextElement());
			StringTokenizer rSt = new StringTokenizer(convLayer.getProperty(key.toString()),"[]");
			//Taking into account only Height to define Array Size - As of now there is no need for asymmetrical matrix
			int h = 0;
			Integer[][] array = new Integer[rSt.countTokens()][rSt.countTokens()]; 
			
			while(rSt.hasMoreTokens()) {
				StringTokenizer cSt = new StringTokenizer(rSt.nextElement().toString(),",");
				int w = 0;
				while(cSt.hasMoreTokens()) {
					Integer value = new Integer(cSt.nextElement().toString());
					array[w][h] = value;
					w++;
				}
				h++;
			}
			result.put(key, array);
		}
		
		return result;
	}

	public static void main(String args[]) {
		HashMap<Integer, Integer[][]> maps = PropUtils.getInstance().getFilters();
		Iterator<Integer> keys = maps.keySet().iterator();
		while(keys.hasNext()) {
			Integer key = keys.next();;
			Integer[][] value = maps.get(key);
			System.out.println(key);
			for(int h=0; h<value.length; h++) {
				for(int w=0; w<value.length; w++) {
					System.out.print(value[w][h]+",");
				}
				System.out.println("");
			}
			
		}
			
	}

}
