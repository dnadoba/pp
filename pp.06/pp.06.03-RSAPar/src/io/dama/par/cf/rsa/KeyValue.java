package io.dama.par.cf.rsa;
import java.math.BigInteger;

public class KeyValue {
    public final BigInteger N;
    public final BigInteger e;
    public final BigInteger d;

    public KeyValue(final BigInteger N, final BigInteger e, final BigInteger d) {
        this.N = N;
        this.e = e;
        this.d = d;
    }

    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        str.append(System.lineSeparator());
        str.append("N : " + this.N);
        str.append(System.lineSeparator());
        str.append("e : " + this.e);
        str.append(System.lineSeparator());
        str.append("d : " + this.d);

        return str.toString();
    }
}
