package by.hospital.console.command;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import by.hospital.service.impl.PatientServiceImpl;

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
        PatientService patientService = new PatientServiceImpl(mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Patient.class));
        String lastName = scanner.nextLine();
        out.println(patientService.FindLastName(lastName));
    }
}
