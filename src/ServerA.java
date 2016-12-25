import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alina on 21.12.16.
 */
public class ServerA {
    final int PORT = 3000;
    ArrayList<Connection> connections;
    Map onlines;
    Map onlinesFrames;


    public ServerA() throws IOException{
        connections = new ArrayList<>();
        onlines = new HashMap();
        onlinesFrames = new HashMap();

        go();
    }

    public void go() throws IOException{
        ServerSocket s1 = new ServerSocket(PORT);
        while(true){
            Socket client = s1.accept();
            connections.add(new Connection(this, client, connections, onlines, onlinesFrames));

        }
    }
    public static void main(String[] args) throws IOException{
        ServerA server = new ServerA();
    }
}
