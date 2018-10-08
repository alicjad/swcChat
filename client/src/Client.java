import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 80);
        System.out.println("you're connected");
        System.out.println("type \"bye\" to disconnect chat! :)");
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String outgoingMessage = scanner.nextLine() +"\n";
            dataOutputStream.writeBytes(outgoingMessage);
            dataOutputStream.flush();
            if (outgoingMessage.equals("bye")) {
                socket.close();
                break;
            }
            String messageIn = getRequest(socket);
            System.out.println(messageIn);
        }
    }

    public static String getRequest(Socket socket) throws IOException {
        BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String messageIn = inputStreamReader.readLine();

        return messageIn;
    }
}
