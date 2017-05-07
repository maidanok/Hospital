package by.hospital.service.impl;

import by.hospital.DAO.GenericDAO;
import by.hospital.DAO.conditions.FindStaffID;
import by.hospital.DAO.conditions.LoginAndPassword;
import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.domain.PrescriptionExecution;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.StaffService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public class StaffServiceImpl implements StaffService {
    private GenericDAO<Staff, Integer> staffDao;

    public StaffServiceImpl(GenericDAO<Staff, Integer> staffDao) throws PersistentException {
        this.staffDao = staffDao;
    }

    @Override
    public Staff findByLogPass(Staff staff) {
        Staff newstaff;
        List<Staff> list = new ArrayList<>();
        try {
            list = staffDao.FindByCondition(new LoginAndPassword(staff.getLogin(), staff.getPassword()));
        } catch (PersistentException e) {
            e.printStackTrace();
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
    public Staff createNewStaff(String firstN, String lastN, String middleN, Date birth, String sex, String addr,
                                String passp, String post, String login, String passw) {
        Staff staff = new Staff();
        staff.setLogin(login);
        staff.setPassword(passw);
        if (findByLogPass(staff) == null) {
            staff.setFirstName(firstN);
            staff.setLastName(lastN);
            staff.setMiddleName(middleN);
            staff.setBirthday(birth);
            staff.setSex(sex);
            staff.setAddress(addr);
            staff.setPassportNumber(passp);
            staff.setPost(post);
            try {
                staff = staffDao.persist(staff);
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return staff;
    }

    @Override
    public List<Staff> getAllStaff() {
        List<Staff> result = new ArrayList<>();
        try {
            result = staffDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Staff returnStaffFull(Staff staff) throws PersistentException {
        return staffDao.getByPrimaryKey(staff.getPrimaryKey());
    }

    @Override
    public Staff returnStaffShort(Staff staf) throws PersistentException {
        Staff staff = staffDao.getByPrimaryKey(staf.getPrimaryKey());
        staff.setPassword(null);
        staff.setLogin(null);
        staff.setPassportNumber(null);
        staff.setAddress(null);
        staff.setBirthday(null);
        return staff;
    }

    @Override
    public boolean deleteStaff(Staff staff) throws PersistentException {
        List<PrescriptionExecution> listPE = MySqlDaoFactory.getInstance().getDao(MySqlDaoFactory.getInstance().getContext(), PrescriptionExecution.class)
                .FindByCondition(new FindStaffID(staff.getPrimaryKey()));
        List<SurveyHistory> listSH = MySqlDaoFactory.getInstance().getDao(MySqlDaoFactory.getInstance().getContext(), SurveyHistory.class)
                .FindByCondition(new FindStaffID(staff.getPrimaryKey()));
        if ((listPE.isEmpty()) && (listSH.isEmpty())) {
            staffDao.delete(staffDao.getByPrimaryKey(staff.getPrimaryKey()));
            return true;
        }
        return false;
    }
}
