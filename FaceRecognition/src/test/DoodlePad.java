package test;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import can.core.NetworkTrainer;
import can.util.Matrix;

    public class DoodlePad
    {
      public static void main(String[] args) throws IOException
      {
        JFrame frame = new JFrame("DoodlePad");
        frame.setLayout(new BorderLayout());
        final DrawPad drawPad = new DrawPad();
        frame.add(drawPad, BorderLayout.CENTER);
        JPanel panel = new JPanel();
       
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            drawPad.clear();
          }
        });
        panel.add(clearButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            drawPad.save();
          }
        });
        panel.add(saveButton);
       
        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLocation(900, 300);
        frame.setVisible(true);
      }

    } // end of class DoodlePad

    @SuppressWarnings("serial")
	class DrawPad extends JComponent
    {
      Image image;
      Graphics2D graphics2D;
      int currentX, currentY, oldX, oldY;

      public DrawPad() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            oldX = e.getX();
            oldY = e.getY();
          }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
          public void mouseDragged(MouseEvent e) {
            currentX = e.getX();
            currentY = e.getY();
            if (graphics2D != null)
              graphics2D.drawLine(oldX, oldY, currentX, currentY);
            repaint();
            oldX = currentX;
            oldY = currentY;
          }
        });
      }

      public void paintComponent(Graphics g) {
        if (image == null) {
          image = createImage(getSize().width, getSize().height);
          graphics2D = (Graphics2D)image.getGraphics();
          graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
          clear();
        }
        g.drawImage(image, 0, 0, null);
      }

      public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        graphics2D.setStroke(new BasicStroke(10));
        repaint();
      }
      
      public void save() {
    	  BufferedImage tempImage = (BufferedImage)createImage(getWidth(),getHeight());
          Graphics2D g2 = (Graphics2D)tempImage.getGraphics();
          g2.drawImage(image, 0, 0, this);
          try {
			ImageIO.write(tempImage, "png", new File("\\Java\\canvas.png"));
          } catch (IOException e) {
			e.printStackTrace();
          }
          
          

          
          if( classes == null) {
        	  classes = NetworkTrainer.getClasses("\\Java\\mnist\\Images\\3_095.png");
              //classes = NetworkTrainer.getClasses("\\Java\\canvas.png");
          }
          
          Double max = -.1;
          Integer index = -1;
          for (int i=0; i<classes.length; i++) {
        	  if ( classes[i][0] > max ) {
        		  max = classes[i][0];
        		  index = i;
        	  }
          }
          Matrix.print(classes);
          System.out.println("Your Number is: " + (index));
      }
      
      Double[][] classes = null;
    }