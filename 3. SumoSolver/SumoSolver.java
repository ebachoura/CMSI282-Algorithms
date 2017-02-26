public class SumoSolver {
  public static void main (String[] args) {
    try {
      if (args.length%2 == 0 || args.length < 1) { throw new IllegalArgumentException(); }
      int numberOfItems = (args.length-1) / 2;
      int[] prices = new int[numberOfItems];
      int[] gains = new int[numberOfItems];
      int budget = Integer.parseInt(args[args.length - 1]);
      for (int i = 0; i < numberOfItems; i+=1) {
        prices[i] = Integer.parseInt(args[2*i]);
        gains[i] = Integer.parseInt(args[2*i+1]);
      }
      SumoSolver keiryo = new SumoSolver(budget, numberOfItems, prices, gains);
      System.out.println(keiryo.solve());
    } catch (Exception e) { System.err.println(e); }
  }

  Tuple[][] chart;
  int[] prices;
  int[] gains;
  int budget;

  public SumoSolver (int b, int items, int[] p, int[] g) {
    chart = new Tuple[b + 1][items];
    for (int i = 0; i < p.length; i++) {
      chart[0][i] = new Tuple(0,0,0);
    }
    prices = p;
    gains = g;
    budget = b;
  }

  public String solve() {
    solveHelp(budget, prices.length - 1);
    Tuple answerBox = chart[budget][prices.length - 1];
    return answerBox.toString();
  }

  private void solveHelp(int x, int y) {
    if (prices[y] <= x) {
      chart[x][y] = new Tuple(1, prices[y], gains[y]);
      if (chart[x - prices[y]][y] == null) { solveHelp(x - prices[y], y); }
      if (chart[x - prices[y]][y] != null) {
        chart[x][y] = chart[x][y].add(chart[x - prices[y]][y]);
      } else { chart[x][y] = null; }
    }

    if (y != 0) {
      if (chart[x][y-1] == null) { solveHelp(x,y-1); }
      if (chart[x][y] == null && chart[x][y-1] == null) {
      } else if ((chart[x][y-1] != null && chart[x][y] == null) || (chart[x][y-1].getElement(2) > chart[x][y].getElement(2))) {
        chart[x][y] = chart[x][y-1];
      }
    }
  }
}
