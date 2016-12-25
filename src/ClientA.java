import java.io.*;
import java.net.Socket;

/**
 * Created by alina on 21.12.16.
 */
public class ClientA {
    int port = 3000;
    String host = "localhost";
    Socket socket;
    InputStream is;
    int x;

    public ClientA() throws IOException {
        socket = new Socket(host, port);
        final Reader from_server=new InputStreamReader(socket.getInputStream());
        PrintWriter to_server = new PrintWriter(socket.getOutputStream());
        BufferedReader from_user =
                new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter to_user = new PrintWriter(System.out, true);
        to_user.println("Установлено подключение к " + socket.getInetAddress() +
                ":" + socket.getPort());
        Thread t = new Thread() {
            public void run() {
                char[] buffer = new char[1024];
                int chars_read;
                try {
                    while((chars_read = from_server.read(buffer)) != -1) {
                        for(int i = 0; i < chars_read; i++) {
                            if (buffer[i] == '\n') to_user.println();
                            else to_user.print(buffer[i]);
                        }
                        to_user.flush();
                    }
                }catch (IOException e) { to_user.println(e); }
                to_user.println("Сервер закрыл подключение.");
                System.exit(0);
            }
        };
        t.setPriority(Thread.currentThread().getPriority() + 1);
        t.start();
        String line;
        while((line = from_user.readLine()) != null) {
            to_server.print(line + "\n");
            to_server.flush();
        }
        socket.close();
        to_user.println("Подключение закрыто клиентом.");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException{
        ClientA client = new ClientA();
    }
}
