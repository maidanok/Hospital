package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlPrescriptionDao;
import by.hospital.DAO.mysql.MySqlPrescriptionExecutionDao;
import by.hospital.domain.Prescription;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PrescriptionService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.hospital.domain.enumeration.PrescriptionType.DISCHARGE;

/**
 * Created by Admin on 23.04.2017.
 */
public class PrescriptionServiceImpl implements PrescriptionService {
    private MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
    private MySqlPrescriptionDao prescriptionDao =
            (MySqlPrescriptionDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Prescription.class);
    private MySqlPrescriptionExecutionDao prescriptionExecutionDao =
            (MySqlPrescriptionExecutionDao) mySqlDaoFactory.getDao
                    (mySqlDaoFactory.getContext(), PrescriptionExecution.class);

    public PrescriptionServiceImpl() throws PersistentException {
    }

    @Override
    public List<Prescription> getAllNotDone(){
        List <Prescription> result = new ArrayList<>();
        try {
            result = prescriptionDao.getAllNotDone();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Prescription> findBySickList(int id) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(" WHERE sick_list.sick_list_id = " + id + ";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Prescription> findBySurveyHistory(int id) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(" WHERE survey_history.survey_history_id = " + id + ";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Prescription createNewPrescription(String type, int SurvID, String description, int quantity) {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionType(type);
        prescription.getSurveyHistory().setPrimaryKey(SurvID);
        prescription.setDescription(description);
        prescription.setQuantity(quantity);
        try {
            prescription = prescriptionDao.persist(prescription);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return prescription;
    }

    @Override
    public boolean deletePrescription(int id) {
        List<PrescriptionExecution> prescriptionExecutions = new ArrayList<>();
        try {
            prescriptionExecutions = prescriptionExecutionDao.getAllFromPrescription(id);
            if (prescriptionExecutions.isEmpty()) {
                prescriptionDao.delete(prescriptionDao.getByPrimaryKey(id));
                return true;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean executePrescription(int id, int staffID) throws PersistentException {
        Prescription prescription = prescriptionDao.getByPrimaryKey(id);

        if (prescription.getPrescriptionType().equals(DISCHARGE)){
            prescription.getSurveyHistory().getSickList().setDateOUT(Date.valueOf(LocalDate.now()));
        }
        PrescriptionExecution prescriptionExecution = new PrescriptionExecution();
        prescriptionExecution.setPrescriptionID(id);
        prescriptionExecution.getStaff().setPrimaryKey(staffID);
        prescriptionExecution.setPrescriptionExecutionDate(Date.valueOf(LocalDate.now()));

        prescriptionExecution = prescriptionExecutionDao.persist(prescriptionExecution);

        return true;

    }


}
