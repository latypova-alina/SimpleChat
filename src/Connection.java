/**
 * Created by alina on 21.12.16.
 */
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.*;


class Connection implements Runnable {
    Socket client;
    Thread thread;
    Server server;
    ArrayList<String> names;
    ArrayList<Connection> connections;
    Map onlines;
    String client_name;
    Map onlinesFrames;
    Interface chatter_inface = new Interface();




    public Connection(Server server, Socket client, ArrayList<Connection> connections, Map onlines, Map onlinesFrames, ArrayList<String> names) throws IOException {
        this.client = client;
        this.server = server;
        this.connections = connections;
        this.onlines = onlines;
        this.onlinesFrames = onlinesFrames;
        this.names = names;


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

    public void depictOnlines(){
        Interface inf;
        Iterator iterator = onlinesFrames.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            inf = (Interface) pair.getValue();
            inf.addName(names);
        }
    }

    public void run() {
        Interface inface = new Interface();
        inface.start_working();
        while(true) {
            if ((client_name == null) && inface.userNameHasInput()) {
                client_name = inface.getClientName();
                if ((client_name == null)||(client_name.equals(""))) break;
                if (!loginExist(client_name)) {
                    inface.authorized();
                    onlines.put(client_name, client);
                    onlinesFrames.put(client_name, inface);
                    names.add(client_name);
                    depictOnlines();
                    inface.clear();
                   // break;
                } else {
                    inface.changeLabelText("Имя занято");
                    inface.clear();
                   // break;
                }
            } else if (client_name != null) {
                while (inface.nameAndInput()) {
                    String chatter = inface.getChatterName();
                    if (online(chatter)) {
                        String message = inface.getMessage();
                        client_name = findClientName(inface);
                        try {
                            chatter_inface = findChatterInterface(chatter);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        chatter_inface.messageFrom(client_name);
                        chatter_inface.setMessage(message);
                        inface.clear();
                        break;
                    } else if (chatter.equals("")){
                        inface.clear();
                        break;
                    } else if (!online(chatter)){
                        inface.changeLabelText("Нет такого чатера");
                        inface.clear();
                        break;
                    }
                }
            }
        }
    }
}