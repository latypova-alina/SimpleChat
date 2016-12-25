import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alina on 21.12.16.
 */
public class Server {
    final int PORT = 3000;
    ArrayList<Connection> connections;
    Map onlines;
    Map onlinesFrames;
    ArrayList<String> names;


    public Server() throws IOException{
        connections = new ArrayList<>();
        onlines = new HashMap();
        onlinesFrames = new HashMap();
        names = new ArrayList<>();

        go();
    }

    public void go() throws IOException{
        ServerSocket s1 = new ServerSocket(PORT);
        while(true){
            Socket client = s1.accept();
            connections.add(new Connection(this, client, connections, onlines, onlinesFrames, names));

        }
    }
    public static void main(String[] args) throws IOException{
        Server server = new Server();
    }
}
