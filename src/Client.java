import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client {
    private static InetAddress address;
    private static String userName;

    public static void main(String[] args){

        BufferedReader bufred = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Your name: ");
        try {
            userName = bufred.readLine();
            System.out.println("Welcome " + userName + "!");
        }
        catch (IOException ex){
            System.err.println("Error I/O (2)");
        }
        try {
            address = InetAddress.getByName("localhost");
        }
        catch (UnknownHostException ex1){
            System.err.println("Unknown address");
        }
        System.out.println("Address " + address);
        try {
            Socket socket = new Socket(address, Server.PORT);
            try {
                System.out.println("Socket: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                out.println(userName);
                Thread thread = new Thread(new ReadMessage(in));
                thread.start();

                while (true){
                    String msg = bufred.readLine();
                    Message message = new Message(userName, msg, new Date());
                    oos.writeObject(message);
                }


            }
            finally {
                System.out.println("Closing " + socket);
                socket.close();
            }
            }
            catch (IOException exs){
                System.err.println("Can't connect to server");
        }
    }
}