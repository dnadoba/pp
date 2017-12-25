package io.dama.par.cf.rsa;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RSAKeyPar {

    private static CompletableFuture<BigInteger> getAsyncPrim(final int len) {
    	return CompletableFuture.anyOf(
            	CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
            	CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
            	CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current())),
            	CompletableFuture.supplyAsync(() -> BigInteger.probablePrime(len, ThreadLocalRandom.current()))
            ).thenApplyAsync((cf) -> {
            	return (BigInteger)cf;
            });
       
    }

    public static KeyValue getKeyParallel(final int len) {
        // Berechne zwei Primzahlen
        final CompletableFuture<BigInteger> primPFuture = RSAKeyPar.getAsyncPrim(len);
        final CompletableFuture<BigInteger> primQFuture = RSAKeyPar.getAsyncPrim(len);

        // Berechne phi und N
        final CompletableFuture<BigInteger> NFuture = primPFuture.thenCombineAsync(primQFuture, (primP, primQ) -> primP.multiply(primQ));
        final CompletableFuture<BigInteger> phiFuture = primPFuture.thenCombineAsync(primQFuture, (primP, primQ) -> primP.subtract(BigInteger.ONE).multiply(primQ.subtract(BigInteger.ONE)));

        // Wähle e
        final CompletableFuture<BigInteger> eFuture = phiFuture.thenApplyAsync((phi) -> {
        	final Random rnd = new Random();
        	 BigInteger rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
             while (rndBigInt.gcd(phi).equals(BigInteger.ONE) == false) {
                 rndBigInt = new BigInteger(phi.bitLength() / 3, rnd);
             }
             return rndBigInt;
        });
       
        // Berechne d
        final CompletableFuture<BigInteger> dFuture = eFuture.thenCombineAsync(phiFuture, (e, phi) -> e.modInverse(phi));
        
        return new KeyValue(NFuture.join(), eFuture.join(), dFuture.join());
    }

    public static void main(final String[] args) {
    	System.out.println("getParallelism:" + ForkJoinPool.commonPool().getParallelism());
    	;
        final long now = System.currentTimeMillis();
        final KeyValue result = RSAKeyPar.getKeyParallel(2048);
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");
    }
}
