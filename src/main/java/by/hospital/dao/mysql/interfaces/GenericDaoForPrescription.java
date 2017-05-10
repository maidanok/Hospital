package by.hospital.dao.mysql.interfaces;

import by.hospital.dao.GenericDAO;
import by.hospital.domain.Prescription;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Pasha on 19.04.2017.
 */
public interface GenericDaoForPrescription extends GenericDAO <Prescription, Integer> {
    List<Prescription> getAllNotDone() throws PersistentException;
}
