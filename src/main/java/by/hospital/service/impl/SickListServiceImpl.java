package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlSickListDao;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.SickListService;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class SickListServiceImpl implements SickListService {
    MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
    MySqlSickListDao sickListDao = (MySqlSickListDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), SickList.class);

    public SickListServiceImpl() throws PersistentException {
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
            result = sickListDao.FindByCondition("WHERE person.person_id = " + patientID + ";");
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
                result = sickListDao.FindByCondition("WHERE date_in = " + dateIn + ";");
            } else {
                if (dateIn == null) {
                    result = sickListDao.FindByCondition("first_name like " + patientFirstName + ";");
                }else {
                    if (patientFirstName==null && dateIn==null){
                        result=findAllActive();
                    }else {
                        result = sickListDao.FindByCondition("first_name like " + patientFirstName +" date_in = " + dateIn + ";");
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
}
