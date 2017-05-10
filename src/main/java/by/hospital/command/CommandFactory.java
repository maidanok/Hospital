package by.hospital.command;

import by.hospital.command.directories.OpenDirectoriesPage;
import by.hospital.command.login.LogOutCommand;
import by.hospital.command.login.LoginCommand;
import by.hospital.command.patient.EditPatient;
import by.hospital.command.patient.SavePatient;
import by.hospital.command.staff.EditStaff;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Admin on 07.05.2017.
 */
public class CommandFactory {
    private static CommandFactory instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();

    private CommandFactory(){
        commands.put("Login",new LoginCommand());
        commands.put("Logout",new LogOutCommand());
        commands.put("OpenDirectories", new OpenDirectoriesPage());
        commands.put("EditPatient",new EditPatient());
        commands.put("SavePatient",new SavePatient());

        commands.put("EditStaff", new EditStaff());
    }

    public Command getCommand(HttpServletRequest request){
        String action = request.getParameter("COMMAND");
        Command command = commands.get(action);
        if (command == null) {
            System.out.println("console.command==null");
            command = new NoCommand();
        }
        return command;
    }

    public static CommandFactory getInstance(){
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }
}
