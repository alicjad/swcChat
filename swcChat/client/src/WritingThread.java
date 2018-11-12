import java.io.DataOutputStream;
import java.util.Scanner;

public class WritingThread extends Thread {

    Client client;

    @Override
    public void run() {
        try{
            DataOutputStream dataOutputStream = new DataOutputStream(client.socket.getOutputStream());
            dataOutputStream.writeBytes(client.userName + "\n");
            dataOutputStream.flush();
            Scanner scanner = new Scanner(System.in);
            while (client.isConnected) {
                String outgoingMessage = scanner.nextLine() + "\n";
                dataOutputStream.writeBytes(client.userName + ": " + outgoingMessage);
                dataOutputStream.flush();
                sleep(100);
                if (outgoingMessage.equals("EXIT" + "\n")) {
                    System.out.println("disconnecting...");
                    this.client.isConnected = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
