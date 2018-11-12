import java.io.IOException;
import java.net.Socket;

public class ListeningThread extends Thread {

    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_GREEN = "\u001B[32m";
    Server server;
    Socket socket;
    int i;

    public ListeningThread(Socket socket, int i, Server server) {
        this.socket = socket;
        this.i = i;
        this.server=server;
    }

    @Override
    public void run() {
        System.out.println(ANSI_BLUE+"Someone connected"+ANSI_RESET);
        ClientSocket cs = new ClientSocket(socket);
        String clientName = null;
        try {
            clientName = cs.getRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Client client = new Client(cs, clientName);
        server.getClients().add(client);
        System.out.println(ANSI_GREEN+"client " + i + " saved as: " + clientName+ANSI_RESET);
        for (int j = 0; j < server.getClients().size(); j++) {
            if (server.getClients().get(j) != client) {
                try {
                    server.getClients().get(j).getClientSocket().writeResponse(ANSI_PURPLE+"New user connected: " + clientName+ANSI_RESET);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ClientHandlingThread handler = new ClientHandlingThread(server, client);
        Thread clientThread = new Thread(handler);
        clientThread.start();
        try {
            cs.writeResponse(ANSI_GREEN+"Here is a list of all the connected users: "+ANSI_RESET);
            cs.writeResponse(printUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String printUsers() {
        String users = "";
        for (int i = 0; i < server.getClients().size(); i++) {
            users = users + server.getClients().get(i).getClientName() + "\n";
        }
        return users;

    }
}
