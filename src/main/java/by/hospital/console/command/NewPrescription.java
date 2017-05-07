package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;

import static java.lang.System.out;

/**
 * Created by Admin on 25.04.2017.
 */
public class NewPrescription extends AbstractCommandFactory {
    public NewPrescription() {
        super("Добавить новое назначение");
    }

    @Override
    public void runCommand() throws PersistentException {
        out.println("Введите данные нового назначения");
        out.println("Введите тип назначения PROCEDURE, MEDICATION, SURGERY, DISCHARGE");
        String type = scanner.nextLine();
        out.println("Введите номер осмотра");
        int sureveyID = Integer.valueOf(scanner.nextLine());
        out.println("введите описание");
        String descript = scanner.nextLine();
        out.println("Введите количество");
        int qant = Integer.valueOf(scanner.nextLine());
        ServiceLocator.getService(PrescriptionService.class).createNewPrescription(type,sureveyID,descript,qant);
    }
}
