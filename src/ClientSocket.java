import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {

    private Socket clientSocket;
    public Client client;

    public ClientSocket(Socket socket){
        this.clientSocket = socket;
    }

    public String getRequest(){
        String messageIn = "";
        try{
            int howManyletters = this.clientSocket.getInputStream().available();
            InputStreamReader inputStreamReader = new InputStreamReader(this.clientSocket.getInputStream());
            while (messageIn.length() != howManyletters) {
                messageIn = messageIn + (char)inputStreamReader.read();
            }
        }catch (IOException e){

        }
        return messageIn;
    }

    public void writeResponse(){
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(this.clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String messageOut = scanner.next();
            while (scanner.hasNext()) {
                dataOutputStream.writeBytes(messageOut);
            }
            dataOutputStream.flush();
            System.out.println(messageOut);
        }catch (IOException e){

        }
    }
}
