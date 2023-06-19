import client.Client;
import server.Server;
import server.db.RepositoryImpl;
import server.db.dto.MessageDto;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException {

        RepositoryImpl<Long, MessageDto> repo = new RepositoryImpl<>();
        try {
            repo.getById("messages", 1L);
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        System.setProperty("logback.configurationFile", "/home/maryia/projects/jsmpp-example/logback.xml");
//
//
//        Server server = new Server();
//
//        Thread th = new Thread(server);
//        th.start();
//
//        Client cl = new Client();
//        cl.sendFromClient("12345");

    }
}