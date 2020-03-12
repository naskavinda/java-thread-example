package v7;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("Duplicates")
public class ClientFactory {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int productId = random.nextInt(4) + 1;
            int qty = random.nextInt(10) + 1;
            Thread client = new Thread(
                    new Client(
                            "localhost",
                            8080,
                            String.format("%s|%s", productId, qty)
                    )
            );
            client.start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
