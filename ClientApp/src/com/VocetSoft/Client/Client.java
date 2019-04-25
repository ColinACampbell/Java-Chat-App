package com.VocetSoft.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.VocetSoft.ChatApp.Main;

public class Client
{
    static Socket  client = null;
    DataInputStream inputStream;
    DataOutputStream outputStream;

    public Client(final int port, final String hostName)
    {
        /**final int port2 = port;
        final String hostName2 = hostName;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(hostName2,port2);
                    Main.showMsg("Connection successful");
                    client.setKeepAlive(true);
                } catch (IOException e) {
                    Main.showMsg("\nFrom client :");
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(r,"Thread 1");
        thread.start();**/

        try {
            client = new Socket(hostName,port);
            Main.showMsg("Connection successful");
            client.setKeepAlive(true);
        } catch (IOException e) {
            Main.showMsg("\nFrom client :");
            e.printStackTrace();
        }
    }

    public void chat()
    {
        String msg = "Null";
        //String inComing = "Null";
        Scanner scanner = new Scanner(System.in);
        try {
            //inputStream = new DataInputStream(client.getInputStream());
            outputStream = new DataOutputStream(client.getOutputStream());
        } catch (IOException e)
        {
            Main.showMsg("\nFrom server Chat :");
            e.printStackTrace();
        }

        Main.showMsg("\nEnter message to send to the Server - Enter 'Exit' to end this chat");
        while(!msg.equals("Exit"))
        {
            System.out.println("> ");
            msg = scanner.next();
            try {
                outputStream.writeUTF(msg);
                //inComing = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void msgListener()
    {
        String inComing = null;
        try {
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e)
        {
            Main.showMsg("\nFrom server Chat :");
            e.printStackTrace();
        }

        while(!client.isClosed())
        {
            try {
                inComing = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server > " +inComing);
        }
    }


    public static Socket getClientSocket()
    {
        return client;
    }

}
