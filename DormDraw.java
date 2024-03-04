//////////////// Dorm Draw //////////////////////////
//
// Title: P02 Dorm Draw
// Course: CS 300 Spring 2024
//
// Author: Angelique Carrillo
// Email: arcarrillo2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: N/A
// Online Sources: ZyBooks - helped me create the switch cases
//
///////////////////////////////////////////////////////////////////////////////


import java.io.File;
import processing.core.PImage;

/**
 * This class models an application to draw a floor plan for a dorm. It allows users to add, drag,
 * rotate, and delete symbols to create and customize a dorm layout.
 */
public class DormDraw {
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Symbol[] symbols; // non-compact perfect size array storing
  // dorm symbols added to the display window


  public static void main(String[] args) {
    Utility.runApplication(); // starts the application
  }

  /**
   * This static method initializes the DormDraw data fields. This callback method is called once
   * when the program starts.
   * 
   * @param none
   * @return none
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    symbols = new Symbol[12];
  }

  /**
   * This callback method continuously draws and updates the application display window. It is
   * automatically called directly after setup() and continuously executes until the program is
   * stopped.
   * 
   * @param none
   * @return none
   */
  public static void draw() {
    Utility.background(Utility.color(255, 250, 250));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] != null) {
        symbols[i].draw();
      }
    }
  }

  /**
   * Adds a new element (toAdd) to the perfect size array symbols
   * 
   * @param symbols a non-compact perfect size array storing elements of type Symbol
   * @param toAdd   the symbol to add
   * @return none
   */
  public static void addSymbol(Symbol[] symbols, Symbol toAdd) {
    // iterates through symbols array for a null spot
    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] == null) {
        // when first null position is found in array, add the desired symbol in that position and
        // stop iterating
        symbols[i] = toAdd;
        break;
      }
    }
  }

  /**
   * Callback method called once every time a key is pressed. The key that was pressed is returned
   * by the Utility.key() utility method.
   * 
   * @param none
   * @return none
   */
  public static void keyPressed() {
    // getting current mouse coordinates
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();

    // iterate through array of symbols and find first null position
    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] == null) {

        // check the pressed key and create a new symbol accordingly in the current coordinates of
        // the mouse
        switch (Utility.key()) {
          case 'b':
          case 'B':
            symbols[i] = new Symbol("bed.png", mouseX, mouseY);
            break;
          case 'c':
          case 'C':
            symbols[i] = new Symbol("chair.png", mouseX, mouseY);
            break;
          case 'd':
          case 'D':
            symbols[i] = new Symbol("dresser.png", mouseX, mouseY);
            break;
          case 'k':
          case 'K':
            symbols[i] = new Symbol("desk.png", mouseX, mouseY);
            break;
          case 'f':
          case 'F':
            symbols[i] = new Symbol("sofa.png", mouseX, mouseY);
            break;
          case 'g':
          case 'G':
            symbols[i] = new Symbol("rug.png", mouseX, mouseY);
            break;
          case 'p':
          case 'P':
            symbols[i] = new Symbol("plant.png", mouseX, mouseY);
            break;

          default:
            continue;
        }
        // break out of the loop after adding a symbol
        break;
      }
    }
    // seperately iterate through the array of symbols
    for (int i = 0; i < symbols.length; i++) {
      // check that current slot has a symbol and the mouse coordinates are currently over the
      // symbol
      if (symbols[i] != null && isMouseOver(symbols[i]) == true) {
        // if the 'r'/'R' key is pressed and rotate the symbol the mouse is over
        switch (Utility.key()) {
          case 'r':
          case 'R':
            symbols[i].rotate();
            break;
          // if the backspace button is hit delete the symbol your mouse is over (considered null in
          // the array)
          case Utility.BACKSPACE:
            symbols[i] = null;
            break;
        }
        // break out of loop after actions fulfilled
        break;
      }
    }
    // if the 's'/'S' key is pressed take a screenshot of the dorm and save it to dormDraw.png
    switch (Utility.key()) {
      case 's':
      case 'S':
        Utility.save("dormDraw.png");
        break;
    }
  }

  /**
   * Checks if the mouse is over a given symbol.
   * 
   * @param symbol - reference to a given symbol in the dorm
   * @return true if the mouse is over the given symbol object, false otherwise
   */
  public static boolean isMouseOver(Symbol symbol) {
    // gets x and y position of added symbol
    int x_sym = symbol.x();
    int y_sym = symbol.y();
    // gets width and height position of added symbol
    int w_sym = symbol.width();
    int h_sym = symbol.height();

    // getting x & y position of mouse
    int mouseX = Utility.mouseX();
    int mouseY = Utility.mouseY();

    // boundaries of each added symbol
    int left_bound = x_sym - (w_sym) / 2;
    int right_bound = x_sym + (w_sym) / 2;
    int top_bound = y_sym - (h_sym) / 2;
    int bottom_bound = y_sym + (h_sym) / 2;

    // return true if the mouse is within the bounds of the symbol (mouse is over the symbol)
    return (mouseX >= left_bound && mouseX <= right_bound && mouseY >= top_bound
        && mouseY <= bottom_bound);
  }

  /**
   * Callback method called each time the user presses the mouse
   * 
   * @param none
   * @return none
   */
  public static void mousePressed() {
    // Initialize to an invalid index
    int topmostIndex = -1;

    // Iterate through symbols to find the topmost symbol under the mouse (ensuring that mouse will
    // drag ONLY the symbol we initially clicked on and nothing else (even if symbol is dragged over
    // another symbol)
    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] != null && isMouseOver(symbols[i]) == true) {
        if (topmostIndex == -1 || i > topmostIndex) {
          // set topmost index to whatever symbol the mouse is over
          topmostIndex = i;
        }
      }
    }

    // Start dragging the topmost symbol under the mouse
    if (topmostIndex != -1) {
      symbols[topmostIndex].startDragging();
    }
  }

  /**
   * Callback method called every time the mouse button is released
   * 
   * @param none
   * @return none
   */
  public static void mouseReleased() {
    // iterate through array of symbols and ensure the current slot has a smybol
    for (int i = 0; i < symbols.length; i++) {
      if (symbols[i] != null) {
        // since mouse is NOT over the symbol stop dragging it
        symbols[i].stopDragging();
      }
    }
  }
}


