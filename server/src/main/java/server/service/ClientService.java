package server.service;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.db.Repository;
import server.db.dto.MessageDto;

import java.sql.ResultSet;

import server.db.dto.ClientDto;

public class ClientService {

    private static final String tableName = "client";

    Repository<Long, MessageDto> repository;

    @Inject
    public ClientService(Repository repository){
        this.repository = repository;
    }

    Logger logger = LoggerFactory.getLogger(MessageService.class);

    public ClientDto getById(Long id){

        try {
            ResultSet rs = repository.getById(tableName, id);
            if (rs.next()) {
                return ClientDto.parseToClientDto(rs);
            }

        } catch (Exception e) {
            logger.warn("Sql error happened! :: {}", e.getMessage());
        }
        return null;
    }


}
