package by.hospital.dao;

import by.hospital.dao.conditions.Condition;
import by.hospital.domain.Entity;
import by.hospital.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class AbstractJDBCDao<Type extends Entity<PrimaryKey>, PrimaryKey extends Integer> implements GenericDAO<Type, PrimaryKey> {
    private Logger logger = Logger.getLogger(AbstractJDBCDao.class);
    protected Connection connection;

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    //возвращает sql название поля первичного ключа
    protected abstract String getPrimaryKeyQuery();

    //возвращает sql запрос для получения всех записей
    protected abstract String getSelectedQuery();

    //возвращает sql запрос для вставки новой записи в базу
    protected abstract String getCreateQuery();

    //возвращает sql запрос для обновления записи
    protected abstract String getUpdateQuery();

    //возвращает sql запрос для удаления записи из базы данных
    protected abstract String getDeleteQuery();

    //разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet
    protected abstract List<Type> parseResultSet(ResultSet resultSet) throws PersistentException;

    /*
    устанавливает аргументы insert запроса в соответствии со значение полей object
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, Type object) throws PersistentException;

    /*
    устанавливает аргументы update запроса в соответствии со значение полей object
    */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, Type object) throws PersistentException;

    public Type persist(Type entity) throws PersistentException {
        if (!entity.getPrimaryKey().equals(0)) {
            throw new PersistentException("Object is already persist.");
        }
        Type persistInstanse;
        String sql = getCreateQuery();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, entity);
            int count = statement.executeUpdate();
            statement.close();
            if (count != 1) {
                throw new PersistentException("On persist modify more then 1 record " + count);
            }
        } catch (Exception e) {
            logger.error("Error persist" + e.getLocalizedMessage());
        }

        //получаем только что вставленную запись
        sql = getSelectedQuery() + " WHERE " + getPrimaryKeyQuery() + " = LAST_INSERT_ID();";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Type> list = parseResultSet(resultSet);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistentException("Exception on findByPrimaryKey new persist data. " + list.size());
            }
            persistInstanse = list.iterator().next();
            statement.close();
        } catch (Exception e) {
            logger.error("Error persist" + e.getLocalizedMessage());
            throw new PersistentException(e);
        }

        return persistInstanse;
    }

    @Override
    public void update(Type entity) throws PersistentException {

        String sql = getUpdateQuery();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            prepareStatementForUpdate(statement, entity);

            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistentException("On update modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            logger.error("Error update " + e.getLocalizedMessage());
        }
    }

    @Override
    public void delete(Type entity) throws PersistentException {
        String sql = getDeleteQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, entity.getPrimaryKey());
            int cont = statement.executeUpdate();
            if (cont != 1) {
                throw new PersistentException("On delete modify more then 1 record: " + cont);
            }
            statement.close();
        } catch (Exception e) {
            logger.error("Error delete " + e.getLocalizedMessage());
            logger.error("\n" + sql);
        }
    }

    @Override
    public Type getByPrimaryKey(Integer primaryKey) throws PersistentException {
        List<Type> list;
        String sql = getSelectedQuery();
        sql += " WHERE " + getPrimaryKeyQuery() + " = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, primaryKey);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
            statement.close();
        } catch (Exception e) {
            logger.error("Error getByPrimaryKey " + e.getLocalizedMessage());
            logger.error("\n" + sql);
            throw new PersistentException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            logger.info("more than one record");
        }
        return list.iterator().next();
    }

    @Override
    public List<Type> getAll() throws PersistentException {
        List<Type> list;
        String sql = getSelectedQuery() + " ;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
            statement.close();
        } catch (Exception e) {
            logger.error("Error getAll " + e.getLocalizedMessage());
            logger.error("\n" + sql);
            throw new PersistentException(e);
        }
        return list;
    }

    public List<Type> FindByCondition(Condition condition) throws PersistentException {
        List<Type> list = new ArrayList<>();
        String sql = getSelectedQuery();
        sql += condition.getValue();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
            statement.close();
        } catch (Exception e) {
            logger.error("Error FindByCondition " + e.getLocalizedMessage());
            logger.error("\n" + sql);
            throw new PersistentException(e);
        }
        return list;
    }


    //с такой конвертацией не выдает NullPointerException
    //и адекватно осуществляет вставку записи где дата равна null
    protected java.sql.Date convert(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
