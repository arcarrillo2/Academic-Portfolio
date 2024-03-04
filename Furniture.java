//////////////// Furniture Class //////////////////////////
//
// Title: Furniture Class
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
 * A class that represents a piece of furniture that can be displayed in out graphic visualizer
 */
public class Furniture {
  // instance fields
  public final PImage IMAGE;
  private String name;
  private int x;
  private int y;

  /**
   * Constructs a new Furniture object with a name and x, y position.
   *
   * @param name the name of the furniture
   * @param x    the x-coordinate of the furniture
   * @param y    the y-coordinate of the furniture
   */
  public Furniture(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
    // initialize IMAGE instance by loading in PImage
    String imagePath = "images" + File.separator + name + ".png";
    this.IMAGE = Utility.loadImage(imagePath);
  }

  /**
   * Constructs a new Furniture object with a specified name. The object is positioned at the center
   * of the screen.
   *
   * @param name the name of the furniture
   */
  public Furniture(String name) {
    this.name = name;
    // find center of screen to set position of object
    this.x = Utility.width() / 2;
    this.y = Utility.height() / 2;

    String imagePath = "images" + File.separator + name + ".png";
    this.IMAGE = Utility.loadImage(imagePath);
  }

  // public instance method -- getter/accessor methods


  /**
   * Gets the x-coordinate of the furniture.
   * 
   * @return the x-coordinate of the furniture
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-coordinate of the furniture.
   * 
   * @return the y-coordinate of the furniture
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the name of the furniture.
   * 
   * @return the name of the furniture
   */
  public String name() {
    return this.name;
  }

  /**
   * Draws the furniture at its current position.
   */
  public void draw() {
    Utility.image(IMAGE, this.x, this.y);
  }
}
