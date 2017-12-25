package io.dama.par.cf.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RSAKeyFuture {
    public static KeyValue getKeySequential(final int len) throws InterruptedException, ExecutionException {
        // Bestimme zwei Primzahlen mit Bitlänge len
        final Random rnd = new Random();
        final ExecutorService pool = Executors.newFixedThreadPool(2);
        final Future<BigInteger> primPFuture = pool.submit(() -> BigInteger.probablePrime(len, rnd));
        final Future<BigInteger> primQFuture = pool.submit(() -> BigInteger.probablePrime(len, rnd));

        // Berechne phi und N
        final Future<BigInteger> nFuture = pool.submit(() -> primPFuture.get().multiply(primQFuture.get()));
        final Future<BigInteger> phiFuture = pool.submit(
                () -> primPFuture.get().subtract(BigInteger.ONE).multiply(primQFuture.get().subtract(BigInteger.ONE)));
        final BigInteger n = nFuture.get();
        final BigInteger phi = phiFuture.get();
        // Wähle e
        BigInteger rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
        while (rndBigInt.gcd(phi).equals(BigInteger.ONE) == false) {
            rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
        }
        final BigInteger e = rndBigInt;

        // Berechne d
        final BigInteger d = e.modInverse(phi);

        return new KeyValue(n, e, d);
    }

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final long now = System.currentTimeMillis();
        final KeyValue result = RSAKeyFuture.getKeySequential(1024);
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");
    }
}
