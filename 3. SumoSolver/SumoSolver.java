public class SumoSolver {
  public static void main (String[] args) {
    try {
      if (args.length%2 == 0 || args.length < 1 || Integer.parseInt(args[args.length - 1]) < 0) { throw new IllegalArgumentException(); }
      int numberOfItems = (args.length-1) / 2;
      int[] prices = new int[numberOfItems];
      int[] gains = new int[numberOfItems];
      int budget = Integer.parseInt(args[args.length - 1]);
      for (int i = 0; i < numberOfItems; i+=1) {
        prices[i] = Integer.parseInt(args[2*i]);
        gains[i] = Integer.parseInt(args[2*i+1]);
        if (prices[i] < 0 || gains[i] < 0) { throw new IllegalArgumentException(); }
      }
      SumoSolver keiryo = new SumoSolver(budget, numberOfItems, prices, gains);
      System.out.println(keiryo.solve());
    } catch (Exception e) { System.err.println(e); }
  }

  private Tuple[][] chart;
  private int[] prices;
  private int[] gains;
  private int budget;

  public SumoSolver (int b, int items, int[] p, int[] g) {
    chart = new Tuple[b + 1][items];
    for (int i = 0; i < p.length; i++) {
      chart[0][i] = new Tuple(p.length);
    }
    prices = p;
    gains = g;
    budget = b;
  }

  public String solve() {
    solveHelp(budget, prices.length - 1);
    Tuple answerBox = chart[budget][prices.length - 1];
    if (answerBox == null) {
      return "0 items / $0 / 0 pounds";
    } else {
      return answerBox.toString(prices, gains);
    }
  }

  private void solveHelp(int x, int y) {
    if (y == 0) {
      if (isNull(x, y) && x >= prices[0]) { chart[x][y] = new Tuple(prices.length, 0); }
    } else {
      if (prices[y] <= x) {
        if (isNull(x, y)) { chart[x][y] = new Tuple(prices.length, y); }
        if (isNull(x - prices[y], y - 1)) { solveHelp(x - prices[y], y - 1); }
        if (!isNull(x - prices[y], y - 1) && (chart[x - prices[y]][y - 1].price(prices) + chart[x][y].price(prices)) <= x) {
          chart[x][y] = chart[x][y].add(chart[x - prices[y]][y - 1]);
        }
      }
      if (isNull(x, y - 1)) { solveHelp(x, y - 1); }
      if (!(isNull(x, y) && isNull(x, y - 1))) {
        if (!isNull(x, y - 1) && isNull(x, y) || isGreater(chart[x][y-1], chart[x][y])) {
          chart[x][y] = chart[x][y - 1];
        }
      }
    }
  }

  private boolean isGreater(Tuple a, Tuple b) {
    return a.gains(gains) > b.gains(gains);
  }

  private boolean isNull(int x,int  y) {
    return chart[x][y] == null;
  }
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
}
