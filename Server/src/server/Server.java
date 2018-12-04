package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



// Server class 
public class Server {

    ServerSocket ss;
    static ArrayList<ServerConnection> connection = new ArrayList<ServerConnection>();

    public static void main(String[] args) throws IOException {
        
        new Server();
    }
    
    public Server(){
        try {
            ss = new ServerSocket(8001);
            while(true){
                Socket s = ss.accept();
                ServerConnection sc = new ServerConnection(s, this);
                sc.start();
                connection.add(sc);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}