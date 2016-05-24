//374 - Big mod
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        Main main = new Main();
        main.start();
    }

    void start() throws java.io.IOException {
        Scanner input = new Scanner(System.in);
        BigInteger b, p, m;
        while(input.hasNext()) {
            b = input.nextBigInteger();
            p = input.nextBigInteger();
            m = input.nextBigInteger();
            System.out.println(b.modPow(p, m).toString());
            //ModPow Additive Chaining, solves smaller parts and adds up to total sum.
        }
    }
}
