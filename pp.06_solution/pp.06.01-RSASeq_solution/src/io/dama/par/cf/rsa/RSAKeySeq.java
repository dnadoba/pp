package io.dama.par.cf.rsa;

import java.math.BigInteger;
import java.util.Random;

public class RSAKeySeq {
    public static KeyValue getKeySequential(final int len) {
        // Bestimme zwei Primzahlen mit Bitlänge len
        final Random rnd = new Random();
        final BigInteger primP = BigInteger.probablePrime(len, rnd);
        final BigInteger primQ = BigInteger.probablePrime(len, rnd);

        // Berechne phi und N
        final BigInteger N = primP.multiply(primQ);
        final BigInteger phi = primP.subtract(BigInteger.ONE).multiply(primQ.subtract(BigInteger.ONE));

        // Wähle e
        BigInteger rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
        while (rndBigInt.gcd(phi).equals(BigInteger.ONE) == false) {
            rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
        }
        final BigInteger e = rndBigInt;

        // Berechne d
        final BigInteger d = e.modInverse(phi);

        return new KeyValue(N, e, d);
    }

    public static void main(final String[] args) {
        final long now = System.currentTimeMillis();
        final KeyValue result = RSAKeySeq.getKeySequential(2048);
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");
    }
}
