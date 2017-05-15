package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FindStaffID;
import by.hospital.dao.conditions.LoginAndPassword;
import by.hospital.dao.conditions.NotFieldStaff;
import by.hospital.dao.mysql.MySqlStaffDao;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.StaffService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class StaffServiceImpl implements StaffService {
    Logger logger = Logger.getLogger(StaffServiceImpl.class);
    private MySqlStaffDao staffDao;
    private GenericDAO<PrescriptionExecution,Integer> persistentExceptionDao;
    private GenericDAO<SurveyHistory,Integer> surveyHistoryDao;

    public StaffServiceImpl(MySqlStaffDao staffDao,GenericDAO<PrescriptionExecution,Integer> persistentExceptionDao,
                            GenericDAO<SurveyHistory,Integer> surveyHistoryDao) throws PersistentException {
        this.staffDao = staffDao;
        this.persistentExceptionDao=persistentExceptionDao;
        this.surveyHistoryDao=surveyHistoryDao;
    }

    @Override
    public Staff findByLogPass(Staff staff) {
        Staff newstaff;
        List<Staff> list = new ArrayList<>();
        try {
            list = staffDao.FindByCondition(new LoginAndPassword(staff.getLogin(), staff.getPassword()));
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        if (list.isEmpty()) {
            return null;
        }
        newstaff = list.get(0);
        return newstaff;
    }

    @Override
    public Staff changePassword(Staff st) throws PersistentException {
        Staff staff = staffDao.getByPrimaryKey(st.getPrimaryKey());
        staff.setPassword(st.getPassword());
        staffDao.update(staff);
        return staff;
    }

    @Override
    public Staff createNewStaff(Staff staff) {
        Staff newStaff = null;
        try {
            newStaff = staffDao.persist(staff);
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return newStaff;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> result = new ArrayList<>();
        try {
            result = staffDao.getAll();
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public Staff returnStaffFull(Staff staf) {
        Staff staff = null;
        try {
            staff = staffDao.getByPrimaryKey(staf.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return staff;
    }

    @Override
    public Staff returnStaffShort(Staff staf) {
        Staff staff = null;
        try {
            staff = staffDao.getByPrimaryKey(staf.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        staff.setPassword(null);
        staff.setLogin(null);
        staff.setPassportNumber(null);
        staff.setAddress(null);
        staff.setBirthday(null);
        return staff;
    }

    @Override
    public boolean deleteStaff(Staff staff){
        if (staff.getPrimaryKey()==0){
            return false;
        }
        try {
            List<PrescriptionExecution> listPE = persistentExceptionDao.FindByCondition(new FindStaffID(staff.getPrimaryKey()));
            List<SurveyHistory> listSH = surveyHistoryDao.FindByCondition(new FindStaffID(staff.getPrimaryKey()));
            if ((listPE.isEmpty()) && (listSH.isEmpty())) {
                staffDao.delete(staff);
                return true;
            }
        }catch (PersistentException e){
            logger.error(e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public void saveStaff(Staff staff) {
        if (staff.getPrimaryKey() != 0) {
            try {
                staffDao.update(staff);
            } catch (PersistentException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        if (staff.getPrimaryKey() == 0) {
            createNewStaff(staff);
        }
    }

    public List<Staff> getAllNotField(){
        List<Staff> list = new ArrayList<>();
        try {
            list=staffDao.FindByCondition(new NotFieldStaff(false));
        } catch (PersistentException e) {
            logger.error(e.getLocalizedMessage());
        }
        return list;
    }
}
