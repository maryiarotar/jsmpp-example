import client.Client;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import server.Server;
import server.configs.AppInjector;
import server.db.Repository;
import server.db.RepositoryImpl;
import server.db.dto.MessageDto;
import server.service.MessageService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Injector injector = Guice.createInjector(new AppInjector());
//        Repository repository = injector.getInstance(RepositoryImpl.class);
//
//        MessageService service = new MessageService(repository);
//
//        MessageDto dto = MessageDto.parseToMessage(new byte[]{});
//
//        System.out.println(service.getById(2l).getMessage());

//        System.setProperty("logback.configurationFile", "/home/maryia/projects/jsmpp-example/logback.xml");
//

        Server server = new Server();

        Thread th = new Thread(server);
        th.start();

        //todo сделать отсылку смс от клиента в цикле 
        Client cl = new Client();
        cl.sendFromClient("12345");
        Thread.sleep(15000);
        Client cl2 = new Client();
        cl2.sendFromClient("Abcs");
        Client cl3 = new Client();
        Thread.sleep(15000);
        cl3.sendFromClient("ytueyueu");
    }
}