/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kepa
 */
public class ServerConnection extends Thread{
    
    Socket socket;
    Server server;
    DataInputStream din;
    DataOutputStream dout;
    Boolean shouldrun =true;
    
   ServerConnection(Socket socket, Server server){
        super("ServerConnectionThread");
        this.socket = socket;
    }

    
//    public void sendStringtoClient(String text){
//        try {
//            dout.writeUTF(text);
//            dout.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void sendStringtoAll(String text){
//        for(int index = 0;index<server.connection.size();index++){
//            ServerConnection sc = server.connection.get(index);
//            sc.sendStringtoClient(text);
//        }
//    }
   
      public void request(){
          for(int g = 0; g<server.con.length;g++){
              if(server.con[g].equals(this.socket)){
                  System.out.println("It Works!!");
                  System.out.println(this.socket);
          }
      }
      }
    
    @Override
    public void run(){
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            
            while(shouldrun){
                dout.writeUTF("What do you want to do? |Request| Type Exit to end connection.");
                while(din.available()==0){  //no input
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            String textin = din.readUTF();
            if (textin.toLowerCase().equals("exit")){
                System.out.println("Client "+this.socket+"sends exit..");
                System.out.println("Closing connection");
                this.socket.close();
                break;
            }else if(textin.toLowerCase().equals("request")){
                /* NEED TO COMEPLETE THIS CODE */
                request();
                System.out.println("Enter code for request method plz");
            }
            //sendStringtoAll(textin);
            }
            din.close();
            dout.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
