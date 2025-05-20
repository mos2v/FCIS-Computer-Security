package Security;

import java.util.ArrayList;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb)
    {

        int ya = modPow(alpha, xa, q);
        int yb = modPow(alpha, xb, q);

        int keyA = modPow(yb, xa, q);
        int keyB = modPow(ya, xb, q);

        List<Integer> keys = new ArrayList<>();
        keys.add(keyA);
        keys.add(keyB);
        return keys;

    }

    private int modPow(int base, int exponent, int modulus) {
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

        return (int)(result);
    }
}
