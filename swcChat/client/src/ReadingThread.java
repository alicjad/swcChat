import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadingThread extends Thread {

    Client client;

    @Override
    public void run() {
        try {
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
            while (client.isConnected) {
                String messageIn = inputStreamReader.readLine();
                if (messageIn != null && !messageIn.equals((client.userName+":"))){
                    System.out.println(messageIn);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
