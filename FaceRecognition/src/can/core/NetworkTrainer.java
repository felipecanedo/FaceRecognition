package can.core;


import can.util.MatrixUtils;

public class NetworkTrainer {  
	
	
	public static void main(String[] args) {
		NetworkTrainer.trainer();
	}
	
	public static void trainer() {
		MatrixUtils np = MatrixUtils.getInstance();
		
		Double[][][] input 	   = np.random(32,32,3);
		Double[][][] convLayer = np.random(32,32,3);
		Double[][][] poolLayer = np.random(16,16,3);
		Double[][][] fcLayer   = np.random(1,1,10);
		
		
	}
}
