import java.io.*;
import java.net.*;

 
public class ServerThread extends Thread {
    private Socket socket;
    Chat c;
    public ServerThread(Socket socket,Chat chat) {
        this.socket = socket;
        c = chat;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String clientID = reader.readLine();
            System.out.println(clientID+" has connected");
            ServerMessage sm = new ServerMessage(c,reader,clientID);
            sm.start();
            int lastval=-1;
            while(true){
                if((lastval!=c.value)&&(c.text.length()!=0)&&(!clientID.equals(c.lastUp))){
                    if(!clientID.equals(c.lastUp)){//Don't remove this even tho it seems redundant
                        writer.println(c.lastUp+": "+c.text);
                        lastval = c.value;
                    }
                }
            } 

            //socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
