import client.Client;
import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setProperty("logback.configurationFile", "/home/maryia/projects/jsmpp-example/logback.xml");


        Server server = new Server();

        Thread th = new Thread(server);
        th.start();

        Client cl = new Client();
        cl.sendFromClient("qwew");

    }
}