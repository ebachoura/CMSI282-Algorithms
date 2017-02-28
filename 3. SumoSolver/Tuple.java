public class Tuple {

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

  public Tuple(int n, int index) {
      data = new int[n];
      for (int i = 0; i < n; i++) {
          if (i == index) {
            data[i] = 1;
          } else {
            data[i] = 0;
          }
      }
  }

  private void setElement(int i, int j) {
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

  public String toString(int[] p, int[] g) {
      if (this.data == null) {
          return "0 items / $0 / 0 pounds";
      }
      return (itemsListed(p, g) + (itemCount() == 1? "1 item / " : (itemCount() + " items / ")) + "$" + price(p) + " / " + gains(g) + " pounds");
  }

  private int itemCount() {
      int count = 0;
      for (int i = 0; i < data.length; i++) {
        count+=data[i];
      }
      return count;
  }

  public int gains(int[] g) {
    int gains = 0;
    for (int i = 0; i < g.length; i++) {
      gains += g[i]*data[i];
    }
    return gains;
  }

  public int price(int[] p) {
    int cost = 0;
    for (int i = 0; i < p.length; i++) {
      cost += (p[i]*data[i]);
    }
    return cost;
  }

  private String itemsListed(int[] p, int[] g) {
    String s = "";
    for (int i = 0; i < data.length; i++) {
      if (data[i] == 1) {
        s = s + "$" + p[i] + " / " + g[i] + " pounds \n" + "\n";
      }
    }
    return s;
  }
}
