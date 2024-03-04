//////////////// ToySaga Class //////////////////////////
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
import java.util.ArrayList;
import processing.core.PImage;

/**
 * Manages the toy application with furniture and toys.
 */
public class ToySaga {

  private static PImage backgroundImage;
  private static ArrayList<Furniture> furnitureList;
  private static ArrayList<Toy> toyList;
  private static final String BOX_NAME = "box";
  private static final int MAX_TOYS_COUNT = 8;

  /**
   * Initializes the ToySaga data fields.
   */

  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");

    furnitureList = new ArrayList<>();
    toyList = new ArrayList<>();

    furnitureList.add(new Furniture("bed", 520, 270));
    furnitureList.add(new Furniture("rug", 220, 370));
    furnitureList.add(new Furniture("nightstand", 325, 240));
    furnitureList.add(new Furniture(BOX_NAME, 90, 230));
  }

  /**
   * This callback method continuously draws and updates the application display window.
   */

  public static void draw() {
    // first draw background image
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    // Draw furniture items
    for (Furniture furniture : furnitureList) {
      furniture.draw();
    }
    // update and draw toy items
    for (int i = 0; i < toyList.size(); i++) {
      Toy toy = toyList.get(i);

      // Remove any toys over the box furniture -- putting away the toy
      Furniture toyBox = getToyBox();
      if (toyBox != null && toy.isOver(toyBox)) {
        toyList.remove(i);
        // adjust index because we removed a toy from our list
        i--;
        continue;
      }
      // draw toy
      toy.draw();

    }

  }

  /**
   * Returns the Furniture object with a name matching BOX_NAME (exact match: case sensitive
   * comparison) if any is found.
   */
  public static Furniture getToyBox() {
    // Find and return the toy box furniture from the list -- exact match
    for (Furniture furniture : furnitureList) {
      if (furniture.name().equals(BOX_NAME)) {
        return furniture;
      }
    }
    // if its not found return null
    return null;
  }

  /**
   * Returns the toy which is currently dragging.
   */

  public static Toy getDraggingToy() {
    // see if the current toy is dragging -- if it is return true
    for (Toy toy : toyList) {
      if (toy.isDragging()) {
        return toy;
      }
    }
    // if not found return null
    return null;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    Toy topmostToy = null;
    int topmostIndex = -1;

    // Iterate through toyList to find the topmost toy under the mouse
    for (int i = toyList.size() - 1; i >= 0; i--) {
      Toy currentToy = toyList.get(i);
      if (currentToy.isOver(Utility.mouseX(), Utility.mouseY())) {
        topmostToy = currentToy;
        topmostIndex = i;
        break; // Stop searching once the topmost toy is found
      }
    }

    // Start dragging the topmost toy under the mouse
    if (topmostToy != null) {
      for (int i = 0; i < toyList.size(); i++) {
        Toy toy = toyList.get(i);
        // Stop dragging all other toys
        if (i != topmostIndex && toy.isDragging()) {
          toy.stopDragging();
        }
      }
      topmostToy.startDragging();
    }
  }

  /**
   * Callback method called every time the mouse button is released
   */
  public static void mouseReleased() {
    // Stop dragging all toys
    for (Toy toy : toyList) {
      if (toy.isDragging()) {
        toy.stopDragging();
      }
    }
  }

  public static void keyPressed() {
    // first getting the current x and y coordinates of the mouse
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();

    // make sure the toyList size has not surpassed the max toy count of 8 toys
    if (toyList.size() < MAX_TOYS_COUNT) {

      // use switch cases to associate keys with certain toys and functions
      switch (Utility.key()) {
        case 'c':
        case 'C':
          toyList.add(new Toy("car", mouseX, mouseY));
          break;

        case 't':
        case 'T':
          toyList.add(new Toy("teddyBear", mouseX, mouseY));
          break;

        case 'r':
        case 'R':
          for (Toy t : toyList) {
            if (t != null && t.isOver(mouseX, mouseY)) {
              t.rotate();
              break;
            }
          }
          // break after keys pressed
          break;
      }
    }
  }


  public static void main(String[] args) {
    Utility.runApplication(); // starts the application - ONLY STATEMENT ALLOWED HERE
  }

}
