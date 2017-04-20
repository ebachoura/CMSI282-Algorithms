class CrownAndAnchor {
  public static void main (String[] args) {
    int NUMBER_OF_ROUNDS = 10000;
    int NUMBER_OF_SIMULATIONS = 100;
    int WAGER_AMOUNT = 3;

    int total = 0;
    for (int i = 0; i < NUMBER_OF_SIMULATIONS; i++) {
      total += singleSimulation(NUMBER_OF_ROUNDS, WAGER_AMOUNT);
    }
    double average = (double) total / (double) NUMBER_OF_SIMULATIONS;
    double averagePercentageLost = average / (double) (NUMBER_OF_ROUNDS * WAGER_AMOUNT) * 100;

    System.out.println("In " + NUMBER_OF_SIMULATIONS + " simulations of " + NUMBER_OF_ROUNDS + " rounds, there was an average net loss of " + average + " dollars per simulation, which means that there was an average net percent loss of " + averagePercentageLost + "% per hand.");
  }

  public static int playRoundOfCrownAndAnchor (int bet) {
    int numberOfMatches = 0;
    for (int i = 0; i < 3; i++) {
      if ((int) Math.floor(6 * Math.random() + 1) == 3) { numberOfMatches++; }
    }
    return (numberOfMatches == 0)? -1 * bet : numberOfMatches * bet;
  }

  public static int singleSimulation (int rounds, int bet) {
    int gains = 0;
    for (int i = 0; i < rounds; i++) {
      gains += playRoundOfCrownAndAnchor(bet);
    }
    return gains;
  }
}
