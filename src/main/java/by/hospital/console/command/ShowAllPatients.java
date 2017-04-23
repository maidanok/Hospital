package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import by.hospital.service.impl.PatientServiceImpl;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllPatients extends AbstractCommandFactory {
    private PatientService patientService = new PatientServiceImpl();
    public ShowAllPatients() throws PersistentException {
        super("Показать список всех пациентов");
    }

    @Override
    public void runComand() {
        System.out.println(patientService.getALLPatienst());
    }
}
