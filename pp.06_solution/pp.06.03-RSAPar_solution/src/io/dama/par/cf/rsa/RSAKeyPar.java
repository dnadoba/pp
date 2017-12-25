package io.dama.par.cf.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RSAKeyPar {

    private static CompletableFuture<BigInteger> getAsyncPrim(final int len) {
        final Supplier<BigInteger> primSupplier = () -> {
            final CompletableFuture<Object> cfPrim = CompletableFuture.anyOf(
                    CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
                    CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
                    CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
                    CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())));
            return (BigInteger) cfPrim.join();
        };

        return CompletableFuture.supplyAsync(primSupplier);
    }

    public static KeyValue getKeyParallel(final int len) {
        // Berechne zwei Primzahlen
        final CompletableFuture<BigInteger> primPFuture = RSAKeyPar.getAsyncPrim(len);
        final CompletableFuture<BigInteger> primQFuture = RSAKeyPar.getAsyncPrim(len);

        // Berechne N und Phi
        final CompletableFuture<BigInteger> NFuture = primPFuture.thenCombineAsync(primQFuture, BigInteger::multiply);

        final CompletableFuture<BigInteger> PhiFuture = primPFuture.thenApplyAsync((b) -> b.subtract(BigInteger.ONE))
                .thenCombineAsync(primQFuture.thenApplyAsync((b) -> b.subtract(BigInteger.ONE)), BigInteger::multiply);

        final BigInteger N = NFuture.join();
        final BigInteger Phi = PhiFuture.join();

        // Wähle e
        final Random rd = new Random();
        BigInteger rndBigInt = new BigInteger(Phi.bitLength() / 3, rd);
        while (rndBigInt.gcd(Phi).equals(BigInteger.ONE) == false) {
            rndBigInt = new BigInteger(Phi.bitLength() / 3, rd);
        }
        final BigInteger e = rndBigInt;

        // Berechne d
        final BigInteger d = e.modInverse(Phi);

        return new KeyValue(N, e, d);
    }

    public static void main(final String[] args) {
        final long now = System.currentTimeMillis();
        final KeyValue result = RSAKeyPar.getKeyParallel(1024);
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");
    }
}
