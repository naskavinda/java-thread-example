package v1;

import java.net.*;
import java.io.*;

@SuppressWarnings("Duplicates")
public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 8080;
        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
            ) {

                String inputLine;

                if ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    out.println("From the Server.");
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
