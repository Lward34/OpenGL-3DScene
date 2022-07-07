/*
* File: Image2DPanel.java
* Author:
* Date: October 21, 2021
* CMSC405
* Purpose:This program generates 2D graphics 
* of 3 images of letters T,Square,P 
* rotate each letter 45 counter clockwise and 90 clockwise.
* 
*/


// Image2DPanel class implementation
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;

/*
* Reference:
* https://math.hws.edu/graphicsbook/source/java2d/AnimationStarter.java
* package cmsc405;
*/
public class Image2DPanel extends JPanel {

    // instance variables
    private static int transX;
    private static int transY;
    private static double rotation;
    private static double scaleX;
    private static double scaleY;
    private int framesCounter;
    private final  Image2D myImage;
    private BufferedImage ltImage, SqImage, lpImage;
    long elapsedTimeMillis;
    
    
    // default constructor
    public Image2DPanel() {
        transX = 0;
        transY = 0;
        rotation = 0.0;
        scaleX = 2.0;
        scaleY = 0.5;
        myImage = new Image2D();
       
        ltImage = myImage.generateImage(Image2D.letterT);
        SqImage = myImage.generateImage(Image2D.Square);//rectangle
        lpImage = myImage.generateImage(Image2D.letterP);
        
        
    } // end of constructor

    /**
     * The paintComponent method draws the content of the JPanel. The parameter is a graphics context that can be used for drawing on the panel.
     *
     */
    // paintComponent method implementation
    @Override
    @SuppressWarnings("fallthrough")
    protected void paintComponent(Graphics g) {
        // creates the graphics2D drawing context on the panel 
        Graphics2D g2d = (Graphics2D) g.create();
        //Turn on antialiasing in this graphics context, for better drawing.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //set the colors
        g2d.setPaint(Color.cyan);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Controls the zoom and the area of image
        startAnimation(g2d, -75, 75, -75, 75, true);
        // save the current transform
        AffineTransform afnTrans = g2d.getTransform();
        // Show which frame image is on for debugging
        System.out.println("Frame Number: " + framesCounter);

        switch (framesCounter) {

            case 0:
                // starting position
                transX = 0;
                transY = 0;
                scaleX = 1.0;
                scaleY = 1.0;
                rotation = 0.0;
                break;
            case 1:
                // Translate -5 in x direction
                transX += -5;
                transY += 0;
                break;
            case 2:
                // Translate +7 in the y direction.
                transX += 0;
                transY += 7;
                break;
            case 3:
                // Rotates each image 45 degrees counter clockwise.
                transX = -5;
                transY = 7;
                rotation += 45 * Math.PI / 180.0;
                break;
            case 4:
                // Rotates each image 90 degrees clockwise
                transX = -5;
                transY = 7; 
                rotation += -90*Math.PI /180.0;               
                break;
            case 5:
                // Scales the image in the x direction 2 times 
                scaleX = 2.0 * scaleX;
                break;
            case 6:
                // Scales the image in the y direction 0.5 times                
                scaleY = 0.5 * scaleY;
                break;
            default:
                break;
        } // End switch
        
        printImage(g2d, -60, -60, ltImage, afnTrans);//draws the letter T
        printImage(g2d, -40, -40, SqImage, afnTrans);//draws the Square
        printImage(g2d, -20, -20, lpImage, afnTrans);//draws the letter P
    } // end of paintComponent method

    // getFrameNumber method implementation
    public int getFrameNumber() {
        return framesCounter;
    } // end of getFrameNumber method

    // setFrameNumber method implementation
    public void setFrameNumber(int number) {
        framesCounter = number;
    } // end of setFrameNumber method

   // printImage method implementation
   private void printImage(Graphics2D g2d, int x, int y, BufferedImage image, AffineTransform afnTrans)
   {
       g2d.translate(transX, transY);
       g2d.translate(x, y);
       // To offset translate again
       g2d.translate(-10,10);
       g2d.rotate(rotation);
       g2d.scale(scaleX, scaleY);
       g2d.drawImage(image, 0, 0, this);
       g2d.setTransform(afnTrans);
   } // end of printImage method
  
   // startAnimation method implementation
   private void startAnimation(Graphics2D g2d, double left, double right, double bottom, double top, boolean isPreserve)
   {
       int drawingAreaWidth = getWidth();
       int drawingAreaHeight = getHeight();
       double displayArea;
       double requiredArea;
       double expandedViewport;
      
       if(isPreserve)
       {
           displayArea = Math.abs((double) drawingAreaHeight / drawingAreaWidth);
           requiredArea = Math.abs((bottom - top) / (right - left));
           if(displayArea > requiredArea)
           {
               expandedViewport = (bottom - top) * (displayArea / requiredArea - 1);
               bottom += expandedViewport / 2;
               top -= expandedViewport / 2;
           }
           else if(displayArea < requiredArea)
           {
               expandedViewport = (right - left) * (requiredArea / displayArea - 1);
               right += expandedViewport / 2;
               left -= expandedViewport / 2;
           }
       }
      
       g2d.scale(drawingAreaWidth / (right - left), drawingAreaHeight / (bottom - top));
       g2d.translate(-left, -top); 
       
   } // end of startAnimation method  
} // end of Image2DPanel class
