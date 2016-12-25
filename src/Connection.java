/**
 * Created by alina on 21.12.16.
 */
import java.io.*;
import java.net.Socket;
import java.util.*;


class Connection implements Runnable {
    Socket client;
    Thread thread;
    Server server;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Connection> connections;
    Map onlines;
    String client_name;
    String answer;
    Map onlinesFrames;
    Interface chatter_inface = new Interface();




    public Connection(Server server, Socket client, ArrayList<Connection> connections, Map onlines, Map onlinesFrames) throws IOException {
        this.client = client;
        this.server = server;
        this.connections = connections;
        this.onlines = onlines;
        this.onlinesFrames = onlinesFrames;


        thread = new Thread(this);
        thread.start();

    }

    public boolean loginExist(String name){
        boolean exist = false;
        for (int i=0; i<connections.size(); i++) {
            if (connections.get(i).client == (Socket) onlines.get(name)) {
                exist = true;
                break;
            }
        }
        if (exist == true){
            return true;
        }else{
            return false;
        }
    }

    public boolean online(String chatter){
        if (onlines.get(chatter) != null){
            return true;
        }else{
            return false;
        }
    }



    public OutputStream findChatter(String chatter) throws IOException {
        if (online(chatter)) {
            Socket s = (Socket) onlines.get(chatter);
            return s.getOutputStream();
        }else{
            return null;
        }
    }

    public Interface findChatterInterface(String chatter) throws IOException{
        if (online(chatter)){
            Interface f = (Interface) onlinesFrames.get(chatter);
            return f;
        }else {
            return null;
        }
    }

    public String findClientName(Interface i){
        Iterator it = onlinesFrames.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue().equals(i)) {
                return (String) pair.getKey();
            }
        }
        return null;
    }

    public void run() {
        try {
            Interface inface = new Interface();
            inface.start_working();





            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            PrintWriter out = new PrintWriter(os);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean authorized = false;

            while(true) {
                //out.print("> ");
                //out.flush();
                while(inface.hasInput()){
                    line = inface.getCommand();
                    if (line == null) break;
                    StringTokenizer t = new StringTokenizer(line);
                    if (!t.hasMoreTokens()) continue;
                    String command = t.nextToken().toLowerCase();
                    if (command.equals("имя")) {
                        client_name = t.nextToken();
                        if (!loginExist(client_name)) {
                            inface.changeLabelText("OK");
                            inface.authorized();
                            onlines.put(client_name, client);
                            onlinesFrames.put(client_name, inface);
                            names.add(client_name);
                            inface.clear();
                            break;
                        } else {
                        //    out.print("Имя уже занято\n");
                            inface.changeLabelText("Имя занято");
                            inface.clear();
                            break;
                        }
                    }else if (command.equals("связаться")) {
                        String chatter = t.nextToken();
                        if (online(chatter)) {
                            String message = t.nextToken();
                            client_name = findClientName(inface);
                            chatter_inface = findChatterInterface(chatter);
                            //out = new PrintWriter(findChatter(chatter));
                            //out.print("Сообщение от: \n" + client_name + "\n" + message + "\n");
                            chatter_inface.changeLabelText("Сообщение от: \n" + client_name + "\n" + message + "\n");
                            inface.clear();
                            break;
                        } else {
                            //out.print("Нет такого чатера \n");
                            inface.changeLabelText("Нет такого чатера");
                            inface.clear();
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}