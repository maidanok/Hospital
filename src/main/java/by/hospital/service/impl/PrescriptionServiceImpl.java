package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.PrescriptionID;
import by.hospital.dao.conditions.QuantityMoreCompleted;
import by.hospital.dao.conditions.SickListID;
import by.hospital.dao.conditions.SurveyHistoryID;
import by.hospital.domain.*;
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
    private GenericDAO<Prescription,Integer> prescriptionDao;
    private GenericDAO<PrescriptionExecution,Integer> prescriptionExecutionDao;

    public PrescriptionServiceImpl(GenericDAO<Prescription,Integer> prescriptionDao, GenericDAO<PrescriptionExecution,Integer> prescriptionExecutionDao) throws PersistentException {
        this.prescriptionDao=prescriptionDao;
        this.prescriptionExecutionDao=prescriptionExecutionDao;
    }

    @Override
    public List<Prescription> getAllNotDone(){
        List <Prescription> result = new ArrayList<>();
        try {
            result = prescriptionDao.FindByCondition(new QuantityMoreCompleted());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Prescription> findBySickList(SickList sickList) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Prescription> findBySurveyHistory(SurveyHistory surveyHistory) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SurveyHistoryID(surveyHistory.getPrimaryKey()));
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
    public boolean deletePrescription(Prescription prescription) {
        List<PrescriptionExecution> prescriptionExecutions = new ArrayList<>();
        try {
            prescriptionExecutions = prescriptionExecutionDao.FindByCondition(new PrescriptionID(prescription.getPrimaryKey()));
            if (prescriptionExecutions.isEmpty()) {
                prescriptionDao.delete(prescriptionDao.getByPrimaryKey(prescription.getPrimaryKey()));
                return true;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean executePrescription(Prescription prescription, Staff staff) throws PersistentException {
        Prescription prescr = prescriptionDao.getByPrimaryKey(prescription.getPrimaryKey());

        if (prescr.getPrescriptionType().equals(DISCHARGE)){
            prescr.getSurveyHistory().getSickList().setDateOUT(Date.valueOf(LocalDate.now()));
        }
        PrescriptionExecution prescriptionExecution = new PrescriptionExecution();
        prescriptionExecution.setPrescriptionID(prescription.getPrimaryKey());
        prescriptionExecution.getStaff().setPrimaryKey(staff.getPrimaryKey());
        prescriptionExecution.setPrescriptionExecutionDate(Date.valueOf(LocalDate.now()));

        prescriptionExecution = prescriptionExecutionDao.persist(prescriptionExecution);

        return true;

    }


}
