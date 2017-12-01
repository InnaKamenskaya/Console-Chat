/**
 * Created by Kamenskaya on 15.05.2016.
 */
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Kamenskaya on 15.05.2016.
 */
public class ServerOne extends Thread {

    private Socket socket;

    // private BufferedReader reader; //  так было
    private BufferedReader in; // так стало

    private PrintWriter print;
    private ObjectInputStream ois;
    private String userName;

    public ServerOne(Socket s) throws IOException{
        socket = s;

        // reader = new BufferedReader(new InputStreamReader(System.in));// так было
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // так стало

        print = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
       ois = new ObjectInputStream(socket.getInputStream());
        Server.lastMessage.send(print);
        start();
    }

    public void run(){
        try {

            // userName = reader.readLine(); так было
            userName = in.readLine();// так стало

            Server.clients.distribution(socket, "Joined: " + userName);

            while (true){
                Message message = (Message) ois.readObject();
                Server.lastMessage.add(message.getMessage());
                System.out.println("Socket: " + socket + " Message: " + message.getMessage());
                DateFormat dateFormat = new SimpleDateFormat("hh:mm");
                String complite = message.getName() + " (" + dateFormat.format(message.getDate()) + ") : " + message.getMessage();
                Server.clients.distribution(socket, complite);
            }
        }
        catch (IOException e){
            System.err.println("Error I/O (1)");
        }
        catch (ClassNotFoundException e){
            System.err.println("Class not found");
        }
        finally {
            System.out.println("Closing: " + socket);
            Server.clients.distribution(socket, "Leave: " + userName);
            try {
                socket.close();
            }
            catch (IOException e){
                System.err.println("Socket not closed");
            }
        }
    }

    public void sendMessage(String message){
        print.println(message);
    }

    public Socket getSocket(){
        return socket;
    }
}
