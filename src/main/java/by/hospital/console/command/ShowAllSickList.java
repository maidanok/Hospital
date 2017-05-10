package by.hospital.console.command;

import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.SickListService;

/**
 * Created by Admin on 22.04.2017.
 */
public class ShowAllSickList extends AbstractCommandFactory {

    public ShowAllSickList() throws PersistentException {
        super("Показать всех больных на стационаре");
    }

    @Override
    public void runCommand() {
        System.out.println(ServiceLocator.getService(SickListService.class).findAllActive());
    }
}
