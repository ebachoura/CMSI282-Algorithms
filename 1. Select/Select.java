import java.util.ArrayList;
import java.io.*;

public class Select {
  public static void main (String[] args) throws Exception {
    int k = Integer.parseInt(args[0]);
    BufferedReader stdIn = new BufferedReader ( new InputStreamReader ( System.in ) );
    ArrayList<Integer> list = new ArrayList<Integer>();
    try {
      String s = stdIn.readLine();
      while (s != null) {
        Integer line = Integer.parseInt(s);
        list.add(line);
        s = stdIn.readLine();
      }
      if (!inputCheck(list, k)) {
        throw new IOException();
      }
    } catch (Exception e) {
      System.out.println("BAD DATA");
      return;
    }
    Select test = new Select(list);
    System.out.println(test.master(k));
  }

  private ArrayList<Integer> list;

  Select (ArrayList<Integer> input) {
    list = input;
  }

  public static boolean inputCheck(ArrayList<Integer> list, int k) {
    if (k <= 0 || k > list.size()) { return false; } return true;
  }

  private int partition (boolean part1, int leftBound, int rightBound, int pivot) {
    if (part1) {
      pivot = leftBound + (int) Math.floor(Math.random() * (rightBound - leftBound + 1));
    }
    boolean rightToLeft = true;
    while (!(pivot == leftBound && pivot == rightBound)) {
      if (rightToLeft && pivot != rightBound) {
        int comparison = (list.get(pivot)).compareTo(list.get(rightBound));
        boolean comparisonA = part1 ? comparison < 0 : comparison <= 0;
        if (comparisonA) {
            rightBound --;
        } else {
            list = swap(list, pivot, rightBound);
            rightToLeft = false;
            pivot = rightBound;
          }
      } else {
        int comparison = (list.get(pivot)).compareTo(list.get(leftBound));
        boolean comparisonA = part1 ? comparison >= 0 : comparison > 0;
        if (comparisonA) {
          leftBound ++;
        } else {
          list = swap(list, pivot, leftBound);
          rightToLeft = true;
          pivot = leftBound;
        }
      }
    }
    return pivot;
  }

  public int master (int a) {
    return selectHelp(0, list.size() - 1, a - 1);
  }

  private Integer selectHelp (int leftBound, int rightBound, int k) {
    int x = partition(true, leftBound, rightBound, -1);
    int y = partition(false, leftBound, rightBound, x);
    if (k <= x && k >= y) { return list.get(x); }
    if (k < y) {
      return selectHelp(leftBound, y - 1, k);
    } else {
      return selectHelp(x + 1, rightBound, k);
    }
  }

  private static ArrayList<Integer> swap (ArrayList<Integer> list, int pivot, int pointer) {
    Integer save = list.get(pivot);
    list.set(pivot, list.get(pointer));
    list.set(pointer, save);
    return list;
  }
}
