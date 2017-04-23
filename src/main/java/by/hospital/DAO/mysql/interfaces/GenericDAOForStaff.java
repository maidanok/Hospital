package by.hospital.DAO.mysql.interfaces;

import by.hospital.DAO.GenericDAO;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Pasha on 13.04.2017.
 */
public interface GenericDAOForStaff extends GenericDAO<Staff,Integer>{

    List<Staff> getAllForField(Boolean field) throws PersistentException;

}
