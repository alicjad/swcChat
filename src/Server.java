import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(80);
        // lista client socketow
        ArrayList<ClientSocket>clientSockets = new ArrayList<>();
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Someone connected");
            ClientSocket cs = new ClientSocket(socket);
            clientSockets.add(cs);
            String request = cs.getRequest();
            System.out.println(request);
            //cs.writeResponse();
            // cs.getUserName()
            // cs.hasMessage()
            // cs.getMessage()
            //cs.sendMessage()
            // dodaj cs do listy socketow
            /*byte[] request = new byte[100];
            socket.getInputStream().read(request);
            socket.getOutputStream().flush();
            String s = new String(request);
            System.out.println("Someone connected");
            */
        }
    }

}
