import java.io.*;
import java.util.concurrent.Callable;

/* Reference: https://www.javaspecialists.eu/archive/Issue153.html */

import java.util.concurrent.*;

public class ConsoleInput {
    private final int tries;
    private final int timeout;
    private final TimeUnit unit = TimeUnit.SECONDS;

    public ConsoleInput(int tries, int timeout) {
        this.tries = tries;
        this.timeout = timeout;
    }

    public String readLine() throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String input = null;
        try {
            for (int i = 0; i < tries; i++) {
                Future<String> result = ex.submit(new ConsoleInputReadTask());
                try {
                    input = result.get(timeout, unit);
                    break;
                } catch (ExecutionException e) {
                    e.getCause().printStackTrace();
                } catch (TimeoutException e) {
                    result.cancel(true);
                }
            }
        } finally {
            ex.shutdownNow();
        }

        return input;
    }
}



class ConsoleInputReadTask implements Callable<String> {
    public String call() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        do {
            try {
                while (!br.ready()  /*  ADD SHUTDOWN CHECK HERE */) {
                    Thread.sleep(200);
                }
                input = br.readLine();
            } catch (InterruptedException e) {
                return null;
            }
        } while ("".equals(input));
        return input;
    }
}
