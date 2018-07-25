import java.io.*;
public class ServerMessage extends Thread
{
    BufferedReader reader;
    ChatManager chats;
    String clientID;
    Number num;
    PrintWriter p;
    public ServerMessage(ChatManager c,Number n,BufferedReader reader1,PrintWriter print,String cID){
        chats = c;
        reader = reader1;
        clientID = cID;
        num = n;
        p = print;
    }

    public void run(){
        while(true){
            try{
                String s = reader.readLine();
                //System.out.println(s);
                if(s.length()>5&&s.substring(0,5).equals("/open")){
                    if(chats.getIndex(s.substring(6))==-1){
                        chats.add(new Chat(s.substring(6)));
                        num.i = chats.getIndex(s.substring(6));
                        p.println("Opening lobby \""+s.substring(6)+"\"");
                        p.println("Lobby: "+s.substring(6).toUpperCase());
                    }else{
                        num.i = chats.getIndex(s.substring(6));
                        p.println("Lobby already exists, joining \""+s.substring(6)+"\"");
                        p.println("Lobby: "+s.substring(6).toUpperCase());
                    }
                }else if(s.length()>5&&s.substring(0,5).equals("/join")){
                    int x = chats.getIndex(s.substring(6));
                    if(x!=-1){
                        num.i = x;
                        p.println("Joining \""+s.substring(6)+"\"");
                        p.println("Lobby: "+s.substring(6).toUpperCase());
                    }else{
                        p.println("Lobby doesn't exist");
                    }
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
