package by.hospital.console;

import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import by.hospital.service.impl.PatientServiceImpl;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllPatients extends AbstractCommandFactory {
    PatientService patientService = new PatientServiceImpl();
    public ShowAllPatients() throws PersistentException {
        super("Показать список всех пациентов");
    }

    @Override
    void runComand() {
        System.out.println(patientService.getALLPatienst());
    }
}
