package by.hospital.service.api;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface PatientService {

    Patient createNewPatient(Patient patient);

    Patient returnPatientFull(Patient patient);

    Patient returnPatientShort(Patient patient);

    List <Patient> getALLPatients();

    List <Patient> FindLastName(Patient patient);

    boolean deletePatient (Patient patient) throws PersistentException;

    void savePatient(Patient patient);
}
