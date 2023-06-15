package server.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.resources.cldr.lo.CurrencyNames_lo;

import java.io.FileInputStream;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DbConfig {

    private static Logger logger = LoggerFactory.getLogger(DbConfig.class);
    private static Connection conn = null;
    private static String url, name, user, password;

    public DbConfig() {


        Properties prop = new Properties();


        try {
            prop.load(new FileInputStream("./config.properties"));
            url = prop.getProperty("mysql.db.url");
            name = prop.getProperty("mysql.db.dbname");
            user = prop.getProperty("mysql.db.username");
            password = prop.getProperty("mysql.db.password");

            String driver = prop.getProperty("db.driver.name");
            Class.forName(driver).newInstance();
            logger.info("properties file successfully read! :: {config.properties}");

            //or "jdbc:mysql://localhost/test?user=root&password=123"
            conn = DriverManager.getConnection( url+name, user, password );
            logger.info("successfully connected to DB : {}", url+name);



        } catch (SQLException e) {
            logger.error("Exception while connecting to Database :: {}\n, VendorError={}",
                    e.getMessage(), e.getErrorCode());
        }
        catch (Exception e) {
            logger.error("Error in reading config.properties! :: {}", e.getMessage());
        }
    }


    public Connection getConnection(){
        return conn;
    }


//    private void createTableMessages(){
//
//        String query_create = "CREATE TABLE IF NOT EXISTS table_name( " +
//
//
//        conn.createStatement();
//
//    }


}
