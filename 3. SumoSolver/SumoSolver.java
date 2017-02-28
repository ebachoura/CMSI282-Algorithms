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
    // System.out.println("visited: " + x + ", " + y);
    if (y == 0) {
      if (isNull(x, y) && x >= prices[0]) { chart[x][y] = new Tuple(prices.length, 0); }
    } else {
      if (prices[y] <= x) {
        if (isNull(x, y)) { chart[x][y] = new Tuple(prices.length, y); }
        if (isNull(x - prices[y], y - 1)) { solveHelp(x - prices[y], y - 1); }
        if (!isNull(x - prices[y], y - 1) && (chart[x - prices[y]][y - 1].price(prices) + chart[x][y].price(prices)) <= x) {
          chart[x][y] = chart[x][y].add(chart[x - prices[y]][y - 1]);
          // if (x == budget + 1 && y == prices.length) { System.out.println("            EDIT (" + x + ", " + y + ") = " + chart[x][y].toString(prices, gains)); }
        }
      }
      if (isNull(x, y - 1)) { solveHelp(x, y - 1); }
      if (!(isNull(x, y) && isNull(x, y - 1))) {
        if (!isNull(x, y - 1) && isNull(x, y) || isGreater(chart[x][y-1], chart[x][y])) {
          chart[x][y] = chart[x][y - 1];
        }
      }
      if (!isNull(x, y)) {
        // System.out.println("final (" + x + ", " + y + ") = " + chart[x][y].toString(prices, gains));
      }
    }
  }

  private boolean isGreater(Tuple a, Tuple b) {
    return a.gains(gains) > b.gains(gains);
  }

  private boolean isNull(int x,int  y) {
    return chart[x][y] == null;
  }
}
