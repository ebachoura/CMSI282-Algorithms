import java.util.ArrayList;
import java.io.*;
/**
 *BucketSort algorithm as described by Professor Dorin. Written by Edward Bachoura.
 *Takes in a list of doubles through standard in and then returns them in ascending
 *order. Note: I wrote this class with all static methods and therefore there are no
 *fields that are native to the BucketSort class and also no constructor.
 *@author Edward Bachoura
 */


public class BucketSort {
  public static void main (String[] args) {
    //Reading from file and inserting all Doubles into an ArrayList<Double>
    //While reading, also keeping track of largest and smallest values
    BufferedReader stdIn = new BufferedReader ( new InputStreamReader ( System.in ) );
    ArrayList<Double> listOfDoubles = new ArrayList<Double>();
    Double largestValue = null;
    Double smallestValue = null;
    try {
      String line = stdIn.readLine();
      while (line != null) {
        double currentLineValue = Double.parseDouble(line);
        if (largestValue == null || smallestValue == null) {
          largestValue = currentLineValue;
          smallestValue = currentLineValue;
        } else {
          if (currentLineValue > largestValue) { largestValue = currentLineValue; }
          if (currentLineValue < smallestValue) { smallestValue = currentLineValue; }
        }
        listOfDoubles.add(currentLineValue);
        line = stdIn.readLine();
      }
    } catch (Exception e) {
      System.err.println(e);
      return;
    }

    //Calling the sort function
    if (listOfDoubles.size() < 10) {
      System.out.println(insertSort(listOfDoubles));
    } else {
      System.out.println(bucketSort(listOfDoubles, largestValue, smallestValue));
    }
  }

  /**
   *Uses a bucketSorting algorithm to sort any given list of doubles in ascending order. Uses multiple private helper methods and help in instantiating the buckets, calculating the bucket index, and sorting the actual buckets.
   *@param listOfDoubles A list of doubles in an ArrayList, as recieved from System.in.
   *@param largestValue The largest value that is contained within the listOfDoubles.
   *@param smallestValue The smallest value that is contained within the listOfDoubles.
   *@return Returns the original listOfDoubles, but sorted in ascending order.
  */

  public static ArrayList<Double> bucketSort (ArrayList<Double> listOfDoubles, double largestValue, double smallestValue) {
    int listOfDoublesLength = listOfDoubles.size();
    ArrayList<ArrayList<Double>> bucketList = instantiateBuckets(listOfDoublesLength);
    for (int i = 0; i < listOfDoublesLength; i++) {
      double currentDouble = listOfDoubles.get(i);
      int currentBucketIndex = calculateBucketIndex(currentDouble, largestValue, smallestValue, listOfDoublesLength - 1);
      bucketList.get(currentBucketIndex).add(currentDouble);
    }
    bucketList = sortBucketList(bucketList);
    int count = 0;
    for (int i = 0; i < bucketList.size(); i++) {
      if (bucketList.get(i) != null) {
        for (int j = 0; j < bucketList.get(i).size(); j++) {
          listOfDoubles.set(count, bucketList.get(i).get(j));
          count++;
        }
      }
    }
    return listOfDoubles;
  }

  private static ArrayList<ArrayList<Double>> instantiateBuckets (int length) {
    ArrayList<ArrayList<Double>> bucketList = new ArrayList<ArrayList<Double>>(length);
    for (int i = 0; i < length; i++) {
      bucketList.add(new ArrayList<Double>());
    }
    return bucketList;
  }

  private static int calculateBucketIndex (double doubleInQuestion, double largestValue, double smallestValue, double listOfDoublesLength) {
    int index = (int) Math.round(((doubleInQuestion - smallestValue) / (largestValue - smallestValue)) * listOfDoublesLength);
    return index;
  }

  private static ArrayList<ArrayList<Double>> sortBucketList (ArrayList<ArrayList<Double>> bucketList) {
    for (int i = 0; i < bucketList.size(); i++) {
      if (bucketList.get(i) != null && bucketList.get(i).size() > 1) {
        bucketList.set(i, insertSort(bucketList.get(i)));
      }
    }
    return bucketList;
  }

  private static ArrayList<Double> insertSort (ArrayList<Double> listToSort) {
    double temp;
       for (int i = 0; i < listToSort.size(); i++) {
           for(int j = i ; j > 0 ; j--){
               if(listToSort.get(j) < listToSort.get(j-1)){
                   temp = listToSort.get(j);
                   listToSort.set(j, listToSort.get(j - 1));
                   listToSort.set(j - 1, temp);
               }
           }
       }
       return listToSort;
  }
}
