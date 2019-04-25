package com.VocetSoft.ChatApp;


import java.util.Scanner;
import com.VocetSoft.Client.Client;
import com.VocetSoft.Server.Server;

public class Main {

    public static void main(String[] args)
    {
        String cmd = null;
	    Scanner scanner = new Scanner(System.in);
	    showMsg("Choose 'Server' or 'Client'");
	    cmd = scanner.next();
	    Server server = null;
        Client client = null;

	    if (cmd.equals("Server"))
        {
            showMsg("Server selected");
            showMsg("Enter port");
            int port = scanner.nextInt();
            showMsg("Listening out for a client.....");
            server = new Server(port);

            final Server server2 =  server;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    server2.msgListener();
                }
            };
            Thread t = new Thread(r,"");
            t.start();
            server.chat();
        }

        if (cmd.equals("Client"))
        {
            showMsg("Client Selected");
            showMsg("Enter port");
            int port = scanner.nextInt();
            showMsg("Enter host's address");
            String hostName = scanner.next();
            client = new Client(port,hostName);
            final Client client2 = client;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    client2.msgListener();
                }
            };
            Thread t = new Thread(r,"");
            t.start();
            client.chat();
        }

    }

    public static void showMsg(String msg)
    {
        System.out.println(msg);
    }
}
