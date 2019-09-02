package can.util;

public class Convolution {

	public static Double[][][] convolute(Double[][][] inputData, Double[][][] kernel, Integer stride, Integer padding) {
		if ( kernel.length != kernel[0].length )
			throw new RuntimeException("Illegal kernel dimensions.");
		if ( inputData.length != inputData[0].length )
			throw new RuntimeException("Illegal matrix dimensions.");
		if ( inputData[0][0].length != kernel[0][0].length )
			throw new RuntimeException("Illegal depeth dimension between filter and input.");
		
		//Apply the equation for the output size (W1-F+2P)/S+1
		Integer oSize = ( (inputData.length-kernel.length+2*padding) / stride + 1 );
		Double[][][] outputData = new Double[oSize][oSize][1];
		
		for (int w=0; w<outputData.length; w++) {
			for (int h=0; h<outputData[0].length; h++) {
				for (int d=0; d<inputData[0][0].length; d++) {
					outputData[w][h][0] =  
				}
			}
		}
		return outputData;
	}

	public static Double[][][] convolute(Double[][][] inputData, Double[][][] kernel) {
		return convolute(inputData,kernel,2,0);
	}
}
