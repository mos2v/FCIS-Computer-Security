package Security;

import java.util.ArrayList;
import java.util.List;

public class ElGamal {
    public List<Long> encrypt(int q, int alpha, int y, int k, int m)
    {
        long K = modPow(y, k, q);
        long c1 = modPow(alpha, k, q);
        long c2 = (m * K) % q;

        List<Long> C = new ArrayList<Long>();
        C.add(c1);
        C.add(c2);

        return C;
    }

    public int decrypt(int c1, int c2, int x, int q)
    {
        long s = modPow(c1, q-1-x, q);
        int m = (int) ((c2 * s) % q);
        return m;
    }

    private long modPow(int base, int exponent, int modulus) {
        if (modulus == 1) return 0;

        long result = 1;
        long b = base % modulus;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * b) % modulus;
            }

            exponent = exponent >> 1;
            b = (b * b) % modulus;
        }

        return (result);
    }


}
