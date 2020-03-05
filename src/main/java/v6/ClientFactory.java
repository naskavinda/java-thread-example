package v6;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClientFactory {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Thread client = new Thread(
                    new KnockKnockClient(
                            "localhost",
                            8080,
                            String.format("%s|%s", random.nextInt(4) + 1, random.nextInt(10) + 1)
                    )
            );
            client.start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
