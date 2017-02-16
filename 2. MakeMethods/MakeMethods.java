public class MakeMethods {
  public static void main (String[] args) {
    String function = args[0];
    try {
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
          break;
        case "sqrt": System.out.println(sqrt(Double.parseDouble(args[1]), Double.parseDouble(args[1])));
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
    //CHECK
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
    throw new UnsupportedOperationException();
  }

  public static long lcm(long m, long n) {
    throw new UnsupportedOperationException();
  }

  public static double poly(double x, double[] coeff) {
    throw new UnsupportedOperationException();
  }

  public static double sqrt(double x, double epsilon) {
    throw new UnsupportedOperationException();
  }

  public static double root(int n, double x, double epsilon) {
    throw new UnsupportedOperationException();
  }

  public static double power(double x, int n) {
    throw new UnsupportedOperationException();
  }

  //HELPER METHODS

}
