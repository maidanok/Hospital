package by.hospital.service.impl;

import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.conditions.FindFinalDiagnoseID;
import by.hospital.DAO.conditions.FindSdiagnoseID;
import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.DiagnoseService;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public class DiagnoseServiceImpl implements DiagnoseService {
   private GenericDAO<Diagnose, Integer> diagnoseDao;
   private GenericDAO<SurveyHistory,Integer> surveyHistoryDao;
   private GenericDAO<SickList,Integer> sickListDao;

    public DiagnoseServiceImpl(GenericDAO<Diagnose, Integer> diagnoseDao,GenericDAO<SurveyHistory,Integer> surveyHistoryDao,GenericDAO<SickList,Integer> sickListDao) throws PersistentException {
        this.diagnoseDao = diagnoseDao;
        this.sickListDao=sickListDao;
        this.surveyHistoryDao=surveyHistoryDao;
    }

    @Override
    public Diagnose createNewDiagnose(String name, String therapy) {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseName(name);
        diagnose.setTherapy(therapy);
        try {
            diagnose = diagnoseDao.persist(diagnose);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return diagnose;
    }

    @Override
    public Diagnose getDiagmose(Diagnose diagnose) throws PersistentException {
        return diagnoseDao.getByPrimaryKey(diagnose.getPrimaryKey());
    }

    @Override
    public List<Diagnose> getAll() throws PersistentException {
        return diagnoseDao.getAll();
    }

    @Override
    public boolean deleteDiagnose(Diagnose diagnose) {
        try {
            if (surveyHistoryDao.FindByCondition(new FindSdiagnoseID(diagnose.getPrimaryKey())).isEmpty()
                    && sickListDao.FindByCondition(new FindFinalDiagnoseID(diagnose.getPrimaryKey())).isEmpty()){
                diagnoseDao.delete(diagnoseDao.getByPrimaryKey(diagnose.getPrimaryKey()));
                return true;
            }
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return false;
    }
}
