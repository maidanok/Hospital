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

    SickList getSickList(SickList sickList);

    List<SickList> findByPatient(Patient patient);

    List<SickList> findByPatientAndDAte(String patientFirstName, Date dateIn);

    SickList createNewSickIst(SickList sickList);

    boolean deleteSickList(SickList sickList);

    List<SickList> findByDiagnoseID(Diagnose diagnose);

    void saveSickList(SickList sickList);
}
