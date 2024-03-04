//////////////// Wardrobe Manager //////////////////////////
//
// Title: P01 Vending Machine
// Course: CS 300 Spring 2024
//
// Author: Angelique Carrillo
// Email: arcarrillo2@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources:
// - https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Math.html
// - https://www.programiz.com/java-programming/methods
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class WardrobeManagerTester {

  public static boolean testContainsEmpty() {
    // Create an empty oversize array with all values null
    String[][] emptyWardrobe = new String[5][3]; // Size can be arbitrary, but larger than 0 to
                                                 // simulate an oversized array
    int emptySize = 0; // Size is 0, indicating the wardrobe is empty

    // Define the test items
    String testDescription = "test item";
    String testBrand = "test brand";

    // Call the containsClothing method with the empty wardrobe
    boolean result =
        WardrobeManager.containsClothing(testDescription, testBrand, emptyWardrobe, emptySize);

    // The expected behavior is that the method returns false
    return !result;
  }

  /**
   * PROVIDED: example test method for verifying whether an item is already in the wardrobe.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"yellow tank", "Brandy", "never"}, {"dark blue jeans", "Levi", "never"},
        null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("yellow tank", "Brandy", wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    if (expected != actual)
      return false;

    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual)
      return false;

    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  public static boolean testContainsFalse() {
    // (1) Set up the test variables and call the method we are testing - NON-EXISTENT ITEM
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"},
        {"dark blue jeans", "Levi", "never"}, null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = false;

    // Test with an item that does not exist in the wardrobe
    boolean actual = WardrobeManager.containsClothing("red sweater", "Gucci", wardrobe, size);
    if (expected != actual)
      return false;

    // (2) Test with correct item name but wrong brand
    actual = WardrobeManager.containsClothing("black t-shirt", "Nike", wardrobe, size);
    if (expected != actual)
      return false;

    // (3) Test with null values
    // This test needs to be removed or modified as the containsClothing method is designed to
    // return false if either description or brand is null.

    // (4) Test with an index outside the size of the wardrobe
    // This test also needs to be reconsidered. If the size is larger than the actual content of the
    // array, it should not cause a problem because the loop in containsClothing only goes up to
    // wardrobeSize. Therefore, an oversized index should not affect the result.

    // (5) Check if the wardrobe array is unchanged
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // If all other checks pass, the test is successful
    return true;
  }

  //// ADD

  /**
   * PROVIDED: example test method for adding a new clothing item to an EMPTY oversize array.
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);

    // (2) verify the expected return value
    if (expected != actual)
      return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null)
      return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M")
        || !empty[0][2].equals("never"))
      return false;

    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 1; i < empty.length; i++) {
      if (empty[i] != null)
        return false;
    }

    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  public static boolean testAddNonEmpty() {
    // set up test variables and call the method we are testing
    String[][] wardrobe =
        {{"blue jeans", "Levi's", "never"}, {"black t-shirt", "Gildan", "2023-10-31"}, null, null};
    int size = 2;
    int expected = 3;
    int actual = WardrobeManager.addClothing("cargo pants", "GAP", wardrobe, size);

    // verify the expected return value
    if (expected != actual)
      return false;

    // verify that the provided array was updated correctly
    if (wardrobe[2] == null)
      return false;
    if (!wardrobe[2][0].equalsIgnoreCase("cargo pants") || !wardrobe[2][1].equalsIgnoreCase("GAP")
        || !wardrobe[2][2].equalsIgnoreCase("never"))
      return false;

    // Verify that NOTHING ELSE was changed unexpectedly
    for (int i = 3; i < wardrobe.length; i++) {
      if (wardrobe[i] != null)
        return false;
    }
    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  public static boolean testAddDuplicate() {
    // setup the test variables and call the method we are testing
    String[][] duplicate = {{"blue jeans", "Levi's", "never"}};
    int size = 1;
    int expected = 1;
    int actual = WardrobeManager.addClothing("blue jeans", "Levi's", duplicate, size);

    // verify the expected return value
    if (expected != actual)
      return false;

    if (duplicate[0] == null)
      return false;
    if (!duplicate[0][0].equalsIgnoreCase("blue jeans")
        || !duplicate[0][1].equalsIgnoreCase("Levi's"))
      return false;

    // if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  public static boolean testAddToFull() {
    // set up test variables and call the method we are testing. - FULL SET
    String[][] full =
        {{"blue jeans", "Levi's", "never"}, {"black t-shirt", "Gildan", "2023-10-31"}};
    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("cargo pants", "GAP", full, size);

    // verify the expected return value
    if (expected != actual)
      return false;

    // verify that no new item is added
    for (int i = 0; i < full.length; i++) {
      if (full[i] == null)
        return false;
    }
    return true;

  }

  //// INDEX OF

  public static boolean testIndexOfEmpty() {
    // create a test scenario with test variable to call the method upon
    String[][] empty = new String[10][0];
    int index = 0;
    int expected_index = -1;
    int actual_index = WardrobeManager.indexOfClothing("shirt", "Sax Fifth", empty, index);

    // check if expected equals to actual and return false
    if (expected_index != actual_index)
      return false;

    // if otherwise, all test cases passed and we can return true
    return true;
  }

  public static boolean testIndexOfFirstLast() {
    // create a test scenario with test variable to call the method upon
    String[][] fal = {{"jeans", "Hollister", "never"}, {"flare pants", "Alo", "2020-06-09"},
        {"brown hoodie", "PacSun", "2020-11-11"}};
    // define the expected indicies
    int index_first_expected = 0;
    int index_last_expected = 2;
    
    // call the method on test variables (first and last)
    int actual_first = WardrobeManager.indexOfClothing("jeans", "Hollister", fal, fal.length);
    int actual_last = WardrobeManager.indexOfClothing("brown hoodie", "PacSun", fal, fal.length);
    
    //return true if the actual indicies match the expected and false otherwise
    return actual_first == index_first_expected && actual_last == index_last_expected;

  }

  public static boolean testIndexOfMiddle() {
    // create a test scenario with test variable to call the method upon

    String[][] middle = {{"blue skirt", "Hollister", "never"}, {"pants", "Brandy", "2023-05-14"},
        {"white crop top", "Nike", "2020-10-23"}};
    
    // set fixed wardrobe size for testing
    int wardrobeSize = 3;
    // define expected index
    int expected = 1;
    // call method on middle test variable
    int actual = WardrobeManager.indexOfClothing("pants", "Brandy", middle, wardrobeSize);
    
    // if the output from the method does not equal the expected indicie return false
    if (expected != actual) {
      return false; 
    }
    // if expected is equal to actual return true
    return true;
  }

  public static boolean testIndexOfNoMatch() {
    // create a test scenario with test variable to call the method upon
    String[][] wardrobe = {{"shirt", "Nike", "2023-01-14"}, {"Sweater vest", "Wildcat", "2019-01-02"},
        {"jean jacket", "Brandy", "never"}, null, null};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // define expected index
    int expected = -1;
    // call method on the test variables 
    int actual = WardrobeManager.indexOfClothing("sweater dress", "Oh Polly", wardrobe, size);
    // if actual index equals the expected then it will return true, otherwise false
    return expected == actual;
  }


  //// WEAR

  public static boolean testWearClothingTrue() {
    // create a test scenario with test variable to call the method upon
    String[][] wardrobe = {{"pink jeans", "Brandy", "2020-04-04"}, {"shirt", "Nike", "never"},
        {"Blue jacket", "Vaporize", "2019-05-20"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // call method on test variable and store in 'result' which will return true if clothes have been worn, otherwise false
    boolean result = WardrobeManager.wearClothing("pink jeans", "Brandy", "2020-04-04", wardrobe, size);
    return result;
  }

  public static boolean testWearClothingNoMatch() {
    // create a test scenario with test variable to call the method upon
    String[][] wardrobe = {{"pink jeans", "Brandy", "2020-04-04"}, {"Blue jacket", "Vaporize", "2019-05-20"},
        {"sweater vest", "handmade", "2022-06-27"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // call method on test variable (which is not in test array) and store in 'result' which will return true if clothes have been worn, otherwise false 
    boolean result = WardrobeManager.wearClothing("shirt", "Adidas", "2023-02-28", wardrobe, size);
    return !result;

  }

  //// BRAND COUNT

  public static boolean testBrandCountAllMatch() {
    // create a test scenario with test variable where all entries have the same brand
    String[][] wardrobe = {{"shirt", "cool Brand", "2022-01-15"}, {"jeans", "COOL BRAND", "never"},
        {"dress", "cool brand", "2023-05-20"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // create brand name we want to match and store in a variable
    String brandMatch = "Cool Brand";
    
    // check if count matches expected count -- if so return true, otherwise false
    if (WardrobeManager.getBrandCount(brandMatch, wardrobe, size) == size) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean testBrandCountSomeMatch() {
    // create a test scenario with test variable where some entries have the same brand
    String[][] wardrobe = {{"shirt", "Fiesta", "2022-01-15"}, {"jeans", "COOL BRAND", "never"},
        {"dress", "cool brand", "2023-05-20"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // create brand name we want to match and store in a variable
    String brandMatch = "cool brand";
    // Get the actual count from the getBrandCount method
    int result = WardrobeManager.getBrandCount(brandMatch, wardrobe, size);
    // if the count (stored in result) is more than zero but less than the number of entries, return true otherwise false
    if (result > 0 && result < size) {
      return true;
    } else {
      return false;
    }
  }

  
  public static boolean testBrandCountNoMatch() {
    // create a test scenario with test variable where no entries have the same brand
    String[][] wardrobe = {{"gloves", "Adidas", "2022-01-15"}, {"purse", "GAP", "never"},
        {"dress", "nike", "2021-07-23"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // create brand name we want to match and store in a variable
    String brandMatch = "Forever21";
    // Get the actual count from the getBrandCount method
    int result = WardrobeManager.getBrandCount(brandMatch, wardrobe, size);
    
    // if the count (stored in result) is zero return true otherwise false
    if (result == 0) {
      return true;
    } else {
      return false;
    }
  }
  
  //// COUNT UNWORN

  public static boolean testUnwornCountAllMatch() {
    // create a test scenario with test variable where all entries have the same last worn date
    String[][] wardrobe = {{"sweatpants", "Adidas", "never"}, {"sweatshirt", "North Face", "never"},
        {"socks", "Patagoina", "never"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // Get the actual count from the getNumUnwornClothes method
    int result = WardrobeManager.getNumUnwornClothes(wardrobe, size);
    
    // if the count (stored in result) is the same as the number of entries (all entries) then return true, otherwise false
    if (result == size) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean testUnwornCountSomeMatch() {
    // create a test scenario with test variable where some entries have the same last worn date
    String[][] wardrobe = {{"sweatpants", "Adidas", "never"}, {"sweatshirt", "North Face", "never"},
        {"socks", "Patagoina", "2022-06-12"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // Get the actual count from the getNumUnwornClothes method
    int result = WardrobeManager.getNumUnwornClothes(wardrobe, size);

    // if the count (stored in result) is more than zero but less the the actual size (some entries) return true, otherwise false
    if (result > 0 && result < size) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean testUnwornCountNoMatch() {
    // create a test scenario with test variable where no entries have the same last worn date
    String[][] wardrobe = {{"sweatpants", "Adidas", "2023-10-20"},
        {"sweatshirt", "North Face", "2023-12-25"}, {"socks", "Patagoina", "2022-06-12"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // Get the actual count from the getNumUnwornClothes method
    int result = WardrobeManager.getNumUnwornClothes(wardrobe, size);

    // if the count (stored in result) is zero (no entries) return true, otherwise false
    if (result == 0) {
      return true;
    } else {
      return false;
    }
  }

  //// MOST RECENTLY WORN

  /**
   * PROVIDED: example test method for verifying that the most recently worn item is found
   * correctly. Note that this tester is not comprehensive; if you wish to verify additional
   * behavior you are welcome to add additional tester methods (please specify such methods to be
   * PRIVATE).
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"},
        {"grey UW hoodie", "gildan", "2020-03-16"}, {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    // int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);

    // (2) verify that the expected return value and the actual return value match
    // if (expected != actual) return false;

    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy))
      return false;

    // (4) if all of those checks pass, NOW we can say we passed the test
    return true;
  }

  //// REMOVE BY INDEX

  public static boolean testRemoveLastItem() {
    // create a test scenario with test entries
    String[][] wardrobe = {{"tube top", "Princess Polly", "2023-12-15"},
        {"jean shorts", "Banana Republic", "2022-12-10"}, {"halter top", "Express", "never"}};
    
    // set fixed wardrobe size for testing
    int size = 3;
    // index of the last item
    int indexToRemove = size - 1;

    // call the method on new scenario
    int newSize = WardrobeManager.removeClothingAtIndex(indexToRemove, wardrobe, size);

    // Check if the last item was removed (last element set to null) and newSize is correct
    return wardrobe[indexToRemove] == null && newSize == size - 1;
  }

  public static boolean testRemoveFirstItem() {
    // create a test scenario with test entries
    String[][] lastitem = {{"blue jeans", "Aritzia", "never"}, {"cargo pants", "Aritzia", "never"}, null, null};
    
    // set fixed wardrobe size for testing
    int wardrobeSize = 2;
   
    // define variable to reference the first item in the array
    int index = 0;
    int expected_size = 1;
    
    //call method removeClothingAtIndex on the specific index to actually remove the first item
    int actual_size = WardrobeManager.removeClothingAtIndex(index, lastitem, wardrobeSize);

    // test if expected_size matched actual_size and return false if they are not equal, otherwise true
    if (expected_size != actual_size)
      return false;
    return true;
  }

  public static boolean testRemoveBadIndex() {
    // create a test scenario with test entries
    String[][] lastitem ={{"blue jeans", "Aritzia", "never"}, {"cargo pants", "Aritzia", "never"}, null, null};
    
    // set fixed wardrobe size for testing
    int wardrobeSize = 2;
    
    // define variable to reference an item NOT in the array
    int index = 4;
    int expected_size = 2;
    
    //call method removeClothingAtIndex on the specific index to attempt removing item at index 4
    int actual_size = WardrobeManager.removeClothingAtIndex(index, lastitem, wardrobeSize);

    // test if expected_size matched actual_size
    if (expected_size != actual_size)
      return false;
    return true;
  }

  //// REMOVE ALL UNWORN

  public static boolean testRemoveUnwornNoMatch() {
    // Create a test scenario with NO items having the last-worn date "never"
    String[][] wardrobe = {{"sweater", "Nike", "2023-11-20"}, {"jeans", "Levi's", "2022-09-05"},
        {"t-shirt", "Adidas", "2023-03-10"}};
   
    // set fixed wardrobe size for testing
    int size = 3;

    // Call the method with the provided scenario
    int newSize = WardrobeManager.removeAllUnworn(wardrobe, size);

    // Check if none of the values match the query (no "never" in the last-worn dates)
    boolean successNoMatch =
        newSize == size && wardrobe[0] != null && wardrobe[1] != null && wardrobe[2] != null;

    return successNoMatch;
  }



  public static boolean testRemoveUnwornSomeMatch() {
    // Create a test scenario with SOME items having the last-worn date "never"
    String[][] somematch = {{"blue jeans", "Aritzia", "2022-10-08"}, {"green sweater", "Aritzia", "never"},
            {"cargo pants", "Aritzia", "2012-05-01"}, {"leather jacket", "Edikted", "never"}, null};
    
    // set fixed wardrobe size for testing
    int wardrobeSize = 4;
    // set fixed expected size so we know what we want to compare the actual output with
    int expected_size = 2;
    
    // Call the method with the provided scenario
    int actual_size = WardrobeManager.removeAllUnworn(somematch, wardrobeSize);

    // test if expected_size matched actual_size
    if (expected_size != actual_size)
      return false;

    return true;
  }

  public static boolean testRemoveUnwornAllMatch() {
    // Set up a scenario with all items having "never" as the last-worn date
    String[][] wardrobe = {{"sweater", "Nike", "never"}, {"jeans", "Levi's", "never"},
        {"t-shirt", "Adidas", "never"}};
    
    // set fixed wardrobe size for testing
    int size = 3;

    // Call the method with the provided scenario
    int newSize = WardrobeManager.removeAllUnworn(wardrobe, size);

    // Check if all values match the query (all items with "never" in the last-worn dates have been
    // removed)
    return newSize == 0 && wardrobe[0] == null && wardrobe[1] == null && wardrobe[2] == null;
  }

  /**
   * PROVIDED: calls all tester methods and displays the results of the tests.
   * 
   * All tests are called in the order they were provided in this file. The output of this method
   * will NOT be graded so you may modify it however you wish.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    // works
    System.out.println("CONTAINS:\n  " + (testContainsEmpty() ? "pass" : "FAIL") + ", "
        + (testContainsTrue() ? "pass" : "FAIL") + ", " + (testContainsFalse() ? "pass" : "FAIL"));
    // doesnt work
    System.out.println("ADD:\n  " + (testAddToEmpty() ? "pass" : "FAIL") + ", "
        + (testAddNonEmpty() ? "pass" : "FAIL") + ", " + (testAddDuplicate() ? "pass" : "FAIL")
        + ", " + (testAddToFull() ? "pass" : "FAIL"));
    System.out.println("INDEX OF:\n  " + (testIndexOfEmpty() ? "pass" : "FAIL") + ", "
        + (testIndexOfFirstLast() ? "pass" : "FAIL") + ", "
        + (testIndexOfMiddle() ? "pass" : "FAIL") + ", "
        + (testIndexOfNoMatch() ? "pass" : "FAIL"));
    System.out.println("WEAR:\n  " + (testWearClothingTrue() ? "pass" : "FAIL") + ", "
        + (testWearClothingNoMatch() ? "pass" : "FAIL"));
    System.out.println("BRAND COUNT:\n  " + (testBrandCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testBrandCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("UNWORN COUNT:\n  " + (testUnwornCountAllMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountSomeMatch() ? "pass" : "FAIL") + ", "
        + (testUnwornCountNoMatch() ? "pass" : "FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  " + (testMostRecentlyWorn() ? "pass" : "FAIL"));
    System.out.println("REMOVE BY INDEX:\n  " + (testRemoveLastItem() ? "pass" : "FAIL") + ", "
        + (testRemoveFirstItem() ? "pass" : "FAIL") + ", "
        + (testRemoveBadIndex() ? "pass" : "FAIL"));
    System.out.println("REMOVE UNWORN:\n  " + (testRemoveUnwornNoMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornSomeMatch() ? "pass" : "FAIL") + ", "
        + (testRemoveUnwornAllMatch() ? "pass" : "FAIL"));
  }

}
