import java.io.*;
public class ServerMessage extends Thread
{
    BufferedReader reader;
    ChatManager chats;
    String clientID;
    Number num;
    public ServerMessage(ChatManager c,Number n,BufferedReader reader1,String cID){
        chats = c;
        reader = reader1;
        clientID = cID;
        num = n;
    }

    public void run(){
        while(true){
            try{
                String s = reader.readLine();
                //System.out.println(s);
                if(s.length()>5&&s.substring(0,5).equals("/open")){
                    chats.add(new Chat(s.substring(6)));
                    num.i = chats.getIndex(s.substring(6));
                }else if(s.length()>5&&s.substring(0,5).equals("/join")){
                    num.i = chats.getIndex(s.substring(6));
                }else{
                    chats.get(num.i).text = s;
                    chats.get(num.i).value++;
                    chats.get(num.i).lastUp = clientID;
                }
                
            }catch(Exception e){
                //e.printStackTrace();
            }
        }
    }
}
