package by.hospital.console.command;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PatientService;

import static java.lang.System.out;

/**
 * Created by Admin on 26.04.2017.
 */
public class FindPatientByLastName extends AbstractCommandFactory {
    public FindPatientByLastName() {
        super("Найти пациента по фамилии");
    }

    @Override
    public void runCommand() throws PersistentException {
        out.println("Введите фамилию пациента");
        String lastName = scanner.nextLine();
        Patient patient = new Patient();
        patient.setLastName(lastName);
        out.println(ServiceLocator.getService(PatientService.class).FindLastName(patient));
    }
}
