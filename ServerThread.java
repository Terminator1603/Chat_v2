import java.io.*;
import java.net.*;
import java.util.*;
 
public class ServerThread extends Thread {
    private Socket socket;
    Chat currentChat;
    ChatManager chats;
    Number num;
    public ServerThread(Socket socket,ChatManager chats1) {
        this.socket = socket;
        chats = chats1;
        currentChat = chats.get("lobby");
        System.out.println(currentChat.name);
        num =new Number();
        num.i = 0;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            String clientID = reader.readLine();
            System.out.println(clientID+" has connected");
            ServerMessage sm = new ServerMessage(chats,num,reader,writer,clientID);
            sm.start();
            while(true){
                if((num.lastval!=chats.get(num.i).value)&&(chats.get(num.i).text.length()!=0)&&(!clientID.equals(chats.get(num.i).lastUp))){
                    if(!clientID.equals(chats.get(num.i).lastUp)){//Don't remove this even tho it seems redundant
                        writer.println(chats.get(num.i).lastUp+": "+chats.get(num.i).text);
                        num.lastval = chats.get(num.i).value;
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
