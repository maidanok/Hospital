package by.hospital.service.api;

import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface StaffService {

    Staff findByLogPass(String log, String pass);

    Staff changePassword(int id, String pass) throws PersistentException;

    Staff createNewStaff(String fersN, String lastN, String middleN, Date birth, String sex, String addr, String passp, String post, String login, String passw);

    List<Staff> getAllStaff() throws PersistentException;

    Staff returnStaffFull (int id) throws PersistentException;

    Staff returnStaffShort(int id) throws PersistentException;

    boolean deleteStaff(int staffId);
}
