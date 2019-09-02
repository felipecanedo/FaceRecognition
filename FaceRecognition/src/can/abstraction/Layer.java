package can.abstraction;

import java.util.HashMap;


public abstract class Layer implements java.io.Serializable {
		
	private static final long serialVersionUID = 2L;
	
	//Input Parameters
	public Double[][][] input;
	
	//Output Parameters
	public Double[][][] output;
	//Weights
	public HashMap<Integer, Double[][][]> weights;
	//Biases
	public Double[][][] biases;

	public abstract void activateLayer();
	
    /**
     * Apply ReLU function for a specific parameter
     * @param value - Input parameter for ReLu function
     * @return - Output a ReLU function of the value 
     */
	public static Double ReLU(Double value) {
		return Math.max(0.0,value);
	}
	
	/**
	 * Activate function for all layers
	 * @param value to be activated
	 * @return A Double with activation function
	 */
	public static Double activationFunction(Double value) {	
		Double maxValue = ReLU(Math.sin(value));
		maxValue = Math.min(1.0, maxValue);
		return maxValue;
	}
	
	/**
	 * Activate function for all layers
	 * @param value to be activated
	 * @return A Double with activation function
	 */
	public void activationFunction() {	
		for (int d=0; d<this.output[0][0].length; d++) {
			for (int h=0; h<this.output[0].length; h++) {
				for (int w=0; w<this.output.length; w++) {
					this.output[w][h][d] = ReLU(this.output[w][h][d]);
				}
			}
		}
	}

	//TODO Still to implement padding code
	public HashMap<Integer, Double[][][]> padding(){
		return null;

	}

	public Double[][][] getInput() {
		return input;
	}

	public void setInput(Double[][][] input) {
		this.input = input;
	}

	public Double[][][] getOutput() {
		return output;
	}

	public void setOutput(Double[][][] output) {
		this.output = output;
	}

	public HashMap<Integer, Double[][][]> getWeights() {
		return weights;
	}

	public void setWeights(HashMap<Integer, Double[][][]> weights) {
		this.weights = weights;
	}

	public Double[][][] getBiases() {
		return biases;
	}

	public void setBiases(Double[][][] biases) {
		this.biases = biases;
	}


}
