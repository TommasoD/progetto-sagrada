package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket socket;
    private static ArrayList<String> playerList = new ArrayList<String>();
    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true) {
                out.println("Insert username");
                out.flush();
                String line = in.nextLine();
                //logout
                if (line.equals("quit")) {
                    break;
                } else {
                    if (playerList.contains(line))  {
                        out.println("Username " + line + " already used");
                        out.flush();
                    } else {
                        playerList.add(line);
                        this.username = line;
                        out.println("Welcome");
                        out.flush();

                        //inserimento mosse
                        while (true) {
                            out.println("Insert move");
                            out.flush();
                            line = in.nextLine();
                            if (line.equals("quit")) break;
                            ////player moves
                            ////use synchronized
                        }
                        break;
                    }
                }
            }



            playerList.remove(username);  ////aggiungere gestione caso solo offline, no logout
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}