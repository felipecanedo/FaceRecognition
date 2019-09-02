package can.util;

public final class NetParameters {
	
	public static final String SER_NAME = "/Java/eclipse-workspace/FaceRecognition/netWork.ser";
	public static final String SER_DIR = "/Java/eclipse-workspace/FaceRecognition/";
	
	//Input Image resolution
	public static final Integer Width = 28;
	public static final Integer Height = 28;


	//Stride (Filter movement)
	public static final Integer Stride = 1;
	//Padding (Number of extra spaces in the borders)
	public static final Integer Padding = 0;
	
	// Back propagation parameters
	public static Double LEARNING_RATE = 1e-0;
	public static Double REG = 1e-3;
	
	public static Integer BATCH_SIZE = 50;
	public static Integer TRAINER = 100;
	public static Integer IMAGES_NUMBER = 1000;
	
	
	
	/** DATA TEST **/
		
	public static Double[][][] literalInput =  { { {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {2.0, 0.0, 1.0}, {1.0, 0.0, 1.0}, {2.0,1.0,0.0}, {0.0,0.0,0.0}, {0.0,0.0,1.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {0.0, 0.0, 2.0}, {1.0, 0.0, 2.0}, {2.0,1.0,1.0}, {0.0,2.0,0.0}, {2.0,2.0,1.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {2.0, 1.0, 1.0}, {1.0, 1.0, 1.0}, {1.0,1.0,0.0}, {1.0,2.0,1.0}, {2.0,1.0,0.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {2.0, 1.0, 2.0}, {1.0, 0.0, 2.0}, {1.0,2.0,0.0}, {1.0,0.0,0.0}, {1.0,0.0,0.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {2.0, 0.0, 1.0}, {2.0, 2.0, 1.0}, {0.0,0.0,1.0}, {1.0,2.0,2.0}, {1.0,0.0,0.0}, {0.0,0.0,0.0} },
		        {   {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0}, {0.0,0.0,0.0} },
	};
	
	static Double[][][] w1 =  	{ 	
	{ 	{ 1.0, 0.0, 0.0}, { 1.0, 0.0, 0.0}, {-1.0,-1.0,-1.0}	},
		{  	{-1.0,-1.0,-1.0}, {-1.0,-1.0, 0.0}, {-1.0, 1.0, 1.0} },
		{  	{ 1.0,-1.0, 0.0}, {-1.0,-1.0,-1.0}, { 1.0, 0.0, 0.0} },
	};
	
	
	static Double[][][] w0 =  	{ 	
	{ 	{ 1.0,-1.0, 0.0}, { 1.0, 1.0, 1.0}, {-1.0,-1.0, 0.0}	},
		{  	{-1.0,-1.0,-1.0}, {-1.0, 0.0, 1.0}, { 0.0,-1.0, 1.0} },
		{  	{-1.0, 1.0,-1.0}, { 1.0,-1.0,-1.0}, {-1.0, 1.0, 1.0} },
	};
	
	static Double[][][] booooo =  	{ 	
			{ 	{1.0,0.0} },
		};
	
	static Double[][][] m1 =  	{ 	
			{ 	{ 0.0, 0.0, 0.0}, { 0.0, 0.0, 0.0}, { 0.0, 0.0, 0.0}	},
      		{  	{ 0.0, 0.0, 0.0}, { 2.0, 0.0, 1.0}, { 1.0, 0.0, 1.0} },
      		{  	{ 0.0, 0.0, 0.0}, { 0.0, 0.0, 2.0}, { 1.0, 0.0, 2.0} },
		};
	
	static Double[][][] m2 =  	{ 	
			{ 	{ 1.0,-1.0, 0.0}, { 1.0, 1.0, 1.0}, {-1.0,-1.0, 0.0}	},
      		{  	{-1.0,-1.0,-1.0}, {-1.0, 0.0, 1.0}, { 0.0,-1.0, 1.0} },
      		{  	{-1.0, 1.0,-1.0}, { 1.0,-1.0,-1.0}, {-1.0, 1.0, 1.0} },
		};
	
	static Double[][][] m22 =  	{ 	
			{ 	{ 1.0, 0.0, 0.0}, { 1.0, 0.0, 0.0}, {-1.0,-1.0,-1.0}	},
      		{  	{-1.0,-1.0,-1.0}, {-1.0,-1.0, 0.0}, {-1.0, 1.0, 1.0} },
      		{  	{ 1.0,-1.0, 0.0}, {-1.0,-1.0,-1.0}, { 1.0, 0.0, 0.0} },
		};
}

/*
HashMap<Integer, Double[][][]> randomHash = new HashMap<Integer, Double[][][]>();
randomHash.put(0, pInput0);
randomHash.put(1, pInput1);
randomHash.put(2, pInput2);
randomHash.put(3, pInput3);
randomHash.put(4, pInput4);
randomHash.put(5, pInput5);
randomHash.put(6, pInput6);
randomHash.put(7, pInput7);
randomHash.put(8, pInput8);
randomHash.put(9, pInput9);

net.localProbs 		=  Matrix.getMatrix(1,1,NetParameters.BATCH_SIZE);
net.localLogProbs   =  Matrix.getMatrix(1,1,NetParameters.BATCH_SIZE);

net.expectedResults = new Double[2][1][NetParameters.BATCH_SIZE];

net.expectedResults[0][0][0] = 0.0; //index
net.expectedResults[1][0][0] = 1.0; //value

net.expectedResults[0][0][1] = 1.0; //index
net.expectedResults[1][0][1] = 2.0; //value

net.expectedResults[0][0][2] = 2.0; //index
net.expectedResults[1][0][2] = 3.0; //value

net.expectedResults[0][0][3] = 3.0; //index
net.expectedResults[1][0][3] = 4.0; //value

net.expectedResults[0][0][4] = 4.0; //index
net.expectedResults[1][0][4] = 5.0; //value

net.expectedResults[0][0][5] = 5.0; //index
net.expectedResults[1][0][5] = 6.0; //value

net.expectedResults[0][0][6] = 6.0; //index
net.expectedResults[1][0][6] = 7.0; //value

net.expectedResults[0][0][7] = 7.0; //index
net.expectedResults[1][0][7] = 8.0; //value

net.expectedResults[0][0][8] = 8.0; //index
net.expectedResults[1][0][8] = 9.0; //value

net.expectedResults[0][0][9] = 9.0; //index
net.expectedResults[1][0][9] = 10.0; //value

	public static Double[][][] pInput0 =  { {{0., 0., 0., 0., 0., 0., 0., 0., 0., 1.}} };
	public static Double[][][] pInput1 =  { {{0., 0., 0., 0., 0., 0., 0., 0., 1., 1.}} };
	public static Double[][][] pInput2 =  { {{0., 0., 0., 0., 0., 0., 0., 1., 1., 1.}} };
	public static Double[][][] pInput3 =  { {{0., 0., 0., 0., 0., 0., 1., 1., 1., 1.}} };
	public static Double[][][] pInput4 =  { {{0., 0., 0., 0., 0., 1., 1., 1., 1., 1.}} };
	public static Double[][][] pInput5 =  { {{0., 0., 0., 0., 1., 1., 1., 1., 1., 1.}} };
	public static Double[][][] pInput6 =  { {{0., 0., 0., 1., 1., 1., 1., 1., 1., 1.}} };
	public static Double[][][] pInput7 =  { {{0., 0., 1., 1., 1., 1., 1., 1., 1., 1.}} };
	public static Double[][][] pInput8 =  { {{0., 1., 1., 1., 1., 1., 1., 1., 1., 1.}} };
	public static Double[][][] pInput9 =  { {{1., 1., 1., 1., 1., 1., 1., 1., 1., 1.}} };
*/
