package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PatientService;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllPatients extends AbstractCommandFactory {

    public ShowAllPatients() throws PersistentException {
        super("Показать список всех пациентов");
    }

    @Override
    public void runCommand() {
        System.out.println(ServiceLocator.getService(PatientService.class).getALLPatients());
    }
}
