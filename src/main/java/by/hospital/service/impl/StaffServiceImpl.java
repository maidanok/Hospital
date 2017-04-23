package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlStaffDao;
import by.hospital.domain.Staff;
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
    public List<Staff> getAllStaff() throws PersistentException {
        return staffDao.getAll();
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
    public boolean deleteStaff(int staffId) {
        return false;
        //TODO доделать метод delete
    }
}
