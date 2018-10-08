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
            return "ERROR";
        }
        return messageIn;
    }

    public void writeResponse(Socket socket){
        try{
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String outgoingMessage = scanner.nextLine();
            dataOutputStream.writeBytes(outgoingMessage);
            dataOutputStream.flush();
            System.out.println("insert any number to continue");
            while (!scanner.hasNextByte()){
                scanner.next();
            }
            System.out.println("message sent");
        }catch (IOException e){

        }
    }
}
