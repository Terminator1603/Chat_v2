import java.net.*;
import java.io.*;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input
 * from the user and prints echoed message from the server.
 *
 * @author www.codejava.net
 */
public class Client {

    public static void main(String[] args) {
        String clientID;
        if(args.length==0){
            clientID = idGenerator(3);
        }else{
            clientID = args[0];
        }
        String hostname = "localhost";
        int port = 6868;
        try (Socket socket = new Socket(hostname, port)) {
            System.out.println(clientID);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(clientID);
            Console console = System.console();
            String text;
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            MessageReceive mr = new MessageReceive(reader);
            mr.start();
            do {
                text = console.readLine();

                writer.println(text);

                //String message = reader.readLine();
                //System.out.println(message);
            } while (!text.equals("bye"));

            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (Exception ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    public static String idGenerator(int n){
        String id = "";
        for(int i =0;i<n;i++){
            id += (char)(int)(Math.random()*(91-65)+65);
        }
        return id;
    }
}