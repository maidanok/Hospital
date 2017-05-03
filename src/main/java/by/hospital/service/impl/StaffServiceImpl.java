package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlStaffDao;
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
    MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
    MySqlStaffDao staffDao = (MySqlStaffDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),Staff.class);

    public StaffServiceImpl() throws PersistentException {
    }

    @Override
    public Staff findByLogPass(String log, String pass) {
        Staff staff;
        List<Staff>list = new ArrayList<>();
        try {
            list=staffDao.FindByCondition(" WHERE login="+log+" AND password="+pass+";");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) {
            return null;
        }
        staff=list.get(0);
        return staff;
    }

    @Override
    public Staff changePassword(int id, String pass) throws PersistentException {
        Staff staff = staffDao.getByPrimaryKey(id);
        staff.setPassword(pass);
        staffDao.update(staff);
        return staff;
    }

    @Override
    public Staff createNewStaff(String firstN, String lastN, String middleN, Date birth, String sex, String addr,
                                String passp, String post, String login, String passw) {
        Staff staff =  new Staff();
        if (findByLogPass(login,passw)==null){
            staff.setFirstName(firstN);
            staff.setLastName(lastN);
            staff.setMiddleName(middleN);
            staff.setBirthday(birth);
            staff.setSex(sex);
            staff.setAddress(addr);
            staff.setPassportNumber(passp);
            staff.setPost(post);
            staff.setLogin(login);
            staff.setPassword(passw);
            try {
                staff=staffDao.persist(staff);
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return staff;
    }

    @Override
    public List<Staff> getAllStaff(){
        List<Staff> result = new ArrayList<>();
        try {
            result=staffDao.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Staff returnStaffFull(int id) throws PersistentException {
        return staffDao.getByPrimaryKey(id);
    }

    @Override
    public Staff returnStaffShort(int id) throws PersistentException {
        Staff staff = new Staff();
        staff = staffDao.getByPrimaryKey(id);
        staff.setPassword(null);
        staff.setLogin(null);
        staff.setPassportNumber(null);
        staff.setAddress(null);
        staff.setBirthday(null);
        return staff;
    }

    @Override
    public boolean deleteStaff(int staffId) throws PersistentException {
        List<PrescriptionExecution> listPE = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),PrescriptionExecution.class).FindByCondition("" +
                " WHERE staff_id = "+staffId+";");
        List <SurveyHistory> listSH = mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),SurveyHistory.class)
                .FindByCondition(" WHERE staff_id = "+staffId+";");
        if ((listPE.isEmpty())&&(listSH.isEmpty())){
            staffDao.delete(staffDao.getByPrimaryKey(staffId));
            return true;
        }
        return false;

    }
}
