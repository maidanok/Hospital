package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FirstNameLike;
import by.hospital.dao.conditions.PersonPersonID;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.domain.comparator.SortPersonByFirstName;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class PatientServiceImpl implements PatientService {
    private Logger logger = Logger.getLogger(PatientServiceImpl.class);

    private GenericDAO<Patient, Integer> patientDao;
    private GenericDAO<SickList, Integer> sickListDao;

    public PatientServiceImpl(GenericDAO<Patient, Integer> patientDao, GenericDAO<SickList, Integer> sickListDao) throws PersistentException {
        this.patientDao = patientDao;
        this.sickListDao = sickListDao;
    }

    @Override
    public Patient createNewPatient(Patient patient) {
        if (patient.getBirthday().after(new Date())) {
            return null;
        }
        try {
            return patientDao.persist(patient);
        } catch (PersistentException e) {
            logger.error("createNewPatient()" + e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Patient getPatient(Patient patient) {
        try {
            return patientDao.getByPrimaryKey(patient.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error("getPatient()" + e.getLocalizedMessage());
        }
        return patient;
    }


    @Override
    public List<Patient> getALLPatients() {
        List<Patient> list = new ArrayList<>();
        try {
            list = patientDao.getAll();
            list.sort(new SortPersonByFirstName());
        } catch (PersistentException e) {
            logger.error("getALLPatients()" + e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<Patient> findLastName(Patient patient) {
        try {
            if (patient.getLastName() != "") {
                return patientDao.FindByCondition(new FirstNameLike(patient.getLastName()));
            } else {
                return patientDao.getAll();
            }
        } catch (PersistentException e) {
            logger.error("findLastName()" + e.getLocalizedMessage());
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
            logger.error("deletePatient()" + e.getLocalizedMessage());
        }
        return false;
    }

    public void savePatient(Patient patient) {
        if (patient.getPrimaryKey() != 0 && patient.getBirthday().before(new Date())) {
            try {
                patientDao.update(patient);
            } catch (PersistentException e) {
                logger.error("savePatient()" + e.getLocalizedMessage());
            }
        }
        if (patient.getPrimaryKey() == 0) {
            createNewPatient(patient);
        }
    }
}
