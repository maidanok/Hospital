package by.hospital.DAO.mysql.interfaces;

import by.hospital.DAO.GenericDAO;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface GenericDaoForSickList extends GenericDAO<SickList, Integer> {

    List<SickList> FindByCondition(String condition) throws PersistentException;

}