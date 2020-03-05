package v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        int portNumber = 8080;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Thread worker = new Thread(new WorkerThread(clientSocket));
                worker.start();

            }
        }
    }
}
