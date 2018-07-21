import java.util.*;
public class ChatManager{
    ArrayList<Chat> chats = new ArrayList<Chat>(0);
    public ChatManager(){
        chats.add(new Chat("lobby"));
    }
    public void add(Chat c){
        chats.add(c);
    }
    public Chat get(String s){
        for(int i=0;i<chats.size();i++){
            if(s.equals(chats.get(i).name)){
                return chats.get(i);
            }
        }
        return chats.get(0);
    }
    public int getIndex(String s){
        for(int i=0;i<chats.size();i++){
            if(s.equals(chats.get(i).name)){
                return i;
            }
        }
        return -1;
    }
    public Chat get(int index){
        return chats.get(index);
    }
}
