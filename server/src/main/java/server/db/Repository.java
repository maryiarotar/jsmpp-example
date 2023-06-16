package server.db;

import java.util.List;

public interface Repository<Key, Entity> {

    default Entity getById(Key key){

    };

    List<Entity> getAll();

    boolean insert(Entity entity);

    boolean insertAll(List<Entity> entities);

    boolean deleteById(Key key);

}
