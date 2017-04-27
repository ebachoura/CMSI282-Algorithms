import java.util.Arrays;
import java.util.ArrayList;

class PentagonSolver {
  public static void main (String[] args) {
    int[] set = new int[]{0,1,2,3,4,5,6,7,8,9};
    ArrayList<int[]> solutionSet = new ArrayList<int[]>();
    traverseThroughPermutations(set, solutionSet);
    // System.out.println(print(solutionSet));
  }

  public static void traverseThroughPermutations(int[] currentCombination, ArrayList<int[]> solutionSet) {
    if (Arrays.equals(currentCombination, new int[]{9,8,7,6,5,4,3,2,1,0})) { return; }

    int lastIndex = currentCombination.length - 1;
    if (currentCombination[lastIndex] > currentCombination[lastIndex - 1]) {
      currentCombination = swap(currentCombination, lastIndex, lastIndex - 1);
    } else {
      int count = lastIndex;
      while (currentCombination[count] < currentCombination[count - 1]) { count--; } count--;
      currentCombination = swapAndOrder(currentCombination, count);
    }
    System.out.println(Arrays.toString(currentCombination));
    // if (satisfies(currentCombination)) { solutionSet.add(currentCombination); }
    traverseThroughPermutations(currentCombination, solutionSet);
  }

  private static int[] swap(int[] currentArray, int x, int y) {
    int tempHold = currentArray[x];
    currentArray[x] = currentArray[y];
    currentArray[y] = tempHold;
    return currentArray;
  }

  private static int[] swapAndOrder(int[] currentArray, int x) {
    int nextSmallestValueIndex = x + 1;
    for (int i = x + 1; i < currentArray.length; i++) {
      if (currentArray[nextSmallestValueIndex] > currentArray[i] && currentArray[x] < currentArray[i]) { nextSmallestValueIndex = i; }
    }
    currentArray = swap(currentArray, x, nextSmallestValueIndex);
    currentArray = insertSort(currentArray, x + 1);
    return currentArray;
  }

  private static int[] insertSort (int[] listToSort, int left) {
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
    return listToSort;
  }

  private static boolean satisfies (int[] arrayToTest) {
    int sum1 = arrayToTest[0] + arrayToTest[1] + arrayToTest[2];
    int sum2 = arrayToTest[2] + arrayToTest[3] + arrayToTest[4];
    int sum3 = arrayToTest[4] + arrayToTest[5] + arrayToTest[6];
    int sum4 = arrayToTest[6] + arrayToTest[7] + arrayToTest[8];
    int sum5 = arrayToTest[8] + arrayToTest[9] + arrayToTest[0];
    if (sum1 == sum2 && sum1 == sum3 && sum1 == sum4 && sum1 == sum5) {
      return true;
    }
    return false;
  }

  private static String print(ArrayList<int[]> solutionSet) {
    String print = "";
    for (int i = 0; i < solutionSet.size(); i++) {
      print = print + java.util.Arrays.toString(solutionSet.get(i));
      if (i != solutionSet.size() - 1) { print = print + "\n"; }
    }
    return print;
  }
}
