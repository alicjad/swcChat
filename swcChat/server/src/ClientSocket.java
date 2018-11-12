import java.io.*;
import java.net.Socket;

public class ClientSocket {

    public Socket getClientSocket() {
        return clientSocket;
    }

    private Socket clientSocket;

    public ClientSocket(Socket socket){
        this.clientSocket = socket;
    }

    public String getRequest() throws IOException {
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        String request = inputStreamReader.readLine();

        return request;
    }

    public void writeResponse(String outgoingMessage) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        dataOutputStream.writeBytes(outgoingMessage +"\n" );
        dataOutputStream.flush();
    }
}
