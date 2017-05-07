package by.hospital.console.command;
import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PatientService;

import java.sql.Date;
import java.sql.SQLException;

import static java.lang.System.out;

/**
 * Created by Admin on 22.04.2017.
 */
public class NewPatientCommand extends AbstractCommandFactory{

    public NewPatientCommand() throws PersistentException, SQLException {
        super("Добавить нового пациента");
    }

    @Override
    public void runCommand() {
        out.println("Ведите данные о пациенте");
        out.println("Фамилия");
        String ln = scanner.nextLine();
        out.println("Имя");
        String fn = scanner.nextLine();
        out.println("Отчество");
        String mn = scanner.nextLine();
        out.println("Дата рождения в формате гггг-мм-дд");
        Date date = Date.valueOf(scanner.nextLine());
        out.println("Пол MALE, FEMALE");
        String sex = scanner.nextLine();
        out.println("Адрес");
        String addr = scanner.nextLine();
        out.println("Номер паспорта");
        String pas = scanner.nextLine();
        ServiceLocator.getService(PatientService.class).createNewPatient(fn,ln,mn,date,sex,addr,pas);
    }
}

