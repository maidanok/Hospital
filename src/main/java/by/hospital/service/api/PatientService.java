package by.hospital.service.api;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface PatientService {

    Patient createNewPatient(String fersN, String lastN, String middleN, Date birth, String sex, String addr, String passp);

    Patient returnPatientFull(int id);

    Patient returnPatientShort(int id);

    List <Patient> getALLPatienst();

    List <Patient> FindLastName(String lastName);

    boolean deletePatient (int patientID) throws PersistentException;
}
