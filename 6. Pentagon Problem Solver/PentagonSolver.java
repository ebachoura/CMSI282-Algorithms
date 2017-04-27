import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * This is a program that solves for all of the solutions to the Pentagon Problem that Philip Dorin
 * posed in class on 4/25/2017. This is a program with only static methods, with the one main method,
 * simply a solve method, solves for all possible solutions with the given data set of length 10. If
 * you leave the input blank, the problem would solve on the data set {0,1,2,3,4,5,6,7,8,9}. Otherwise,
 * if you input exactly 10 distinct integers, it would solve for all solutions with that data set.
 * @author Edward Bachoura
 */

class PentagonSolver {
  public static void main (String[] args) {
    // Main method that has the default case if the user doesn't type anything following java PentagonSolve
    // and the other options is if the user inputs exactly 10 unique arguments

    int[] set;
    if (args.length == 0) {
      set = new int[]{0,1,2,3,4,5,6,7,8,9};
    } else if (args.length != 10) {
      System.out.println("If you do not input EXACTLY ten integers, this program can not run it's algorithms, try again."); return;
    } else {
      set = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
      for (int i = 0; i < args.length; i++) {
        int currentArg = Integer.parseInt(args[i]);
        if (IntStream.of(set).anyMatch(x -> x == currentArg)) {
          System.out.println("You have two or more values of equal value, please try again but with no repeated values."); return;
        }
        set[i] = currentArg;
      }
    }
    traverseThroughPermutations(set);
  }

  /**
   * Takes in an array of int's and prints out all of the solutions to the
   * Pentagon Problem with the given data set as it finds them.
   * @param currentCombination Should be an Array of unique ints of length 10.
   */

  public static void traverseThroughPermutations(int[] currentCombination) {
    // Start by sorting the 10-digit data set
    insertSort(currentCombination, 0);
    int counter = 0;
    int lastIndex = currentCombination.length - 1;
    int[] reversedSet = reverseSet(currentCombination);
    if (satisfiesPentagonProblemCondition(currentCombination)) { counter++; System.out.println(Arrays.toString(currentCombination) + "            side-sum = " + sideSum(currentCombination)); }

    // Iteration: keep going until we have hit the completely reversed set
    while (!Arrays.equals(currentCombination, reversedSet)) {
      if (currentCombination[lastIndex] > currentCombination[lastIndex - 1]) {
        // Swap is the helper function that switches the two given indices of a given
        swap(currentCombination, lastIndex, lastIndex - 1);
      } else {
        int count = lastIndex;
        while (currentCombination[count] < currentCombination[count - 1]) { count--; } count--;
        // SwapAndOrder is the helper function that switches the given index with the smallest
        // integer greater than itself to it's right, and then sorts all data greater than the
        // given index using insert sort.
        swapAndOrder(currentCombination, count);
      }
      // After each step of rearranging the data, this line checks to see if the current combination is a valid solution,
      // and then prints it if it.
      if (satisfiesPentagonProblemCondition(currentCombination)) { counter++; System.out.println(Arrays.toString(currentCombination) + "            side-sum = " + sideSum(currentCombination)); }
    }
    System.out.println("\ntotal number of solutions = " + counter);
  }

  private static void swap(int[] currentArray, int x, int y) {
    int tempHold = currentArray[x];
    currentArray[x] = currentArray[y];
    currentArray[y] = tempHold;
  }

  private static void swapAndOrder(int[] currentArray, int x) {
    int nextSmallestValueIndex = x + 1;
    for (int i = x + 1; i < currentArray.length; i++) {
      if (currentArray[nextSmallestValueIndex] > currentArray[i] && currentArray[x] < currentArray[i]) { nextSmallestValueIndex = i; }
    }
    swap(currentArray, x, nextSmallestValueIndex);
    insertSort(currentArray, x + 1);
  }

  private static void insertSort (int[] listToSort, int left) {
    int temp;
    for (int i = left; i < listToSort.length; i++) {
      for(int j = i ; j > left ; j--){
        if(listToSort[j] < listToSort[j-1]) {
          temp = listToSort[j];
          listToSort[j] = listToSort[j - 1];
          listToSort[j - 1] = temp;
        }
      }
    }
  }

  private static boolean satisfiesPentagonProblemCondition (int[] arrayToTest) {
    return arrayToTest[0] + arrayToTest[1] + arrayToTest[2] == arrayToTest[2] + arrayToTest[3] + arrayToTest[4] && arrayToTest[0] + arrayToTest[1] + arrayToTest[2] == arrayToTest[4] + arrayToTest[5] + arrayToTest[6] && arrayToTest[0] + arrayToTest[1] + arrayToTest[2] == arrayToTest[6] + arrayToTest[7] + arrayToTest[8] && arrayToTest[0] + arrayToTest[1] + arrayToTest[2] == arrayToTest[8] + arrayToTest[9] + arrayToTest[0];
  }

  private static int sideSum (int[] arrayToCount) {
    return arrayToCount[0] + arrayToCount[1] + arrayToCount[2];
  }

  private static int[] reverseSet (int[] listToSort) {
    int[] listToReverse = listToSort.clone();
    int temp;
    for (int i = 0; i < listToReverse.length; i++) {
      for(int j = i ; j > 0 ; j--){
        if(listToReverse[j] > listToReverse[j-1]) {
          temp = listToReverse[j];
          listToReverse[j] = listToReverse[j - 1];
          listToReverse[j - 1] = temp;
        }
      }
    }
    return listToReverse;
  }
}
