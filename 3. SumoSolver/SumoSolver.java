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
    chart = new Tuple[b][items];
    prices = p;
    gains = g;
    budget = b;
  }

  public String solve() {
    solveHelp(budget - 1, prices.length - 1);
    Tuple answerBox = chart[budget - 1][prices.length - 1];
    return answerBox.toString();
  }

  // public void solveHelp(int budget, int numberOfItems) {
  //   chart[budget - 1][numberOfItems - 1] = new Tuple(2,97,103);
  // }

  public void solveHelp(int x, int y) {
    if (prices[y] < x) {
      chart[x][y] = new Tuple(1, prices[y], gains[y]);
      chart[x][y] = chart[x][y].add(solveHelp(x - prices[y], y));
    } else {
      chart[x][y] = Tuple.IMPOSSIBLE;
    }



  }
}
