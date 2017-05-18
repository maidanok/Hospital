package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.*;
import by.hospital.domain.*;
import by.hospital.domain.enumeration.Post;
import by.hospital.domain.enumeration.PrescriptionType;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PrescriptionService;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.hospital.domain.enumeration.PrescriptionType.DISCHARGE;

/**
 * Created by Admin on 23.04.2017.
 */
public class PrescriptionServiceImpl implements PrescriptionService {
    private Logger logger = Logger.getLogger(PatientServiceImpl.class);
    private GenericDAO<Prescription, Integer> prescriptionDao;
    private GenericDAO<PrescriptionExecution, Integer> prescriptionExecutionDao;
    private GenericDAO<SickList,Integer> sickListDao;

    public PrescriptionServiceImpl(GenericDAO<Prescription, Integer> prescriptionDao, GenericDAO<PrescriptionExecution, Integer> prescriptionExecutionDao, GenericDAO<SickList,Integer> sickListDao) throws PersistentException {
        this.prescriptionDao = prescriptionDao;
        this.prescriptionExecutionDao = prescriptionExecutionDao;
        this.sickListDao=sickListDao;
    }

    @Override
    public List<Prescription> getAllNotDone() {
        List<Prescription> result = new ArrayList<>();
        try {
            result = prescriptionDao.FindByCondition(new QuantityMoreCompleted());
        } catch (PersistentException e) {
            logger.error("getAllNotDone()"+e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public List<Prescription> findBySickList(SickList sickList) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SickListID(sickList.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error("findBySickList()"+e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<Prescription> findBySurveyHistory(SurveyHistory surveyHistory) {
        List<Prescription> list = new ArrayList<>();
        try {
            list = prescriptionDao.FindByCondition(new SurveyHistoryID(surveyHistory.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error("findBySurveyHistory()"+e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public List<Prescription> findByPatientFirstName(Patient patient) {
        List<Prescription> list = new ArrayList<>();
        try {
            if (patient.getFirstName()!=null) {
                list = prescriptionDao.FindByCondition(new PersonFirstName(patient.getFirstName()));
            }else {
                getAllNotDone();
            }
        } catch (PersistentException e) {
            logger.error(""+e.getLocalizedMessage());
        }
        return list;
    }

    @Override
    public Prescription createNewPrescription(Prescription prescription) {
        try {
            prescription = prescriptionDao.persist(prescription);
        } catch (PersistentException e) {
            logger.error("createNewPrescription()"+e.getLocalizedMessage());
        }
        return prescription;
    }

    @Override
    public Prescription getPrescription(Prescription prescription) {
        Prescription prescr = new Prescription();
        try {
            prescr = prescriptionDao.getByPrimaryKey(prescription.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error("getPrescription()"+e.getLocalizedMessage());
        }
        return prescr;
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
            logger.error("deletePrescription()"+e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public boolean executePrescription(Prescription prescription, Staff staff) {
        Prescription prescr;
        try {
            prescr=prescriptionDao.getByPrimaryKey(prescription.getPrimaryKey());
            if (prescr.getQuantity()==prescr.getCompleted()){
                return false;
            }
        if (prescr.getPrescriptionType().equals(DISCHARGE)) {
            prescr.getSurveyHistory().getSickList().setDateOUT(Date.valueOf(LocalDate.now()));
            Diagnose diagnose = prescr.getSurveyHistory().getDiagnose();
            prescr.getSurveyHistory().getSickList().setFinalDiagnose(diagnose);
            sickListDao.update(prescr.getSurveyHistory().getSickList());
            return true;
        }

        if (staff.getPost().equals(Post.NURSE)&&prescription.getPrescriptionType().equals(PrescriptionType.SURGERY)){
            return false;
        }
        PrescriptionExecution prescriptionExecution = new PrescriptionExecution();
        prescriptionExecution.setPrescription(prescr);
        prescriptionExecution.getStaff().setPrimaryKey(staff.getPrimaryKey());
        prescriptionExecution.setPrescriptionExecutionDate(Date.valueOf(LocalDate.now()));
        prescriptionExecutionDao.persist(prescriptionExecution);
            return true;
        } catch (PersistentException e) {
            logger.error("executePrescription()"+e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public Prescription savePrescription(Prescription prescription) {
        Prescription prescr=new Prescription();
        if (prescription.getQuantity()>=prescription.getCompleted()) {
            if (prescription.getPrimaryKey() != 0) {
                try {
                    prescriptionDao.update(prescription);
                    prescr = prescriptionDao.getByPrimaryKey(prescription.getPrimaryKey());
                } catch (PersistentException e) {
                    logger.error("savePrescription()" + e.getLocalizedMessage());
                }
            } else {
                prescr = createNewPrescription(prescription);
            }
        }
        return prescr;
    }

    public List <PrescriptionExecution> getPrescriptionExecutionByPrescription(Prescription prescription){
        List<PrescriptionExecution> list = new ArrayList<>();
        try {
            list=prescriptionExecutionDao.FindByCondition(new PrescriptionID(prescription.getPrimaryKey()));
        } catch (PersistentException e) {
            logger.error("Error getPrescriptionExecutionByPrescription "+e.getLocalizedMessage());
        }
        return list;
    }

}
