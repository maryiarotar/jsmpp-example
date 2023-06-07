package org.example;


import org.example.client.Client;
import org.example.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = new Server();

        Thread th = new Thread(server);
        th.start();

        Client cl = new Client();
        cl.sendFromClient("qwew");

    }
}