package graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MyImage {

	public MyImage() {
		
	}
	
	public static void main(String[] args) throws Exception {
	    

	    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	    
	    Integer count = 0; 
	    for (int n=0; n<10; n++) {
		    for (int i = 0; i < 50; i++) {
		    	final BufferedImage image = ImageIO.read(new File("C:\\Java\\eclipse-workspace\\FaceRecognition\\ImageBKP\\test\\background.png"));
		    	Graphics g = image.getGraphics();
		 	    g.setFont(new Font(fonts[i], Font.PLAIN, 180));
		 	    g.setColor(Color.BLACK);
		 	    g.drawString(new Integer(n).toString(), 50, 200);
		 	    g.dispose();
		 	    ImageIO.write(image, "png", new File("C:\\Java\\eclipse-workspace\\FaceRecognition\\Images\\SQ"+ count +"-IDX"+n+".png"));
		 	   count++;
		    }
	    }
	}
}
