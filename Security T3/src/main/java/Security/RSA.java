package Security;

public class RSA {

    public int encrypt(int p, int q, int M, int e) {
        int N = p * q;
        int C = modPow(M, e, N);
        return C;
    }

    public int decrypt(int p, int q, int C, int e) {
        int N = p * q;
        int phi_of_N = (p - 1) * (q - 1);

        // Calculate d (modular multiplicative inverse of e mod phi_of_N)
        int d = modInverse(e, phi_of_N);

        // Using modular exponentiation to avoid overflow
        int M = modPow(C, d, N);

        return M;
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

    private int modInverse(int a, int m) {
        a = ((a % m) + m) % m;

        for (int x = 1; x < m; x++) {
            if ((long)(a) * x % m == 1) {
                return x;
            }
        }

        return -1; // Should never reach here if a and m are coprime
    }

}