import java.net.SocketException;

public class ClientHandlingThread implements Runnable {

    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_RESET = "\u001B[0m";

    Client client;
    Server server;

    public ClientHandlingThread(Server server, Client client){
        this.server = server;
        this.client = client;
    }

    @Override
    public void run(){
        try {
            while (true) {
                String messageIn = this.client.getClientSocket().getRequest();
                String[]message  = messageIn.split(" ", 4);
                System.out.println(messageIn);
                if (message[1].equals("PM") && server.getClientByName(message[2]) != null ){
                    server.getClientByName(message[2]).getClientSocket().writeResponse(ANSI_CYAN+ messageIn+ANSI_RESET);
                }else {
                    for (Client client : server.getClients()) {
                        try {
                            if (client != this.client) {
                                client.getClientSocket().writeResponse(messageIn);
                            }
                        } catch (SocketException ex) {
                            this.deleteClient(client);
                        }
                    }
                    if (messageIn.equals(client.getClientName() + ": " + "EXIT")) {
                        System.out.println(ANSI_RED+ client.getClientName() +"  HAS LEFT CHAT" + ANSI_RESET);
                        deleteClient(this.client);
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteClient(Client client){
        server.getClients().remove(client);
    }

}
