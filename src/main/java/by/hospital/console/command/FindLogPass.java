package by.hospital.console.command;

import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.StaffService;

import static java.lang.System.out;

/**
 * Created by Admin on 08.05.2017.
 */
public class FindLogPass extends AbstractCommandFactory {
    public FindLogPass() {
        super("Найти по логину и паролю");
    }

    @Override
    public void runCommand() throws PersistentException {
        out.println("Login");
        String login = scanner.nextLine();
        out.println("Pass");
        String pass = scanner.nextLine();
        Staff staff = new Staff();
        staff.setLogin(login);
        staff.setPassword(pass);
        out.println(ServiceLocator.getService(StaffService.class).findByLogPass(staff));

    }
}
