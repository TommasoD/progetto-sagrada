package it.polimi.ingsw.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 7777);
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void startClient() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        DataInputStream socketIn = new DataInputStream(socket.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(socket.getOutputStream());

        Scanner stdin = new Scanner(System.in);
        try {

            boolean done = false;

            while (!done) {
                String inputLine = stdin.nextLine();
                socketOut.writeUTF(inputLine);
                socketOut.flush();
                String socketLine = socketIn.readUTF();
                System.out.println(socketLine);
                if (socketLine.equals("Welcome " + inputLine)) done = true;

            }
        } catch (NoSuchElementException e) {
            System.out.println("Connection closed");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}