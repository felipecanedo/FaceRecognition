package can.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import can.util.Matrix;
import can.util.NetParameters;

public class Params implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String fileName;
	public Double[][] weights;
	public Double[][] bias;	
	
	private Params(Integer inputSize, Integer outputSize) {
		this.weights = Matrix.randomMatrix(outputSize,inputSize);
		this.bias 	= Matrix.fixedValueMatrix(outputSize, 1, 0.82);
	}

	public static Params getInstance(Integer inputSize, Integer outputSize, String fileName) {
		Params params = null;
		ObjectInputStream in = null;
		try {
			FileInputStream file = new FileInputStream(NetParameters.SER_DIR + fileName + ".ser");
			in = new ObjectInputStream(file);
			
			params = (Params) in.readObject();
			in.close(); 
			file.close();
			System.out.println("*********************** LOADING A SAVED NETWORK INSTANCE ***********************\n");
		}catch (Exception e) {
			System.out.println("*********************** CREATING A NEW NETWORK INSTANCE ***********************\n");
			try {
				params = new Params(inputSize, outputSize);
				params.fileName = fileName;
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return params;
	}
	
	public void save() {
		FileOutputStream file;
		try {
			file = new FileOutputStream(NetParameters.SER_DIR + fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this);
			out.close(); 
			file.close();
			System.out.println("Network data is saved " + NetParameters.SER_DIR + fileName + ".ser");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
