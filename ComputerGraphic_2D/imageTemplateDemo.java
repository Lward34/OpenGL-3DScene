/*
* File: imageTemplateDemo.java
* Date: October 21, 2021
* CMSC405
* Purpose: Homework2. This program generates 2D graphics 
* of 3 images of letters T,Rectangle,P 
* rotate 45 counter clockwise and 90 clockwise.
* 
*/

/*
* Reference:
* https://math.hws.edu/graphicsbook/source/java2d/AnimationStarter.java
* package cmsc405;
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class imageTemplateDemo{
   // start main method
   public static void main(String[] args)
           
   {
       // TODO code application logic here
       final Image2DPanel panel = new Image2DPanel();  // The drawing area.
       JFrame frame = new JFrame("Java 2D Graphics");// The parameter shows in the window title bar.  
       frame.setContentPane(panel);// Show the panel in the window.
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // End program when window closes.   
       frame.setPreferredSize(new Dimension(500, 400));// Set size of drawing area, in pixels.
       frame.setResizable(false);//not allow the user resize the frame.
       Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
      
        // Center window on screen.  
       frame.setLocation((windowDimension.width - frame.getWidth()) / 2, 
               (windowDimension.height - frame.getHeight()) / 2);
       // defined the window's frame size
       frame.pack();
       // A Timer that will emit events to drive the animation.
       Timer animationTimer; 
       final long startTime = System.currentTimeMillis();
       // Taken from AnimationStarter
       // Modified to change timing and allow for recycling
       animationTimer = new Timer(1600, new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent ae0)
           {
    
               if(panel.getFrameNumber()>=6)// A counter that increases by one in each frame. 
               {
                  panel.setFrameNumber(0);// set frame number start 0
               }
               else
              {
                 panel.setFrameNumber(panel.getFrameNumber() + 1);
              }
               panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;  
               panel.repaint();
           }
       });
       frame.setVisible(true);// Open the window, making it visible on the screen.
       animationTimer.start();// Start the animation running.
   } // end of main method
 } // end of ImageTemplateDemo classss