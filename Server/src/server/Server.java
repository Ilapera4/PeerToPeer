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
    static int ptr = 0;
    static ArrayList<ServerConnection> connection = new ArrayList<ServerConnection>();
    static Socket con[] = new Socket[10];

    public static void main(String[] args) throws IOException {
        
        new Server();
    }
    
    public Server(){
        try {
            ss = new ServerSocket(8001);
            while(true){
                Socket s = ss.accept();
                System.out.println("A new client is connected : " + (ptr+1)+" client(s) connected");
                ServerConnection sc = new ServerConnection(s, this);
                sc.start();
                connection.add(sc);
                System.out.println(s+" is the port");
                con[ptr] = s;
                ptr++;
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}