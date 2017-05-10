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

    Staff createNewStaff(Staff staff);

    List<Staff> getAllStaff();

    Staff returnStaffFull (Staff staff);

    Staff returnStaffShort(Staff staff);

    boolean deleteStaff(Staff staff);

    void saveStaff(Staff staff);
}
