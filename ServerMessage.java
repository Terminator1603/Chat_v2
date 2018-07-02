import java.io.*;
public class ServerMessage extends Thread
{
    BufferedReader reader;
    chat chat;
    public ServerMessage(chat c,BufferedReader reader1){
        chat = c;
        reader = reader1;
    }
}
