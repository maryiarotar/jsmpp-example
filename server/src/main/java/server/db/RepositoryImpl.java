package server.db;

import server.db.dto.MessageDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RepositoryImpl implements Repository<String, MessageDto>{

    @Override
    public MessageDto getById(String s) {
        return null;
    }

    @Override
    public List<MessageDto> getAll() {
        return null;
    }

    @Override
    public boolean insert(MessageDto messageDto) {

        try {
            Connection connection = DataSource.getConnection();

            Statement statement = connection.createStatement();

            boolean result = statement.execute(query);

            logger.info("Query is executed, status=", result);
            return result;

        } catch (SQLException e) {
            logger.error("SQL exception when making insert query! : {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean insertAll(List<MessageDto> messageDtos) {
        return false;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }
}
