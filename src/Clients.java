import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Kamenskaya on 15.05.2016.
 */
public class Clients {

    private ArrayList<ServerOne> list = new ArrayList<>();
    private int count = 0;

    public void addUser(ServerOne client){
        list.add(client);
        count++;
    }

    public void distribution(Socket s, String msg){
        Iterator<ServerOne> it = list.iterator();
        while (it.hasNext()){
            ServerOne element = it.next();
            if(!((element.getSocket()).equals(s))) {
                element.sendMessage(msg);
            }
        }
    }
}
