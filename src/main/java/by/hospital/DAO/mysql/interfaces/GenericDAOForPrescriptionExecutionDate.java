package by.hospital.DAO.mysql.interfaces;

import by.hospital.DAO.GenericDAO;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Pasha on 17.04.2017.
 */
public interface GenericDAOForPrescriptionExecutionDate extends GenericDAO<PrescriptionExecution,Integer> {

    List<PrescriptionExecution> getAllFromPrescription(int prescriptionPK) throws PersistentException;
}
