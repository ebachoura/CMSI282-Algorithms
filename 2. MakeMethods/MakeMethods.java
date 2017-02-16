public class MakeMethods {
  public static void main (String[] args) {
    try {
      String function = args[0];
      switch (function) {
        case "factorial": System.out.println(factorial(Integer.parseInt(args[1])));
          break;
        case "fibonacci": System.out.println(fibonacci(Integer.parseInt(args[1])));
          break;
        case "gcd": System.out.println(gcd(Long.parseLong(args[1]), Long.parseLong(args[2])));
          break;
        case "lcm": System.out.println(lcm(Long.parseLong(args[1]), Long.parseLong(args[2])));
          break;
        case "poly":
          double[] coeff = new double[args.length- 2];
          for (int i = 0; i < args.length - 2; i++) {
            coeff[i] = Double.parseDouble(args[i + 2]);
          }
          System.out.println(poly(Double.parseDouble(args[1]), coeff));
          break;
        case "sqrt": System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[2])));
          break;
        case "root": System.out.println(root(Integer.parseInt(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3])));
          break;
        case "power": System.out.println(power(Double.parseDouble(args[1]), Integer.parseInt(args[2])));
          break;
        default:
        System.out.println("BAD DATA");
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public static java.math.BigInteger factorial(int n) {
    if (n < 0) { throw new IndexOutOfBoundsException(); }
    java.math.BigInteger factorial = new java.math.BigInteger("1");
    for (int i = 2; i <= n; i++) {
      factorial = factorial.multiply(new java.math.BigInteger(Integer.toString(i)));
    }
    return factorial;
  }

  public static java.math.BigInteger fibonacci(int n) {
    if (n < 0) { throw new IndexOutOfBoundsException(); }
    if (n == 0) { return new java.math.BigInteger("0"); }
    if (n == 1) { return new java.math.BigInteger("1"); }
    java.math.BigInteger fibonacci = new java.math.BigInteger("1");
    java.math.BigInteger previous = new java.math.BigInteger("1");
    for (int i = 2; i < n; i++) {
      java.math.BigInteger temp = fibonacci;
      fibonacci = fibonacci.add(previous);
      previous = temp;
    }
    return fibonacci;
  }

  public static long gcd(long m, long n) {
    if (n == 0 || m == 0) { throw new IndexOutOfBoundsException(); }
    if (m % n == 0) { return Math.abs(n); }
    return gcd(n, m % n);
  }

  public static long lcm(long m, long n) {
    if (n == 0 || m == 0) { throw new IndexOutOfBoundsException(); }
    return m * n / gcd(m, n);
  }

  public static double poly(double x, double[] coeff) {
    double polySum = 0;
    for (int i = coeff.length - 1; i >= 0; i--) {
      polySum = coeff[i] + (x * polySum);
    }
    return polySum;
  }

  public static double sqrt(double x, double epsilon) {
    if (x < 0 || epsilon < 0) { throw new IndexOutOfBoundsException(); }
    if (x == 1) { return 1; }
    return rootHelp(0, x, x, epsilon, 2);
  }

  public static double root(int n, double x, double epsilon) {
    if ((x < 0 && n%2 == 0) || epsilon < 0) { throw new IndexOutOfBoundsException(); }
    if (x == 1) { return 1; }
    if (n > 0) {
      if (x >= 0) {
        if (x > 0 && x < 1) { return 1 / rootHelp(0, 1/x, 1/x, epsilon, n); }
        return rootHelp(0, x, x, epsilon, n);
      }
      if (x < 0 && x > -1) { return -1 / rootHelp(0, 1/-x, 1/-x, epsilon, n); }
      return -1 * rootHelp(0, -x, -x, epsilon, n);
    }
    if (x >= 0) {
      if (x > 0 && x < 1) { return rootHelp(0, 1/x, 1/x, epsilon, -n); }
      return 1 / rootHelp(0, x, x, epsilon, -n);
    }
    if (x < 0 && x > -1) { return -1 * rootHelp(0, 1/-x, 1/-x, epsilon, -n); }
    return -1 / rootHelp(0, -x, -x, epsilon, -n);
  }

  public static double power(double x, int n) {
    if (n == 2) { return x * x; }
    if (n % 2 != 0) { return x * power(x, n-1); }
    double save = power(x, n/2);
    return save * save;
  }

  //HELPER METHODS
  private static double rootHelp(double left, double right, double x, double epsilon, int n) {
    double half = (left + right) * 0.5;
    double halfToTheNth = power(half, n);
    if (halfToTheNth > (x - epsilon) && halfToTheNth < (x + epsilon)) { return half; }
    if (halfToTheNth > x) { return rootHelp(left, half, x, epsilon, n); }
    else { return rootHelp(half, right, x, epsilon, n); }
  }
}
