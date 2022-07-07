package TestCode;

/*
*  File: Project2.java
*  CMSC405
*  Purpose: Project2 class creates the GUI panel. 
*  It will display a 3D scene, containing six different shapes 
*  With six different transformations each.
*  Will allow the user to stop the animation
*  
*
*/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

/**
 * Reference: 
 * template created based on the FourLights.java provided at CMSC405,Retrieved from
 * https://learn.umgc.edu/d2l/le/content/615107/viewContent/22462669/View
 *
 */

public class Project2 extends JPanel implements GLEventListener {

    //Main method
    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Graphics Scene");// creates panel title
        Project2 panel = new Project2();// create panel
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack(); // Set panel size based on the preferred sizes of its contents.
        frame.setResizable(false);// not allow user resized the panel
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation( // Center window on screen.
                (screen.width - frame.getWidth()) / 2,
                (screen.height - frame.getHeight()) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //variables instance
    private JCheckBox Animating;
    private GLJPanel display;
    private Timer animationTimer;
    private Camera camera;  // Contains the current view transform and projection.     
    private int frameNumber = 0;
    // private GLUT glut = new GLUT();

    private Shapes shapes = new Shapes(); // instance subclass  
    //constructor

    public Project2() {
        GLCapabilities caps = new GLCapabilities(null);
        display = new GLJPanel(caps);
        display.setPreferredSize(new Dimension(640, 480));
        display.addGLEventListener(this);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);
        //-----start Camera-----
        camera = new Camera();
        camera.viewAt(5, 10, 30, 0, 0, 0, 0, 1, 0);
        camera.setScale(15);
        camera.installTrackball(display);
        //------start the animation-----
        animationTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                frameNumber++;
                display.repaint();
            }
        });
        //Add Listener
        ActionListener boxHandler = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == Animating) {
                    if (Animating.isSelected()) {
                        animationTimer.start();
                    } else {
                        animationTimer.stop();
                    }
                } else {
                    display.repaint();
                }
            }
        };

        //Add components
        Animating = new JCheckBox("Animate", true);
        Animating.addActionListener(boxHandler);
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2, 1));
        JPanel row1 = new JPanel();
        row1.add(Animating);
        bottom.add(row1);
        add(bottom, BorderLayout.SOUTH);
        animationTimer.setInitialDelay(500); //set the animation Timer
        animationTimer.start();
    }

//can add method light here.
// --------------- Method GLEventListener interface -----------
    @SuppressWarnings("static-access")
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 drawGl2 = drawable.getGL().getGL2();// calls the panel when is created
        drawGl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); // need depth buffer for 3D.

        // calls the panel when is created
        drawGl2.glEnable(GL2.GL_DEPTH_TEST); // Required for 3D drawing
        drawGl2.glLineWidth(2);
        drawGl2.glShadeModel(GL2.GL_SMOOTH);
        drawGl2.glEnable(GL2.GL_COLOR_MATERIAL);
        drawGl2.glClearColor(0.0f, 0.0f, 0.0f, 1); // Set background for color "Black"
        // The next lines help set up the coordinates system.
        drawGl2.glMatrixMode(GL2.GL_PROJECTION);
        drawGl2.glLoadIdentity();
        drawGl2.glOrtho(-10, 10, -10, 10, -5, 5); //sets up the orthographic projection with the near and far perspective to 5.
        drawGl2.glMatrixMode(GL2.GL_MODELVIEW);
        drawGl2.glClearDepth(1.0f);
        drawGl2.glDepthFunc(GL2.GL_LEQUAL);
        drawGl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        //turn transparency on
        drawGl2.glEnable(GL2.GL_BLEND);
        drawGl2.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        //------------------------Draw the six different shapes--------------------             
        //Draw the 3D Triangle       
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(2);
        drawGl2.glTranslated(-6.0, 5.0, 0);// Move 6 units left and into screen
        drawGl2.glRotated(-45, 1.5, 2,-3);//Rotates at 45 degrees Counter-clockwise on +x-axis, 
        drawGl2.glScaled(1.5, 1.5, 1.5);//Uniform scaling by a factor of 1.5
        drawGl2.glRotated(frameNumber * 0.7, 0, 0, 1);
        shapes.draw3DTriangle(drawGl2);
        drawGl2.glPopMatrix();

        // Draw the 3D Cube.
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(1);
        drawGl2.glTranslated(0, 5.0, 0);// position at top-center
        drawGl2.glRotated(90, 1.5, -2, 3);//Rotates at 90 degrees clockwise on the -z-axis.
        drawGl2.glScaled(1.5, 1.5, 1.5);
        drawGl2.glRotated(-frameNumber * 0.7, 1, 0, 0);
        shapes.draw3DCube(drawGl2);
        drawGl2.glPopMatrix();

        // Draw the 3D Plus symbol.
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(2);
        drawGl2.glTranslated(-6.0, -5.0, 0);//Move 6 units left 
        drawGl2.glRotated(20, 1.5, -2, 3);//rotates at 20 degrees about the -y-axis
        drawGl2.glScaled(.99 * (frameNumber % 250) / 250, .99 * (frameNumber % 250)
                / 250, .99 * (frameNumber % 250) / 250);// Scaling for increase the size of this object
        drawGl2.glScaled(.275, .275, .275);
        drawGl2.glRotated(-frameNumber * 0.7, 0, 1, 0);
        shapes.draw3DPlus(drawGl2);
        drawGl2.glPopMatrix();

        // Draw the 3D Icosahedron.
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(2);
        drawGl2.glTranslated(-12 + 24 * (frameNumber % 500) / 500.0, 0, 0);// Moves the symbol to the right, passing across the window 
        drawGl2.glRotated(45, 1.5, -2, 3);//rotates at 45 degrees about the -x-axis.
        drawGl2.glScaled(1.5, 1.5, 1.5);// set size
        drawGl2.glRotated(-frameNumber * 0.7, 1, 0, 0);
        shapes.draw3DIcosahedron(drawGl2);
        drawGl2.glPopMatrix();

       //Draw a Toru
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(2);
        drawGl2.glTranslated(1.0, -5.0, 0);//Move 1 unit to the right into the panel, through points (x,y,z).
        drawGl2.glRotated(270, 1.5, -2, 3);//rotates about the y-axis
        drawGl2.glScaled(.65, .65, .65);// scale size
        drawGl2.glRotated(-frameNumber * 0.7, 0, 1, 0);
        shapes.draw3DTorus(drawGl2,3.0f, 1.0f, 20, 20);
        drawGl2.glPopMatrix();    
      
        // Draw the 3D Diamond.
        drawGl2.glPushMatrix();
        drawGl2.glLineWidth(2);
        drawGl2.glTranslated(7.0, -5.0, 0);//Move 6 units to the right
        drawGl2.glRotated(360, 1.5, -2, 3);//rotates  at 180 degrees 
        drawGl2.glScaled(.35, .35, .35);// scale size
        drawGl2.glRotated(-frameNumber * 0.7, 0, 1, 0);
        shapes.draw3DDiamond(drawGl2);
        drawGl2.glPopMatrix();

        // Draw the 3D Cylinder.
        drawGl2.glPushMatrix();
        drawGl2.glTranslated(2 * Math.cos(.015 * frameNumber) + 0, 2 * Math.sin(.015 * frameNumber) + 0,
                0.0);// creates Counter-clock moviment
        drawGl2.glTranslated(6.0, 5.0, 0);// Move six units to the right
        drawGl2.glRotated(45, 1.5, -2, 3);//rotates at 45 degrees
        drawGl2.glScaled(1.6, 1.6, 1.6);
        drawGl2.glRotated(-frameNumber * 0.7, 0, 1, 0);
        shapes.draw3DCylinder(drawGl2, 0.5, 1, 32, 10, 5);
        drawGl2.glPopMatrix();
          
        
    }

    //--------Initialization, setting up, and configuring the camera and lights---------   
    @Override
    public void init(GLAutoDrawable drawable) {

        // calls the panel when is created
        GL2 gl = drawable.getGL().getGL2();
        gl.glEnable(GL2.GL_DEPTH_TEST); // Required for 3D drawing
        gl.glLineWidth(2);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1); // Set background for color "Black"
// The next lines help set up the coordinates system.
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-10, 10, -10, 10, -5, 5); //sets up the orthographic projection with the near and far perspective to 5.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glClearDepth(1.0f);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
//turn transparency on
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 100);

    }

    //Calls for the GLJPanel changes.
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    //This calls before the GLJPanel is destroyed.
    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    // private void viewAt(int i, int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
    // }
}
