import java.io.*;
public class MessageReceive extends Thread
{
    BufferedReader reader;
    public MessageReceive(BufferedReader reader1)
    {
        reader = reader1;
    }
    public void run(){
        while(true){
        try{
          
            Thread.sleep(100);
            System.out.println(reader.readLine());
        }catch(Exception e){
        }
    }
    }
}
