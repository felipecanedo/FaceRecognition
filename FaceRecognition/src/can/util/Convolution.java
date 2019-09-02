package can.util;

public class Convolution {

	public static Double[][][] convolute(Double[][][] inputData, Double[][][] kernel, Integer stride, Integer padding, Double bias) {
		if ( kernel.length != kernel[0].length )
			throw new RuntimeException("Illegal kernel dimensions.");
		if ( inputData.length != inputData[0].length )
			throw new RuntimeException("Illegal matrix dimensions.");
		if ( inputData[0][0].length != kernel[0][0].length )
			throw new RuntimeException("Illegal depeth dimension between filter and input.");
		
		//Apply the equation for the output size (W1-F+2P)/S+1
		Integer oSize = ( (inputData.length-kernel.length+2*padding) / stride + 1 );
		Double[][][] outputData = MatrixUtils.getInstance().zeroMatrix(oSize,oSize,1);
		
		for (int h=0; h<outputData[0].length; h++) {
			for (int w=0; w<outputData.length; w++) {
				outputData[w][h][0] += convoluteKernel(inputData,kernel,w*stride,h*stride,0);
				outputData[w][h][0] += convoluteKernel(inputData,kernel,w*stride,h*stride,1);
				outputData[w][h][0] += convoluteKernel(inputData,kernel,w*stride,h*stride,2);
				outputData[w][h][0] += bias;
			}
			
		}
		return outputData;
	}
	
	public static Double convoluteKernel(Double[][][] inputData,
										 Double[][][] kernel,
										 Integer wIndex,
										 Integer hIndex,
										 Integer d) {
		Double output = 0.0;
		for (int h=0; h<kernel[0].length; h++) {
			for (int w=0; w<kernel.length; w++) {
				output += (inputData[w+wIndex][h+hIndex][d] * kernel[w][h][d]);
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		MatrixUtils.printMatrix( Convolution.convolute(NetParameters.literalInput, NetParameters.w1, 2, 0, 1.0) );	
	}

}
