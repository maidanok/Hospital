package by.hospital.DAO;

import by.hospital.DAO.conditions.Condition;
import by.hospital.domain.Entity;
import by.hospital.exception.PersistentException;


import java.util.List;

/**
 * Created by Admin on 08.04.2017.
 */
public interface GenericDAO<Type extends Entity<PrimaryKey>, PrimaryKey extends Integer> {


    Type create() throws PersistentException;

    Type persist(Type entity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Type entity) throws PersistentException;

    Type getByPrimaryKey(PrimaryKey primaryKey) throws PersistentException;

    List<Type> getAll() throws PersistentException;

    List<Type> FindByCondition(Condition condition) throws PersistentException;


}
