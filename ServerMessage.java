import java.io.*;
public class ServerMessage extends Thread
{
    BufferedReader reader;
    Chat chat;
    String clientID;
    public ServerMessage(Chat c,BufferedReader reader1,String cID){
        chat = c;
        reader = reader1;
        clientID = cID;
    }

    public void run(){
        while(true){
            try{
                chat.text = reader.readLine();
                chat.value++;
                chat.lastUp = clientID;
            }catch(Exception e){
                //e.printStackTrace();
            }
        }
    }
}
