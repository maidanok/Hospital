package by.hospital.service;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.mysql.MySqlDaoFactory;
import by.hospital.dao.mysql.MySqlStaffDao;
import by.hospital.domain.*;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.*;
import by.hospital.service.impl.*;
import org.apache.log4j.Logger;

/**
 * Created by Admin on 06.05.2017.
 */
public class ServiceInitializer {
    static Logger logger = Logger.getLogger(ServiceInitializer.class);
    public static void init() {
        ServiceLocator locator = new ServiceLocator();
        MySqlDaoFactory mySqlDaoFactory = MySqlDaoFactory.getInstance();

        try {
            GenericDAO<Patient, Integer> patientDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Patient.class);
            MySqlStaffDao staffDao = (MySqlStaffDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Staff.class);
            GenericDAO<Diagnose, Integer> diagnoseDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Diagnose.class);
            GenericDAO<SickList, Integer> sickListDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), SickList.class);
            GenericDAO<SurveyHistory, Integer> surveyHistoryDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), SurveyHistory.class);
            GenericDAO<Prescription, Integer> prescriptionDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Prescription.class);
            GenericDAO<PrescriptionExecution, Integer> prescriptionExecutionDao = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), PrescriptionExecution.class);

            DiagnoseService diagnoseService = new DiagnoseServiceImpl(diagnoseDao,surveyHistoryDao,sickListDao);
            PatientService patientService = new PatientServiceImpl(patientDao,sickListDao);
            PrescriptionService prescriptionService = new PrescriptionServiceImpl(prescriptionDao,prescriptionExecutionDao,sickListDao);
            SickListService sickListService = new SickListServiceImpl(sickListDao,surveyHistoryDao);
            StaffService staffService = new StaffServiceImpl(staffDao,prescriptionExecutionDao,surveyHistoryDao);
            SurveyHistoryService surveyHistoryService= new SurveyHistoryServiceImpl(surveyHistoryDao,prescriptionDao,sickListDao);

            locator.registerService(DiagnoseService.class,diagnoseService);
            locator.registerService(PatientService.class,patientService);
            locator.registerService(PrescriptionService.class,prescriptionService);
            locator.registerService(SickListService.class,sickListService);
            locator.registerService(StaffService.class,staffService);
            locator.registerService(SurveyHistoryService.class,surveyHistoryService);
        } catch (PersistentException e) {
            logger.error("ServiceInitializer error init "+e.getLocalizedMessage());
        }
        ServiceLocator.setLocator(locator);
    }
}
