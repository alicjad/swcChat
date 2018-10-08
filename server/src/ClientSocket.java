import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

    private Socket clientSocket;
    public Client client;

    public ClientSocket(Socket socket){
        this.clientSocket = socket;
    }

    public String getRequest() throws IOException {
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        String messageIn = inputStreamReader.readLine();

        return messageIn;
    }

    public void writeResponse(String outgoingMessage) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        dataOutputStream.writeBytes(outgoingMessage +"\n" );
        dataOutputStream.flush();
    }
}
