package by.hospital.service.api;

import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface SickListService {

    List<SickList> findAllActive();

    List<SickList> findByPatient(Patient patient);

    List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn);

    SickList createNewSickIst(int patientID, Date dateIn, String roon, String symt, int diagnId) throws PersistentException;

    boolean deleteSickList(SickList sickList) throws PersistentException;

    List<SickList> findByDiagnoseID(Diagnose diagnose);
}
