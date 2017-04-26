package by.hospital.service.api;

import by.hospital.domain.Prescription;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface PrescriptionService {

    List<Prescription> getAllNotDone() throws PersistentException;

    List<Prescription> findBySickList(int id);

    List<Prescription> findBySurveyHistory(int id);

    Prescription createNewPrescription(String type, int SurvID, String description, int quantity);

    boolean deletePrescription(int id);

    boolean executePrescription(int id, int staffID) throws PersistentException;


}
