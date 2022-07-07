package TestCode;
/*
*  File: Project2.java
*  CMSC405
*  Purpose: Interface "Shapes" class 
*  It contains all the shapes to be drawn. 
* 
*/

/**
 * File:Shapes.java
 * @author Lilian
 */
import javax.media.opengl.GL2;

public class Shapes {

    /**
     * 
     * Reference:
     * "3D Shapes"
     * @Author:Nehe 
     * Retrieved from :
     * http://myhsts.org/blog/CG_Examples.html
     * @param glTri
     */
    
 //Creates 3D Triangle 
    public static void draw3DTriangle(GL2 glTri) {
//a GL_LINE_LOOP,to draw only the edges of triangle.
        glTri.glBegin(GL2.GL_LINE_LOOP);

// Front side
        glTri.glColor3f(1.0f, 0.0f, 0.0f); // Red
        glTri.glVertex3f(0f, 1.5f, 0.0f); // Top Of Triangle (Front)

        glTri.glColor3f(0.0f, 1.0f, 0.0f); // Green
        glTri.glVertex3f(-.5f, -.5f, .5f); // Left Of Triangle (Front)

        glTri.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        glTri.glVertex3f(.5f, -.5f, .5f); // Right Of Triangle (Front)

// Right side
        glTri.glColor3f(1.0f, 0.0f, 0.0f); // Red
        glTri.glVertex3f(0f, 1.5f, 0.0f); // Top Of Triangle (Right)

        glTri.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        glTri.glVertex3f(.5f, -.5f, .5f); // Left Of Triangle (Right)

        glTri.glColor3f(0.0f, 1.0f, 0.0f); // Green
        glTri.glVertex3f(.5f, -.5f, -.5f); // Right Of Triangle (Right)

// Back side
        glTri.glColor3f(1.0f, 0.0f, 0.0f); // Red
        glTri.glVertex3f(0f, 1.5f, 0.0f); // Top Of Triangle (Back)

        glTri.glColor3f(0.0f, 1.0f, 0.0f); // Green
        glTri.glVertex3f(.5f, -.5f, -.5f); // Left Of Triangle (Back)

        glTri.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        glTri.glVertex3f(-.5f, -.5f, -.5f); // Right Of Triangle (Back)

//Left side
        glTri.glColor3f(1.0f, 0.0f, 0.0f); // Red
        glTri.glVertex3f(0f, 1.5f, 0.0f); // Top Of Triangle (Left)

        glTri.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        glTri.glVertex3f(-.5f, -.5f, -.5f); // Left Of Triangle (Left)

        glTri.glColor3f(0.0f, 1.0f, 0.0f); // Green
        glTri.glVertex3f(-.5f, -.5f, .5f); // Right Of Triangle (Left)

        glTri.glEnd(); // Done Drawing 3d triangle (Pyramid)
        glTri.glFlush();
    }

//Create the 3D cube (six sides w/ colors)
    public static void square(GL2 gl) {
//GL_TRIANGLE_FAN to create a solid filled shape.
// Each of the 4 corners of this shape will have a different color
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
        gl.glVertex3d(-0.5, -0.5, 0.5); // Bottom left corner

        gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
        gl.glVertex3d(0.5, -0.5, 0.5); // Bottom right corner

        gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
        gl.glVertex3d(0.5, 0.5, 0.5); //Top right corner

        gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow
        gl.glVertex3d(-0.5, 0.5, 0.5); //Top left corner
        gl.glEnd();

//Using GL_LINE_LOOP to outline the edges in black
        gl.glColor3d(0, 0, 0); //black
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex3d(-0.5, -0.5, 0.5);
        gl.glVertex3d(0.5, -0.5, 0.5);
        gl.glVertex3d(0.5, 0.5, 0.5);
        gl.glVertex3d(-0.5, 0.5, 0.5);
        gl.glEnd();
    }

// 3D cube side faces
    public static void draw3DCube(GL2 glCub) {
        glCub.glPushMatrix();
        square(glCub); //Front face
        glCub.glPopMatrix();

        glCub.glPushMatrix();
        glCub.glRotated(90, 0, 1, 0); //Right face
        square(glCub);
        glCub.glPopMatrix();

        glCub.glPushMatrix();
        glCub.glRotated(-90, 1, 0, 0); //Top face
        square(glCub);
        glCub.glPopMatrix();

        glCub.glPushMatrix();
        glCub.glRotated(180, 0, 1, 0); //Back face
        square(glCub);
        glCub.glPopMatrix();

        glCub.glPushMatrix();
        glCub.glRotated(-90, 0, 1, 0); //Left face
        square(glCub);
        glCub.glPopMatrix();

        glCub.glPushMatrix();
        glCub.glRotated(90, 1, 0, 0); //Bottom face
        square(glCub);
        glCub.glPopMatrix();
        glCub.glFlush();
    }

// Creates a 3D plus  
    public static void draw3DPlus(GL2 glPlus) {
// An array of all the vertices of the plus symbol in no particular order.
        double[][] vertices = new double[][]{
            {2, -2, 2},
            {2, -2, -2},
            {2, 1, -2},
            {2, 1, 2},
            {1.5, 5.0, 0},
            {-1.5, 5.0, 0},
            {-2, -2, 2},
            {-2, 1, 2},
            {-2, 1, -2},
            {-2, -2, -2},
            {1.5, -5.0, 0},
            {-1.5, -5.0, 0},
            {7, 1.5, 0},
            {7, -1.5, 0},
            {-7, 1.5, 0},
            {-7, -1.5, 0},};
// Using an Indexed Face Set
        int[][] faces = new int[][]{
            {0, 1, 2, 3},
            {3, 2, 4},
            {7, 3, 4, 5},
            {2, 8, 5, 4},
            {5, 8, 7},
            {0, 3, 7, 6},
            {2, 1, 9, 8},
            {6, 7, 8, 9},
            {0, 1, 10},
            {6, 9, 11},
            {6, 0, 10, 11},
            {9, 1, 10, 11},
            {3, 0, 13, 12},
            {1, 2, 12, 13},
            {2, 3, 12},
            {0, 1, 13},
            {6, 7, 14, 15},
            {9, 8, 14, 15},
            {7, 8, 14},
            {6, 9, 15},};
// set Indexe Face colors
        double[][] faceColors = new double[][]{
            {1, 0, 0}, //red side
            {1, 1, 0}, //yellow top side
            {0, 0, 1}, //blue top side
            {0, 0, 1}, //blue top side
            {1, 1, 0}, //yellow top side
            {0, 1, 0}, //green side
            {0, 1, 0}, //green side
            {1, 0, 0}, // red side
            {1, 1, 0}, // yellow bottom side
            {1, 1, 0}, // yellow bottom side
            {0, 0, 1}, //blue bottom side
            {0, 0, 1}, //blue bottom side

            {0, 0, 1}, //blue right side
            {0, 0, 1}, //blue right side
            {1, 0, 0}, //yellow right side
            {1, 0, 0}, //yellow right side

            {0, 0, 1}, //blue left side
            {0, 0, 1}, //blue left side
            {1, 0, 0}, //yellow left side
            {1, 0, 0}, //yellow left side
        };

        glPlus.glPushMatrix();

        for (int i = 0; i < faces.length; i++) {
            glPlus.glColor4d(0, 0, 0, .3); //color the faces of the star shape a transparent black

            glPlus.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++) {
                int vertexNum = faces[i][j];
                glPlus.glVertex3dv(vertices[vertexNum], 0);
            }
            glPlus.glEnd();
        }

//draw edges colored in black
        glPlus.glColor3f(0, 0, 0);
        for (int i = 0; i < faces.length; i++) {
            glPlus.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++) {
                glPlus.glColor3dv(faceColors[j], 0);

                int vertexNum = faces[i][j];
                glPlus.glVertex3dv(vertices[vertexNum], 0);
            }
            glPlus.glEnd();
        }

        glPlus.glPopMatrix();
        glPlus.glFlush();
    }


    /*
    *@Author
    *Reference:
    *https://math.hws.edu/graphicsbook/source/jogl/TexturedShapes.java
    *
    */
    
    public static void draw3DTorus(GL2 drawGl2, double outerRadius, double innerRadius,
            int slices, int rings) {
        double centerRadius = (innerRadius + outerRadius) / 2;
        double tubeRadius = outerRadius - centerRadius;
        for (int i = 0; i < slices; i++) {
            double s1 = 1.0 / slices * i;
            double s2 = 1.0 / slices * (i + 1);
            double centerCos1 = Math.cos(2 * Math.PI * s1);
            double centerSin1 = Math.sin(2 * Math.PI * s1);
            double centerCos2 = Math.cos(2 * Math.PI * s2);
            double centerSin2 = Math.sin(2 * Math.PI * s2);
            drawGl2.glBegin(GL2.GL_QUAD_STRIP);
            for (int j = 0; j <= rings; j++) {
                double t = 1.0 / rings * j;
                double cos = Math.cos(2 * Math.PI * t - Math.PI);
                double sin = Math.sin(2 * Math.PI * t - Math.PI);
                double x1 = centerCos1 * (centerRadius + tubeRadius * cos);
                double y1 = centerSin1 * (centerRadius + tubeRadius * cos);
                double z1 = sin * tubeRadius;
                drawGl2.glColor3f(0.0f, 1.0f, 0.0f); // Green
                drawGl2.glNormal3d(centerCos1 * cos, centerSin1 * cos, sin);
                drawGl2.glVertex3d(x1, y1, z1);
                double x2 = centerCos2 * (centerRadius + tubeRadius * cos);
                double y2 = centerSin2 * (centerRadius + tubeRadius * cos);
                double z2 = sin * tubeRadius;
                drawGl2.glColor3f(0.0f, 0.0f, 1.0f); // Blue
                drawGl2.glNormal3d(centerCos2 * cos, centerSin2 * cos, sin);
                drawGl2.glVertex3d(x2, y2, z2);
            }
            drawGl2.glEnd();
        }
    } // end draw3DTorus

// Create a transparent 3D Icosahedron.
    // Using an Indexed Face Set
    public static void draw3DIcosahedron(GL2 glIcos) {
        double[][] vertices = new double[][]{
            // An array of all the vertices of the tetrahedron in no particular order.
            {-1, -0.618034, 0},
            {0, 1, 0.618034},
            {0, 1, -0.618034},
            {1, 0.618034, 0},
            {1, -0.618034, 0},
            {0, -1, -0.618034},
            {0, -1, 0.618034},
            {0.618034, 0, 1},
            {-0.618034, 0, 1},
            {0.618034, 0, -1},
            {-0.618034, 0, -1},
            {-1, 0.618034, 0}
        };

        int[][] faces = new int[][]{
            {3, 7, 1},
            {4, 7, 3},
            {6, 7, 4},
            {8, 7, 6},
            {7, 8, 1},
            {9, 4, 3},
            {2, 9, 3},
            {2, 3, 1},
            {11, 2, 1},
            {10, 2, 11},
            {10, 9, 2},
            {9, 5, 4},
            {6, 4, 5},
            {0, 6, 5},
            {0, 11, 8},
            {11, 1, 8},
            {10, 0, 5},
            {10, 5, 9},
            {0, 8, 6},
            {0, 10, 11}
        };

// To indicate the level of transparency, each face color will have a 4th column 
        double[][] faceColors = new double[][]{
            {1, 0, 0, 0.4},//red
            {0, 1, 0, 0.4},//green
            {0, 0, 1, 0.4},//navy-blue
            {1, 1, 0, 0.4},//yellow
            {0, 1, 1, 0.4},//light-blue
            {1, 0, 0, 0.4},//red
            {0, 1, 0, 0.4},//green
            {0, 0, 1, 0.4},//navy-blue
            {1, 1, 0, 0.4},//yellow
            {0, 1, 1, 0.4},//light-blue
            {1, 0, 0, 0.4},//red
            {0, 1, 0, 0.4},//green
            {0, 0, 1, 0.4},//navy-blue
            {1, 1, 0, 0.4},//yellow
            {0, 1, 1, 0.4},//light-blue
            {1, 0, 0, 0.4},//red
            {0, 1, 0, 0.4},//green
            {0, 0, 1, 0.4},//navy-blue
            {1, 1, 0, 0.4},//yellow
            {0, 1, 1, 0.4},//light-blue
            {0, 0, 1, 0.4},};//blue

        glIcos.glPushMatrix();
//color the tetrahedron faces
        for (int i = 0; i < faces.length; i++) {
            glIcos.glColor4dv(faceColors[i], 0);

            glIcos.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++) {
                int vertexNum = faces[i][j];
                glIcos.glVertex3dv(vertices[vertexNum], 0);
            }
            glIcos.glEnd();
        }

//draw edges in color black
        glIcos.glColor3f(0, 0, 0);
        for (int i = 0; i < faces.length; i++) {
            glIcos.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++) {
                int vertexNum = faces[i][j];
                glIcos.glVertex3dv(vertices[vertexNum], 0);
            }
            glIcos.glEnd();
        }

        glIcos.glPopMatrix();
        glIcos.glFlush();
    }

// Using an Indexed Face Set to Create a 3D diamond.
    public static void draw3DDiamond(GL2 glDiam) {
// An array of all the vertices of the diamond shape in no particular order.
        double[][] vertices = new double[][]{
            {2, -2, 2},
            {2, -2, -2},
            {2, 1, -2},
            {2, 1, 2},
            {1.5, 5.0, 0},
            {-1.5, 5.0, 0},
            {-2, -2, 2},
            {-2, 1, 2},
            {-2, 1, -2},
            {-2, -2, -2},
            {1.5, -5.0, 0},
            {-1.5, -5.0, 0},};

        //Creates the top, middle, and bottom side
        int[][] faces = new int[][]{
            {0, 1, 2, 3}, // middle 
            {3, 2, 4}, // top 
            {7, 3, 4, 5}, //top 
            {2, 8, 5, 4}, // top 
            {5, 8, 7}, // top 
            {0, 3, 7, 6}, // middle 
            {2, 1, 9, 8}, // middle 
            {6, 7, 8, 9}, // middle 
            {0, 1, 10}, //bottom 
            {6, 9, 11}, //bottom 
            {6, 0, 10, 11}, //bottom 
            {9, 1, 10, 11}, //bottom 
        };
        // Creates the top, middle, and bottom color sides
        double[][] faceColors = new double[][]{
            {1, 1, 1}, // middle side-blue
            {0, 0, 1}, //top side-blue
            {0, 0, 1}, //top side-blue
            {0, 0, 1}, //top side-blue
            {1, 1, 1}, //top side-white
            {0, 0, 1}, // middle side-blue
            {0, 0, 1}, // middle side-blue
            {1, 1, 1}, // middle side-blue
            {1, 1, 1}, // bottom side-white
            {0, 0, 1}, // bottom side-blue
            {0, 0, 1}, //bottom side-blue
            {0, 0, 1}, //bottom side-blue
        };

        glDiam.glPushMatrix();
//color the diamond shape faces
        for (int i = 0; i < faces.length; i++) {
            glDiam.glColor3dv(faceColors[i], 0);

            glDiam.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++) {
                int vertexNum = faces[i][j];
                glDiam.glVertex3dv(vertices[vertexNum], 0);
            }
            glDiam.glEnd();
        }

//draw edges colored in black
        glDiam.glColor3f(0, 0, 0);
        for (int i = 0; i < faces.length; i++) {
            glDiam.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++) {
                int vertexNum = faces[i][j];
                glDiam.glVertex3dv(vertices[vertexNum], 0);
            }
            glDiam.glEnd();
        }

        glDiam.glPopMatrix();
        glDiam.glFlush();

    }

    /*
    *@Author
    *Reference:
    *https://math.hws.edu/graphicsbook/source/jogl/TexturedShapes.java
    *
     */
    //Creates 3D cylinder 
    public static void draw3DCylinder(GL2 gl, double radius, double height, int slices, int stacks, int rings) {
        for (int j = 0; j < stacks; j++) {
            double z1 = (height / stacks) * j;
            double z2 = (height / stacks) * (j + 1);
            gl.glBegin(GL2.GL_QUAD_STRIP);
            for (int i = 0; i <= slices; i++) {
                double longitude = (2 * Math.PI / slices) * i;
                double sinLong = Math.sin(longitude);
                double cosLong = Math.cos(longitude);
                double x = cosLong;
                double y = sinLong;
                gl.glNormal3d(x, y, 0);

                gl.glColor4f(1.0f, 1, 0.0f, 0.5f); // Transparent yellow
                gl.glVertex3d(radius * x, radius * y, z2);
                gl.glVertex3d(radius * x, radius * y, z1);
            }
            gl.glEnd();
        }
        if (rings > 0) { // draw top and bottom
            gl.glNormal3d(0, 0, 1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);
                    gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
                    gl.glVertex3d(radius * cos * d1, radius * sin * d1, height);
                    gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
                    gl.glVertex3d(radius * cos * d2, radius * sin * d2, height);
                }
                gl.glEnd();
            }

            gl.glNormal3d(0, 0, -1);
            for (int j = 0; j < rings; j++) {
                double d1 = (1.0 / rings) * j;
                double d2 = (1.0 / rings) * (j + 1);
                gl.glBegin(GL2.GL_QUAD_STRIP);
                for (int i = 0; i <= slices; i++) {
                    double angle = (2 * Math.PI / slices) * i;
                    double sin = Math.sin(angle);
                    double cos = Math.cos(angle);

                    gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
                    gl.glVertex3d(radius * cos * d2, radius * sin * d2, 0);

                    gl.glColor3f(0.0f, 1.0f, 0.0f); // Green

                    gl.glVertex3d(radius * cos * d1, radius * sin * d1, 0);
                }
                gl.glEnd();
            }
        }

        gl.glFlush();
    }

}
