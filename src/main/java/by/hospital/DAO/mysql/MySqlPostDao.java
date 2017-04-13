package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.Post;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 12.04.2017.
 */
public class MySqlPostDao  extends AbstractJDBCDao <Post, Integer>{

    public MySqlPostDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    private class PersistPost extends Post{
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }


    @Override
    public Post create() throws PersistentException {
        Post post = new Post();
        return persist(post);
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return " post_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT post_id, post_name FROM posts";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO posts (post_name) VALUES (?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE posts SET post_name = ? WHERE post_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM posts WHERE post_id = ?;";
    }

    @Override
    protected List<Post> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<Post> result = new ArrayList<Post>();
        try {
            while (resultSet.next()){
                PersistPost post = new PersistPost();
                post.setPrimaryKey(resultSet.getInt("post_id"));
                post.setPostName(resultSet.getString("post_name"));
                result.add(post);
            }
        }catch (Exception e){
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Post object) throws PersistentException {
        try {
            statement.setString(1,object.getPostName());
        }catch (Exception e){
            throw new PersistentException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Post object) throws PersistentException {
        try {
            statement.setString(1,object.getPostName());
            statement.setInt(2,object.getPrimaryKey());
        }catch (Exception e){
            throw new PersistentException(e);
        }
    }
}
