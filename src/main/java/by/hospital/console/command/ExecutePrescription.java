package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.impl.PrescriptionServiceImpl;

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
        PrescriptionService prescriptionService = new PrescriptionServiceImpl();
        out.println(prescriptionService.executePrescription(id,staffID)? "успешно":"неудача");
    }
}
