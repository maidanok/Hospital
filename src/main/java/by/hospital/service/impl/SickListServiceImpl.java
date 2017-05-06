package by.hospital.service.impl;

import by.hospital.DAO.GenericDAO;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SickListService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class SickListServiceImpl implements SickListService {
    private GenericDAO<SurveyHistory,Integer> surveyHistoryDao;
    private GenericDAO<SickList,Integer> sickListDao;

    public SickListServiceImpl(GenericDAO<SickList,Integer> sickListDao,GenericDAO<SurveyHistory,Integer> surveyHistoryDao) throws PersistentException {
        this.sickListDao=sickListDao;
        this.surveyHistoryDao=surveyHistoryDao;
    }

    @Override
    public List<SickList> findAllActive() {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(" WHERE date_out is null;");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SickList> findByPatient(int patientID) {
        List<SickList> result = null;
        try {
            result = sickListDao.FindByCondition(" WHERE person.person_id = " + patientID + ";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn) {
        List<SickList> result = null;
        try {
            if (patientFirstName == null) {
                result = sickListDao.FindByCondition(" WHERE date_in = " + dateIn + ";");
            } else {
                if (dateIn == null) {
                    result = sickListDao.FindByCondition("WHERE first_name like '" + patientFirstName + "';");
                }else {
                    if (patientFirstName==null && dateIn==null){
                        result=findAllActive();
                    }else {
                        result = sickListDao.FindByCondition("WHERE first_name like '" + patientFirstName +"' AND date_in = " + dateIn + ";");
                    }
                }
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SickList createNewSickIst(int patientID, Date dateIn, String roon, String symt, int diagnId) throws PersistentException {
        SickList sickList = new SickList();
        sickList.getPatient().setPrimaryKey(patientID);
        sickList.setDateIN(dateIn);
        sickList.setRoom(roon);
        sickList.setSymptoms(symt);
        sickList.getFinalDiagnose().setPrimaryKey(diagnId);
        return sickListDao.persist(sickList);
    }

    @Override
    public boolean deleteSickList(int sickListID) throws PersistentException {

        List<SurveyHistory> list = surveyHistoryDao.FindByCondition("WHERE sick_list.sick_list_id = " + sickListID + ";");
        if (list.isEmpty()){
            sickListDao.delete(sickListDao.getByPrimaryKey(sickListID));
            return true;
        } else
        return false;
    }

    @Override
    public List<SickList> findByDiagnoseID(int id) {
        List<SickList> list=new ArrayList<>();
        try {
            list= sickListDao.FindByCondition(" WHERE final_diagnose_id = "+id+";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return list;
    }
}
