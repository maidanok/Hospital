package by.hospital.service.api;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 22.04.2017.
 */
public interface PatientService {

    Patient createNewPatient(Patient patient);

    Patient getPatient(Patient patient);

    List<Patient> getALLPatients();

    List<Patient> findLastName(Patient patient);

    boolean deletePatient(Patient patient);

    void savePatient(Patient patient);
}
