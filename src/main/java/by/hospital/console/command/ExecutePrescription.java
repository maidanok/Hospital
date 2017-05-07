package by.hospital.console.command;

import by.hospital.domain.Prescription;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;

import static java.lang.System.out;

/**
 * Created by Admin on 25.04.2017.
 */
public class ExecutePrescription extends AbstractCommandFactory {
    public ExecutePrescription() {
        super("Выполнить назначение");
    }


    @Override
    public void runCommand() throws PersistentException {
        out.println("Введите id назначения");
        int id = Integer.valueOf(scanner.nextLine());
        out.println("введите id врача");
        int staffID =Integer.valueOf(scanner.nextLine());
        Prescription prescription = new Prescription();
        prescription.setPrimaryKey(id);
        Staff staff = new Staff();
        staff.setPrimaryKey(staffID);
        out.println(ServiceLocator.getService(PrescriptionService.class).executePrescription(prescription,staff) ? "успешно":"неудача");
    }
}
