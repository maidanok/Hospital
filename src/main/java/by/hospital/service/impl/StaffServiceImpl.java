package by.hospital.service.impl;

import by.hospital.dao.GenericDAO;
import by.hospital.dao.conditions.FindStaffID;
import by.hospital.dao.conditions.LoginAndPassword;
import by.hospital.dao.conditions.NotFieldStaff;
import by.hospital.dao.mysql.MySqlStaffDao;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.comparator.SortPersonByFirstName;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.StaffService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class StaffServiceImpl implements StaffService {
    private Logger logger = Logger.getLogger(StaffServiceImpl.class);
    private MySqlStaffDao staffDao;
    private GenericDAO<PrescriptionExecution, Integer> persistentExceptionDao;
    private GenericDAO<SurveyHistory, Integer> surveyHistoryDao;

    public StaffServiceImpl(MySqlStaffDao staffDao, GenericDAO<PrescriptionExecution, Integer> persistentExceptionDao,
                            GenericDAO<SurveyHistory, Integer> surveyHistoryDao) throws PersistentException {
        this.staffDao = staffDao;
        this.persistentExceptionDao = persistentExceptionDao;
        this.surveyHistoryDao = surveyHistoryDao;
    }

    @Override
    public Staff findByLogPass(Staff staff) {
        Staff newstaff = new Staff();
        List<Staff> list = new ArrayList<>();
        try {
            list = staffDao.FindByCondition(new LoginAndPassword(staff.getLogin(), staff.getPassword()));
        } catch (PersistentException e) {
            logger.error("findByLogPass()" + e.getLocalizedMessage());
        }
        if (list.isEmpty()) {
            logger.info("findByLogPass() result not found");
            return newstaff;
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
        Staff newStaff = new Staff();
        if (staff.getBirthday().before(new Date())) {
            try {
                newStaff = staffDao.persist(staff);
            } catch (PersistentException e) {
                logger.error("createNewStaff()" + e.getLocalizedMessage());
            }
        }
        return newStaff;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> result = new ArrayList<>();
        try {
            result = staffDao.getAll();
            result.sort(new SortPersonByFirstName());
        } catch (PersistentException e) {
            logger.error("getAllStaff()" + e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public Staff getStaff(Staff staf) {
        Staff staff = null;
        try {
            staff = staffDao.getByPrimaryKey(staf.getPrimaryKey());
        } catch (PersistentException e) {
            logger.error("getStaff()" + e.getLocalizedMessage());
        }
        return staff;
    }

    @Override
    public boolean deleteStaff(Staff staff) {
        if (staff.getPrimaryKey() == 0) {
            return false;
        }
        try {
            List<PrescriptionExecution> listPE = persistentExceptionDao.FindByCondition(new FindStaffID(staff.getPrimaryKey()));
            List<SurveyHistory> listSH = surveyHistoryDao.FindByCondition(new FindStaffID(staff.getPrimaryKey()));
            if ((listPE.isEmpty()) && (listSH.isEmpty())) {
                staffDao.delete(staff);
                return true;
            }
        } catch (PersistentException e) {
            logger.error("deleteStaff()" + e.getLocalizedMessage());
        }
        return false;
    }

    @Override
    public void saveStaff(Staff staff) {
        if (staff.getPrimaryKey() != 0 && staff.getBirthday().before(new Date())) {
            try {
                staffDao.update(staff);
            } catch (PersistentException e) {
                logger.error("saveStaff()" + e.getLocalizedMessage());
            }
        }
        if (staff.getPrimaryKey() == 0) {
            createNewStaff(staff);
        }
    }

    public List<Staff> getAllNotField() {
        List<Staff> list = new ArrayList<>();
        try {
            list = staffDao.FindByCondition(new NotFieldStaff(false));
            list.sort(new SortPersonByFirstName());
        } catch (PersistentException e) {
            logger.error("getAllNotField()" + e.getLocalizedMessage());
        }
        return list;
    }
}
