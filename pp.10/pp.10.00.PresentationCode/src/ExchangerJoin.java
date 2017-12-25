
import java.util.concurrent.Exchanger;

public class ExchangerJoin {

    public static void main(final String[] args) {
        final Exchanger<Boolean> quit = new Exchanger<>();
        final Thread t1 = new Thread(() -> {
            try {
                quit.exchange(Boolean.TRUE);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        try {
            quit.exchange(null);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
