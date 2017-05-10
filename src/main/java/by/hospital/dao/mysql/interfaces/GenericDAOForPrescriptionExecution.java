package by.hospital.dao.mysql.interfaces;

import by.hospital.dao.GenericDAO;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Pasha on 17.04.2017.
 */
public interface GenericDAOForPrescriptionExecution extends GenericDAO<PrescriptionExecution,Integer> {

    List<PrescriptionExecution> getAllFromPrescription(int prescriptionPK) throws PersistentException;
}
