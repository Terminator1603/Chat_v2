import java.net.*;
import java.io.*;
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
                //try{
                    processText(text,socket,writer);
                //}catch(Exception e){
                //}
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

    static void processText(String text, Socket s,PrintWriter p) throws Exception{
        if(text.equals("/c")){
            System.out.print('\u000C');
            System.out.println("Enter a number");
        }else if(text.equals("/e")){
            System.out.print('\u000C');
            s.close();
            System.exit(0);
        }else if(text.length()>5&&(text.substring(0,5).equals("/open")||text.substring(0,5).equals("/join"))){
            p.println(text);
        }else if(text.equals("/wot")){
            System.out.println("(ಠ_ಠ)");
        }else{
            p.println(text);
        }
    }
}

