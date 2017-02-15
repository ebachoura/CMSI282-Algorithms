public class MakeMethods {
  public static void main (String[] args) {
    String function = args[0];
    switch (function) {
      case "factorial": int n1 = Integer.parseInt(args[1]); System.out.println(factorial(n1));
        break;
      case "fibonacci":
        try {
          int n2 = Integer.parseInt(args[1]);
          System.out.println(fibonacci(n2));
        } catch (Exception e) { throw new IllegalArgumentException(); }
        break;
      case "gcd": long m1 = Long.parseLong(args[1]); long n3 = Long.parseLong(args[2]); System.out.println(gcd(m1, n3));
        break;
      case "lcm": long m2 = Long.parseLong(args[1]); long n4 = Long.parseLong(args[2]); System.out.println(lcm(m2, n4));
        break;
      case "poly":
        break;
      case "sqrt": Double x2 = Double.parseDouble(args[1]); Double epsilon = Double.parseDouble(args[1]); System.out.println(sqrt(x2, epsilon));
        break;
      case "root": int n5 = Integer.parseInt(args[1]); Double x3 = Double.parseDouble(args[2]); Double epsilon1 = Double.parseDouble(args[3]); System.out.println(root(n5, x3, epsilon1));
        break;
      case "power": Double x4 = Double.parseDouble(args[1]); int n6 = Integer.parseInt(args[2]); System.out.println(power(x4, n6));
        break;
      default:
      System.out.println("BAD DATA");
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
