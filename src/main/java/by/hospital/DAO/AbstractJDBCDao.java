package by.hospital.DAO;

import by.hospital.domain.Entity;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public abstract class AbstractJDBCDao<Type extends Entity<PrimaryKey>, PrimaryKey extends Integer> implements GenericDAO<Type, PrimaryKey> {

    private DaoFactory<Connection> parentFactory;
    protected Connection connection;

    public AbstractJDBCDao(DaoFactory<Connection> parentFactory, Connection connection) {
        this.parentFactory = parentFactory;
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
/*        //сохраняем зависимости
        saveDependence(entity);*/

        //добавляем запись
        Type persistInstanse;
        String sql = getCreateQuery();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistentException("On persist modify more then 1 record " + count);
            }
        } catch (Exception e) {
            new PersistentException(e);
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
        } catch (Exception e) {
            throw new PersistentException(e);
        }

        return persistInstanse;
    }

    @Override
    public void update(Type entity) throws PersistentException {
/*        //сохраняем зависимости
        saveDependence(entity);*/

        String sql = getUpdateQuery();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            prepareStatementForUpdate(statement, entity);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistentException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistentException(e);
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
            throw new PersistentException(e);
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
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistentException("more than one record");
        }
        return list.iterator().next();
    }

    @Override
    public List<Type> getAll() throws PersistentException {
        List<Type> list;
        String sql = getSelectedQuery()+" ;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }

    public  List<Type> FindByCondition(String condition) throws PersistentException {
        List<Type> list;
        String sql = getSelectedQuery();
        sql += condition;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistentException(e);
        }
        return list;
    }



    //получить вложенный объект получается во время реализации конкретного класса
    //когда парсируем результат sql закроса мы в данный метод подставляем класс поля которое хотим получить и
    //ID который нам пришел в ответе parentFactory по классу найдет нам нужную фабрику
    //и по ID выполнит запрос getByPrimaryKey.
    protected Entity getDependence(Class<? extends Entity> dtoClass, Integer primaryKey) throws PersistentException {
        return parentFactory.getDao(connection, dtoClass).getByPrimaryKey(primaryKey);
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
