
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class IntermediateServer {
  public static void main(String[] args) {
    try {

      ServerSocket serverSocket = new ServerSocket(5000);
      Socket socketA = serverSocket.accept();
      System.out.println("Client Connected");
      BufferedReader input = new BufferedReader(new InputStreamReader(socketA.getInputStream()));
      PrintWriter output = new PrintWriter(socketA.getOutputStream(), true);

      Socket socketB = new Socket("localhost", 4412);
      BufferedReader recieveFromServer = new BufferedReader(new InputStreamReader(socketB.getInputStream()));
      PrintWriter sendToServer = new PrintWriter(socketB.getOutputStream(), true);

      while (true) {

        String msgFromClient;
        String msgFromServer;
        msgFromClient = input.readLine();
        System.out.println("Message from client - " + msgFromClient);

        try {

          sendToServer.println(msgFromClient);
          if (msgFromClient.equals("exit")) {
            break;
          }
          msgFromServer = recieveFromServer.readLine();
          System.out.println("Message received back from Server - " + msgFromServer);
          output.println(msgFromServer);

        } catch (IOException e) {
          // TODO: handle exception
          System.out.println("Error occured 1: " + e.getMessage());
          e.printStackTrace();
        }

      }
      socketB.close();
      serverSocket.close();
    }

    catch (Exception e) {
      // e.printStackTrace();
      System.out.println("Server exception " + e.getMessage());
    }
  }
}
