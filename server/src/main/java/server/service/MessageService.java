package server.service;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.db.Repository;
import server.db.RepositoryImpl;
import server.db.dto.MessageDto;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

//public class MessageService extends RepositoryImpl<String, MessageDto> {

public class MessageService {

    Repository<String, MessageDto> repository;

    @Inject
    public MessageService(Repository repository){
        this.repository = repository;
    }

    Logger logger = LoggerFactory.getLogger(MessageService.class);

    public boolean insertMessage(MessageDto messageDto){

        String message = "'" + messageDto.getMessage() + "'";
        String date_str = "'" + messageDto.getDate() + "'";

        boolean result = false;
        try {
            result = repository.insert("messages", new String[]{"message", "date"}, new String[]{message, date_str});
            logger.info("Message inserted!");
        } catch (SQLException e) {
            logger.error("Message hasn't inserted! exception:: {}", e.getMessage());
        }

        return result;

    }


    public boolean getAllMessages(){ return false;}

    public boolean getMessageById(){ return false;}

    public boolean deleteById(){ return false;}

    public boolean deleteAll(){ return false;}



}
