package io.dama.par.cf.rsa;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RSAKeySeq {
    public static KeyValue getKeySequential(final int len) throws InterruptedException, ExecutionException {
    	ExecutorService pool = Executors.newCachedThreadPool();
    	
        // Bestimme zwei Primzahlen mit Bitlänge len
        final Random rnd = new Random();
        final Future<BigInteger> primP = pool.submit(() -> BigInteger.probablePrime(len, rnd));
        final Future<BigInteger> primQ = pool.submit(() -> BigInteger.probablePrime(len, rnd));

        // Berechne phi und N
        final Future<BigInteger> N = pool.submit(() -> primP.get().multiply(primQ.get()));
        final Future<BigInteger> phi = pool.submit(() -> primP.get().subtract(BigInteger.ONE).multiply(primQ.get().subtract(BigInteger.ONE)));

        // Wähle e
        BigInteger rndBigInt = new BigInteger(phi.get().bitLength() / 3, rnd);
        while (rndBigInt.gcd(phi.get()).equals(BigInteger.ONE) == false) {
            rndBigInt = new BigInteger(phi.get().bitLength() / 3, rnd);
        }
        final BigInteger e = rndBigInt;

        // Berechne d
        final BigInteger d = e.modInverse(phi.get());

        return new KeyValue(N.get(), e, d);
    }

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
    	long start = System.currentTimeMillis();
        System.out.println(RSAKeySeq.getKeySequential(2048));
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Duration: " + duration + "ms");
    }
}
