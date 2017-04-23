package by.hospital.service.api;

import by.hospital.domain.Prescription;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface PrescriptionServise {

    List<Prescription> getAllNotDone() throws PersistentException;

    List<Prescription> findBySickList(int id);

    List<Prescription> findBySurveyHistory(int id);

    Prescription createNewPrescription(String type, int SurvID, String description, int quantity);

    boolean executePrescription(int prescriptionID, int staffID);

    boolean deletePrescription(int id);

}
