
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    try {

      ServerSocket serverSocket = new ServerSocket(4412);
      Socket socketA = serverSocket.accept();
      System.out.println("Client Connected");
      BufferedReader input = new BufferedReader(new InputStreamReader(socketA.getInputStream()));
      PrintWriter output = new PrintWriter(socketA.getOutputStream(), true);

      while (true) {

        String msgFromClient = "";
        msgFromClient = input.readLine();
        System.out.println("Message from Intermediate Server - " + msgFromClient);
        if (msgFromClient.equals("exit")) {
          break;
        }
        output.println(msgFromClient);

      }
      serverSocket.close();

    }

    catch (Exception e) {
      // e.printStackTrace();
      System.out.println("Server exception " + e.getMessage());
    }
  }
}
