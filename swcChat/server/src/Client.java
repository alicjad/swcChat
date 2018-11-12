public class Client {

    private String clientName;
    private ClientSocket clientSocket;

    public Client(ClientSocket clientSocket, String clientName){
        this.clientSocket = clientSocket;
        this.clientName = clientName;
    }

    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    public String getClientName() {
        return clientName;
    }
}
