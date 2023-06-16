package server.db;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class DataSource {

    private static Logger logger = LoggerFactory.getLogger(DataSource.class);
    private static final String propertiesFile = "./config.properties";
    private static HikariConfig config;
    private static HikariDataSource ds = null;

    static {
        config = new HikariConfig(propertiesFile);
        ds = new HikariDataSource(config);

        logger.info("Configuration for DB is set from ./application.properties! db_url = "
        + ds.getJdbcUrl() + ", username = " + ds.getUsername() + ", data source = " + ds.getDataSourceProperties());
    }

/*
    private static Properties prop = new Properties();
    static {
        try {
            prop.load(new FileInputStream("./config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config = new HikariConfig();
        config.setJdbcUrl(prop.getProperty("jdbcUrl", "default"));
        config.setUsername(prop.getProperty("username", "default"));
        config.setPassword(prop.getProperty("password", "default"));
        config.addDataSourceProperty("cachePrepStmts", prop.getProperty("dataSource.cachePrepStmts", "false"));
        config.addDataSourceProperty("prepStmtCacheSize", prop.getProperty("dataSource.prepStmtCacheSize", "0"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", prop.getProperty("dataSource.prepStmtCacheSqlLimit", "0"));

        ds = new HikariDataSource(config);
    }

*/


    private DataSource(){}

//    public static void main(String[] args) {
////        System.out.println(config.getJdbcUrl() + ", " + config.getUsername() + ", " + ds.getDataSourceProperties());
////
////        System.out.println(config.getMaxLifetime());
////        System.out.println(config.getIdleTimeout());
//
//
//        Date date = new Date(System.currentTimeMillis());
//        String date_str = new SimpleDateFormat("yy:MM:dd HH:mm:ss").format(date);
//
//        String query = "INSERT INTO messages (message, date) VALUES ('"
//                + "MY_MESSAGE" + "' , '" + date_str + "');";
//
//        logger.info("new statements is creating...query={" + query + "}");
//
//    }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static Properties loadProperties(String propertiesFile){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}
