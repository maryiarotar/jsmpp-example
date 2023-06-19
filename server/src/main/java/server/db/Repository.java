package server.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Repository<Key, Entity> {

    ResultSet getById(String table, Key key) throws Exception;

    List<Entity> getAll();

    boolean insert(Entity entity);

    boolean insert(String table, String[] columns, String[] values) throws SQLException;

    boolean insertAll(List<Entity> entities);

    boolean deleteById(Key key);

}
