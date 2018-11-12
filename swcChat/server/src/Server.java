import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private int port;

    public ArrayList<Client> getClients() {
        return clients;
    }

    private ArrayList<Client>clients;

    public Client getClientByName(String userName){
        for (int c=0; c< this.clients.size(); c++){
            if (this.clients.get(c).getClientName().equals(userName)){
                return this.clients.get(c);
            }
        }
        return null;
    }

    public Server(int port){
        this.port = port;
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server(80);
        server.startServer();
    }

    public void startServer() throws IOException{
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server set up! waiting for people to connect");
        clients = new ArrayList<>();
        int i=0;

        while (true) {
            i = i + 1;
            Socket socket = serverSocket.accept();
            ListeningThread listen= new ListeningThread(socket,i,this);
            listen.start();
        }

    }

}
