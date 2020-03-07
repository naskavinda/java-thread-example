package v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class WorkerThread implements Runnable{

    private final Socket clientSocket;

    public WorkerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {

            String inputLine;

            if ((inputLine = in.readLine()) != null) {
                System.out.println(String.format("Received Client Message is [%s]",inputLine));
                out.println(inputLine.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
