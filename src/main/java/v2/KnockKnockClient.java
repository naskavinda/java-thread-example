package v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class KnockKnockClient implements Runnable {

    private final String hostName;
    private final int portNumber;
    private final String message;

    public KnockKnockClient(String hostName, int portNumber, String message) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.message = message;
    }


    @Override
    public void run() {
        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
        ) {

            String fromServer;
            out.println(message);
            if ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
            }
        } catch (IOException e) {

        }
    }
}