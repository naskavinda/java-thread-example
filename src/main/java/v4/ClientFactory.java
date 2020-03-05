package v4;

import java.util.concurrent.TimeUnit;

public class ClientFactory {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread client = new Thread(new KnockKnockClient("localhost", 8080, String.format("Clint No : %s", i)));
            client.start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
