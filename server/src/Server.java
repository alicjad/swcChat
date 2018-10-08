import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(80);
        ArrayList<ClientSocket>clientSockets = new ArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Someone connected");
            ClientSocket cs = new ClientSocket(socket);
            clientSockets.add(cs);
            String request = cs.getRequest();
            System.out.println(request);
            cs.writeResponse("you send: "+request);
        }
    }

}
