package by.hospital.console.command;

import by.hospital.exception.PersistentException;

import java.util.Scanner;

/**
 * Created by Admin on 22.04.2017.
 */
public abstract class AbstractCommandFactory {

    protected Scanner scanner = new Scanner(System.in);

    private String menuItem;

    public AbstractCommandFactory(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public abstract void runCommand() throws PersistentException;
}
