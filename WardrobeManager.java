//////////////// Wardrobe Manager //////////////////////////
//
// Title: P01 Wardrobe Manager
// Course: CS 300 Spring 2024
//
// Author: Angelique Carrillo
// Email: arcarrillo2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: None
// Online Sources:
// - https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Math.html
// - https://www.programiz.com/java-programming/methods
///////////////////////////////////////////////////////////////////////////////


/**
 * The wardrobe will be represented by an oversize two-dimensional array defined by an array
 * String[][] and a variable of type int keeping track of the number of items available in the
 * wardrobe. The VendingMachine defines eight static methods that can alter and describe contents.
 */
  
// First initialize the correct variables
public class WardrobeManager {
  String description = "";
  String brand = "handmade";
  int wardrobeSize = 0;
  String[][] wardrobe = new String[wardrobeSize + 1][3];



  /**
   * Checks whether the array, wardrobe, contains an entry with the provided description and brand.
   *
   * @param description  the description of the clothing item to check
   * @param brand        the brand of the clothing item to check
   * @param wardrobe     the oversize array containing clothing items
   * @param wardrobeSize the size of the wardrobe array
   * @return true if the clothing item with the exact description and brand is found, false if it
   *         does not match
   */
  public static boolean containsClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {
    // if there are no items in the parameters, there is no item and therefore return false

    if (description == null || brand == null) {
      return false;
    }
    // search through the wardrobe array and check if the item we're currently looking at matches
    // the brand and description were looking for
    // ignoring case sensitivity to ensure that any capitols do not interfere with the marhc

    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i][0].equalsIgnoreCase(description) && wardrobe[i][1].equalsIgnoreCase(brand)) {
        return true;
        // if we have a full match return true, otherwise return false
      }
    }
    return false;
  }


  /**
   * Appends a new clothing item to the given wardrobe oversize array and returns the new size of
   * the oversize array.
   *
   * @param description  the description of the new clothing item
   * @param brand        the brand of the new clothing item
   * @param wardrobe     the oversize array containing existing clothing items
   * @param wardrobeSize the current size of the wardrobe array
   * @return the new size of the wardrobe array after adding the new clothing item
   */
  public static int addClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {
    // checking if the size of the item we check is any larger than the wardrobe length OR
    // calling containsClothing method on the item were checking to see if it is already in the
    // wardrobe array
    if (wardrobeSize >= wardrobe.length
        || containsClothing(description, brand, wardrobe, wardrobeSize)) {
      // returning the original wardrobe size since no items have been added
      return wardrobeSize;
    }
    // if the first two conditions do not match the item were checking then we may add it as a new
    // item and append it to the end of the array and declaring that it has never been worn
    wardrobe[wardrobeSize] = new String[] {description, brand, "never"};
    // increasing the wardrobe size by one to show that a clothing item has been added
    wardrobeSize++;
    // returning new wardrobe size
    return wardrobeSize;
  }

  /**
   * Finds the index of a clothing item in the oversize array, wardrobe. If the item is NOT present
   * in the array, returns -1.
   *
   * @param description  the description of the clothing item
   * @param brand        the brand of the clothing item
   * @param wardrobe     the oversize array storing the existing clothing item entries
   * @param wardrobeSize number of items currently in the wardrobe array
   * @return the new size of the wardrobe array after adding the new clothing item
   */

  public static int indexOfClothing(String description, String brand, String[][] wardrobe,
      int wardrobeSize) {
    for (int i = 0; i < wardrobeSize; i++) {
      // searching through the array and ensuring the item is not null
      if (wardrobe[i] != null) {
        // ensuring that the item we are currently checking matches the clothing item we are looking
        // for
        if (wardrobe[i][0].equalsIgnoreCase(description)
            && wardrobe[i][1].equalsIgnoreCase(brand)) {
          // if the item we're checking matches what we're looking for then we return it
          return i;
        }
      }
    }
    // otherwise return -1
    return -1;
  }

  /**
   * Locates the clothing item matching the provided description/brand in the oversize array and
   * size, and updates the last-worn date to the provided date value. If the wardrobe does not
   * contain a clothing item matching the description and brand then the method returns false.
   *
   * @param description  the description of the clothing item
   * @param brand        the brand of a clothing item, or "handmade"
   * @param date         the date on which this clothing item was worn, as "YYYY-MM-DD"
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return true if the last worn date was updated successfully, returns false otherwise
   */

  public static boolean wearClothing(String description, String brand, String date,
      String[][] wardrobe, int wardrobeSize) {
    for (int i = 0; i < wardrobeSize; i++) {
      // Ensuring the item we are checking is not null
      if (wardrobe[i] != null) {
        // making sure the clothing item matches the description and matches the brand/is handmade
        if (wardrobe[i][0].equalsIgnoreCase(description)
            && (wardrobe[i][1].equalsIgnoreCase(brand) || brand.equalsIgnoreCase("handmade"))) {
          // Set the last-worn date of the matched clothing item to the new date
          wardrobe[i][2] = date;
          // If successful return true
          return true;
        }
      }
    }
    // otherwise return false
    return false;
  }

  /**
   * Counts the number of clothing items in the oversize array with a brand that matches the
   * provided brand.
   *
   * @param brand        the brand of a clothing item, or "handmade"
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the number of items in wardrobe which have a brand matching the provided string
   */

  public static int getBrandCount(String brand, String[][] wardrobe, int wardrobeSize) {
    // Initialize a counter to keep track of the number of items with the specified brand
    int brandCounter = 0;

    for (int i = 0; i < wardrobeSize; i++) {
      // Check if the current item is not null
      if (wardrobe[i] != null) {
        // Check if the brand of the current item matches the provided brand (case sensitive)
        if (wardrobe[i][1].equalsIgnoreCase(brand)) {
          // Add one to the counter to account for the item that matches the brand
          brandCounter++;
        }
      }
    }
    // return the counter after it has searched through the entire array and counted all items
    // matching the brand
    return brandCounter;
  }

  /**
   * Counts the number of clothing items in the oversize array defined by the provided
   * two-dimensional array of strings and size which have a last-worn date of "never".
   *
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the number of clothing items in wardrobe which have a last-worn date of "never"
   */

  public static int getNumUnwornClothes(String[][] wardrobe, int wardrobeSize) {
    // initialize a counter which will track the number of clothing items that have never been worn
    int unwornCount = 0;
    // Searching through the array for clothing items which have never been worn (have a date of
    // 'never') and add them to the counter
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i][2].equalsIgnoreCase("never")) {
        unwornCount++;
      }
    }
    // return the counter after it has added all items with last worn date "never"
    return unwornCount;

  }

  /**
   * Finds the most recently worn item of clothing in the oversize array defined by the provided
   * two-dimensional array of strings and size. The most recently worn item of clothing will be the
   * one with the largest date value. (as date values increase over time); "never" is considered the
   * smallest possible date, so if all items have a last-worn date of "never", the method should
   * return 0.
   *
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the smallest index of a clothing item in the wardrobe with the most recent last-worn
   *         date, or -1 if the wardrobe is empty
   */

  public static int getMostRecentlyWorn(String[][] wardrobe, int wardrobeSize) {
    // if the wardrobe is empty we immediately return -1
    if (wardrobeSize == 0) {
      return -1;
    }
    // Initialize the index of the most recently worn item to the first item in the wardrobe
    int recentIndex = 0;
    // Define a special date string for "never"
    String dateNever = "never";
    // iterate through wardrobe getting last worn date of each item.
    for (int i = 1; i < wardrobeSize; i++) {
      String currDate = wardrobe[i][2];
      // Check if the current item has a date other than "never" and has a more recent date than the
      // current most recently worn item
      if (!dateNever.equals(currDate) && (dateNever.equals(wardrobe[recentIndex][2])
          || currDate.compareTo(wardrobe[recentIndex][2]) > 0)) {
        // Update the index of the most recently worn item to the current index
        recentIndex = i;
      }
    }
    // Once the most recently worn item is found, return its index
    return recentIndex;
  }

  /**
   * Removes the single clothing item entry at the provided index of the oversize array defined by
   * the provided two-dimensional array of strings and size, updates the array to uphold the
   * oversize array requirements, and returns the new size of the array. If the index is outside of
   * the bounds of the provided oversize array, the array must NOT be modified and the previous size
   * returned.
   * 
   * @param index        the index of the clothing item to remove from the wardrobe
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the size of the oversize array after attempting to remove the item at the provided
   *         index
   */

  public static int removeClothingAtIndex(int index, String[][] wardrobe, int wardrobeSize) {
    // Check if the provided index is outside the bounds of the current wardrobe size, if it is then
    // return the unmodified wardrobeSize
    if (index < 0 || index >= wardrobeSize) {
      return wardrobeSize;
    }

    // Shift elements to fill the gap left by removing the item at the specified index
    for (int i = index; i < wardrobeSize - 1; i++) {
      // move each item one position back in the array
      wardrobe[i] = wardrobe[i + 1];
    }

    // Set the last element to null to remove the reference to the previous last item
    wardrobe[wardrobeSize - 1] = null;
    return wardrobeSize - 1;
  }

  /**
   * Removes any clothing item from the oversize array defined by the provided two-dimensional array
   * of strings and size where the item's last-worn date is "never", and returns the updated size of
   * the oversize array.
   * 
   * @param wardrobe     a two-dimensional array of Strings storing wardrobe entries
   * @param wardrobeSize number of items currently stored in the wardrobe
   * @return the size of the oversize array after attempting to remove the item at the provided
   *         index
   */


  public static int removeAllUnworn(String[][] wardrobe, int wardrobeSize) {
    // Initialize a variable to keep track of the new size of the wardrobe after removal of clothing
    // items
    int newSize = 0;

    // Iterate through wardrobe and ensure that the entries are not empty, has the correct amount of
    // elements, and was worn at least once.
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i] != null && wardrobe[i].length >= 3
          && !wardrobe[i][2].equalsIgnoreCase("never")) {
        wardrobe[newSize] = wardrobe[i];
        // if all conditions met increment newSize to represent the addition of the new item
        newSize++;
      }
    }
    // Set all other elements in the array which have not met the proper conditions to null so we
    // are only left with clothes that have been worn
    for (int i = newSize; i < wardrobeSize; i++) {
      wardrobe[i] = null;
    }
    return newSize;
  }
}

