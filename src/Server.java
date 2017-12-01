
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kamenskaya on 15.05.2016.
 */
public class Server {
    public static final int PORT = 1234;
    public static Clients clients;
    public static LastMessage lastMessage;

    public static void main(String[] args) throws IOException{
        ServerSocket s = new ServerSocket(PORT);
        clients = new Clients();
        lastMessage = new LastMessage();
        System.out.println("Started: " + s);
        try {
            while (true){
                Socket socket = s.accept();
                try {
                    System.out.println("Connected: " + socket);
                    clients.addUser(new ServerOne(socket));
                   // System.out.println("типа сообщение " + lastMessage ); // попробуй еще раз
                }
                catch (IOException e){
                    System.out.println("Closing: " + socket);
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}

