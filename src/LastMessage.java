import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by Kamenskaya on 15.05.2016.
 */
public class LastMessage {
    private LinkedList<String> list = new LinkedList<>();

    public void add(String msg){
        if (list.size()<10){
            list.add(msg);
        }
        else {
            list.addLast(msg);
            list.removeFirst();
        }
    }

    public void send(PrintWriter out){
        for (String t : list){
            out.println(t);
        }
    }
}