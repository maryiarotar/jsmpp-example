package org.example.server.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MySqlRepository {

    Logger logger = LoggerFactory.getLogger(MySqlRepository.class);

    public boolean insert(String message){

        Date date = new Date(System.currentTimeMillis());
        String date_str = new SimpleDateFormat("yy:MM:dd HH:mm:ss").format("");

        String query = "INSERT INTO messages (message, date) VALUES ('"
                + message + "' , " + date_str + ");";

        logger.info("new statements is creating...query={" + query + "}");
        try {

            Connection connection = new DbConfig().getConnection();

            Statement statement = connection.createStatement();

            boolean result = statement.execute(query);

            logger.info("Query is executed, status=", result);
            return result;

        } catch (SQLException e) {
            logger.error("SQL exception when making insert query! : {}", e.getMessage());
        }
        return false;
    }


}
