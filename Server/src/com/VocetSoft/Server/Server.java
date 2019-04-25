package com.VocetSoft.Server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.VocetSoft.ChatApp.Main;


public class Server
{

    private static Socket socket = null;
    ServerSocket serverSocket = null;
    DataInputStream inputStream = null;
    DataOutputStream outputStream = null;

    public Server(int port)
    {
        /**final int port2 = port;
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    serverSocket = new ServerSocket(port2);
                    socket = serverSocket.accept();
                    Main.showMsg("Connection successful");
                    socket.setKeepAlive(true);
                } catch (IOException e)
                {
                    Main.showMsg("\nFrom Client :");
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(r,"");
        thread.start();**/

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            Main.showMsg("Connection successful");
            socket.setKeepAlive(true);
        } catch (IOException e)
        {
            Main.showMsg("\nFrom Client :");
            e.printStackTrace();
        }

    }

    public void chat()
    {
        String msg = "Null";
        //String inComing = "Null";
        Scanner scanner = new Scanner(System.in);

        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e)
        {
            Main.showMsg("\nFrom server Chat :");
            e.printStackTrace();
        }


        Main.showMsg("\nEnter message to send to the Server - Enter 'Exit' to end this chat");
        while(!socket.isClosed() || !msg.equals("Exit"))
        {
            System.out.println("> ");
            msg = scanner.next();
            try {
                outputStream.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void msgListener() {

        String inComing = null;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            Main.showMsg("\nFrom server Chat :");
            e.printStackTrace();
        }

        while (!socket.isClosed()) {
            try {
                inComing = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client > " + inComing);
        }

    }

    public static Socket getServerSocket()
    {
        return socket;
    }

}
