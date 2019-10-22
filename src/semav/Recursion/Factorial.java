package semav.Recursion;

import java.math.BigInteger;

public class Factorial {
    public static void main(String[] args) {
        BigInteger f = factorial(BigInteger.valueOf(100));
        System.out.println(f);

    }

    private static BigInteger factorial(BigInteger x) {
        if (x.compareTo(BigInteger.valueOf(1)) < 0) {
            return BigInteger.valueOf(1);
        }

        return x.multiply(factorial(x.subtract(BigInteger.valueOf(1))));
    }
}
