package by.hospital.console;

import by.hospital.exception.PersistentException;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Admin on 22.04.2017.
 */
public class Runner {
    AbstractCommandFactory[] commandFactory = {
            new NewPatientCommand(),
            new ShowAllPatients(),
            new ShowAllSickList()
    };
    private Scanner scanner = new Scanner(System.in);


    public Runner() throws PersistentException, SQLException {
    }


    private void menu() {
        int i = 1;
        for (AbstractCommandFactory factory : commandFactory) {
            System.out.printf("%2d. %s\n", i++, factory.getMenuItem());
        }
        System.out.print("Ваш выбор: ");
        commandFactory[Integer.parseInt(scanner.nextLine()) - 1].runComand(); // bad example
    }

    public static void main(String[] args) throws PersistentException, SQLException {
        Runner runner = new Runner();
        runner.menu();
    }
}
