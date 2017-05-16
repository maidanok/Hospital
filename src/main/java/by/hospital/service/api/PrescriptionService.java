package by.hospital.service.api;

import by.hospital.domain.*;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface PrescriptionService {

    List<Prescription> getAllNotDone();

    List<Prescription> findBySickList(SickList sickList);

    List<Prescription> findBySurveyHistory(SurveyHistory surveyHistory);

    List<Prescription> findByPatientFirstName(Patient patient);

    Prescription createNewPrescription(Prescription prescription);

    Prescription getPrescription(Prescription prescription);

    boolean deletePrescription(Prescription prescription);

    boolean executePrescription(Prescription prescription, Staff staff);

    Prescription savePrescription(Prescription prescription);


}
