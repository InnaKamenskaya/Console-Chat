import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Kamenskaya on 16.05.2016.
 */
public class ReadMessage implements Runnable {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ReadMessage(BufferedReader in){
        this.reader = in;
    }

    @Override
    public void run(){
        try{
            while (true){
                String s = reader.readLine();
                System.out.println(s);
            }
        }
        catch (IOException e){
            System.err.println("Error I/O (3)");
        }
    }
}
