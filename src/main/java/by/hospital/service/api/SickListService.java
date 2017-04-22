package by.hospital.service.api;

import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface SickListService {

    List<SickList> findAllActive();

    List<SickList> findByPatient(int patientID);

    List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn);

    SickList createNewSickIst(int patientID, Date dateIn, String roon, String symt, int diagnId) throws PersistentException;
}
