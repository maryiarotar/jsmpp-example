package server.db;

import com.google.inject.Guice;
import com.sun.javaws.IconUtil;
import server.db.dto.MessageDto;

import java.sql.*;
import java.util.List;

public class RepositoryImpl<Key, Entity> implements Repository<Key, Entity>{

    @Override
    public ResultSet getById(String table, Key key) throws Exception {

        Connection connection = DataSource.getConnection();
        //getting the name of id column
        ResultSet rs = connection.getMetaData().getColumns(null, null, table, null);
        rs.next(); //move cursor for the first column (it usually id columns) and get its full name;
        String idColumnName =  rs.getString("COLUMN_NAME");


        String query = "SELECT * FROM " + table + " WHERE " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if (key instanceof String) { preparedStatement.setString(1, (String) key); }
        if (key instanceof Long) { preparedStatement.setLong(1, (Long) key); }
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        return result;
    }

    @Override
    public List<Entity> getAll() {
        return null;
    }

    @Override
    public boolean insert(Entity entity) {
        return false;
    }

    @Override
    public boolean insert(String table, String[] columns, String[] values) throws SQLException {

        String query =  "INSERT INTO " + table + " (" +
                String.join(",", columns) + ") VALUES (" + String.join(",", values)
                + ");";

        //System.out.println(query);

        Connection connection = DataSource.getConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute(query);
        return result;
    }

    @Override
    public boolean insertAll(List<Entity> entities) {
        return false;
    }

    @Override
    public boolean deleteById(Key key) {
        return false;
    }

}
