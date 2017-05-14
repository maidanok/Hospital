package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FirstNameLike;
import by.hospital.dao.conditions.PersonPersonID;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class PatientServiceImpl implements PatientService {
    Logger logger = Logger.getLogger(PatientServiceImpl.class);

    private GenericDAO<Patient, Integer> patientDao;
    private GenericDAO<SickList, Integer> sickListDao;

    public PatientServiceImpl(GenericDAO<Patient, Integer> patientDao, GenericDAO<SickList, Integer> sickListDao) throws PersistentException {
        this.patientDao = patientDao;
        this.sickListDao = sickListDao;
    }

    @Override
    public Patient createNewPatient(Patient patient) {
        try {
            return patientDao.persist(patient);
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Patient returnPatientFull(Patient patient) {
        try {
            return patientDao.getByPrimaryKey(patient.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return patient;
    }

    @Override
    public Patient returnPatientShort(Patient pat) {
        Patient patien = null;
        try {
            Patient patient = patientDao.getByPrimaryKey(pat.getPrimaryKey());
            patient.setPassportNumber(null);
            patient.setAddress(null);
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return patien;
    }

    @Override
    public List<Patient> getALLPatients() {
        try {
            return patientDao.getAll();
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public List<Patient> FindLastName(Patient patient) {
        try {
            if (patient.getLastName() != null) {
                return patientDao.FindByCondition(new FirstNameLike(patient.getLastName()));
            } else {
                return patientDao.getAll();
            }
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return new ArrayList<Patient>();
    }

    @Override
    public boolean deletePatient(Patient patient) {
        if (patient.getPrimaryKey() == 0) {
            return false;
        }
        try {
            if (sickListDao.FindByCondition(new PersonPersonID(patient.getPrimaryKey())).size() == 0) {
                patientDao.delete(patientDao.getByPrimaryKey(patient.getPrimaryKey()));
                return true;
            }
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return false;
    }

    public void savePatient(Patient patient) {
        if (patient.getPrimaryKey() != 0) {
            try {
                patientDao.update(patient);
            } catch (PersistentException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        if (patient.getPrimaryKey() == 0) {
            createNewPatient(patient);
        }
    }
}
