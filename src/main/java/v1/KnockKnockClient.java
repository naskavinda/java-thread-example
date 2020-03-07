package v1;

import java.io.*;
import java.net.*;

@SuppressWarnings("Duplicates")
public class KnockKnockClient {
    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 8080;

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()))
        ) {

            String fromServer;
            out.println("from the Client");
            if ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
            }
        } catch (IOException e) {

        }
    }
}