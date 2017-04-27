class CrownAndAnchor {
  /**
   *Crown and Anchor simulation as described by Ray Toal on 4/18/2017 for Prof. Dorin. Written by Edward Bachoura.
   *If you were to run the program with no args, the program defaults to 100,000 rounds
   *and 100 simulations, with each round having a bet of $1. Otherwise, you must put EXACTLY three arguments
   *in this order: number of rounds, number of simulations, and wager amount.
   *@author Edward Bachoura
   */
  public static void main (String[] args) {
    int NUMBER_OF_ROUNDS = 10000;
    int NUMBER_OF_SIMULATIONS = 100;
    double WAGER_AMOUNT = 1;

    if (args.length == 0) {
    } else if (args.length != 3) {
      System.out.println("You have an invalid number of arguments. If you would like, you could leave the arguments blank and the program will run the default simulation. Otherwise, make sure you have three arguments in this order: number of rounds, number of simulations, and wager amount per round.");
      return;
    } else {
      try {
        NUMBER_OF_ROUNDS = Integer.parseInt(args[0]);
        NUMBER_OF_SIMULATIONS = Integer.parseInt(args[1]);
        WAGER_AMOUNT = Double.parseDouble(args[2]);
      } catch (Exception e) {
        System.err.println(e);
        return;
      }
    }

    int total = 0;
    for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
      total += singleSimulation(NUMBER_OF_ROUNDS, WAGER_AMOUNT);
    }
    double average = (double) total / (double) NUMBER_OF_SIMULATIONS;
    double averagePercentageLost = average / ((double) NUMBER_OF_ROUNDS * WAGER_AMOUNT) * 100;

    System.out.println("In " + NUMBER_OF_SIMULATIONS + " simulations of " + NUMBER_OF_ROUNDS + " rounds, there was an average net loss of " + average + " dollars per simulation, which means that there was an average net percent loss of " + averagePercentageLost + "% per hand. This game favors the House, as usually expected when gambling!");
  }

  private static int playRoundOfCrownAndAnchor (double bet) {
    int numberOfMatches = 0;
    int symbolBet = (int) Math.floor(6 * Math.random() + 1);
    for (int i = 0; i < 3; i++) {
      if ((int) Math.floor(6 * Math.random() + 1) == symbolBet) { numberOfMatches++; }
    }
    return (int) ((numberOfMatches == 0)? -1.0 * bet : (double) numberOfMatches * bet);
  }

  private static int singleSimulation (int rounds, double bet) {
    int gains = 0;
    for (int i = 0; i < rounds; i++) {
      gains += playRoundOfCrownAndAnchor(bet);
    }
    return gains;
  }
}
