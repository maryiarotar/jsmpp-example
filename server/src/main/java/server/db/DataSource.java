package server.db;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static Properties prop = new Properties();
    static {
        try {
            prop.load(new FileInputStream("./config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        config.setJdbcUrl(prop.getProperty("mysql.db.url", "default")
                + prop.getProperty("mysql.db.dbname", "default"));
        config.setUsername(prop.getProperty("mysql.db.username", "default"));
        config.setPassword(prop.getProperty("mysql.db.password", "default"));
        config.addDataSourceProperty("cachePrepStmts", prop.getProperty("mysql.db.cachePrepStmts", "false"));
        config.addDataSourceProperty("prepStmtCacheSize", prop.getProperty("mysql.db.prepStmtCacheSize", "0"));
        config.addDataSourceProperty("prepStmtCacheSqlLimit", prop.getProperty("mysql.db.prepStmtCacheSqlLimit", "0"));

        ds = new HikariDataSource(config);
    }

    private DataSource(){}

    public static void main(String[] args) {
        System.out.println(config.getJdbcUrl() + "    " + config.getUsername());
    }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
