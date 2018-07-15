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
