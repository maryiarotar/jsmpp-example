import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import server.db.Repository;
import server.db.RepositoryImpl;
import server.db.dto.MessageDto;
import server.service.MessageService;

import java.sql.SQLException;

public class RepositoryTest {

    private Injector injector;

    @Before
    public void SetUp() throws Exception{
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure(){

                bind(Repository.class).to(RepositoryImpl.class);

            }

        });
    }

    @After
    public void tearDown() throws Exception{
        injector = null;
    }

    @Test
    public void testInsertion() throws SQLException {

        MessageDto dto = MessageDto.createInstance(1l, "abcd", "2023-06-19 16:07:00");

        String message = "'" + dto.getMessage() + "'";
        String date_str = "'" + dto.getDate() + "'";

        RepositoryImpl repository = Mockito.mock(server.db.RepositoryImpl.class);
//        Mockito.when(repository.insert("messages", new String[]{"message", "date"}, new String[]{message, date_str}))
//                        .thenReturn(true);

        Mockito.when(repository.insert(Mockito.any(), Mockito.any(),  Mockito.any())).thenReturn(true);

        //MessageService service = injector.getInstance(MessageService.class);
        //MessageService service = injector.getInstance(MessageService.class);
        //service.insertMessage(dto);
        //Assert.assertTrue(service.insertMessage(dto));
        Assert.assertTrue(repository.insert("messages", new String[]{"message", "date"}, new String[]{message, date_str}));
    }

}
