package by.hospital.service.impl;

import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.conditions.FirstNameLike;
import by.hospital.DAO.conditions.PersonPersonID;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class PatientServiceImpl implements PatientService {

    private GenericDAO<Patient,Integer> patientDao;
    private GenericDAO<SickList,Integer> sickListDao;

    public PatientServiceImpl(GenericDAO<Patient,Integer> patientDao,GenericDAO<SickList,Integer> sickListDao) throws PersistentException {
        this.patientDao=patientDao;
        this.sickListDao=sickListDao;
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
    public Patient returnPatientFull(Patient patient) {
        try {
            return patientDao.getByPrimaryKey(patient.getPrimaryKey());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient returnPatientShort(Patient pat) {
        Patient patien=null;
        try {
            Patient patient = patientDao.getByPrimaryKey(pat.getPrimaryKey());
            patient.setPassportNumber(null);
            patient.setAddress(null);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return patien;
    }

    @Override
    public List<Patient> getALLPatients() {
        try {
            return patientDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> FindLastName(Patient patient) {
        try {
            return patientDao.FindByCondition(new FirstNameLike(patient.getLastName()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deletePatient(Patient patient) throws PersistentException {

        if (sickListDao.FindByCondition(new PersonPersonID(patient.getPrimaryKey())).size()==0){
            try {
                patientDao.delete(patientDao.getByPrimaryKey(patient.getPrimaryKey()));
                return true;
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
