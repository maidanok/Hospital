package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlPatientDao;
import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import by.hospital.service.api.SickListService;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class PatientServiceImpl implements PatientService {
    MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
    MySqlPatientDao patientDao = (MySqlPatientDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),Patient.class);

    public PatientServiceImpl() throws PersistentException {
    }

    @Override
    public Patient createNewPatient(String fersN, String lastN, String middleN, Date birth, String sex, String addr, String passp) {
        Patient patient = new Patient();
        patient.setFirstName(fersN);
        patient.setLastName(lastN);
        patient.setMiddleName(middleN);
        patient.setBirthday(birth);
        patient.setSex(sex);
        patient.setAddress(addr);
        patient.setPassportNumber(passp);
        try {
            return patientDao.persist(patient);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient returnPatientFull(int id) {
        try {
            return patientDao.getByPrimaryKey(id);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient returnPatientShort(int id) {
        Patient patien=null;
        try {
            Patient patient = patientDao.getByPrimaryKey(id);
            patient.setPassportNumber(null);
            patient.setAddress(null);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return patien;
    }

    @Override
    public List<Patient> getALLPatienst() {
        try {
            return patientDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> FindLastName(String lastName) {
        try {
            return patientDao.FindLastName(lastName);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletePatient(int patientID) throws PersistentException {
        SickListService sickListService = new SickListServiceImpl();
        if (sickListService.findByPatient(patientID).size()==0){
            try {
                patientDao.delete(patientDao.getByPrimaryKey(patientID));
                return true;
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
