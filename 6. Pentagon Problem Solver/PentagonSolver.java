import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.IntStream;

class PentagonSolver {
  public static void main (String[] args) {
    int[] set = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    if (args.length == 0) {
      set = new int[]{0,1,2,3,4,5,6,7,8,9};
    } else if (args.length != 10) {
      System.out.println("If you do not input EXACTLY ten intergers, this program can not run it's algorithms, try again.");
      return;
    } else {
      for (int i = 0; i < args.length; i++) {
        int currentArg = Integer.parseInt(args[i]);
        if (IntStream.of(set).anyMatch(x -> x == currentArg)) {
          System.out.println("You have two or more values of equal value, please try again but with no repeated values.");
          return;
        }
        set[i] = currentArg;
      }
    }
    insertSort(set, 0);
    traverseThroughPermutations(set);
  }

  public static void traverseThroughPermutations(int[] currentCombination) {
    int lastIndex = currentCombination.length - 1;
    int[] reversedSet = reverseSet(currentCombination);
    if (satisfiesPentagonProblemCondition(currentCombination)) { System.out.println(Arrays.toString(currentCombination)); }

    while (!Arrays.equals(currentCombination, reversedSet)) {
      if (currentCombination[lastIndex] > currentCombination[lastIndex - 1]) {
        swap(currentCombination, lastIndex, lastIndex - 1);
      } else {
        int count = lastIndex;
        while (currentCombination[count] < currentCombination[count - 1]) { count--; } count--;
        swapAndOrder(currentCombination, count);
      }
      if (satisfiesPentagonProblemCondition(currentCombination)) { System.out.println(Arrays.toString(currentCombination)); }
    }
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
