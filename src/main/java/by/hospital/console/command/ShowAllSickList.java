package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.api.SickListService;
import by.hospital.service.impl.SickListServiceImpl;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllSickList extends AbstractCommandFactory {
    SickListService sickListService = new SickListServiceImpl();
    public ShowAllSickList() throws PersistentException {
        super("Показать всех больных на стационаре");
    }

    @Override
    public void runComand() {
        System.out.println(sickListService.findAllActive());
    }
}
