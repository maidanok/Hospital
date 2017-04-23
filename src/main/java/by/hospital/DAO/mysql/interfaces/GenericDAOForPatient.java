package by.hospital.DAO.mysql.interfaces;

import by.hospital.DAO.GenericDAO;
import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface GenericDAOForPatient extends GenericDAO<Patient, Integer> {

    List<Patient> FindLastName(String lastName) throws PersistentException;
}
