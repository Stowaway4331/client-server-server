package com.jeremie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IntermediateServer {
  public static void main(String[] args) {
    try{

      ServerSocket serverSocket = new ServerSocket(5000);
      Socket socketA = serverSocket.accept();
      System.out.println("Client Connected");
      BufferedReader input = new BufferedReader(new InputStreamReader(socketA.getInputStream()));
      PrintWriter output = new PrintWriter(socketA.getOutputStream(), true);

      Socket socketB = new Socket("localhost", 4412);
      
      while (true) {

        String msgFromClient="";
        String msgFromServer="";
        msgFromClient = input.readLine();
        
        try {
          
          BufferedReader recieveFromServer = new BufferedReader(new InputStreamReader(socketB.getInputStream()));
          PrintWriter sendToServer = new PrintWriter(socketB.getOutputStream());

          try {
            
            sendToServer.println(msgFromClient);
            if(msgFromClient.equals("exit")){
              break;
            }
            msgFromServer = recieveFromServer.readLine();
            
            output.println(msgFromServer);
              

          } catch (IOException e) {
            //TODO: handle exception
            System.out.println("Error occured 1: "+e.getMessage());
            e.printStackTrace();
          }

        } catch (IOException e) {
          //TODO: handle exception
          System.out.println("Error occured 2: "+e.getMessage());
          e.printStackTrace();
        }
        finally {
          socketB.close();
        }
      }

    } 
    
    catch(Exception e){
      // e.printStackTrace();
      System.out.println("Server exception " + e.getMessage());
    }
  }
}
