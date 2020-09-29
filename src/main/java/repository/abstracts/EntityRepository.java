package repository.abstracts;
import model.abstracts.Entity;
import util.pool.DBCPPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EntityRepository<T extends Entity> {

    default Connection getConnection() throws SQLException {
        return DBCPPool.getInstance().getConnection();
    }

    //create
    long add(T entity);

    //read
    T getById(long id);
    List<T> getAll();

    //update
    boolean edit(T entity);

    //delete
    boolean remove(T entity);
}