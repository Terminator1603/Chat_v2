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
                String s  = reader.readLine();
                if(s!=null){
                    System.out.println(s);
                }
            }catch(Exception e){
            }
        }
    }
}
