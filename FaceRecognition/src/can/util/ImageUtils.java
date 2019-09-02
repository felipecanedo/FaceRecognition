package can.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;

import can.core.LabeledImage;

public final class ImageUtils {
	
	public static final String imageDir = "/Java/mnist/Images/";

	public static LabeledImage next(String name) {

		BufferedImage bf = ImageUtils.loadImage(name);
		
		Double[][][] base1Image = ImageUtils.getBase1Image(createRGBArray(bf)); 
		
		String idx = name.substring(0,1);
		
		return new LabeledImage(new Integer(idx), base1Image);
	}
	
	public static Double[][][] getImage(String name) {

		File file  = new File(name);
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage resized = ImageUtils.resizeImage(bf, NetParameters.Width, NetParameters.Width, bf.getType());
		Double[][][] array = ImageUtils.createRGBArray(resized);
		
		Double[][][] base1Image = ImageUtils.getBase1Image(array); 
		
		return base1Image;
	}
	
	public static BufferedImage loadImage(String fileName) {
		File image  = new File(ImageUtils.imageDir + fileName);
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return originalImage;
	}
	
	public static void saveImage(File outputImage, String format, Integer type, Double[][][] array) {
		try {
			
			BufferedImage bi = 	new BufferedImage(array.length, array[0].length, type);
			
			for(int h=0 ; h<array[0].length; h++) {
				for(int w=0 ; w<array.length; w++) {
					Integer r = ImageUtils.getRGBFromDouble(array[w][h][0]);
					Integer g = ImageUtils.getRGBFromDouble(array[w][h][1]);
					Integer b = ImageUtils.getRGBFromDouble(array[w][h][2]);
					Color c = new Color(r,g,b);
					bi.setRGB(w, h, c.getRGB());
				}
			}
			
			ImageIO.write(bi,format, outputImage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getRGBFromDouble(Double value) {
		Integer rValue = Math.round(value.floatValue());
		
		if (rValue > 255) {
			rValue = 255;
		}
		
		if (rValue < 0) {
			rValue = 0;
		}
		
		return rValue;
	}
	
	public static Double[][][] createRGBArray(BufferedImage bi) {
		
		Double[][][] rgbArray = new Double[bi.getWidth()][bi.getHeight()][3];
		
		for (int d=0;d<3;d++) {
			for(int h=0 ; h<bi.getHeight(); h++) {
				for(int w=0 ; w<bi.getWidth(); w++) {
					Color c = new Color(bi.getRGB(w,h));
					rgbArray[w][h][0] = new Double(c.getRed());
					rgbArray[w][h][1] = new Double(c.getGreen());
					rgbArray[w][h][2] = new Double(c.getBlue());
				}
			}
		}
		return rgbArray;
	}
	
    public static BufferedImage resizeImage(BufferedImage originalImage, Integer nWidth, Integer nHeight, int type){
		BufferedImage rImage = new BufferedImage(nWidth, nHeight, type);
		Graphics2D g = rImage.createGraphics();
		g.drawImage(originalImage, 0, 0, nWidth, nHeight, null);
		g.dispose();
		return rImage;
    }
    
    public static Double[][][] getBase1Image(Double[][][] input) {
    	for (int d=0; d<input[0][0].length;d++) {
			for(int h=0; h<input[0].length;h++) {
				for(int w=0; w<input.length;w++) {
					input[w][h][d] = input[w][h][d] / 255;
				}
			}
		}
    	return input;
    }
    
    public static Double[][][] convert3Dinto2DImage(Double[][][] input) {
    	Double[][][] output = new Double[input.length][input[0].length][1];
    	for(int h=0; h<input[0].length;h++) {
    		for(int w=0; w<input.length;w++) {
				output[w][h][0] = input[w][h][0] + input[w][h][1] + input[w][h][2]; 
			}
		}
    	return output;
    }
    
    public static Double[][][] getBase1toRGB(Double[][][] input) {
    	Double[][][] output = new Double[input.length][input[0].length][input[0][0].length];
    	for(int d=0; d<input[0][0].length;d++) {
			for(int h=0; h<input[0].length;h++) {
				for(int w=0; w<input.length;w++) {
					output[w][h][d] = input[w][h][d] * 255;
				}
			}
		}
    	return output;
    }
}
