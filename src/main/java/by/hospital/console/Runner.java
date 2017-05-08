package by.hospital.console;

import by.hospital.console.command.*;
import by.hospital.exception.PersistentException;
import by.hospital.service.ServiceInitializer;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Admin on 22.04.2017.
 */
public class Runner {
    private int i=0;
    AbstractCommandFactory[] commandFactory = {
            new NewPatientCommand(),
            new FindPatientByLastName(),
            new ShowAllPatients(),
            new ShowAllSickList(),
            new NewSickList(),
            new NewSurveyHistory(),
            new NewPrescription(),
            new ExecutePrescription(),
            new FindLogPass(),
            new MenuCikle()
    };
    private Scanner scanner = new Scanner(System.in);


    public Runner() throws PersistentException, SQLException {
    }
    private class MenuCikle extends AbstractCommandFactory {
        public MenuCikle() {
            super("Выход");
        }

        @Override
        public void runCommand() throws PersistentException {
            i=commandFactory.length;
        }
    }

    private void menu() throws PersistentException {
        int i = 1;
        for (AbstractCommandFactory factory : commandFactory) {
            System.out.printf("%2d. %s\n", i++, factory.getMenuItem());
        }
        System.out.print("Ваш выбор: ");
        commandFactory[Integer.parseInt(scanner.nextLine()) - 1].runCommand(); // bad example
    }

    public static void main(String[] args) throws PersistentException, SQLException {
        ServiceInitializer.init();
        Runner runner = new Runner();
        while (runner.i!=runner.commandFactory.length){
            runner.menu();
        }
    }
}
