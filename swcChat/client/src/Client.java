import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static boolean isConnected;
    public static Socket socket;
    public static String userName;

    public Client(Socket socket){
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
         Client client = new Client(socket = new Socket("localhost", 80));
         client.startClient();
    }

    public void startClient() throws InterruptedException {
        System.out.println(ANSI_RED+"you're connected"+ANSI_RESET);
        isConnected = true;
        ReadingThread readingThread = new ReadingThread();
        WritingThread writingThread = new WritingThread();
        userName = getUserNameFromClient();
        writingThread.start();
        System.out.println("You will appear on the chat list as: " + ANSI_RED+ userName+ANSI_RESET);
        readingThread.start();
        Thread.sleep(500);

        System.out.println("Start chatting now!");
        System.out.println(ANSI_RED+"type \"EXIT\" to disconnect chat! :)"+ANSI_RESET);
        System.out.println(ANSI_BLUE+"type: \"PM name message\" to send private message"+ANSI_RESET);

        while (isConnected){
            Thread.sleep(100);
        }
        writingThread.interrupt();
        readingThread.interrupt();
        System.exit(-1);

    }

    public static String getUserNameFromClient(){
        Scanner scanner = new Scanner(System.in);
         System.out.println("type in your username to get it started:");
         String userName = scanner.nextLine();

        return userName;
    }
}
