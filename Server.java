import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        ArrayList<ServerThread> threads = new ArrayList<ServerThread>(0);
        ChatManager chats = new ChatManager();
        int i=0;
        int port = 6868;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);
            
            
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                threads.add(new ServerThread(socket,chats));
                threads.get(i).start();
                i++;
                //for(int x = 0;x<threads.size();x++){
                //    System.out.println(threads.get(x).getName());
                //}
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
