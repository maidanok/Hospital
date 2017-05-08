package by.hospital.service.api;

import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface PrescriptionService {

    List<Prescription> getAllNotDone();

    List<Prescription> findBySickList(SickList sickList);

    List<Prescription> findBySurveyHistory(SurveyHistory surveyHistory);

    Prescription createNewPrescription(String type, int SurvID, String description, int quantity);

    boolean deletePrescription(Prescription prescription);

    boolean executePrescription(Prescription prescription, Staff staff) throws PersistentException;


}
