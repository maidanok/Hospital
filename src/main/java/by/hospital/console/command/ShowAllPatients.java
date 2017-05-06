package by.hospital.console.command;

import by.hospital.domain.Patient;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PatientService;
import by.hospital.service.impl.PatientServiceImpl;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllPatients extends AbstractCommandFactory {
    private PatientService patientService = new PatientServiceImpl(mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(), Patient.class));
    public ShowAllPatients() throws PersistentException {
        super("Показать список всех пациентов");
    }

    @Override
    public void runCommand() {
        System.out.println(patientService.getALLPatients());
    }
}
