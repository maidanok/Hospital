package by.hospital.service.api;

import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface StaffService {

    Staff findByLogPass(Staff staff);

    Staff changePassword(Staff staff) throws PersistentException;

    Staff createNewStaff(String fersN, String lastN, String middleN, Date birth, String sex, String addr, String passp, String post, String login, String passw);

    List<Staff> getAllStaff();

    Staff returnStaffFull (Staff staff);

    Staff returnStaffShort(Staff staff);

    boolean deleteStaff(Staff staff) throws PersistentException;
}
