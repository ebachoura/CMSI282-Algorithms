public class Tuple {
  public static final Tuple IMPOSSIBLE = new Tuple(1);

  private int[] data;
  public Tuple(int n) {
      if (n < 0) {
          throw new IllegalArgumentException();
      }
      data = new int[n];
      for (int i = 0; i < n; i++) {
          data[i] = 0;
      }
  }
  public Tuple(int n, int m, int o) {
      data = new int[3];
      data[0] = n;
      data[1] = m;
      data[2] = o;
  }

  public boolean isImpossible() {
      return this == IMPOSSIBLE;
  }

  public void setElement(int i, int j) {
      data[i] = j;
  }

  public int getElement(int i) {
      return data[i];
  }

  public int length() {
      return data.length;
  }

  public Tuple add(Tuple t) {
      Tuple sum = new Tuple(length());
      for (int i = 0; i < length(); i++) {
          sum.setElement(i, getElement(i) + t.getElement(i));
      }

      return sum;
  }

  public boolean equals(Object t) {
      if ((t == null) || !(t instanceof Tuple) || (length() != ((Tuple)t).length())) {
          return false;
      }

      for (int i = 0; i < length(); i++) {
          if (getElement(i) != ((Tuple)t).getElement(i)) {
              return false;
          }
      }

      return true;
  }

  public String toString() {
      if (isImpossible()) {
          return "0 items / $0 / 0 pounds";
      }
      return ((data[0] == 1)? "" : (data[0] + " items / ")) + "$" + data[1] + " / " + data[2] + " pounds";
    }
}
