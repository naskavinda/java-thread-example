package v7;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("Duplicates")
public class Server {
    public static void main(String[] args) throws IOException {

        int portNumber = 8080;

        ItemStoreImpl socketStore = new ItemStoreImpl();
        RequestCoordinator rc = new RequestCoordinator(socketStore);
        rc.start();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                final Socket clientSocket = serverSocket.accept();

                socketStore.newRequestReceived(clientSocket);

            }
        }
    }
}
