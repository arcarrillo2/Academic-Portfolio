//////////////// Toy Class //////////////////////////
//
// Title: Toy Class
// Course: CS 300 Sping 2024
//
// Author:Angelique Carrillo
// Email: arcarrillo2@wisc.edu
// Lecturer: Hobbes LeGault
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: zybooks
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import processing.core.PImage;

/**
 * Represents a toy that can move, rotate, and interact in out graphical visualizer.
 */
public class Toy {
  // initialize class fields
  public final PImage IMAGE; // static field for toy's image
  // private String name; // name of toy
  private int x; // x coordinate of toy
  private int y; // y coordinate of toy
  private boolean isDragging; // indicates if toy is being dragged or not (currently)
  private int rotations; // number of 90-degree rotations appplied to the toy


  /**
   * Creates a new Toy object with a specified name, x, and y positions.
   *
   * @param name the name of the toy
   * @param x    the x-coordinate of the toy
   * @param y    the y-coordinate of the toy
   */

  public Toy(String name, int x, int y) {
    // this.name = name;
    this.x = x;
    this.y = y;
    // Initially, the toy is not being dragged and that no rotations are applied
    this.isDragging = false;
    this.rotations = 0;

    // Load the image from the specified path
    String imagePath = "images" + File.separator + name + ".png";
    this.IMAGE = Utility.loadImage(imagePath);
  }

  /**
   * Creates a new Toy object with a specified name. The object is positioned at the center of the
   * screen.
   *
   * @param name the name of the toy
   */

  public Toy(String name) {
    // find center of screen to set position of toy
    this.x = Utility.width() / 2;
    this.y = Utility.height() / 2;
    this.isDragging = false;
    this.rotations = 0;

    // Load the image from the path
    String imagePath = "images" + File.separator + name + ".png";
    IMAGE = Utility.loadImage(imagePath);
  }


  /**
   * Gets the x-coordinate of the toy.
   * 
   * @return the x-coordinate
   */

  public int getX() {
    return x;
  }

  /**
   * Gets the y-coordinate of the toy.
   * 
   * @return the y-coordinate
   */

  public int getY() {
    return y;
  }

  /**
   * Sets the x-coordinate of the toy.
   * 
   * @param x the new x-coordinate
   */

  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y-coordinate of the toy.
   * 
   * @param y the new y-coordinate
   */

  public void setY(int y) {
    this.y = y;
  }

  /**
   * Gets the number of rotations of the toy.
   * 
   * @return the number of rotations
   */

  public int getRotationsCount() {
    return rotations;
  }

  /**
   * Checks if the toy is currently being dragged.
   * 
   * @return true if the toy is being dragged, false otherwise
   */

  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Marks the toy as being dragged.
   */
  public void startDragging() {
    this.isDragging = true;
  }

  /**
   * Marks the toy as not being dragged.
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Rotates the toy.
   */
  public void rotate() {
    rotations++;
  }

  /**
   * Moves the toy by the specified x and y while ensuring it remains within graphic visualizers
   * bounds.
   *
   * @param dx the change in x-coordinate
   * @param dy the change in y-coordinate
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;
    // within window bounds
    int toyWidth = (rotations % 2 == 0) ? IMAGE.width : IMAGE.height;
    int toyHeight = (rotations % 2 == 0) ? IMAGE.height : IMAGE.width;
    // if x coordinate is out of bounds reset it to 0
    if (x < 0) {
      x = 0;
    } else if (x > Utility.width() - toyWidth) {
      x = Utility.width() - toyWidth;
    }
    // if y coordinate is out of bounds reset it to 0
    if (y < 0) {
      y = 0;
    } else if (y > Utility.height() - toyHeight) {
      y = Utility.height() - toyHeight;
    }
  }

  /**
   * Draws the toy on the screen while taking acount rotations and dragging
   */
  public void draw() {
    // Handle toy dragging by updating its position based on mouse movement
    if (isDragging == true) {
      // Calculate how much the mouse has moved
      int dx = Utility.mouseX() - Utility.pmouseX();
      int dy = Utility.mouseY() - Utility.pmouseY();
      // Move the toy based on mouse movement
      move(dx, dy);
    }
    // Draw the toy image WHILE taking into account any rotations
    drawToyImage();
  }

  /**
   * Helper method to draw an image accounting for any rotations to the screen. The implementation
   * of this method is fully provided in the write-up.
   */
  private void drawToyImage() {
    Utility.pushMatrix();
    Utility.translate(x, y);
    Utility.rotate(this.rotations * Utility.PI / 2);
    Utility.image(IMAGE, 0.0f, 0.0f);
    Utility.popMatrix();
  }

  /**
   * Checks if the given (x, y) coordinates fall within the bounds of the toy's image, considering
   * its current position and rotation state.
   *
   * @param x the x-coordinate of the point to check
   * @param y the y-coordinate of the point to check
   * @return true if the specified point is over the toy, false otherwise
   */
  public boolean isOver(int x, int y) {
    // Determine toy width and height WHILE taking in account its rotations
    int toyWidth = (rotations % 2 == 0) ? IMAGE.width : IMAGE.height;
    int toyHeight = (rotations % 2 == 0) ? IMAGE.height : IMAGE.width;

    // Calculate the rotated coordinates
    float rotatedX = (float) (Math.cos(rotations * Utility.PI / 2) * (x - this.x)
        - Math.sin(rotations * Utility.PI / 2) * (y - this.y) + this.x);
    float rotatedY = (float) (Math.sin(rotations * Utility.PI / 2) * (x - this.x)
        + Math.cos(rotations * Utility.PI / 2) * (y - this.y) + this.y);


    // Find the boundaries of the Toy AFTER rotation
    float leftBound = this.x - toyWidth / 2;
    float rightBound = this.x + toyWidth / 2;
    float topBound = this.y - toyHeight / 2;
    float bottomBound = this.y + toyHeight / 2;

    // Return true if rotated coordinates are within the bounds
    return (rotatedX >= leftBound && rotatedX <= rightBound && rotatedY >= topBound
        && rotatedY <= bottomBound);
  }

  /**
   * Checks for overlap between the toy and a Furniture object while taking into account their
   * positions, sizes, and the toy's rotation state.
   *
   * @param other the Furniture object to check for overlap with
   * @return true if there is any overlap between the toy and the furniture, false otherwise
   */
  public boolean isOver(Furniture other) {
    // Determine other furniture width and height
    int otherWidth = other.IMAGE.width;
    int otherHeight = other.IMAGE.height;

    // determine toy width and height taking into account its rotations
    int toyWidth = (rotations % 2 == 0) ? IMAGE.width : IMAGE.height;
    int toyHeight = (rotations % 2 == 0) ? IMAGE.height : IMAGE.width;

    // Calculate the center points
    int thisCenterX = this.x;
    int thisCenterY = this.y;
    int otherCenterX = other.getX();
    int otherCenterY = other.getY();

    // Using half-sizes and the center point we calculated find if there's any overalp
    boolean overlapX = Math.abs(thisCenterX - otherCenterX) < (toyWidth / 2 + otherWidth / 2);
    boolean overlapY = Math.abs(thisCenterY - otherCenterY) < (toyHeight / 2 + otherHeight / 2);

    return overlapX && overlapY;

  }

}
