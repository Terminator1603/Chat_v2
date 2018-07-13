import java.net.*;
import java.io.*;
 
public class Client {
 
    public static void main(String[] args) {
        if (args.length < 2) return;
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String clientID = args[2];
        try (Socket socket = new Socket(hostname, port)) {
 
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
                text = console.readLine("Enter text: ");
                try{
                    checkFunc(text,socket);
                }catch(Exception e){}
                writer.println(text);
                //String message = reader.readLine();
 
                //System.out.println(message);
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    static void checkFunc(String text, Socket s) throws Exception{
        switch(text){
            case "/c":{
                System.out.print('\u000C');
                System.out.println("Enter a number");
                break;
            }
            case "/e":{
                System.out.print('\u000C');
                s.close();
                System.exit(0);
                break;
            }
            case "/wot":{
                System.out.println("(ಠ_ಠ)");
                break;
            }
        }
    }
}