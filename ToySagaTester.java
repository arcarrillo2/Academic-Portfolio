//////////////// ToySagaTester //////////////////////////
//
// Title: Toy Saga Tester
// Course: CS 300 Spring 2024
//
// Author: Angelique Carrillo
// Email: arcarrillo2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: none
// Online Sources: zybooks
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This class implements tester methods to ensure the correctness of the implementation of Furniture
 * and Toy classes in p03 Toy Saga I program.
 */
public class ToySagaTester {

  /**
   * This tester ensures the Furniture constructor which takes a String as input Furniture(String
   * name) correctly constructs a new Furniture object located at the center of the display window,
   * and assigned it a PImage and the name passed as input to the method call.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor1Getters() {
    // Create at least one new Furniture object by called the constructor Furniture(String). The
    // String passed as input to the constructor call should be either "bed", "box", "nightstand",
    // or "rug".
    Furniture furniture = new Furniture("nightstand");

    // Ensure that the call of getX() on that Furniture object equals Utility.width() / 2
    if (furniture.getX() != Utility.width() / 2) {
      System.out.println("1");
      return false;
    }
    // Ensure that the call of getY() on that Furniture object equals Utility.height() / 2
    if (furniture.getY() != Utility.height() / 2) {
      System.out.println("2");
      return false;
    } // Ensure that the call of name() on that Furniture object returns the name passed as input to
    // the constructor call
    if (!furniture.name().equals("nightstand")) {
      System.out.println("3");
      return false;
    }
    // Ensure that the value of the image field on that Furniture object returns a NON-null
    // reference.
    if (furniture.IMAGE == null) {
      System.out.println("4");
      return false;
    }
    // This test should fail if any of the above requirements is NOT satisfied

    return true; // default return statement
  }

  /**
   * This tester ensures the Furniture constructor which takes a String, and two integers as input
   * Furniture(String name, int x, int y) correctly constructs a new Furniture object located at the
   * (x,y) input position, assigned it the name passed as input to the method call, and an image.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testFurnitureConstructor2Getters() {
    // Create at least one new Furniture object by called the constructor Furniture(String, int,
    // int). The String passed as input to the constructor call should be either "bed", "box",
    // "nightstand", or "rug".
    Furniture furniture = new Furniture("bed", 100, 75);

    // Ensure that the call of getX() on that Furniture object equals the input x
    if (furniture.getX() != 100) {
      return false;
    }
    // Ensure that the call of getY() on that Furniture object equals the input y
    if (furniture.getY() != 75) {
      return false;
    }
    // Ensure that the call of name() on that Furniture object returns the name passed as input to
    // the constructor call
    if (furniture.name() != "bed") {
      return false;
    }
    // Ensure that the value of the image field on that Furniture object returns a NON-null
    // reference.
    if (furniture.IMAGE == null) {
      return false;
    }
    // This test should fail if any of the above requirements is NOT satisfied

    return true; // default return statement
  }

  /**
   * This tester ensures the Toy constructors, getters and setters of the x and y positions, and the
   * image field.
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyConstructorsGettersSetters() {
    // Create at least one new Furniture object by called the constructor
    Toy toy = new Toy("teddyBear");

    // Ensure that getX() and getY() returns the initial x-coordinate and y-coordinates (center of
    // the screen)
    if (toy.getX() != Utility.width() / 2) {
      return false;
    }
    if (toy.getY() != Utility.height() / 2) {
      return false;
    }

    // Ensure that the image field is not null
    if (toy.IMAGE == null) {
      return false;
    }

    // Ensure that the new toy is not dragging
    if (toy.isDragging() == true) {
      return false;
    }

    // use setX() and setY() methods so we can effectively test them
    toy.setX(30);
    toy.setY(40);

    // Ensure that setX() and setY() change the value of x and y
    if (toy.getX() != 30) {
      return false;
    }
    toy.setX(30);
    toy.setY(40);

    if (toy.getY() != 40) {
      return false;
    }
    // if all works then return true -- test passed
    return true; // default return statement
  }

  /**
   * This tester ensures the correctness of Toy.startDragging() and Toy.stopDragging instance
   * methods
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyStartStopDragging() {
    // TODO Implement this tester
    // This tester should construct at least one Toy object and call startDragging() and
    // stopDragging() methods on that object.
    Toy toy = new Toy("car");

    // first making sure car is not moving when it is first constructed
    if (toy.isDragging()) {
      return false;
    }
    // call the method -- start dragging the object
    toy.startDragging();

    // make sure it drags when it is supposed to
    if (!toy.isDragging()) {
      return false;
    }

    // now stop dragging the car -- call stopDragging() method
    toy.stopDragging();

    // make sure the toy has stopped dragging
    if (toy.isDragging()) {
      return false;
    }
    // if everything works return true -- test passed
    return true; // default return statement
  }

  /**
   * This tester ensures the correctness of Toy.move() method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyMove() {
    // This tester should construct at least one Toy object at a given x,y position
    Toy toy = new Toy("teddyBear", 50, 80);

    // make sure we start at the initialized position
    if (toy.getX() != 50 || toy.getY() != 80) {
      return false;
    }

    // now we move the toy to get a new position
    toy.move(50, 20);

    // make sure teh position has correctly updated
    if (toy.getX() != 100 || toy.getY() != 100) {
      return false;
    }

    // move it again but with negative values to ensure values can be deducted from x and y then
    // check that the position correctly updated
    toy.move(-20, -30);

    if (toy.getX() != 80 || toy.getY() != 70) {
      return false;
    }

    // now lets put the toy out of bounds and make sure that it resets to zero
    toy.move(-200, -200);
    if (toy.getX() != 0 || toy.getY() != 0) {
      return false;
    }
    // if all works -- test passed
    return true;
  }

  /**
   * This tester ensures the correctness of Toy.rotate() method.
   * 
   * @author Mouna
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   * 
   */
  public static boolean testToyRotate() {
    // This method's implementation is entirely provided to you
    // Create two Toy objects
    Toy car1 = new Toy("car");
    Toy car2 = new Toy("car");

    // Ensures getRotationsCount() returns zero when called on newly constructed Toy objects
    if (car1.getRotationsCount() != 0) {
      System.out.println(
          "Toy.getRotationsCount() should return zero when called on a new created Toy object.");
      return false;
    }

    if (car2.getRotationsCount() != 0) {
      System.out.println(
          "Toy.getRotationsCount() should return zero when called on a new created Toy object.");
      return false;
    }
    // rotate car1 5 times
    for (int i = 0; i < 5; i++) {
      car1.rotate();
    }
    // Ensure the getRotationsCount returns the expected output
    if (car1.getRotationsCount() != 5) {
      System.out.println(
          "Toy.getRotationsCount() did not return the expected output after calling the rotate() "
              + "method multiple times.");
      return false;
    }
    // rotate car2 3 times
    for (int i = 0; i < 3; i++) {
      car2.rotate();
    }
    // Ensure the getRotationsCount returns the expected output
    if (car2.getRotationsCount() != 3) {
      System.out.println(
          "Toy.getRotationsCount() did not return the expected output after calling the rotate() "
              + "method multiple times.");
      return false;
    }
    return true; // Test passes with no errors
  }

  /**
   * This tester checks the correctness of Toy.isOver(int, int) method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverPoint() {
    // This tester should check for the correctness of Toy.isOver(int x, int y) method
    // This tester should construct at least one Toy object at a given (x,y) position
    Toy car2 = new Toy("car", 100, 100);

    // case 1: passing it a point (x,y coords) defined inside the area of the image should be true
    if (!car2.isOver(100, 100)) {
      return false;
    }
    // Case 2: a point outside of image area should return false
    if (car2.isOver(70, 70)) {
      return false;
    }

    // Call the rotate() method one time on the toy so we are able to futher see if the isOver()
    // method works
    car2.rotate();
    car2.rotate();
    car2.rotate();

    // Case 3: point inside the rotated image area should return true
    if (!car2.isOver(130, 100)) {
      return false;
    }

    // Case 4: point outside rotated image area should return false
    if (car2.isOver(130, 150)) {
      return false;
    }
    // if all works then test case passed
    return true;
  }

  /**
   * This tester checks the correctness of Toy.isOver(Furniture) method
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean testToyIsOverFurniture() {
    // This tester should check for the correctness of Toy.isOver(Furniture other) method

    // Create a Furniture object at a given (x, y) position.
    Furniture furniture = new Furniture("nightstand", 100, 150);
    // You can then create at least three Toy objects:
    // (1) one intersecting with the Furniture object,
    Toy toy1 = new Toy("teddyBear", 100, 150);
    // (2) one enclosed by it,
    Toy toy2 = new Toy("car", 110, 160);
    // (3) one not overlapping with the Furniture.
    Toy toy3 = new Toy("car", 20, 20);
    // Calling the isOver(Furniture) on each of the Toy objects should return the expected output.

    // Case 1: overlapping toy1
    boolean isOverlapping = toy1.isOver(furniture);
    if (!isOverlapping) {
      return false;
    }

    // Case 2: is enclosed
    boolean isEnclosed = toy2.isOver(furniture);
    if (!isEnclosed) {
      return false;
    }

    // Case 3: is not overlapping
    boolean isNonOverlapping = !toy3.isOver(furniture);
    if (!isNonOverlapping) {
      return false;
    }

    // if all of it work -- test passed
    return true;
  }

  /**
   * Runs all the tester methods defined in this class
   * 
   * @return true when this test verifies a correct functionality (ALL test scenarios PASS), and
   *         false otherwise
   */
  public static boolean runAllTests() {
    System.out.println("Class Furniture Testers:");
    boolean test1Result = testFurnitureConstructor1Getters();
    System.out.println("testFurnitureConstructor1Getters: " + (test1Result ? "PASS" : "FAIL"));

    boolean test2Result = testFurnitureConstructor2Getters();
    System.out.println("testFurnitureConstructor2Getters: " + (test2Result ? "PASS" : "FAIL"));

    System.out.println();
    System.out.println("Class Toy Testers:");
    boolean test3Result = testToyConstructorsGettersSetters();
    System.out.println("testToyConstructorsGettersSetters: " + (test3Result ? "PASS" : "FAIL"));

    boolean test4Result = testToyStartStopDragging();
    System.out.println("testToyStartStopDragging: " + (test4Result ? "PASS" : "FAIL"));

    boolean testToyMove = testToyMove();
    System.out.println("testToyMove: " + (testToyMove ? "PASS" : "FAIL"));

    boolean testToyRotate = testToyRotate();
    System.out.println("testToyRotate: " + (testToyRotate ? "PASS" : "FAIL"));

    boolean testToyIsOverPoint = testToyIsOverPoint();
    System.out.println("testToyIsOverPoint: " + (testToyIsOverPoint ? "PASS" : "FAIL"));

    boolean testToyIsOverFurniture = testToyIsOverFurniture();
    System.out.println("testToyIsOverFurniture: " + (testToyIsOverFurniture ? "PASS" : "FAIL"));

    return test1Result && test2Result && test3Result && test4Result && testToyMove && testToyRotate
        && testToyIsOverPoint && testToyIsOverFurniture;
  }


  /**
   * Driver method to run all the tests defined in this class
   * 
   * @param args list of command-line input arguments if any.
   */
  public static void main(String[] args) {
    // DO NOT MAKE ANY CHANGES TO THE IMPLEMENTATION OF THIS METHOD
    Utility.runTester();
  }

}
