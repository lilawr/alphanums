import java.io.*;
import java.util.concurrent.Callable;

/* Reference: https://www.javaspecialists.eu/archive/Issue153.html */

import java.util.concurrent.*;

public class ConsoleInput {

    public String readLine() throws InterruptedException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        String input = null;
        try {
            Future<String> result = ex.submit(new ConsoleInputReadTask());
            try {
                input = result.get(60, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                e.getCause().printStackTrace();
            } catch (TimeoutException e) {
                result.cancel(true);
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
                while (!br.ready()) {
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
