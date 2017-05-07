package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.SurveyHistoryService;

import java.sql.Date;
import java.time.LocalDate;

import static java.lang.System.out;

/**
 * Created by Admin on 23.04.2017.
 */
public class NewSurveyHistory extends AbstractCommandFactory {
    public NewSurveyHistory() {
        super("Добавить новый осмотр к существующему больничному листу");
    }

    @Override
    public void runCommand() throws PersistentException {
        out.println("Здравствуйте доктор введите свой ID");
        int staffID = Integer.valueOf(scanner.nextLine());
        out.println("введите номер больничного листа");
        int sickListId = Integer.valueOf(scanner.nextLine());
        out.println("Какой диагноз вы поставили? Введите его ID");
        int diagnID = Integer.valueOf(scanner.nextLine());
        Date date = Date.valueOf(LocalDate.now());
        out.println("ведите примечания к осмотру. В одну строку");
        String des = scanner.nextLine();
        ServiceLocator.getService(SurveyHistoryService.class).createNewSurveyHistory(sickListId,diagnID,staffID,date,des);
    }
}
