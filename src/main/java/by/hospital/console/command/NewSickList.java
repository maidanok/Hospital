package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.SickListService;

import java.sql.Date;
import java.time.LocalDate;

import static java.lang.System.out;

/**
 * Created by Admin on 23.04.2017.
 */
public class NewSickList extends AbstractCommandFactory {

    public NewSickList() throws PersistentException {
        super("Добавить новый больничный лист");
    }

    @Override
    public void runCommand() throws PersistentException {
        out.println("Введите id пациента");
        int id = Integer.valueOf(scanner.nextLine());
        Date date = Date.valueOf(LocalDate.now());
        out.println("Введите номер палаты");
        String room = scanner.nextLine();
        out.println("На что жалуется пациент? (в одну строку)");
        String sympt = scanner.nextLine();
        System.out.println("Врач установит диагноз после осмотра");
      //  ServiceLocator.getService(SickListService.class).createNewSickIst(id,date,room,sympt,1);
    }
}
