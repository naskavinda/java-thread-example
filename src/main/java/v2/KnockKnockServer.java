package v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class KnockKnockServer {
    public static void main(String[] args) {

        int portNumber = 8080;
        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()))
            ) {

                String inputLine;

                if ((inputLine = in.readLine()) != null) {
                    System.out.println(String.format("Received Client Message is [%s]",inputLine));
                    out.println(inputLine.toUpperCase());
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
